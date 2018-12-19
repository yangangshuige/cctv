package com.base.library.adapter;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yg on 2016/11/8.
 */

public abstract class CommonListAdapter<T> extends BaseAdapter {
    protected LayoutInflater mInflater;
    protected List<T> mLists = new ArrayList<>();
    protected int mItemLayoutId = 0;
    protected Context mContext;
    public CommonListAdapter(Context context, int itemLayoutId) {
        mInflater = LayoutInflater.from(context);
        this.mItemLayoutId = itemLayoutId;
        this.mContext = context;
    }
    private MultiItemTypeSupport typeSupport;
    @Override
    public int getItemViewType(int position) {
        if (typeSupport != null && mLists.size() != 0) {
            return typeSupport.getItemType(position, mLists.get(position));
        }
        return super.getItemViewType(position);
    }

    public void setTypeSupport(MultiItemTypeSupport typeSupport) {
        this.typeSupport = typeSupport;
    }

    /**
     * 获取当前列表数据
     *
     * @return
     */
    public List<T> getCurData() {
        return mLists;
    }

    /**
     * 设置最新数据
     *
     * @param lists
     */
    public void setData(List<T> lists) {
        mLists.clear();
        this.mLists.addAll(lists);
        notifyDataSetChanged();
    }

    /**
     * 在当前列表的基础上添加新的数据
     *
     * @param lists
     */
    public void addData(List<T> lists) {
        this.mLists.addAll(lists);
        notifyDataSetChanged();
    }

    /**
     * 在某个位置插入一条数据
     *
     * @param ss
     * @param position
     */
    public void addItem(T ss, int position) {
        mLists.add(position, ss);
        notifyDataSetChanged();
    }

    /**
     * 删除一条数据
     *
     * @param position
     */
    public void delItem(int position) {
        mLists.remove(position);
        notifyDataSetChanged();
    }

    /**
     * 删除全部数据
     *
     * @param
     */
    public void delAlldata() {
        mLists.clear();
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mLists.size();
    }

    @Override
    public T getItem(int position) {
        if (position >= mLists.size()) {
            return null;
        }
        return mLists.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = mInflater.inflate(mItemLayoutId, parent,false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        convert(holder, getItem(position), position);
        return convertView;
    }

    public interface MultiItemTypeSupport<T> {
        int getLayoutId(int itemType);

        int getItemType(int position, T t);

    }

    public abstract void convert(ViewHolder holder, T itemBean, int position);

    public class ViewHolder {
        private final SparseArray<View> mViews;//性能优化,使用SparseArray代替HashMap
        public View getmConvertView() {
            return mConvertView;
        }
        private View mConvertView;

        public ViewHolder(View view) {
            this.mViews = new SparseArray<View>();
            this.mConvertView = view;
        }

        /**
         * 通过控件的Id获取对于的控件，如果没有则加入views
         *
         * @param viewId
         * @return
         */
        public <T extends View> T getView(int viewId) {

            View view = mViews.get(viewId);
            if (view == null) {
                view = mConvertView.findViewById(viewId);
                mViews.put(viewId, view);
            }
            return (T) view;
        }

        /**
         * 为TextView设置字符串
         *
         * @param viewId
         * @param text
         * @return
         */
        public ViewHolder setText(int viewId, String text) {
            TextView view = getView(viewId);
            view.setText(text);
            return this;
        }

        public ViewHolder setTextColor(int viewId, int textColor) {
            TextView view = getView(viewId);
            view.setTextColor(textColor);
            return this;
        }

        public ViewHolder setTextColorRes(int viewId, int textColorRes) {
            TextView view = getView(viewId);
            view.setTextColor(mContext.getResources().getColor(textColorRes));
            return this;
        }

    }

}
