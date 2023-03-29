package org.coffeejug.demo.fund;

import java.math.BigDecimal;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.coffeejug.demo.fund.Fund.FundId;
import org.coffeejug.demo.infra.event.InvestmentApprovedEvent;
import org.coffeejug.demo.investment.InvestmentRepository;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.modulith.ApplicationModuleListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
@EnableConfigurationProperties(FundDefaultConfigs.class)
public class FundManagement {

  private final FundDefaultConfigs fundDefaultConfigs;
  private final FundRepository fundRepository;
  private final InvestmentRepository investmentRepository;

  public void increaseFundPortfolioCapital(FundId fundId, BigDecimal amount) {
    if (amount.compareTo(BigDecimal.ZERO) < 0) {
      throw new IllegalStateException("Amount couldn't be less then zero");
    }

    fundRepository.findById(fundId)
        .ifPresent(f -> f.setTargetSize(Objects.requireNonNullElse(f.getTargetSize(), BigDecimal.ZERO).add(amount)));

    System.out.printf("--- Increasing total amount with %s for fund id %s\n", amount, fundId);
  }

  public void listAllFundInvestors(FundId fundId) {
    investmentRepository.listAllFundInvestors(fundId).forEach(System.out::println);
  }
}
