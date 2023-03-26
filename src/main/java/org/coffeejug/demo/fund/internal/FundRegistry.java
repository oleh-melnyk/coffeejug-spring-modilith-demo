package org.coffeejug.demo.fund.internal;

import java.util.Map;
import org.springframework.stereotype.Service;

@Service
public class FundRegistry {

  public Map<String, Object> lookupFundInfo(String publicFundName) {
    System.out.println("Looking for info in public registry...");
    return Map.of();
  }
}
