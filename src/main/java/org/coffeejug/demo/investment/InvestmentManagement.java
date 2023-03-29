package org.coffeejug.demo.investment;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.coffeejug.demo.fund.Fund.FundId;
import org.coffeejug.demo.fund.FundManagement;
import org.coffeejug.demo.fund.registry.FundRegistry;
import org.coffeejug.demo.investment.Investment.InvestmentStatus;
import org.coffeejug.demo.investment.transactions.InvestmentTransactionsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class InvestmentManagement {

  private final InvestmentTransactionsService investmentTransactionsService;
  private final InvestmentRepository investmentRepository;
  private final FundManagement fundManagement;
  private final FundRegistry fundRegistry;

  @Transactional
  public void createAndApproveInvestment(FundId fundId, String investorName, BigDecimal amount) {
    var investment = new Investment();
    investment.setAmount(amount);
    investment.setDate(LocalDateTime.now());
    investment.setStatus(InvestmentStatus.APPROVED);
    investment.setSubscribedFund(fundId);
    investment.setInvestorName(investorName);
    investmentRepository.save(investment);

    System.out.println("--- Investment created and approved");
    System.out.println("Details: " + investment);
    investmentTransactionsService.initBuyTransaction(investment.getId(), amount);

    fundManagement.increaseFundPortfolioCapital(investment.getSubscribedFund(), amount);
  }

  public void dailyReportForInvestor() {
    investmentRepository.findAll().forEach(System.out::println);
    System.out.println("New day, new challenges!");
  }
}
