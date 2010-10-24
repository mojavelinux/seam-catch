package org.jboss.seam.exception.control.prototype.handlers;

import javax.enterprise.context.Conversation;
import javax.inject.Inject;

import org.jboss.seam.exception.control.prototype.ExceptionHandler;
import org.jboss.seam.exception.control.prototype.ExceptionHandlingStatus;

public class ConversationControlExceptionHandler implements ExceptionHandler<Throwable>
{
   //@Inject
   private Conversation conversation;
   
   public ExceptionHandlingStatus handle(Throwable t)
   {
      if (!conversation.isTransient())
      {
         conversation.end();
      }
      return ExceptionHandlingStatus.PROCEED;
   }
}
