package org.jboss.seam.exception.control.prototype.activators;

import org.jboss.seam.exception.control.prototype.ExceptionHandlerActivation;

public class CurrentViewIdActivation implements ExceptionHandlerActivation
{
   private final String mapping;
   
   public CurrentViewIdActivation(String mapping)
   {
      this.mapping = mapping;
   }
   
   public boolean isActive(Throwable t)
   {
      //return mapping.equals(FacesContext.getCurrentInstance().getViewRoot().getViewId());
      return true;
   }

   public boolean isContextual()
   {
      return true;
   }

}
