package org.jboss.seam.exception.control.test.handler;

import org.jboss.seam.exception.control.CaughtException;
import org.jboss.seam.exception.control.Handles;
import org.jboss.seam.exception.control.test.stereotype.GetYourHandlersHere;

@GetYourHandlersHere
public class StereotypedExceptionHandler
{
   public void handlesAll(@Handles CaughtException<Throwable> caught)
   {
   }
}
