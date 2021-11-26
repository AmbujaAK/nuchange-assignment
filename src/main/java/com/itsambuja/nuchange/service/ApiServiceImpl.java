package com.itsambuja.nuchange.service;

import com.itsambuja.nuchange.model.Store;
import com.itsambuja.nuchange.util.Helper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ApiServiceImpl implements  ApiService {
    public static List<Store> localStorage = new ArrayList<Store>();

    @Override
    public ResponseEntity<String> storeUrl(String url) {
        boolean isNew = true;

        try {
            for (Store st : localStorage) {
                if (st.getKey().equals(url)) {
                    st.setCount(st.getCount() + 1);
                    isNew = false;
                }
            }

            if (isNew) {
                localStorage.add(new Store(url, 1));
            }
        } catch (Exception e) {
            return new ResponseEntity<String>("Some Exception", HttpStatus.OK);
        }
        return new ResponseEntity<String>("Success", HttpStatus.OK);
    }
    
    @Override
    public ResponseEntity<Store> getUrl(String url) {
        for (Store st : localStorage) {
            if (st.getKey().equals(url)) {
                return new ResponseEntity<Store>(st, HttpStatus.OK);
            }
        }
        return new ResponseEntity<Store>(new Store(), HttpStatus.OK);
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
