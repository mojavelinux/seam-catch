package org.jboss.seam.exception.control.prototype.config;

import javax.enterprise.inject.Typed;

@Typed()
public class CatchConfig
{
   protected final ExceptionHandlerRegistry registry;

   public CatchConfig()
   {
      registry = new ExceptionHandlerRegistry();
   }
   
   public CatchConfig(ExceptionHandlerRegistry mapping)
   {
      this.registry = mapping;
   }
   
   // TODO support exact match (strict) vs type + super types
   public <T extends Throwable> ExceptionConfig<T> onCatching(Class<T> type)
   {
      return new ExceptionConfig<T>(registry, type);
   }
   
   public ExceptionConfig<Throwable> onCatchingAnyException()
   {
      return new ExceptionConfig<Throwable>(registry);
   }
   
   public ExceptionHandlerRegistry getRegistry()
   {
      return registry;
   }
}
