package ar.edu.unnoba.pdyc.config;

import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class RestClientConfig {

    private final DiscoveryClient discoveryClient;

    public RestClientConfig(DiscoveryClient discoveryClient) {
        this.discoveryClient = discoveryClient;
    }

    @Bean
    public RestClient songServiceRestClient() {
        return RestClient.builder()
                .build();
    }

}
