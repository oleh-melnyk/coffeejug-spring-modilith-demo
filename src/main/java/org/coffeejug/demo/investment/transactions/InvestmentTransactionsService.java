package org.coffeejug.demo.investment.transactions;

import java.math.BigDecimal;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.coffeejug.demo.investment.Investment.InvestmentId;
import org.coffeejug.demo.investment.transactions.InvestmentTransaction.InvestmentTransactionId;
import org.coffeejug.demo.investment.transactions.InvestmentTransaction.TransactionType;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InvestmentTransactionsService {

  public void initBuyTransaction(InvestmentId investmentId, BigDecimal amount) {
    initTransaction(TransactionType.BUY, amount, investmentId);
  }

  private void initTransaction(TransactionType buy, BigDecimal amount, InvestmentId investmentId) {
    System.out.printf("--- Initializing %s trx with amount %s for investment %s\n", buy, amount, investmentId);
    var entity = new InvestmentTransaction();
    entity.setId(new InvestmentTransactionId(UUID.randomUUID().toString().substring(0, 5)));
    entity.setTransactionType(buy);
    entity.setAmount(amount);
    entity.setInvestmentId(investmentId);
  }

  public void initSellTransaction(InvestmentId investmentId, BigDecimal amount) {
    initTransaction(TransactionType.SELL, amount, investmentId);
  }
}
