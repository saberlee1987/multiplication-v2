package com.saber.multiplication.multiplicationv2.config;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {

    @Value(value = "${spring.restTemplate.connectTimeout}")
    private int connectTimeout;
    @Value(value = "${spring.restTemplate.readTimeout}")
    private int readTimeout;
    @Value(value = "${spring.restTemplate.maxConnectionPerRoute}")
    private int maxConnectionPerRoute;
    @Value(value = "${spring.restTemplate.maxConnectionTotal}")
    private int maxConnectionTotal;

    @Bean
    public ObjectMapper objectMapper(){
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS,false);
        objectMapper.configure(DeserializationFeature.FAIL_ON_INVALID_SUBTYPE,false);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
        return objectMapper;
    }

    @Bean
    public Module hibernateModule(){
        return new Hibernate5Module();
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder){

        HttpClient httpClient = HttpClientBuilder.create()
                .setMaxConnPerRoute(maxConnectionPerRoute)
                .setMaxConnTotal(maxConnectionTotal)
                .build();
        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();

        requestFactory.setHttpClient(httpClient);
        requestFactory.setConnectTimeout(connectTimeout);
        requestFactory.setReadTimeout(readTimeout);

        RestTemplate restTemplate = restTemplateBuilder
                .build();

        restTemplate.setRequestFactory(requestFactory);
        return restTemplate;
    }
}
