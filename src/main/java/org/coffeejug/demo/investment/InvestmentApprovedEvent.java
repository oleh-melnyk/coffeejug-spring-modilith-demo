package org.coffeejug.demo.investment;

import java.math.BigDecimal;
import org.coffeejug.demo.fund.Fund.FundId;
import org.jmolecules.event.annotation.DomainEvent;

@DomainEvent
public record InvestmentApprovedEvent(FundId fundId, BigDecimal commitmentAmount) {}
