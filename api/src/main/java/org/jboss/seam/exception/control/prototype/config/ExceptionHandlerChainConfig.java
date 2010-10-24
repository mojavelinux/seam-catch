package org.jboss.seam.exception.control.prototype.config;

import org.jboss.seam.exception.control.prototype.ExceptionHandlerActivation;
import org.jboss.seam.exception.control.prototype.ExceptionHandlerChain;

public class ExceptionHandlerChainConfig<T extends Throwable> extends CatchConfig
{
   protected final ExceptionConfig<T> exceptionConfig;
   protected final ExceptionHandlerChain chain;
   
   public ExceptionHandlerChainConfig(ExceptionConfig<T> exceptionConfig, ExceptionHandlerChain chain)
   {
      super(exceptionConfig.registry);
      this.exceptionConfig = exceptionConfig;
      this.chain = chain;
   }
   
   public ExceptionConfig<T> whenSatisfiesActivationCriteria(ExceptionHandlerActivation...activations)
   {
      chain.addAll(activations);
      return exceptionConfig;
   }
}
