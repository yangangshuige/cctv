
package com.jovision.mobbroadcast.time;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import android.view.View;

import com.todayin.cctv.R;

public class WheelMain {

    private View view;
    private WheelView wv_year;
    private WheelView wv_month;
    private WheelView wv_day;
    private WheelView wv_hours;
    private WheelView wv_mins;
    private WheelView wv_secs;
    /**
     * 0: 骞存�?��ユ椂鍒�
     * 1锛�骞存�?���?     * 2锛�骞�
     * */
    private int iTime;

    private static int START_YEAR = 1990, END_YEAR = 2100;

    public View getView() {
        return view;
    }

    public void setView(View view) {
        this.view = view;
    }

    public static int getSTART_YEAR() {
        return START_YEAR;
    }

    public static void setSTART_YEAR(int sTART_YEAR) {
        START_YEAR = sTART_YEAR;
    }

    public static int getEND_YEAR() {
        return END_YEAR;
    }

    public static void setEND_YEAR(int eND_YEAR) {
        END_YEAR = eND_YEAR;
    }

    public WheelMain(View view) {
        super();
        this.view = view;
        iTime = 0;
        setView(view);
    }

    public WheelMain(View view, int iTime) {
        super();
        this.view = view;
        this.iTime = iTime;
        setView(view);
    }

    public void initDateTimePicker() {
        Calendar calendar = Calendar.getInstance();

        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int min = calendar.get(Calendar.MINUTE);
        int sec = calendar.get(Calendar.SECOND);

        initDateTimePicker(year, month, day, hour, min,sec);
    }

    public void initDateTimePicker(int year, int month, int day) {
        this.initDateTimePicker(year, month, day, 0, 0,0);
    }

