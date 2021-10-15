package com.codingwithmitch.mvvmrecyclerview.models;

public class CountryModel {

    private String cName;
    private String cImageUrl;
    private String cPresident;
    private String cPopulartion;

    public CountryModel(String cName, String cImageUrl, String cPresident, String cPopulartion) {
        this.cName = cName;
        this.cImageUrl = cImageUrl;
        this.cPresident = cPresident;
        this.cPopulartion = cPopulartion;
    }

    public CountryModel() {
    }

    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

    public String getcImageUrl() {
        return cImageUrl;
    }

    public void setcImageUrl(String cImageUrl) {
        this.cImageUrl = cImageUrl;
    }

    public String getcPresident() {
        return cPresident;
    }

    public void setcPresident(String cPresident) {
        this.cPresident = cPresident;
    }

    public String getcPopulartion() {
        return cPopulartion;
    }

    public void setcPopulartion(String cPopulartion) {
        this.cPopulartion = cPopulartion;
    }
}
