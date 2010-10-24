package org.jboss.seam.exception.control.prototype;

public interface ExceptionHandler<T extends Throwable>
{
   public ExceptionHandlingStatus handle(T t);
}