package com.todayin.cctv.net;

import java.util.List;

/**
 * Created by yg on 2019/2/21.
 */

public class ListResponse<T> {
    private int count;
    private List<T>list;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}
