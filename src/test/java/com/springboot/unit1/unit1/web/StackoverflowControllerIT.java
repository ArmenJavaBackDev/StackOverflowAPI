package com.springboot.unit1.unit1.web;

import com.google.common.collect.ImmutableList;
import com.springboot.unit1.unit1.model.StackoverflowWebsite;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.collectingAndThen;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class StackoverflowControllerIT {
    private TestRestTemplate restTemplate=new TestRestTemplate();

    @Test
    public void getListOfProviders() throws Exception{
        ResponseEntity<List<StackoverflowWebsite>> responseEntity = restTemplate.exchange("http://localhost:8099/api/stackoverflow", HttpMethod.GET, null,
                new ParameterizedTypeReference<List<StackoverflowWebsite>>() {
                });
        List<StackoverflowWebsite> actualList = responseEntity.getBody();
        //validate
        assertThat(actualList.size(),is(2));

        List<String> actualIDs = actualList.stream().map(stackoverflowWebsite -> stackoverflowWebsite.getId())
                .collect(collectingAndThen(Collectors.toList(), ImmutableList::copyOf));
        assertThat(actualIDs,containsInAnyOrder("website1","website2"));
    }
}