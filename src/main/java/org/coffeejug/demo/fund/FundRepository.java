package org.coffeejug.demo.fund;

import org.coffeejug.demo.fund.Fund.FundId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
interface FundRepository extends MongoRepository<Fund, FundId> {}
