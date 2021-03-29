package com.longkai.canteenorderingsystem.pojo;

public class ProductInfo {
    private int id;
    private String code;
    private String name;
    private Type type;
    private String pic;
    private int num;
    private double price;
    private String intro;
    private int status;
    private double priceFrom;
    private double priceTo;
    private Canteen whichCanteens;
    private int whichday;

    public Canteen getWhichCanteens() {
        return whichCanteens;
    }

    public void setWhichCanteens(Canteen whichCanteens) {
        this.whichCanteens = whichCanteens;
    }

    public int getWhichday() {
        return whichday;
    }

    public void setWhichday(int whichday) {
        this.whichday = whichday;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(Type type) {
        this.type = type;
    }


    public void setPic(String pic) {
        this.pic = pic;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setPriceFrom(double priceFrom) {
        this.priceFrom = priceFrom;
    }

    public void setPriceTo(double priceTo) {
        this.priceTo = priceTo;
    }

    public int getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public Type getType() {
        return type;
    }

    public String getPic() {
        return pic;
    }

    public int getNum() {
        return num;
    }

    public double getPrice() {
        return price;
    }

    public String getIntro() {
        return intro;
    }

    public int getStatus() {
        return status;
    }

    public double getPriceFrom() {
        return priceFrom;
    }

    public double getPriceTo() {
        return priceTo;
    }
}