    /**
     * @Description: TODO 寮瑰嚭鏃ユ湡鏃堕棿閫夋嫨鍣�
     */
    public void initDateTimePicker(int year, int month, int day, int h, int m,int sec) {
        // 娣诲姞澶у皬鏈堟湀浠藉苟灏嗗叾杞崲涓簂ist,鏂�?究涔嬪悗鐨勫垽鏂�?
        String[] months_big = {
                "1", "3", "5", "7", "8", "10", "12"
        };
        String[] months_little = {
                "4", "6", "9", "11"
        };

        final List<String> list_big = Arrays.asList(months_big);
        final List<String> list_little = Arrays.asList(months_little);

        wv_year = (WheelView) view.findViewById(R.id.year);
        wv_month = (WheelView) view.findViewById(R.id.month);
        wv_day = (WheelView) view.findViewById(R.id.day);
        wv_hours = (WheelView) view.findViewById(R.id.hour);
        wv_mins = (WheelView) view.findViewById(R.id.min);
        wv_secs = (WheelView) view.findViewById(R.id.sec);

        // 娣诲�?骞�鐩戝�?
        OnWheelChangedListener wheelListener_year = new OnWheelChangedListener() {
            public void onChanged(WheelView wheel, int oldValue, int newValue) {
                int year_num = newValue + START_YEAR;
                // 鍒ゆ柇澶у皬鏈堝強鏄惁闂板勾,鐢ㄦ潵纭畾"鏃�鐨勬暟鎹�?
                if (list_big.contains(String.valueOf(wv_month.getCurrentItem() + 1))) {
                    wv_day.setAdapter(new NumericWheelAdapter(1, 31));
                } else if (list_little.contains(String.valueOf(wv_month
                        .getCurrentItem() + 1))) {
                    wv_day.setAdapter(new NumericWheelAdapter(1, 30));
                } else {
                    if ((year_num % 4 == 0 && year_num % 100 != 0)
                            || year_num % 400 == 0)
                        wv_day.setAdapter(new NumericWheelAdapter(1, 29));
                    else
                        wv_day.setAdapter(new NumericWheelAdapter(1, 28));
                }
            }
        };

        // 娣诲�?鏈�鐩戝�?
        OnWheelChangedListener wheelListener_month = new OnWheelChangedListener() {
            public void onChanged(WheelView wheel, int oldValue, int newValue) {
                int month_num = newValue + 1;
                // 鍒ゆ柇澶у皬鏈堝強鏄惁闂板勾,鐢ㄦ潵纭畾"鏃�鐨勬暟鎹�?
                if (list_big.contains(String.valueOf(month_num))) {
                    wv_day.setAdapter(new NumericWheelAdapter(1, 31));
                } else if (list_little.contains(String.valueOf(month_num))) {
                    wv_day.setAdapter(new NumericWheelAdapter(1, 30));
                } else {
                    if (((wv_year.getCurrentItem() + START_YEAR) % 4 == 0 && (wv_year
                            .getCurrentItem() + START_YEAR) % 100 != 0)
                            || (wv_year.getCurrentItem() + START_YEAR) % 400 == 0)
                        wv_day.setAdapter(new NumericWheelAdapter(1, 29));
                    else
                        wv_day.setAdapter(new NumericWheelAdapter(1, 28));
                }
            }
        };

        if (iTime == 2) {
            wv_day.setVisibility(View.GONE);
            wv_month.setVisibility(View.GONE);
            wv_hours.setVisibility(View.GONE);
            wv_mins.setVisibility(View.GONE);
            wv_secs.setVisibility(View.GONE);

            // 骞�
            wv_year.setAdapter(new NumericWheelAdapter(START_YEAR, END_YEAR));// 璁剧�?骞�鐨勬樉绀烘暟鎹�
            wv_year.setCyclic(true);// 鍙惊鐜粴鍔�
            // wv_year.setLabel("骞�);// 娣诲姞鏂囧瓧
            wv_year.setCurrentItem(year - START_YEAR);// 鍒濆鍖栨椂鏄剧ず鐨勬暟鎹�
        } else if (iTime == 0) {
            // 骞�
            wv_year.setAdapter(new NumericWheelAdapter(START_YEAR, END_YEAR));// 璁剧�?骞�鐨勬樉绀烘暟鎹�
            wv_year.setCyclic(true);// 鍙惊鐜粴鍔�
            // wv_year.setLabel("骞�);// 娣诲姞鏂囧瓧
            wv_year.setCurrentItem(year - START_YEAR);// 鍒濆鍖栨椂鏄剧ず鐨勬暟鎹�

            // 鏈�
            wv_month.setAdapter(new NumericWheelAdapter(1, 12));
            wv_month.setCyclic(true);
            // wv_month.setLabel("鏈�);
            wv_month.setCurrentItem(month);

            // 鏃�
            wv_day.setCyclic(true);
            // 鍒ゆ柇澶у皬鏈堝強鏄惁闂板勾,鐢ㄦ潵纭畾"鏃�鐨勬暟鎹�?
            if (list_big.contains(String.valueOf(month + 1))) {
                wv_day.setAdapter(new NumericWheelAdapter(1, 31));
            } else if (list_little.contains(String.valueOf(month + 1))) {
                wv_day.setAdapter(new NumericWheelAdapter(1, 30));
            } else {
                // 闂板�?
                if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0)
                    wv_day.setAdapter(new NumericWheelAdapter(1, 29));
                else
                    wv_day.setAdapter(new NumericWheelAdapter(1, 28));
            }
            // wv_day.setLabel("鏃�);
            wv_day.setCurrentItem(day - 1);

            wv_hours.setAdapter(new NumericWheelAdapter(0, 23));
            wv_hours.setCyclic(true);// 鍙惊鐜粴鍔�
            // wv_hours.setLabel("鏃�);// 娣诲姞鏂囧瓧
            wv_hours.setCurrentItem(h);

            wv_mins.setAdapter(new NumericWheelAdapter(0, 59));
            wv_mins.setCyclic(true);// 鍙惊鐜粴鍔�
            // wv_mins.setLabel("鍒�);// 娣诲姞鏂囧瓧
            wv_mins.setCurrentItem(m);

            wv_secs.setAdapter(new NumericWheelAdapter(0, 59));
            wv_secs.setCyclic(true);// 鍙惊鐜粴鍔�
            // wv_mins.setLabel("鍒�);// 娣诲姞鏂囧瓧
            wv_secs.setCurrentItem(sec);

            wv_year.addChangingListener(wheelListener_year);
            wv_month.addChangingListener(wheelListener_month);
        } else if (iTime == 1) {
            wv_hours.setVisibility(View.GONE);
            wv_mins.setVisibility(View.GONE);
            wv_secs.setVisibility(View.GONE);

            // 骞�
            wv_year.setAdapter(new NumericWheelAdapter(START_YEAR, END_YEAR));// 璁剧�?骞�鐨勬樉绀烘暟鎹�
            wv_year.setCyclic(true);// 鍙惊鐜粴鍔�
            wv_year.setLabel("��");// 娣诲姞鏂囧瓧
            wv_year.setCurrentItem(year - START_YEAR);// 鍒濆鍖栨椂鏄剧ず鐨勬暟鎹�

            // 鏈�
            wv_month.setAdapter(new NumericWheelAdapter(1, 12));
            wv_month.setCyclic(true);
            wv_month.setLabel("��");
            wv_month.setCurrentItem(month);

            // 鏃�
            wv_day.setCyclic(true);
            // 鍒ゆ柇澶у皬鏈堝強鏄惁闂板勾,鐢ㄦ潵纭畾"鏃�鐨勬暟鎹�?
            if (list_big.contains(String.valueOf(month + 1))) {
                wv_day.setAdapter(new NumericWheelAdapter(1, 31));
            } else if (list_little.contains(String.valueOf(month + 1))) {
                wv_day.setAdapter(new NumericWheelAdapter(1, 30));
            } else {
                // 闂板�?
                if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0)
                    wv_day.setAdapter(new NumericWheelAdapter(1, 29));
                else
                    wv_day.setAdapter(new NumericWheelAdapter(1, 28));
            }
            wv_day.setLabel("��");
            wv_day.setCurrentItem(day - 1);

