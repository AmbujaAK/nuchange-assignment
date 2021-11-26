package com.itsambuja.nuchange.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.itsambuja.nuchange.model.Store;
import com.itsambuja.nuchange.util.Helper;

@Service
public class ApiServiceImpl implements ApiService {
    public static List<Store> localStorage = new ArrayList<Store>();

    public ResponseEntity<String> storeUrl(String url) {
        boolean isNew = true;

        try {
            for (Store st : localStorage) {
                if (Helper.decrypt(st.getKey()).equals(url)) {
                    st.setCount(st.getCount() + 1);
                    isNew = false;
                }
            }

            if (isNew) {
                localStorage.add(new Store(Helper.encrypt(url), 1));
            }
        } catch (Exception e) {
            return new ResponseEntity<String>("Some Exception", HttpStatus.OK);
        }
        return new ResponseEntity<String>("Success", HttpStatus.OK);
    }

    public ResponseEntity<Store> getUrl(String url) {

        for (Store st : localStorage) {
            if (Helper.decrypt(st.getKey()).equals(url)) {
                return new ResponseEntity<Store>(st, HttpStatus.OK);
            }
        }
        return new ResponseEntity<Store>(new Store(), HttpStatus.OK);
    }

    public ResponseEntity<Integer> getCount(String url) {

        for (Store st : localStorage) {
            if (Helper.decrypt(st.getKey()).equals(url)) {
                return new ResponseEntity<Integer>(st.getCount(), HttpStatus.OK);
            }
        }
        return new ResponseEntity<Integer>(0, HttpStatus.OK);
    }

    public ResponseEntity<List<Store>> getList(int page, int size) {

        List<Store> data = new ArrayList<Store>();

        int start = size * (page -1);
        int end = size * page;
        // return empty data if start > end	or
        if( start < 0 || end <= 0 || start >= end)
            return new ResponseEntity<List<Store>>(data, HttpStatus.OK);

        // return empty if start or end is greater than size of local storage current list
        for(int i=start; i<end; i++) {
            if(start > localStorage.size())
                break;
            if(i < localStorage.size())
                data.add(localStorage.get(i));
        }
        return new ResponseEntity<List<Store>>(data, HttpStatus.OK);

    }
}
