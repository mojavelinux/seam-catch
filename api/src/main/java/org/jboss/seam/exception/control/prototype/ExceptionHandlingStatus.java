package org.jboss.seam.exception.control.prototype;

/**
 * An enum indicating how the status of the exception handling
 * after giving a handler a shot at dealing with it.
 * 
 * @author Dan Allen
 */
public enum ExceptionHandlingStatus
{
   /**
    * Proceed with normal execution, which means notifying
    * each handler in the chains for this exception type
    * in sequence, then popping to the exception cause
    * and repeating until flow ends or is interrupted.
    */
   PROCEED,
   
   /**
    * Consider the exception handled and do not notify
    * any more handlers.
    */
   HANDLED,
   
   /**
    * Stop notifying handlers for the current exception,
    * pop to the exception cause and begin notifying
    * the handlers for that exception.
    */
   POP_STACK
}
