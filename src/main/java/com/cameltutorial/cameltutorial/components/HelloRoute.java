package com.cameltutorial.cameltutorial.components;

import static org.apache.camel.LoggingLevel.ERROR;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(name="camel.hello.enabled", havingValue = "true")
public class HelloRoute extends RouteBuilder {

  @Override
  public void configure() {
    from("direct:greeting")
        .log(ERROR, "Hello ${body}")
        .choice()
        .when().simple("${body} contains 'Team'")
        .log(ERROR, "I like working with Teams")
        .otherwise()
        .log(ERROR, "Solo fighter :)")
        .end()
        .end();

  }
}
