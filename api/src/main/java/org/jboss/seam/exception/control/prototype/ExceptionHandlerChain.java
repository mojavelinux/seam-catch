package org.jboss.seam.exception.control.prototype;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// TODO needs generic parameters to the effect of <T extends Throwable> and <H extends ExceptionHandler<? super T>>
public class ExceptionHandlerChain
{
   private List<ExceptionHandler<?>> handlers;
   
   private List<ExceptionHandlerActivation> activations = new ArrayList<ExceptionHandlerActivation>();
   
   public ExceptionHandlerChain(ExceptionHandler<?>... handlers)
   {
      this.handlers = new ArrayList<ExceptionHandler<?>>(Arrays.asList(handlers));
   }
   
   public void addAll(ExceptionHandlerActivation... activations)
   {
      this.activations.addAll(Arrays.asList(activations));
   }
   
   public boolean isActive(Throwable t)
   {
      for (ExceptionHandlerActivation a : activations)
      {
         if (!a.isActive(t))
         {
            return false;
         }
      }
      return true;
   }
   
   public List<ExceptionHandler<?>> getExceptionHandlers()
   {
      return handlers;
   }
   
   public List<ExceptionHandlerActivation> getActivations()
   {
      return activations;
   }
}
