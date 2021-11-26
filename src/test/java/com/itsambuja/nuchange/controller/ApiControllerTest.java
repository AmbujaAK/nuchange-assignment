package com.itsambuja.nuchange.controller;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.itsambuja.nuchange.model.Store;
import com.itsambuja.nuchange.service.ApiService;
import com.itsambuja.nuchange.util.Helper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

@WebMvcTest(ApiController.class)
public class ApiControllerTest {

    public static String URL = "google.com";
    @Autowired
    public MockMvc mockMvc;

    @MockBean
    public ApiService apiService;

    @Test
    public void storeUrl() throws Exception {
        // given
        when(apiService.storeUrl(anyString())).thenReturn(new ResponseEntity<>(anyString(), HttpStatus.OK));

        // when
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/storeurl?url=" + URL)
                .accept(MediaType.APPLICATION_JSON);

        // then
        MvcResult result = mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void getUrl() throws Exception {
        Store store = new Store();
        store.setKey(URL);
        store.setCount(1);

        when(apiService.getUrl(anyString())).thenReturn(new ResponseEntity<>(store, HttpStatus.OK));

        RequestBuilder builder = MockMvcRequestBuilders
                .get("/get?url="+URL)
                .accept(MediaType.APPLICATION_JSON);

        String jsonString = "{\"key\":\"google.com\",\"count\":1}";
        MvcResult result = mockMvc.perform(builder)
                .andExpect(status().isOk())
                .andExpect(content().json(jsonString))
                .andReturn();
    }

    @Test
    public void getCount() throws Exception {

        when(apiService.getCount(anyString())).thenReturn(new ResponseEntity<>(anyInt(), HttpStatus.OK));

        RequestBuilder builder = MockMvcRequestBuilders
                .get("/count?url="+URL)
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(builder)
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void getList() throws Exception {

        List<Store> stores = new ArrayList<>();
        stores.add(new Store("google.com", 1));
        stores.add(new Store("apple.com", 1));

        when(apiService.getList(anyInt(), anyInt())).thenReturn(new ResponseEntity<>(stores, HttpStatus.OK));

        RequestBuilder builder = MockMvcRequestBuilders
                .get("/list")
                .queryParam("page", "1")
                .queryParam("size", "2")
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(builder)
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.store.key").value("google.com"))
                .andReturn();
    }
}
