package org.jboss.seam.exception.control.prototype.handlers;

import org.jboss.seam.exception.control.prototype.ExceptionHandlerActivation;
import org.jboss.seam.exception.control.prototype.ExceptionHandlingStatus;
import org.jboss.seam.exception.control.prototype.SelfActivatingExceptionHandler;
import org.jboss.seam.exception.control.prototype.activators.AnnotationPresentActivation;

public class AnnotationFacesRedirectExceptionHandler implements SelfActivatingExceptionHandler<Throwable>
{
   public ExceptionHandlingStatus handle(Throwable t)
   {
       FacesRedirect facesRedirect = t.getClass().getAnnotation(FacesRedirect.class);
       // do the thing
       return ExceptionHandlingStatus.HANDLED;
   }
   
   public ExceptionHandlerActivation getActivation()
   {
      return new AnnotationPresentActivation(FacesRedirect.class);
   }
}
