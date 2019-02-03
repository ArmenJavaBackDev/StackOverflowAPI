package com.springboot.unit1.unit1.service;

import com.google.common.collect.ImmutableList;
import com.springboot.unit1.unit1.model.SiteDto;
import com.springboot.unit1.unit1.model.StackoverflowWebsite;
import com.springboot.unit1.unit1.persistence.StackoverflowWebsiteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

@Service
public class StackoverflowService {

    @Autowired
    private StackoverflowWebsiteRepository stackoverflowWebsiteRepository;

    @Autowired
    private StackExchangeClient stackExchangeClient;

    public List<StackoverflowWebsite> findAll(){
        return stackExchangeClient.getSites().stream()
                .map(this::toStackoverflowWebsite)
                .filter(this::ignoreMeta)
                .collect(collectingAndThen(toList(), ImmutableList::copyOf));
    }

    private boolean ignoreMeta(StackoverflowWebsite stackoverflowWebsite) {
        return !stackoverflowWebsite.getId().startsWith("meta.");
    }

    private StackoverflowWebsite toStackoverflowWebsite(SiteDto siteDto) {
        return new StackoverflowWebsite(
                siteDto.getSite_url().substring("https://".length(),siteDto.getSite_url().length()-".com".length()),
                siteDto.getSite_url(),
                siteDto.getFavicon_url(),
                siteDto.getName(),
                siteDto.getAudience());
    }


//    public List<StackoverflowWebsite> findAll() {
//        return stackoverflowWebsiteRepository.findAll();
//    }
}
