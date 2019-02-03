package com.springboot.unit1.unit1.web;

import com.google.common.collect.ImmutableList;
import com.springboot.unit1.unit1.model.StackoverflowWebsite;
import com.springboot.unit1.unit1.service.StackoverflowService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class StackoverflowControllerTest {
    @Mock
    private StackoverflowService stackoverflowService;
    @InjectMocks
    private StackoverflowController stackoverflowController;

    @Test
    public void getListOfProviders() throws Exception {
        //prepare
        when(stackoverflowService.findAll()).thenReturn(ImmutableList.of());
        //testing
        List<StackoverflowWebsite> websites=stackoverflowController.getListOfProviders();
        //validate
        verify(stackoverflowService).findAll();
    }
}