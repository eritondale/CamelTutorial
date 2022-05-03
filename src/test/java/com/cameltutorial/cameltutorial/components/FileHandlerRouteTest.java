package com.cameltutorial.cameltutorial.components;

import com.cameltutorial.cameltutorial.CamelTutorialApplication;
import java.util.Date;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.test.spring.junit5.CamelSpringBootTest;
import org.apache.camel.test.spring.junit5.MockEndpoints;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

@SpringBootTest(classes = CamelTutorialApplication.class, properties = "camel.file.enabled=true")
@CamelSpringBootTest
@MockEndpoints()
public class FileHandlerRouteTest {

  @Autowired
  private ProducerTemplate producerTemplate;

  @Test
  @DirtiesContext
  public void testCamelFileRoute() {
    System.out.println("Sending request to append to existing file...");
    producerTemplate.sendBody("direct:appendToFile", "Hello!" + new Date() + "\n");
    System.out.println("Sent request to append to existing file!");
  }


}
