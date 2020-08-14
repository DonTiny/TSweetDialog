package com.dontiny.t.commponents.tsweetdialog;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.dontiny.t.commponents.sweet.SweetDialog;
import com.dontiny.t.commponents.sweet.SweetDialogManager;
import com.dontiny.t.commponents.sweet.custom.BottomDialog;
import com.dontiny.t.commponents.tsweetdialog.utils.LogUtils;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button mBtn_one_dialog;
    private Button mBtn_tow_dialog;
    SweetDialog sweetDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initListener();
        if (savedInstanceState != null) {
            SweetDialog dialog = (SweetDialog) getSupportFragmentManager().findFragmentByTag("dialog");
            LogUtils.i("当前存在Dialog" + (dialog != null));
            if (dialog != null) {
                // dialog.setPositiveListener(onPositiveListener);
            }
        }
    }

    private void initView() {
        mBtn_one_dialog = findViewById(R.id.btn_one_dialog);
        mBtn_tow_dialog = findViewById(R.id.btn_tow_dialog);
    }

    private void initListener() {
        mBtn_one_dialog.setOnClickListener(this);
        mBtn_tow_dialog.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        LogUtils.i("onClick");
        switch (v.getId()) {
            case R.id.btn_one_dialog:
                sweetDialog = new SweetDialog.Builder(this)
                        .setTitle("我是标题")
                        .setMessage("getResources().getString(R.string.hello_world)")
                        .setPositiveButton("我同意", onPositiveListener)
//                        .setPositiveButtonIcon(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_launcher_background))
                        .setNegativeButton("不同意", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                LogUtils.i("不同意" + which);
                            }
                        })
                        .setNeutralButton("这是啥", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                LogUtils.i("这是啥" + which);
                            }
                        })
                        .setMultiChoiceItems(new CharSequence[]{"sadsad", "asdsad", "sad"}, new boolean[]{true, false, true}, new DialogInterface.OnMultiChoiceClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                                LogUtils.i("选择了" + which);
                            }
                        })
                        .setCancelable(false)
                        .setRetainInstance(true)
                        .builder();

                sweetDialog.show(getSupportFragmentManager(), "dialog");
//                //SweetDialog.createBuilder(this).builder().show(getSupportFragmentManager(), "tag");
                break;
            case R.id.btn_tow_dialog:
                BottomDialog dialog = SweetDialogManager.createBuilder(this, BottomDialog.class)
                        .setMessage("我是标题")
                        .setCancelable(false)
                        .setIsBottomDialog(true)
                        .builder();
                dialog.show(getSupportFragmentManager(), "dialog");
                dialog.setCancelable(false);
                break;
        }
    }


    private DialogInterface.OnClickListener onPositiveListener = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            LogUtils.i("我同意" + which);
        }
    };
}