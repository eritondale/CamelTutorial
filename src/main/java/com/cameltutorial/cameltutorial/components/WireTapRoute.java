package com.cameltutorial.cameltutorial.components;

import static com.cameltutorial.cameltutorial.config.CamelConfiguration.RABBIT_URI;
import static org.apache.camel.LoggingLevel.ERROR;

import com.cameltutorial.cameltutorial.model.TransactionDto;
import java.util.Date;
import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.apache.camel.support.DefaultMessage;
import org.springframework.stereotype.Component;

@Component
public class WireTapRoute extends RouteBuilder {

  public static final String SENDER = "sender";
  public static final String RECEIVER = "receiver";
  public static final String AUDIT_TRANSACTION = "direct:audit-transactions";
  public static final String AUDIT = "audit-transactions";
  
  @Override
  public void configure() {
    fromF(RABBIT_URI, SENDER, SENDER)
        .unmarshal().json(JsonLibrary.Jackson, TransactionDto.class)
        .wireTap(AUDIT_TRANSACTION)
        .process(this::enrichWeatherDto)
        .marshal().json(JsonLibrary.Jackson, TransactionDto.class)
        .toF(RABBIT_URI, RECEIVER, RECEIVER)
        .log(ERROR, "Money transferred: ${body}");

    from(AUDIT_TRANSACTION)
        .process(this::enrichWeatherDto)
        .marshal().json(JsonLibrary.Jackson, TransactionDto.class)
        .toF(RABBIT_URI, AUDIT, AUDIT);
  }

  private void enrichWeatherDto(Exchange exchange) {
    TransactionDto dto = exchange.getMessage().getBody(TransactionDto.class);
    dto.setTransactionDate(new Date().toString());

    Message message = new DefaultMessage(exchange);
    message.setBody(dto);
    exchange.setMessage(message);
  }
}
