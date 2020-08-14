package com.dontiny.t.commponents.sweet;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StyleRes;

import com.dontiny.t.commponents.sweet.custom.BottomDialog;

/**
 * Author: DonTiny
 * Date: 2020/8/14 0014 16:34
 * Email:tg_0804@163.com
 */
public class SweetDialogManager {



    public static SweetDialog.Builder bottomBuilder(@NonNull Context context) {
        return bottomBuilder(context, 0);
    }

    public static SweetDialog.Builder bottomBuilder(@NonNull Context context, @StyleRes int themeResId) {
        return createBuilder(context, themeResId, BottomDialog.class);
    }


    /**
     * 直接创建一个SweetDialog.Builder实例
     *
     * @param context
     * @return
     */
    public static SweetDialog.Builder sweetBuilder(@NonNull Context context) {
        return sweetBuilder(context, 0);
    }

    public static SweetDialog.Builder sweetBuilder(@NonNull Context context, @StyleRes int themeResId) {
        return createBuilder(context, themeResId, null);
    }

    /**
     * 通过反射去创建一个对应的Builder实例，则子类不再需要实现BaseBuilder可创建自身Builder实例
     *
     * @param context
     * @return
     */
    public static <Dialog extends SweetDialog> SweetDialog.Builder createBuilder(@NonNull Context context, final Class<Dialog> clz) {
        return createBuilder(context, 0, clz);
    }

    public static <Dialog extends SweetDialog> SweetDialog.Builder createBuilder(@NonNull Context context, @StyleRes int themeResId, final Class<Dialog> clz) {
        if (themeResId == 0) {
            return new SweetDialog.Builder(context) {
                @Override
                protected SweetDialog createBuilderDialog() {
                    return getEasyDialog(clz);
                }
            };
        } else {
            return new SweetDialog.Builder(context, themeResId) {
                @NonNull
                @Override
                protected SweetDialog createBuilderDialog() {
                    return getEasyDialog(clz);
                }
            };
        }
    }

    // 如果有class对象，那么则反射出一个实例
    @Nullable
    private static <Dialog extends SweetDialog> SweetDialog getEasyDialog(Class<Dialog> clz) {
        if (clz == null) {
            return new SweetDialog();
        }
        Dialog dialog = null;
        try {
            dialog = clz.newInstance();
        } catch (java.lang.InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return dialog;
    }
}
