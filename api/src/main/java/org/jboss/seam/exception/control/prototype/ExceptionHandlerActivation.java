package org.jboss.seam.exception.control.prototype;

public interface ExceptionHandlerActivation
{
   public boolean isActive(Throwable t);
   public boolean isContextual();
}
