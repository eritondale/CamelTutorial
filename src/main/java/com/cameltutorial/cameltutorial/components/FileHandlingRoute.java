package com.cameltutorial.cameltutorial.components;

import java.util.Optional;
import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(name="camel.file.enabled", havingValue = "true")
public class FileHandlingRoute extends RouteBuilder {
  private static final String DIR_FROM = "/home/elri/Desktop/Projects/CamelTutorial/files/?noop=true&";
  private static final String DIR_TO = "/home/elri/Desktop/Projects/CamelTutorial/files/?";
  private static final String APPEND = "&fileExist=Append";


  public static void main(String[] args) {
    boolean enabled = false;
    Optional<Boolean> micrometerEnabled = Optional.of(enabled).filter(Boolean::booleanValue);
    micrometerEnabled.ifPresent(e -> System.out.println(enabled + " = " + micrometerEnabled));
    System.out.println("All " + enabled + " = " + micrometerEnabled);

  }

  @Override
  public void configure() {
    System.out.println("In file ...");
    CamelContext context = new DefaultCamelContext();
    from("file://" + DIR_FROM + "fileName=camel-demo-in.txt")
        .to("file://" + DIR_TO + "fileName=camel-demo-out.txt");
    from("direct:appendToFile")
        .process(Exchange::getMessage)
        .to("file://" + DIR_TO + "fileName=camel-demo-appends.txt" + APPEND);

  }
}
