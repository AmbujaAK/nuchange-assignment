package com.itsambuja.nuchange.model;

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

class StoreTest {
    Store store;

    @BeforeEach
    void setUp() {
        store = new Store();
    }

    @Test
    void getKey() {
        String url = "google.com";
        store.setKey(url);
        assertEquals(url, store.getKey());
    }

    @Test
    void getCount() {
        store.setCount(1);

        assertEquals(1, store.getCount());
    }
}
