package com.cameltutorial.cameltutorial.components;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.apache.camel.LoggingLevel.ERROR;

import java.util.Date;
import org.apache.camel.Message;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.support.DefaultMessage;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(name = "camel.seda.enabled", havingValue = "true")
public class SedaRoute extends RouteBuilder {

  @Override
  public void configure() {
    from("timer:ping?period=200")
        .process(exchange -> {
          Message message = new DefaultMessage(exchange);
          message.setBody(new Date());
          exchange.setMessage(message);
        })
        .to("seda:weightShifter");

    from("seda:weightShifter?multipleConsumers=true")
        .to("direct:complexProcess");

    from("direct:complexProcess")
        .log(ERROR, "${body}")
        .process(exchange -> SECONDS.sleep(2))
        .end();
  }
}
