package org.jboss.seam.exception.control.prototype.config;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jboss.seam.exception.control.prototype.ExceptionHandlerChain;

public class ExceptionHandlerRegistry
{
   private List<ExceptionHandlerChain> globalChains;
   private Map<Class<? extends Throwable>, List<ExceptionHandlerChain>> chainsByExceptionType;
   
   public ExceptionHandlerRegistry()
   {
      globalChains = new ArrayList<ExceptionHandlerChain>();
      chainsByExceptionType = new HashMap<Class<? extends Throwable>, List<ExceptionHandlerChain>>();
   }
   
   void addGlobalChain(ExceptionHandlerChain chain)
   {
      globalChains.add(chain);
   }
   
   void addChainForExceptionType(Class<? extends Throwable> type, ExceptionHandlerChain chain)
   {
      List<ExceptionHandlerChain> chains;
      if (!chainsByExceptionType.containsKey(type))
      {
         chains = new ArrayList<ExceptionHandlerChain>();
         chainsByExceptionType.put(type, chains);
      }
      else
      {
         chains = chainsByExceptionType.get(type);
      }
      chains.add(chain);
   }
   
   public List<ExceptionHandlerChain> getExceptionHandlerChainsFor(Throwable t)
   {
      List<ExceptionHandlerChain> chains = new ArrayList<ExceptionHandlerChain>();
      chains.addAll(globalChains);
      if (chainsByExceptionType.containsKey(t.getClass()))
      {
         chains.addAll(chainsByExceptionType.get(t.getClass()));
      }
      else
      {
         // huge hack to find more general exception types
         Class<?> superType = t.getClass().getSuperclass();
         while (superType != null)
         {
            if (chainsByExceptionType.containsKey(superType))
            {
               chains.addAll(chainsByExceptionType.get(superType));
               break;
            }
            superType = superType.getSuperclass();
         }
      }
      return Collections.unmodifiableList(chains);
   }
}
