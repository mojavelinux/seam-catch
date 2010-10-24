package org.jboss.seam.exception.control.prototype.http;

public class HttpErrorResponse
{
   public void send(int statusCode, String message)
   {
      System.out.println("HTTP 1.1 " + statusCode + " " + message);
   }
}