package com.onegini.integration.demo.citrus;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.consol.citrus.docker.client.DockerClient;
import com.consol.citrus.dsl.endpoint.CitrusEndpoints;
import com.consol.citrus.http.client.HttpClient;

@Configuration
public class EndpointConfig {

  @Bean
  public HttpClient simpleServiceClient() {
    return CitrusEndpoints
        .http()
          .client()
          .requestUrl("http://localhost:8080")
        .build();
  }

}
