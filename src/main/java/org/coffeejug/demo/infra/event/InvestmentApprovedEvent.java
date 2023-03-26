package org.coffeejug.demo.infra.event;

import java.math.BigDecimal;
import org.coffeejug.demo.fund.Fund.FundId;

public record InvestmentApprovedEvent(FundId fundId, BigDecimal commitmentAmount) {}
