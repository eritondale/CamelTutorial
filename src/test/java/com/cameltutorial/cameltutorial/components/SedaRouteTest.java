package com.cameltutorial.cameltutorial.components;

import static java.util.concurrent.TimeUnit.SECONDS;

import com.cameltutorial.cameltutorial.CamelTutorialApplication;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.test.spring.junit5.CamelSpringBootTest;
import org.apache.camel.test.spring.junit5.MockEndpoints;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

@SpringBootTest(classes = CamelTutorialApplication.class, properties = "camel.seda.enabled=true")
@CamelSpringBootTest
@MockEndpoints()
class SedaRouteTest {

  @Autowired
  private ProducerTemplate producerTemplate;

  @Test
  @DirtiesContext
  public void testMocksAreValid() {
    producerTemplate.sendBody("direct:ticker", "Hello");
    try {
      SECONDS.sleep(10);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

}
