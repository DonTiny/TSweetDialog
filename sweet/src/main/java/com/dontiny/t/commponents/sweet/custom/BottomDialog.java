package com.dontiny.t.commponents.sweet.custom;

import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.dontiny.t.commponents.sweet.BaseCustomSweetDialog;
import com.dontiny.t.commponents.sweet.R;
import com.google.android.material.bottomsheet.BottomSheetBehavior;


public class BottomDialog extends BaseCustomSweetDialog {
    private BottomSheetBehavior mBehavior;

    @Override
    protected int getLayoutResId() {
        return R.layout.custom_dialog_layout;
    }

    @Override
    protected void initView(View view) {
        FrameLayout bottomSheet = view.findViewById(R.id.design_bottom_sheet);
        mBehavior = BottomSheetBehavior.from(bottomSheet);
    }

    @Override
    protected void setViews() {
        final TextView textView = findViewById(R.id.message_tv);
        textView.setText(getParams().mMessage);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }

    /**
     * 禁止滑动关闭
     *
     * @param cancelable
     */
    @Override
    public void setCancelable(boolean cancelable) {
        super.setCancelable(cancelable);
    }
}
