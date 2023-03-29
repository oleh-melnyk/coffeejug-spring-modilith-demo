package org.coffeejug.demo.investment;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.assertj.core.api.Assertions;
import org.coffeejug.demo.fund.Fund.FundId;
import org.coffeejug.demo.fund.FundManagement;
import org.coffeejug.demo.fund.registry.FundRegistry;
import org.coffeejug.demo.infra.event.InvestmentApprovedEvent;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.modulith.moments.support.TimeMachine;
import org.springframework.modulith.test.ApplicationModuleTest;
import org.springframework.modulith.test.PublishedEvents;
import org.springframework.modulith.test.Scenario;

@ApplicationModuleTest
@ExtendWith(MockitoExtension.class)
@RequiredArgsConstructor
public class InvestmentModuleTest {

  @InjectMocks
  private final InvestmentManagement investmentManagement;
  @MockBean
  private FundManagement fundManagement;
  @MockBean
  private InvestmentRepository investmentRepository;
  @MockBean
  private FundRegistry fundRegistry;

  @Test
  void investmentCreated() {

    investmentManagement.createAndApproveInvestment(new FundId("123"), "Demo Inv", BigDecimal.valueOf(150_000));

    Mockito.verify(investmentRepository, Mockito.times(1)).save(ArgumentMatchers.any());
    Mockito.verify(fundManagement, Mockito.times(1)).increaseFundPortfolioCapital(new FundId("123"), BigDecimal.valueOf(150_000));
  }

  @Test
  void investmentCreationEventPublished(Scenario scenario) {
    FundId fundId = new FundId("123");
    BigDecimal amount = BigDecimal.valueOf(150_000);

    scenario.stimulate(() -> investmentManagement.createAndApproveInvestment(fundId, "Demo Inv", amount))
        .andWaitForEventOfType(InvestmentApprovedEvent.class)
        .matchingMappedValue(InvestmentApprovedEvent::fundId, fundId)
        .matchingMappedValue(InvestmentApprovedEvent::fundId, amount)
        .toArrive();
  }

  @Test
  @SneakyThrows
  void investmentCreationEventPublished(PublishedEvents events) {

    FundId fundId = new FundId("123");
    BigDecimal amount = BigDecimal.valueOf(150_000);

    investmentManagement.createAndApproveInvestment(fundId, "Demo Inv", amount);

    // Verification

    var matchingMapped = events.ofType(InvestmentApprovedEvent.class)
        .matching(InvestmentApprovedEvent::fundId, fundId)
        .matching(InvestmentApprovedEvent::commitmentAmount, amount);

    Assertions.assertThat(matchingMapped).hasSize(1);

    /*events.assertThat()
        .contains(InvestmentApprovedEvent.class)
        .matching(InvestmentApprovedEvent::fundId, fundId)
        .matching(InvestmentApprovedEvent::commitmentAmount, amount);*/
  }

  @Test
  public void testMomentsEvents(){
    //timeMachine.shiftBy(Duration.of(10, ChronoUnit.DAYS));
    Mockito.verify(investmentRepository,Mockito.times(10)).findAll();
  }
}
