package com.dontiny.t.commponents.sweet;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;

import com.dontiny.t.commponents.sweet.SweetDialog;
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

    @Override
    public void setupDialog(Dialog dialog, int style) {
        super.setupDialog(dialog, style);
        if (isBottomDialog) {
            dialog.setContentView(getLayoutResId());
        }
    }

    @Override
    protected void modifyAlertDialogBuilder(AlertDialog.Builder builder) {
        super.modifyAlertDialogBuilder(builder);
        if (getLayoutResId() != 0) {
            //设置自定义View
            builder.setView(getLayoutResId());
        }
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
