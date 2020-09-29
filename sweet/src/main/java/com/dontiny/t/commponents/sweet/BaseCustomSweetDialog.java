package com.dontiny.t.commponents.sweet;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;

import com.blankj.utilcode.util.ScreenUtils;
import com.google.android.material.bottomsheet.BottomSheetDialog;

/**
 * Author: DonTiny
 * Date: 2020/8/14 0014 14:42
 * Email:tg_0804@163.com
 */
public abstract class BaseCustomSweetDialog extends SweetDialog {

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        if (!isBottomDialog) {
            return super.onCreateDialog(savedInstanceState);
        } else {
            return new BottomSheetDialog(getContext(), getTheme());
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // 隐藏标题栏, 不加弹窗上方会一个透明的标题栏占着空间
        //getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        // 必须设置这两个,才能设置宽度
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        getDialog().getWindow().getDecorView().setBackgroundColor(Color.TRANSPARENT);
        // 设置宽度
        WindowManager.LayoutParams params = getDialog().getWindow().getAttributes();
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        if (widthRation <= 1 && widthRation >= 0) {
            params.width = (int) (ScreenUtils.getScreenWidth() * widthRation);
        }else {
            params.width = WindowManager.LayoutParams.WRAP_CONTENT;
        }
        //此封装弹窗的显示位置以及尺寸
        //params.gravity = Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL;
        getDialog().getWindow().setAttributes(params);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void setupDialog(Dialog dialog, int style) {
        super.setupDialog(dialog, style);
        if (isBottomDialog) {
            dialog.setContentView(getLayoutResId());
        }
    }

    @Override
    protected boolean modifyAlertDialogBuilder(AlertDialog.Builder builder) {
        if (getLayoutResId() != 0) {
            //设置自定义View
            builder.setView(getLayoutResId());
            return true;
        }
        return super.modifyAlertDialogBuilder(builder);
    }

    @Override
    protected void initAndSetViews(View root) {
        super.initAndSetViews(root);
        initView(root);
        setViews();
    }

    protected abstract int getLayoutResId();

    protected abstract void initView(View view);

    protected abstract void setViews();

}
