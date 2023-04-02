package org.coffeejug.demo.investment;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.assertj.core.api.Assertions;
import org.coffeejug.demo.fund.Fund.FundId;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.modulith.moments.support.TimeMachine;
import org.springframework.modulith.test.ApplicationModuleTest;
import org.springframework.modulith.test.AssertablePublishedEvents;

@ApplicationModuleTest(verifyAutomatically = false)
@RequiredArgsConstructor
public class InvestmentModuleTest {

  @MockBean
  private InvestmentRepository investmentRepository;
  @InjectMocks
  private final InvestmentManagement investmentManagement;
  private final TimeMachine timeMachine;

  @Test
  @SneakyThrows
  void investmentCreationEventPublished(AssertablePublishedEvents events) {

    FundId fundId = new FundId("123");
    BigDecimal amount = BigDecimal.valueOf(150_000);

    investmentManagement.createAndApproveInvestment(fundId, "Demo Inv", amount);

    // Verification

    var matchingMapped = events.ofType(InvestmentApprovedEvent.class)
        .matching(InvestmentApprovedEvent::fundId, fundId)
        .matching(InvestmentApprovedEvent::commitmentAmount, amount);

    Assertions.assertThat(matchingMapped).hasSize(1);

    events.assertThat()
        .contains(InvestmentApprovedEvent.class)
        .matching(InvestmentApprovedEvent::fundId, fundId)
        .matching(InvestmentApprovedEvent::commitmentAmount, amount);
  }

  @Test
  public void testMomentsEvents() {
    timeMachine.shiftBy(Duration.of(10, ChronoUnit.DAYS));
    Mockito.verify(investmentRepository, Mockito.times(10)).findAll();
  }
}
