package com.cameltutorial.cameltutorial.config;

import com.rabbitmq.client.ConnectionFactory;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
@Component
@ConfigurationProperties(prefix = "rabbit")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@EqualsAndHashCode
public class CamelConfiguration {

  //public static final String RABBIT_URI = "rabbitmq:amq.direct?queue=%s&routingKey=%s&autoDelete=false";
  private String host;
  private Integer port;
  private String username;
  private String password;

  @Bean
  public ConnectionFactory rabbitConnectionFactory() {
    ConnectionFactory factory = new ConnectionFactory();
    factory.setHost(this.host);
    factory.setPort(this.port);
    factory.setUsername(this.username);
    factory.setPassword(this.password);
    return factory;
  }
}
