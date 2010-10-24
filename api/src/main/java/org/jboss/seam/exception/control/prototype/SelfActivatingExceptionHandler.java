package org.jboss.seam.exception.control.prototype;

/**
 * There are certain exception handlers which have their own prerequisites
 * and must take the responsibility for enforcing them.
 * 
 * <p>For example, an annotation-based exception handler must make sure the exception
 * class has the annotation the handler is going to use.</p>
 * 
 * @author Dan Allen
 *
 * @param <T> The exception (throwable) type hierarchy this handler can process
 */
public interface SelfActivatingExceptionHandler<T extends Throwable> extends ExceptionHandler<T>
{
   public ExceptionHandlerActivation getActivation();
}
