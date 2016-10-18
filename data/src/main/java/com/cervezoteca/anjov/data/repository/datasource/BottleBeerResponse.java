package com.cervezoteca.anjov.data.repository.datasource;

import com.cervezoteca.anjov.domain.model.BottleBeer;

import java.util.List;

/**
 * Created by anjov on 18/10/2016.
 */

public class BottleBeerResponse {

    private String count;
    private String next;
    private String previous;
    private List<BottleBeer> results;

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public String getPrevious() {
        return previous;
    }

    public void setPrevious(String previous) {
        this.previous = previous;
    }

    public List<BottleBeer> getResults() {
        return results;
    }

    public void setResults(List<BottleBeer> results) {
        this.results = results;
    }
}
