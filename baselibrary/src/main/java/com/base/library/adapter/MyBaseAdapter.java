package com.base.library.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yg on 2016/11/8.
 */
public abstract class MyBaseAdapter<T> extends BaseAdapter {
    public Context mContext;
    public LayoutInflater mInflater;
    public List<T> datas;

    public MyBaseAdapter(Context context) {
        mContext = context;
        mInflater = LayoutInflater.from(mContext);
        datas = new ArrayList<T>();
    }


    public void addDatas(List<T> lists, Boolean isClear) {
        if (isClear) {
            datas.clear();
        }
        if (lists != null) {
            datas.addAll(lists);
            notifyDataSetChanged();
        }
    }

    public void addDatas(T[] lists, Boolean isClear) {
        if (isClear) {
            datas.clear();
        }
        if (lists != null) {
            for (int i = 0; i < lists.length; i++) {
                datas.add(lists[i]);
            }
            notifyDataSetChanged();
        }
    }

    public void addDatas(T t) {

        if (t != null) {
            datas.add(t);
        }
        notifyDataSetChanged();
    }

    public void clear() {
        if (datas != null)
            datas.clear();
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public T getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public abstract View getView(int position, View convertView, ViewGroup parent);
}
