package com.springapp.model;

import org.springframework.stereotype.Component;

@Component
public class Search {
    private String searchRow;

    public String getSearchRow() {
        return searchRow;
    }
    public void setSearchRow(String searchRow) {
        this.searchRow = searchRow;
    }
}
