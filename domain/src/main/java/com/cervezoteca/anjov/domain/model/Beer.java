package com.cervezoteca.anjov.domain.model;

/**
 * Created by anjov on 07/10/2016.
 */

public class Beer {

    private String id;
    private String name;
    private String logo;
    private String brewery_name;
    private String brewery_country;
    private String  abv;
    private String  short_price;
    private String glass_price;
    private String pint_price;
    private String style_name;
    private String hex_code;
    private String slug;
    private String glass;

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

    public String getBrewery_name() {
        return brewery_name;
    }

    public void setBrewery_name(String brewery_name) {
        this.brewery_name = brewery_name;
    }

    public String getBrewery_country() {
        return brewery_country;
    }

    public void setBrewery_country(String brewery_country) {
        this.brewery_country = brewery_country;
    }

    public String getAbv() {
        return abv;
    }

    public void setAbv(String abv) {
        this.abv = abv;
    }

    public String getShort_price() {
        return short_price;
    }

    public void setShort_price(String short_price) {
        this.short_price = short_price;
    }

    public String getGlass_price() {
        return glass_price;
    }

    public void setGlass_price(String glass_price) {
        this.glass_price = glass_price;
    }

    public String getPint_price() {
        return pint_price;
    }

    public void setPint_price(String pint_price) {
        this.pint_price = pint_price;
    }

    public String getStyle_name() {
        return style_name;
    }

    public void setStyle_name(String style_name) {
        this.style_name = style_name;
    }

    public String getHex_code() {
        return hex_code;
    }

    public void setHex_code(String hex_code) {
        this.hex_code = hex_code;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getGlass() {
        return glass;
    }

    public void setGlass(String glass) {
        this.glass = glass;
    }
}
