package org.jboss.seam.exception.control.prototype.handlers;

import javax.inject.Inject;

import org.jboss.seam.exception.control.prototype.ExceptionHandler;
import org.jboss.seam.exception.control.prototype.ExceptionHandlingStatus;
import org.jboss.seam.exception.control.prototype.http.HttpErrorResponse;

public class HttpErrorExceptionHandler implements ExceptionHandler<Throwable>
{
   private final int code;
   private final String message;
   
   @Inject
   private HttpErrorResponse httpErrorResponse;
   
   public HttpErrorExceptionHandler(int code)
   {
      this(code, null);
   }
   
   public HttpErrorExceptionHandler(int code, String message)
   {
      this.code = code;
      this.message = message;
   }
   
   public ExceptionHandlingStatus handle(Throwable t)
   {
      httpErrorResponse.send(code, message != null ? message : t.getLocalizedMessage());
      return ExceptionHandlingStatus.HANDLED;
   }
}
