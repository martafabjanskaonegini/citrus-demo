package com.onegini.integration.demo.citrus;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import org.apache.http.entity.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import com.consol.citrus.annotations.CitrusEndpoint;
import com.consol.citrus.annotations.CitrusResource;
import com.consol.citrus.annotations.CitrusTest;
import com.consol.citrus.docker.client.DockerClient;
import com.consol.citrus.dsl.junit.jupiter.CitrusExtension;
import com.consol.citrus.dsl.runner.TestRunner;
import com.consol.citrus.http.client.HttpClient;
import com.consol.citrus.message.MessageType;

@ExtendWith(CitrusExtension.class)
public class LoginControllerIT {

  @CitrusEndpoint
  private HttpClient simpleServiceClient;

  @Test
  @CitrusTest
  public void testLogin(@CitrusResource TestRunner runner) {

    runner.http(httpActionBuilder -> httpActionBuilder
        .client(simpleServiceClient)
        .send()
        .post("/login")
        .messageType(MessageType.JSON)
        .contentType(ContentType.APPLICATION_JSON.getMimeType())
        .payload("{ \"login\": \"Jakub\", \"password\": \"test\"}"));

    runner.http(httpActionBuilder -> httpActionBuilder
        .client(simpleServiceClient)
        .receive()
        .response(HttpStatus.OK)
        .messageType(MessageType.JSON)
        .validate("$.welcome", "Welcome Jakub"));
  }

}
