package com.cameltutorial.cameltutorial.model;

import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@Accessors(chain = true)
public class TransactionDto implements Serializable {

  private Long transactionId;
  private Long senderAccountId;
  private Long receiverAccountId;
  private Long amount;
  private String currency;
  private String transactionDate;

}
