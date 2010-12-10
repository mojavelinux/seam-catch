package org.jboss.seam.exception.control.test.stereotype;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.enterprise.inject.Stereotype;

import org.jboss.seam.exception.control.HandlesExceptions;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@HandlesExceptions
@Stereotype
@Target({ TYPE })
@Retention(RUNTIME)
@Documented
public @interface GetYourHandlersHere
{
}
