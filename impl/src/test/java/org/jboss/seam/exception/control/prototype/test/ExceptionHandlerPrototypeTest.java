package org.jboss.seam.exception.control.prototype.test;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.enterprise.event.Event;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;

import org.jboss.arquillian.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.seam.exception.control.prototype.ExceptionEvent;
import org.jboss.seam.exception.control.prototype.ExceptionHandler;
import org.jboss.seam.exception.control.prototype.activators.CurrentViewIdActivation;
import org.jboss.seam.exception.control.prototype.config.CatchConfig;
import org.jboss.seam.exception.control.prototype.handlers.AnnotationFacesRedirectExceptionHandler;
import org.jboss.seam.exception.control.prototype.handlers.AnnotationHttpErrorHandler;
import org.jboss.seam.exception.control.prototype.handlers.HttpErrorExceptionHandler;
import org.jboss.seam.exception.control.prototype.handlers.LoggingExceptionHandler;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.jboss.shrinkwrap.impl.base.asset.ByteArrayAsset;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class ExceptionHandlerPrototypeTest
{
   @Deployment
   public static Archive<?> createDeployment()
   {
      return ShrinkWrap.create("test.jar", JavaArchive.class)
         .addPackages(true, ExceptionHandler.class.getPackage())
         .addManifestResource(new ByteArrayAsset(new byte[0]), "beans.xml");
   }
   
   @Inject Event<ExceptionEvent> exceptionEvent;
   
   @Test
   public void test()
   {
      // some asserts would be nice ;)
      exceptionEvent.fire(new ExceptionEvent(new MyException("No wireless!")));
      exceptionEvent.fire(new ExceptionEvent(new SecurityException("You suck!")));
      exceptionEvent.fire(new ExceptionEvent(new IllegalAccessError("It's broke!")));
      exceptionEvent.fire(new ExceptionEvent(new RuntimeException(new NullPointerException("Null pointer. Go fish."))));
   }
   
   @SuppressWarnings("unchecked")
   @Produces
   public CatchConfig configureCatch()
   {
      return new CatchConfig()
         .onCatchingAnyException()
            .handleWith(
               new AnnotationHttpErrorHandler(),
               new AnnotationFacesRedirectExceptionHandler()
            )
         .onCatching(SecurityException.class)
            .handleWith(
               new LoggingExceptionHandler(Level.INFO, false),
               new HttpErrorExceptionHandler(301, "Insufficient priviledges")
               // try to add
               //, new IllegalStateExceptionHandler()
            )
            .whenSatisfiesActivationCriteria(new CurrentViewIdActivation("*"))
         .onCatching(LinkageError.class)
            .handleWith(
               new LoggingExceptionHandler(Level.WARNING)
            )
         .onCatching(NullPointerException.class)
            .handleWith(
               new HttpErrorExceptionHandler(500)
            );
   }
   
   @Produces
   public Logger getLogger()
   {
      return Logger.getLogger("mock");
   }
}
