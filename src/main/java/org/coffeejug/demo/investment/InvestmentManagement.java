package org.coffeejug.demo.investment;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.coffeejug.demo.fund.Fund.FundId;
import org.coffeejug.demo.investment.Investment.InvestmentStatus;
import org.coffeejug.demo.investment.transactions.InvestmentTransactionsService;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.modulith.ApplicationModuleListener;
import org.springframework.modulith.moments.DayHasPassed;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class InvestmentManagement {

  private final InvestmentTransactionsService investmentTransactionsService;
  private final InvestmentRepository investmentRepository;
  private final ApplicationEventPublisher publisher;

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

    publisher.publishEvent(new InvestmentApprovedEvent(fundId, amount));
  }

  @EventListener
  public void dailyReportForInvestor(DayHasPassed passed) {
    investmentRepository.findAll().forEach(System.out::println);
    System.out.println("New day, new challenges!" + passed.getDate());
  }
}
