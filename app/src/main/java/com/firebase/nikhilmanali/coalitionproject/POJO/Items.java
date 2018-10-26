package com.firebase.nikhilmanali.coalitionproject.POJO;

/**
 * Created by Nikhil Manali on 10/24/2018.
 */

public class Items {

    private String description;
    private String price;
    private String product_name;
    private String image;
    private int id;


   public Items(int id,String description,String price,String product_name,String image){
        this.id=id;
        this.description=description;
        this.price=price;
        this.product_name=product_name;
        this.image=image;
    }

     public Items(){

     }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }


}
