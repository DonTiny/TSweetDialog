package com.dontiny.t.commponents.sweet;

import android.graphics.drawable.Drawable;

import java.io.Serializable;

/**
 * Author: DonTiny
 * Date: 2020/8/12 0012 11:14
 * Email:tg_0804@163.com
 */
public class SweetDialogParams implements Serializable {
    //主题资源id
    public int themeResId;
    //图标id
    public int mIconId = 0;
    //图标Drawable
    public Drawable mIcon;
    //标题
    public CharSequence mTitle;
    //消息文本
    public CharSequence mMessage;
    //正按钮文本
    public CharSequence mPositiveButtonText;
    //正按钮图标
    public Drawable mPositiveButtonIcon;
    //负按钮文本
    public CharSequence mNegativeButtonText;
    //负按钮图标
    public Drawable mNegativeButtonIcon;
    //中立按钮文本
    public CharSequence mNeutralButtonText;
    //中立按钮图标
    public Drawable mNeutralButtonIcon;
    //返回键是否关闭弹窗
    public boolean mCancelable;
    //列表项文本
    public CharSequence[] mItems;
    //列表项选择状态，对应多选
    public boolean[] mCheckedItems;
    //是否是多选
    public boolean mIsMultiChoice;
    //是否是单选
    public boolean mIsSingleChoice;
    //选择的项,对应单选
    public int mCheckedItem = -1;
}
