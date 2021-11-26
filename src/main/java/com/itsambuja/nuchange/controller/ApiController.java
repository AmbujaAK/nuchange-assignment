package com.itsambuja.nuchange.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.itsambuja.nuchange.model.Store;
import com.itsambuja.nuchange.service.ApiService;

@RestController
public class ApiController {

    @Autowired
    private ApiService apiService;

    @PostMapping("/storeurl")
    public ResponseEntity<String> storeUrl(@RequestParam(value = "url") String url) {
        return this.apiService.storeUrl(url);
    }

    @GetMapping("/get")
    public ResponseEntity<Store> getUrl(@RequestParam(value = "url") String url) {
        return this.apiService.getUrl(url);
    }

    @GetMapping("/count")
    public ResponseEntity<Integer> getCount(@RequestParam(value = "url") String url) {
        return this.apiService.getCount(url);
    }

    @GetMapping("/list")
    public ResponseEntity<List<Store>> getList(@RequestParam(value = "page") int page,
                                               @RequestParam(value = "size") int size) {
        return this.apiService.getCount(page, size);
    }
}
