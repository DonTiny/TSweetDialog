package com.dontiny.t.commponents.sweet;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.CallSuper;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;


/**
 * Author: DonTiny
 * Date: 2020/8/12 0012 13:46
 * Email:tg_0804@163.com
 */
public class SweetDialog extends BaseSweetDialog {


    protected DialogInterface.OnClickListener mPositiveListener;

    protected DialogInterface.OnClickListener mNeutralListener;

    protected DialogInterface.OnClickListener mNegativeListener;

    protected DialogInterface.OnClickListener mClickListener;

    protected DialogInterface.OnMultiChoiceClickListener mMultiClickListener;

    protected DialogInterface.OnDismissListener mDismissListener;

    protected DialogInterface.OnCancelListener mCancelListener;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        return super.onCreateDialog(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // 遮罩层透明度
        getDialog().getWindow().setDimAmount(dimAmount);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    protected Dialog createDialog() {
        //用父类装配好的sweetDialogParams参数构建AlertDialog
        SweetDialogParams params = getParams();
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), params.themeResId);
        if (!modifyAlertDialogBuilder(builder)) {
            if (params.mIcon != null) {
                builder.setIcon(params.mIcon);
            }
            if (params.mIconId != 0) {
                builder.setIcon(params.mIconId);
            }
            builder.setTitle(params.mTitle);
            builder.setPositiveButtonIcon(params.mPositiveButtonIcon);
            builder.setPositiveButton(params.mPositiveButtonText, mPositiveListener);
            builder.setNegativeButtonIcon(params.mNegativeButtonIcon);
            builder.setNegativeButton(params.mNegativeButtonText, mNegativeListener);
            builder.setNeutralButtonIcon(params.mNeutralButtonIcon);
            builder.setNeutralButton(params.mNeutralButtonText, mNeutralListener);
            if (params.mItems != null) {
                if (params.mIsMultiChoice) {
                    builder.setMultiChoiceItems(params.mItems, params.mCheckedItems, mMultiClickListener);
                } else if (params.mIsSingleChoice) {
                    builder.setSingleChoiceItems(params.mItems, params.mCheckedItem, mClickListener);
                } else {
                    builder.setItems(params.mItems, mClickListener);
                }
            }
        }
        builder.setCancelable(params.mCancelable);
        return builder.create();
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {

    }

    //子类可继承该方法修改AlertDialog.Builder设置的参数,且必须调用父类方法

    /**
     * @param builder
     * @return 是否是自定义视图
     */
    @CallSuper
    protected boolean modifyAlertDialogBuilder(AlertDialog.Builder builder) {

        return false;
    }

    @Override
    protected void initAndSetViews(View root) {

    }

    public static class Builder extends BaseSweetDialog.BaseBuilder<Builder> {

        public Builder(@NonNull Context context) {
            super(context);
        }

        public Builder(@NonNull Context context, int themeResId) {
            super(context, themeResId);
        }

        @Override
        protected SweetDialog createBuilderDialog() {
            return new SweetDialog();
        }
    }


    /**
     * Activity销毁重建导致事件丢失可以在Activity的onCreate(Bundle savedInstanceState)方法中通过
     * getSupportFragmentManager().findFragmentByTag("")获取DialogFragment实例调用此类方法进行事件恢复
     *
     * @param mPositiveListener
     * @param mNeutralListener
     * @param mNegativeListener
     * @param mClickListener
     * @param mMultiClickListener
     * @param mDismissListener
     * @param mCancelListener
     */
    public void onRestoreInstanceStateListener(DialogInterface.OnClickListener mPositiveListener, DialogInterface.OnClickListener mNeutralListener, DialogInterface.OnClickListener mNegativeListener, DialogInterface.OnClickListener mClickListener, DialogInterface.OnMultiChoiceClickListener mMultiClickListener, DialogInterface.OnDismissListener mDismissListener, DialogInterface.OnCancelListener mCancelListener) {
        this.mPositiveListener = mPositiveListener;
        this.mNeutralListener = mNeutralListener;
        this.mNegativeListener = mNegativeListener;
        this.mClickListener = mClickListener;
        this.mMultiClickListener = mMultiClickListener;
        this.mDismissListener = mDismissListener;
        this.mCancelListener = mCancelListener;
    }

}
