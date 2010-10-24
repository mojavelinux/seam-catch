package org.jboss.seam.exception.control.prototype.handlers;

import javax.inject.Inject;

import org.jboss.seam.exception.control.prototype.ExceptionHandlerActivation;
import org.jboss.seam.exception.control.prototype.ExceptionHandlingStatus;
import org.jboss.seam.exception.control.prototype.SelfActivatingExceptionHandler;
import org.jboss.seam.exception.control.prototype.activators.AnnotationPresentActivation;
import org.jboss.seam.exception.control.prototype.http.HttpErrorResponse;

public class AnnotationHttpErrorHandler implements SelfActivatingExceptionHandler<Throwable>
{
   @Inject
   private HttpErrorResponse httpErrorResponse;
   
   public ExceptionHandlingStatus handle(Throwable t)
   {
      HttpError httpError = t.getClass().getAnnotation(HttpError.class);
      String message = httpError.message();
      if (message.length() == 0)
      {
         message = t.getLocalizedMessage();
      }
      httpErrorResponse.send(httpError.statusCode(), httpError.message());
      return ExceptionHandlingStatus.HANDLED;
   }
   
   public ExceptionHandlerActivation getActivation()
   {
      return new AnnotationPresentActivation(HttpError.class);
   }
}
