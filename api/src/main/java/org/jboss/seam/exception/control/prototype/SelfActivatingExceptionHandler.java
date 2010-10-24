package org.jboss.seam.exception.control.prototype;

public interface SelfActivatingExceptionHandler<T extends Throwable> extends ExceptionHandler<T>
{
   public ExceptionHandlerActivation getActivation();
}
