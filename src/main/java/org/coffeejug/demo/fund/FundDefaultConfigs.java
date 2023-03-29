package org.coffeejug.demo.fund;

import java.math.BigDecimal;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @param minAmount - Minimal amount of applied investment subscription
 * @param maxSubscriptions - max number of possible investment subscription assigned to the fund
 */
@ConfigurationProperties(prefix = "fund")
public record FundDefaultConfigs(
    BigDecimal minAmount,
    BigDecimal maxSubscriptions
) {}
