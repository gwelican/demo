package com.example.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.core.io.support.PropertySourceFactory;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Data
@Configuration
@NoArgsConstructor
@AllArgsConstructor
@PropertySource(value = "classpath:routingRule.json")
@ConfigurationProperties
public class RoutingRules {
  private String data;
  private List<Rule> rules;

  @AllArgsConstructor
  @NoArgsConstructor
  @Data
  public static class Rule {
    private List<String> currency;
  }
  @PostConstruct
  void init () {
    System.out.println(this);
  }

}

class JsonPropertySourceFactory implements PropertySourceFactory {
  @Override
  public org.springframework.core.env.PropertySource<?> createPropertySource(
      final String name, final EncodedResource resource) throws IOException {
    Map<String, Object> readValue = new ObjectMapper().readValue(resource.getInputStream(), Map.class);
    return new MapPropertySource("json-property", readValue);
  }

}