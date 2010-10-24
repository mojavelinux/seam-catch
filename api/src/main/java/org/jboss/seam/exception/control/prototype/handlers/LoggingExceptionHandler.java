package org.jboss.seam.exception.control.prototype.handlers;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.inject.Inject;

import org.jboss.seam.exception.control.prototype.ExceptionHandler;
import org.jboss.seam.exception.control.prototype.ExceptionHandlingStatus;

public class LoggingExceptionHandler implements ExceptionHandler<Throwable>
{
   private final Level level;
   private final boolean logException;
   
   @Inject
   private Logger log;
   
   public LoggingExceptionHandler(Level level)
   {
      this(level, true);
   }
   
   public LoggingExceptionHandler(Level level, boolean logException)
   {
      this.level = level;
      this.logException = logException;
   }
   
   public ExceptionHandlingStatus handle(Throwable t)
   {
      if (logException)
      {
         log.log(level, t.getLocalizedMessage(), t);
      }
      else
      {
         log.log(level, t.getLocalizedMessage());
      }
      return ExceptionHandlingStatus.PROCEED;
   }
}
