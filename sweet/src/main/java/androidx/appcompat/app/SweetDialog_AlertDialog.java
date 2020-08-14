package androidx.appcompat.app;

import android.content.Context;

import androidx.annotation.StyleRes;

/**
 * Author: DonTiny
 * Date: 2020/8/12 0012 15:14
 * Email:tg_0804@163.com
 */
public class SweetDialog_AlertDialog {
    public static int resolveDialogTheme(Context context, @StyleRes int resId) {
        return AlertDialog.resolveDialogTheme(context, resId);
    }

}
