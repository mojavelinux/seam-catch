package org.jboss.seam.exception.control.prototype.config;

import org.jboss.seam.exception.control.prototype.ExceptionHandler;
import org.jboss.seam.exception.control.prototype.ExceptionHandlerChain;

public class ExceptionConfig<T extends Throwable> extends CatchConfig
{
   protected final Class<T> exceptionType;

   public ExceptionConfig(ExceptionHandlerRegistry registry)
   {
      this(registry, null);
   }
   
   public ExceptionConfig(ExceptionHandlerRegistry registry, Class<T> exceptionType)
   {
      super(registry);
      this.exceptionType = exceptionType;
   }
   
   // using ? super T here causes a warning to appear about casting a vararg array to a generic array
   // however, it does provide us safety against using handlers that aren't parameterized to the exception hierarchy
   public <H extends ExceptionHandler<? super T>> ExceptionHandlerChainConfig<T> handleWith(H... handler)
   {
      ExceptionHandlerChain chain = new ExceptionHandlerChain(handler);
      if (exceptionType != null)
      {
         registry.addChainForExceptionType(exceptionType, chain);
      }
      else
      {
         registry.addGlobalChain(chain);
      }
      
      return new ExceptionHandlerChainConfig<T>(this, chain);
   }
}
