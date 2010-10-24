package org.jboss.seam.exception.control.prototype.handlers;

import org.jboss.seam.exception.control.prototype.ExceptionHandler;
import org.jboss.seam.exception.control.prototype.ExceptionHandlingStatus;

public class IllegalStateExceptionHandler implements ExceptionHandler<IllegalStateException>
{
   public ExceptionHandlingStatus handle(IllegalStateException t)
   {
      return ExceptionHandlingStatus.PROCEED;
   }

}
