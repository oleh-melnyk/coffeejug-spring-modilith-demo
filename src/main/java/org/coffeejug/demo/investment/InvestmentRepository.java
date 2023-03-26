package org.coffeejug.demo.investment;

import java.util.List;
import org.coffeejug.demo.fund.Fund.FundId;
import org.coffeejug.demo.investment.Investment.InvestmentId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvestmentRepository extends MongoRepository<Investment, InvestmentId> {

  default List<String> listAllFundInvestors(FundId fundId) {
    return this.findAll().stream()
        .filter(inv -> inv.getSubscribedFund().equals(fundId))
        .map(Investment::getInvestorName)
        .distinct().toList();
  }
}