            wv_year.addChangingListener(wheelListener_year);
            wv_month.addChangingListener(wheelListener_month);
        }

        // 鏍规嵁灞忓箷瀵嗗害鏉ユ寚瀹氶�鎷╁櫒瀛椾綋鐨勫ぇ灏�涓嶅悓灞忓箷鍙兘涓嶅悓)
        int textSize = 14;
        // if (iTime == 0)
        // textSize = (screenheight / 100) * 2;
        // else if (iTime == 1)
        // textSize = (screenheight / 100) * 4;
        // else if (iTime == 2)
        // textSize = (screenheight / 100) * 6;

        wv_day.TEXT_SIZE = textSize;
        wv_month.TEXT_SIZE = textSize;
        wv_year.TEXT_SIZE = textSize;
        wv_hours.TEXT_SIZE = textSize;
        wv_mins.TEXT_SIZE = textSize;
        wv_secs.TEXT_SIZE = textSize;
    }

    public String getTime() {
        StringBuffer sb = new StringBuffer();
        if (iTime == 1) {
            sb.append((wv_year.getCurrentItem() + START_YEAR)).append("-")
                    .append((wv_month.getCurrentItem() + 1)).append("-")
                    .append((wv_day.getCurrentItem() + 1));
        } else if (iTime == 0) {
            sb.append((wv_year.getCurrentItem() + START_YEAR)).append("-")
                    .append(lpad(wv_month.getCurrentItem() + 1)).append("-")
                    .append(lpad(wv_day.getCurrentItem() + 1)).append(" ")
                    .append(lpad(wv_hours.getCurrentItem())).append(":")
                    .append(lpad(wv_mins.getCurrentItem())).append(":")
                    .append(lpad(wv_secs.getCurrentItem()));
        } else if (iTime == 2) {
            sb.append((wv_year.getCurrentItem() + START_YEAR));
        }
        return sb.toString();
    }

    /**
     * 琛ラ綈涓嶈冻�?���?
     *
     * @param number 鏁板�?
     * @return
     */
    private String lpad(int number) {
        return lpad(2, number);
    }

    /**
     * 琛ラ綈涓嶈冻�?���?
     *
     * @param length �?���?
     * @param number 鏁板�?
     * @return
     */
    private String lpad(int length, int number) {
        String f = "%0" + length + "d";
        return String.format(f, number);
    }
}
