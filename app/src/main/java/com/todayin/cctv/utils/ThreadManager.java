package com.todayin.cctv.utils;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by yg on 2018/12/18.
 */

public class ThreadManager {
    private static final String TAG = "ThreadManager";

    private static ScheduledThreadPoolExecutor mThreadPool = new ScheduledThreadPoolExecutor(24);
    private static List<String> strings = new ArrayList<String>();

    public static void execute(final Runnable runnable) {
        executeDelay(runnable, 0);
    }

    public static void executeDelay(final Runnable runnable, long delay) {
        long size = mThreadPool.getTaskCount() - mThreadPool.getCompletedTaskCount();
        Log.i(TAG, "executeDelay " + delay + " size=" + size);
        final Runnable run = new Runnable() {
            @Override
            public void run() {
                if (strings.size() > 0) {
                    printfStrs();
                }
                strings.add(runnable.getClass().toString());
                //  Log.i(TAG, "runnable " + runnable.getClass());
                try {
                    runnable.run();
                } catch (Throwable throwable) {
                    Log.e(TAG, "thread error");
                    throwable.printStackTrace();
                }
                //   Log.i(TAG, "runnable end " + runnable.getClass());
                strings.remove(runnable.getClass().toString());
            }
        };
        mThreadPool.schedule(run, delay, TimeUnit.MILLISECONDS);
    }

    private static void printfStrs() {
        StringBuilder stringBuilder = new StringBuilder();
        for (String s : strings) {
            stringBuilder.append(s + ",");
        }
        Log.e(TAG, stringBuilder.toString());
    }

    public static void excuteDelayAtRate(Runnable runnable, long delay, long period) {
        Log.i(TAG, "excuteDelayAtRate");
        mThreadPool.scheduleAtFixedRate(runnable, delay, period, TimeUnit.MILLISECONDS);
    }

    public static void releasePool() {
        Log.i(TAG, "releasePool");
        mThreadPool.shutdown();
    }
}
