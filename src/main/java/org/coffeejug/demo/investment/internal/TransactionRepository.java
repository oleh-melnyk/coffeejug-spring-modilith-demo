package org.coffeejug.demo.investment.internal;

import org.coffeejug.demo.investment.internal.InvestmentTransaction.InvestmentTransactionId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
interface TransactionRepository extends MongoRepository<InvestmentTransaction, InvestmentTransactionId> {
}
