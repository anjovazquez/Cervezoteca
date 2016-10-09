package com.cervezoteca.anjov.domain.model;

/**
 * Created by anjov on 07/10/2016.
 */

public class TapBeer {

    private String id;
    private Beer beer;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Beer getBeer() {
        return beer;
    }

    public void setBeer(Beer beer) {
        this.beer = beer;
    }
}
