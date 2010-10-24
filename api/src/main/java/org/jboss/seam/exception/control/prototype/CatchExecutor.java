package org.jboss.seam.exception.control.prototype;

import java.util.Stack;

import javax.enterprise.context.spi.CreationalContext;
import javax.enterprise.event.Observes;
import javax.enterprise.inject.Any;
import javax.enterprise.inject.spi.BeanManager;
import javax.enterprise.inject.spi.InjectionTarget;

import org.jboss.seam.exception.control.prototype.config.CatchConfig;

public class CatchExecutor
{
   // very rough cut, not pretty in many places
   public void onException(@Observes @Any ExceptionEvent event, CatchConfig config, BeanManager beanManager)
   {
      final Stack<Throwable> exceptionStack = new Stack<Throwable>();
      Throwable exception = event.getException();
      
      do
      {
         exceptionStack.push(exception);
      }
      while ((exception = exception.getCause()) != null);
      
      while (!exceptionStack.isEmpty())
      {
         exception = exceptionStack.pop();
         
         // check activation
         for (ExceptionHandlerChain chain : config.getRegistry().getExceptionHandlerChainsFor(exception))
         {
            boolean skipChain = false;
            for (ExceptionHandlerActivation activation : chain.getActivations())
            {
               CreationalContext<Object> ctx = null;
               if (activation.isContextual())
               {
                  ctx = injectNonContextualInstance(activation, beanManager);
               }
               if (!activation.isActive(exception))
               {
                  skipChain = true;
               }
               if (ctx != null)
               {
                  ctx.release();
               }
               if (skipChain)
               {
                  break;
               }
            }
            if (!skipChain)
            {
               boolean breakChain = false;
               for (ExceptionHandler handler : chain.getExceptionHandlers())
               {
                  if (handler instanceof SelfActivatingExceptionHandler)
                  {
                     ExceptionHandlerActivation a = ((SelfActivatingExceptionHandler) handler).getActivation();
                     if (a.isContextual())
                     {
                        throw new UnsupportedOperationException();
                     }
                     if (!a.isActive(exception))
                     {
                        break;
                     }
                  }
                  CreationalContext<Object> ctx = injectNonContextualInstance(handler, beanManager);
                  ExceptionHandlingStatus status = handler.handle(exception);
                  ctx.release();
                  switch (status)
                  {
                     case POP_STACK:
                        breakChain = true;
                        break;
                     case HANDLED:
                        breakChain = true;
                        exceptionStack.clear();
                        break;
                  }
                  if (breakChain)
                  {
                     break;
                  }
               }
            }
            
         }
      }
   }
   
   // FIXME this is clearly going to be a problem since the handlers and activators are effectively shared
   // by all requests...we need to inject once when setting up...and make it clear that dependent-scope injection
   // is potentially dangerous.
   @SuppressWarnings("unchecked")
   protected CreationalContext<Object> injectNonContextualInstance(Object instance, BeanManager beanManager)
   {
      CreationalContext<Object> ctx = beanManager.createCreationalContext(null);
      InjectionTarget<Object> injectionTarget =
            (InjectionTarget<Object>) beanManager.createInjectionTarget(beanManager.createAnnotatedType(instance.getClass()));
      injectionTarget.inject(instance, ctx);
      return ctx;
   }
}
