package com.cameltutorial.cameltutorial.components;

import static com.cameltutorial.cameltutorial.config.CamelConfiguration.RABBIT_URI;

import com.cameltutorial.cameltutorial.model.WeatherDto;
import java.util.Date;
import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.apache.camel.support.DefaultMessage;
import org.springframework.stereotype.Component;

@Component
public class WeatherRoute extends RouteBuilder {

  private static final String DIR_TO = "/home/elri/Desktop/Projects/CamelTutorial/files/?";
  private static final String APPEND = "&fileExist=Append";

  @Override
  public void configure() {
    from(String.format(RABBIT_URI, "weather", "weather"))
        .log("Before enrichment: ${body}")
        .unmarshal().json(JsonLibrary.Jackson, WeatherDto.class)
        .process(this::enrichWeatherDto)
        .log("After enrichment: ${body}")
        .marshal().json(JsonLibrary.Jackson, WeatherDto.class)
        .to(String.format(RABBIT_URI, "weather-event", "weather-event"))
        .to("file://" + DIR_TO + "fileName=camel-weather.txt" + APPEND);
  }

  private void enrichWeatherDto(Exchange exchange) {
    WeatherDto dto = exchange.getMessage().getBody(WeatherDto.class);
    dto.setReceivedTime(new Date().toString());

    Message message = new DefaultMessage(exchange);
    message.setBody(dto);
    exchange.setMessage(message);

  }
}
