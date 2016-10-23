package com.cervezoteca.anjov.domain.model;

import java.io.Serializable;

/**
 * Created by anjov on 07/10/2016.
 */

public class BottleBeer implements Serializable {

    private String id;
    private String name;
    private String logo;
    private String abv;
    private String ibu;
    private String slug;
    private String style_name;
    private String stock_status;
    private String bar_price;
    private String shop_price;
    private String on_fridge;
    private String description;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getAbv() {
        return abv;
    }

    public void setAbv(String abv) {
        this.abv = abv;
    }

    public String getIbu() {
        return ibu;
    }

    public void setIbu(String ibu) {
        this.ibu = ibu;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getStyle_name() {
        return style_name;
    }

    public void setStyle_name(String style_name) {
        this.style_name = style_name;
    }

    public String getStock_status() {
        return stock_status;
    }

    public void setStock_status(String stock_status) {
        this.stock_status = stock_status;
    }

    public String getBar_price() {
        return bar_price;
    }

    public void setBar_price(String bar_price) {
        this.bar_price = bar_price;
    }

    public String getShop_price() {
        return shop_price;
    }

    public void setShop_price(String shop_price) {
        this.shop_price = shop_price;
    }

    public String getOn_fridge() {
        return on_fridge;
    }

    public void setOn_fridge(String on_fridge) {
        this.on_fridge = on_fridge;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
