package org.jboss.seam.exception.control.prototype.test;

import org.jboss.seam.exception.control.prototype.handlers.HttpError;

@HttpError(statusCode = 500)
public class MyException extends RuntimeException
{
   public MyException()
   {
      super();
   }

   public MyException(String message, Throwable cause)
   {
      super(message, cause);
   }

   public MyException(String message)
   {
      super(message);
   }

   public MyException(Throwable cause)
   {
      super(cause);
   }
}
