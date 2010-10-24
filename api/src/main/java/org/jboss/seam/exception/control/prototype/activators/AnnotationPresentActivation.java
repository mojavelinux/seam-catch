package org.jboss.seam.exception.control.prototype.activators;

import java.lang.annotation.Annotation;

import org.jboss.seam.exception.control.prototype.ExceptionHandlerActivation;

public class AnnotationPresentActivation implements ExceptionHandlerActivation
{
   private final Class<? extends Annotation> annotationType;
   
   public AnnotationPresentActivation(Class<? extends Annotation> annotationType)
   {
      this.annotationType = annotationType;
   }

   public boolean isActive(Throwable t)
   {
      return t.getClass().isAnnotationPresent(annotationType);
   }
   
   public boolean isContextual()
   {
      return false;
   }
}
