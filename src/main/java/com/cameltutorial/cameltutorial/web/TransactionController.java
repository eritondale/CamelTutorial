/*
package com.cameltutorial.cameltutorial.web;

import com.cameltutorial.cameltutorial.model.TransactionDto;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.ProducerTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transaction")
@Slf4j
public class TransactionController {

  private final ProducerTemplate producerTemplate;

  public TransactionController(ProducerTemplate producerTemplate) {
    this.producerTemplate = producerTemplate;
  }


  @PostMapping(value = {"/call"})
  public ResponseEntity<String> callTransaction() {
    producerTemplate.sendBody("direct:trans");
    return ResponseEntity.ok().build();
  }

  @GetMapping(value = {"/perform"})
  public ResponseEntity<TransactionDto> getTransaction() {
    TransactionDto transactionDto = new TransactionDto()
        .setTransactionId("1")
        .setTransactionDate("3 Maggio 2022")
        .setAmount("1000")
        .setCurrency("$")
        .setReceiverAccountId("2")
        .setSenderAccountId("3");
    Optional<TransactionDto> transaction = Optional.of(transactionDto);
    return ResponseEntity.ok().body(transaction.orElse(new TransactionDto()));
  }

  @GetMapping(value = {"/message"})
  public ResponseEntity<String> getSuccess() {
    return ResponseEntity.ok().body("Transaction complete");
  }

}
*/
