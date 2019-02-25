package com.jmateos.mateos_javier_aplicacioninmo.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Property {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("ownerId")
    @Expose
    private String ownerId;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("price")
    @Expose
    private int price;
    @SerializedName("rooms")
    @Expose
    private int rooms;
    @SerializedName("size")
    @Expose
    private int size;
    @SerializedName("categoryId")
    @Expose
    private String categoryId;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("zipcode")
    @Expose
    private String zipcode;
    @SerializedName("city")
    @Expose
    private String city;
    @SerializedName("province")
    @Expose
    private String province;
    @SerializedName("loc")
    @Expose
    private String loc;


    public Property(String id, String ownerId, String title, String description, int price, int rooms, int size, String categoryId, String address, String zipcode, String city, String province, String loc) {
        this.id = id;
        this.ownerId = ownerId;
        this.title = title;
        this.description = description;
        this.price = price;
        this.rooms = rooms;
        this.size = size;
        this.categoryId = categoryId;
        this.address = address;
        this.zipcode = zipcode;
        this.city = city;
        this.province = province;
        this.loc = loc;
    }

    public String getId() {
        return id;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getPrice() {
        return price;
    }

    public int getRooms() {
        return rooms;
    }

    public int getSize() {
        return size;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public String getAddress() {
        return address;
    }

    public String getZipcode() {
        return zipcode;
    }

    public String getCity() {
        return city;
    }

    public String getProvince() {
        return province;
    }

    public String getLoc() {
        return loc;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setRooms(int rooms) {
        this.rooms = rooms;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public void setLoc(String loc) {
        this.loc = loc;
    }

    @Override
    public String toString() {
        return "Property{" +
                "id='" + id + '\'' +
                ", ownerId='" + ownerId + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", rooms=" + rooms +
                ", size=" + size +
                ", categoryId='" + categoryId + '\'' +
                ", address='" + address + '\'' +
                ", zipcode='" + zipcode + '\'' +
                ", city='" + city + '\'' +
                ", province='" + province + '\'' +
                ", loc='" + loc + '\'' +
                '}';
    }
}
