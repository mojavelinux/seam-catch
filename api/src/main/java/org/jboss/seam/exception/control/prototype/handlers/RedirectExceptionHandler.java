package org.jboss.seam.exception.control.prototype.handlers;

import org.jboss.seam.exception.control.prototype.ExceptionHandler;
import org.jboss.seam.exception.control.prototype.ExceptionHandlingStatus;

public class RedirectExceptionHandler implements ExceptionHandler<Throwable>
{
   public ExceptionHandlingStatus handle(Throwable t)
   {
      return ExceptionHandlingStatus.HANDLED;
   }

}
