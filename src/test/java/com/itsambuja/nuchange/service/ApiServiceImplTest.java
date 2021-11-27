package com.itsambuja.nuchange.service;

import com.itsambuja.nuchange.model.Store;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ApiServiceImplTest {

    @MockBean
    ApiServiceImpl apiService;

    public List<Store> localStorage;

    @Mock
    Store store;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(ApiServiceImpl.class);
        store = new Store("google.com", 4);
        localStorage = new ArrayList<>();
        apiService = new ApiServiceImpl();
    }

    @Test
    void storeUrl() {
        String url = "apple.com";

        localStorage.add(new Store(url, 1));

        assertEquals(localStorage.size(), 1);
    }

    @Test
    void getUrl() {
        String url = "apple.com";

        Store store = new Store(url, 1);

        assertEquals(store.getKey(), url);
        assertEquals(store.getCount(), 1);

    }

    @Test
    void getCount() {
        String url = "apple.com";

        Store store = new Store(url, 1);

        assertEquals(store.getCount(), 1);
    }

    @Test
    void getList() {
        localStorage.add(new Store("apple.com", 1));
        localStorage.add(new Store("google.com", 3));

        assertEquals( 2,localStorage.size());
    }
}