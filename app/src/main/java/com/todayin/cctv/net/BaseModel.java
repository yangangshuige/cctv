package com.todayin.cctv.net;

import com.google.gson.GsonBuilder;

import java.io.Serializable;

public class BaseModel implements Serializable {

    public String toJson(){
        return new GsonBuilder().create().toJson(this);
    }
}
