package com.dontiny.t.commponents.tsweetdialog.utils;

import android.util.Log;

import com.orhanobut.logger.Logger;
import com.orhanobut.logger.Printer;
import com.orhanobut.logger.Settings;

public class LogUtils {

    public static boolean isDebug = true;

    private final static String APP_TAG = "demoUtils";

    /**
     * 获取相关数据:类名,方法名,行号等.用来定位行<br>
     * at cn.utils.MainActivity.onCreate(MainActivity.java:17) 就是用來定位行的代碼<br>
     *
     * @return [ Thread:main, at
     * cn.utils.MainActivity.onCreate(MainActivity.java:17)]
     */
    private static String getFunctionName() {
        StackTraceElement[] sts = Thread.currentThread().getStackTrace();
        if (sts != null) {
            for (StackTraceElement st : sts) {
                if (st.isNativeMethod()) {
                    continue;
                }
                if (st.getClassName().equals(Thread.class.getName())) {
                    continue;
                }
                if (st.getClassName().equals(LogUtils.class.getName())) {
                    continue;
                }
                return "[ Thread:" + Thread.currentThread().getName() + ", at " + st.getClassName() + "." + st.getMethodName()
                        + "(" + st.getFileName() + ":" + st.getLineNumber() + ")" + " ]";
            }
        }
        return null;
    }

    public static Settings init() {
        return Logger.init();
    }

    public static Printer t(String tag) {
        return Logger.t(tag);
    }

    public static Printer t(int methodCount) {
        return Logger.t(methodCount);
    }

    public static Printer t(String tag, int methodCount) {
        return Logger.t(tag, methodCount);
    }

    public static void d(String msg) {
        if (isDebug) {
            Logger.d(getMsgFormat("dddd" + msg));
        }
    }

    public static void e(String msg) {
        if (isDebug) {
            Log.e(APP_TAG, getMsgFormat(msg));
            Logger.e(getMsgFormat(msg));
        }
    }

    public static void e(Throwable throwable, String msg) {
        if (isDebug) {
            Logger.e(throwable, getMsgFormat(msg));
        }
    }

    public static void i(String msg) {
        if (isDebug) {
            Logger.i("kankan" + getMsgFormat(msg));
            //logQueue.startDownMediaFile(getMsgFormat("kankan" + msg));
        }
    }

    public static void v(String msg) {
        if (isDebug) {
            Logger.v(getMsgFormat(msg));
        }
    }


    public static void w(String msg) {
        if (isDebug) {
            Logger.w(getMsgFormat(msg));
        }
    }


    public static void wtf(String msg) {
        if (isDebug) {
            Logger.wtf(getMsgFormat(msg));

        }
    }

    public static void json(String json) {
        if (isDebug) {
            Logger.json(json);
        }
    }

    public static void xml(String xml) {

    }

    /**
     * 输出格式定义
     */
    private static String getMsgFormat(String msg) {
        return msg + " ;" + getFunctionName();
    }





}

