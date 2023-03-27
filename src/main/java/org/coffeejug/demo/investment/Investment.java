package org.coffeejug.demo.investment;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.Data;
import org.coffeejug.demo.fund.Fund.FundId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class Investment {

  @Id
  private InvestmentId id = new InvestmentId(UUID.randomUUID().toString());
  private BigDecimal amount;
  private LocalDateTime date;
  private InvestmentStatus status;
  private String investorName;
  private FundId subscribedFund;

  public enum InvestmentStatus {
    APPROVED,
    REJECTED,
    WITHDRAWN,
    COMPLETED
  }

  public record InvestmentId(String value) {}
}
