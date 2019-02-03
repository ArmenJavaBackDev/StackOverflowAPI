package com.springboot.unit1.unit1.service;


import com.springboot.unit1.unit1.model.SiteDto;
import com.springboot.unit1.unit1.model.SitesDto;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;


@Component
public class StackExchangeClient {
    HttpClient httpClient= HttpClientBuilder.create().build();
    ClientHttpRequestFactory requestFactory=new HttpComponentsClientHttpRequestFactory(httpClient);
    private RestTemplate restTemplate=new RestTemplate(requestFactory);

    public List<SiteDto> getSites(){
        String URL="https://api.stackexchange.com/2.2/sites?page=1&pagesize=9999&filter=!)QmCwY51ivjfd_pA.icNAOWD";
        SitesDto response= null;
        try {
            response = restTemplate.getForObject(new URI(URL), SitesDto.class);
            return response.getItems();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
}
