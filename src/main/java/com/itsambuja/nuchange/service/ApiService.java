package com.itsambuja.nuchange.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.itsambuja.nuchange.model.Store;

public interface ApiService {

    public ResponseEntity<String> storeUrl(String url);

    public ResponseEntity<Store> getUrl(String url);

    public ResponseEntity<Integer> getCount(String url);

    public ResponseEntity<List<Store>> getCount(int page, int size);
}
