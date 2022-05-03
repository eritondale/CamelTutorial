package com.cameltutorial.cameltutorial.components;

import static org.junit.jupiter.api.Assertions.*;

import com.cameltutorial.cameltutorial.CamelTutorialApplication;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.test.spring.junit5.CamelSpringBootTest;
import org.apache.camel.test.spring.junit5.MockEndpoints;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = CamelTutorialApplication.class, properties = "camel.hello.enabled=true")
@CamelSpringBootTest
@MockEndpoints()
class HelloRouteTest {

  @Autowired
  private ProducerTemplate producerTemplate;

  @Test
  public void testMocksAreValid() {
    System.out.println("Sending 1");
    producerTemplate.sendBody("direct:greeting", "Team");
    System.out.println("Sending 2");
    producerTemplate.sendBody("direct:greeting", "Me");
  }



}
