package com.mysite.demo.dao;


import com.mysite.demo.entity.product;

import java.util.List;


public class CartDao {

    private double sum = 0;
    private List<product> productList;

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }

    public List<product> getProductList() {
        return productList;
    }

    public void setProductList(List<product> productList) {
        this.productList = productList;
    }

    public void calcSum() {
        for (product prod :
                productList) {
            this.sum += prod.getPrice();
        }
    }

}
