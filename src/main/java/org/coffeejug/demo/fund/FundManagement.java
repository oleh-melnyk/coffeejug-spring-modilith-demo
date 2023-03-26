package org.coffeejug.demo.fund;

import java.math.BigDecimal;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.coffeejug.demo.fund.Fund.FundId;
import org.coffeejug.demo.investment.InvestmentRepository;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class FundManagement {

  private final FundRepository fundRepository;
  private final InvestmentRepository investmentRepository;

  public void increaseFundPortfolioCapital(FundId fundId, BigDecimal amount) {

    System.out.printf("--- Increasing total amount with %s for fund id %s\n", amount, fundId);
  }

  public void listAllFundInvestors(FundId fundId) {
    investmentRepository.listAllFundInvestors(fundId).forEach(System.out::println);
  }
}
