package com.itsambuja.nuchange.service;

import com.itsambuja.nuchange.model.Store;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApiServiceImpl implements  ApiService {
    @Override
    public ResponseEntity<String> storeUrl(String url) {
        return null;
    }

    @Override
    public ResponseEntity<Store> getUrl(String url) {
        return null;
    }

    @Override
    public ResponseEntity<Integer> getCount(String url) {
        return null;
    }

    @Override
    public ResponseEntity<List<Store>> getCount(int page, int size) {
        return null;
    }
}
