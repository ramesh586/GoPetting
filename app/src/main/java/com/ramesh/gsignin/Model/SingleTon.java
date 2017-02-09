package com.ramesh.gsignin.Model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by thrymr on 9/2/17.
 */

public class SingleTon {
    private static SingleTon mInstance;

    private List<Data> cartList;
    public static SingleTon getmInstance() {
        if(mInstance==null)
            mInstance=new SingleTon();
        return mInstance;
    }

    public List<Data> getCartList() {
        if(cartList==null){
            cartList=new ArrayList<>();
        }
        return cartList;
    }

    public void setCartList(List<Data> cartList) {
        this.cartList = cartList;
    }
}
