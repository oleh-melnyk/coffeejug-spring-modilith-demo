package org.coffeejug.demo;

import org.junit.jupiter.api.Test;
import org.springframework.modulith.core.ApplicationModules;
import org.springframework.modulith.docs.Documenter;
import org.springframework.modulith.docs.Documenter.CanvasOptions;
import org.springframework.modulith.docs.Documenter.DiagramOptions;

class ModularityTests {

  ApplicationModules modules = ApplicationModules.of(FundInvestmentApp.class);

  @Test
  void describeModules() {
    modules.forEach(System.out::println);
  }

  @Test
  void verifiesModularStructure() {
    modules.verify();
  }

  @Test
  void createModuleDocumentationDefault() {
    new Documenter(modules)
        .writeDocumentation(DiagramOptions.defaults(), CanvasOptions.defaults());
  }
}
