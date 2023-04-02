package org.coffeejug.demo.fund;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import lombok.Data;
import org.jmolecules.ddd.annotation.Identity;
import org.jmolecules.ddd.types.Identifier;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class Fund {

  @Id
  private FundId id = new FundId(UUID.randomUUID().toString());
  private String name;
  private BigDecimal targetSize;
  private BigDecimal minSubAmount;
  private List<LocalDate> closingDates = new ArrayList<>();

  public record FundId(String value) implements Identifier{}
}
