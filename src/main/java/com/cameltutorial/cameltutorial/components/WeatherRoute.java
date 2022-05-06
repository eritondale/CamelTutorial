package com.cameltutorial.cameltutorial.components;

import static org.apache.camel.LoggingLevel.ERROR;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class WeatherRoute extends RouteBuilder {

  @Override
  public void configure() {
    from("rabbitmq:amq.direct?queue=weather&routingKey=weather&autoDelete=false")
        .log(ERROR, "Got this message from Rabbit: ${body}");
  }
}
