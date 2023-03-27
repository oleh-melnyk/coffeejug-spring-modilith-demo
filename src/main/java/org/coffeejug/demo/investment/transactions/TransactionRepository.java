package org.coffeejug.demo.investment.transactions;

import org.coffeejug.demo.investment.transactions.InvestmentTransaction.InvestmentTransactionId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
interface TransactionRepository extends MongoRepository<InvestmentTransaction, InvestmentTransactionId> {
}
