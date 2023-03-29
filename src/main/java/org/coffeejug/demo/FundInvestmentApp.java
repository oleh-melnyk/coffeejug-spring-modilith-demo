package org.coffeejug.demo;

import java.math.BigDecimal;
import java.time.Clock;
import org.coffeejug.demo.fund.Fund.FundId;
import org.coffeejug.demo.investment.InvestmentManagement;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class FundInvestmentApp {

  public static void main(String[] args) {
    SpringApplication.run(FundInvestmentApp.class, args)
        .getBean(InvestmentManagement.class)
        .createAndApproveInvestment(new FundId("12345"), "Jappware", BigDecimal.valueOf(1_000_000));
  }

  @Bean
  Clock clock() {
    return Clock.systemUTC();
  }
}
