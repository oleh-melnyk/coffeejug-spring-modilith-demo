package org.coffeejug.demo.investment.internal;

import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.Data;
import org.coffeejug.demo.investment.Investment.InvestmentId;
import org.springframework.data.annotation.Id;

@Data
public class InvestmentTransaction {

  @Id
  private InvestmentTransactionId id;
  private InvestmentId investmentId;
  private TransactionType transactionType;
  private LocalDate transactionDate;
  private BigDecimal amount;

  public record InvestmentTransactionId(String value) {}

  public enum TransactionType {
    BUY,
    SELL
  }
}
