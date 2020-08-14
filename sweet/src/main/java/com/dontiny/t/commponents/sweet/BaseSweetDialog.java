package com.dontiny.t.commponents.sweet;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.fragment.app.FragmentManager;

import com.dontiny.t.commponents.sweet.SweetDialog;
import com.dontiny.t.commponents.sweet.SweetDialogParams;

import java.lang.reflect.Field;

import static androidx.appcompat.app.AlertController.AlertParams;
import static androidx.appcompat.app.SweetDialog_AlertDialog.resolveDialogTheme;

/**
 * Author: DonTiny
 * Date: 2020/8/12 0012 10:09
 * Email:tg_0804@163.com
 */
public abstract class BaseSweetDialog extends AppCompatDialogFragment {

    private final static String SWEET_DIALOG_TAG = "SweetDialogTag";
    private final static String SWEET_PARAMS_KEY = "SweetParamsKey";
    private final static String IS_RETAIN_INSTANCE_KEY = "IsRetainInstanceKey";
    private final static String IS_BOTTOM_DIALOG_KEY = "IsBottomDialog";

    private SweetDialogParams params;

    protected boolean isBottomDialog = false;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //获取通过setArguments()装配的属性
        Bundle bundle = getArguments();
        if (bundle != null) {
            params = (SweetDialogParams) bundle.getSerializable(SWEET_PARAMS_KEY);
            setRetainInstance(bundle.getBoolean(IS_RETAIN_INSTANCE_KEY));
            isBottomDialog = bundle.getBoolean(IS_BOTTOM_DIALOG_KEY, false);
        } else {
            params = new SweetDialogParams();
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        super.onCreateDialog(savedInstanceState);
        if (savedInstanceState != null) {
            onRestoreInstanceState(savedInstanceState);
        }
        return createDialog();
    }

    @Override
    public void onStart() {
        super.onStart();
        if (getDialog().getWindow() != null) {
            initAndSetViews(getDialog().getWindow().getDecorView());
        } else {
            initAndSetViews(null);
        }
    }

    //创建一个可供使用的Dialog
    protected abstract Dialog createDialog();

    //存在要恢复的数据
    protected abstract void onRestoreInstanceState(Bundle savedInstanceState);

    //视图创建完毕
    protected abstract void initAndSetViews(View root);

    @Override
    public void setArguments(@Nullable Bundle args) {
        if (getArguments() != null) {
            getArguments().putAll(args);
        } else {
            super.setArguments(args);
        }
    }


    public void show(@NonNull FragmentManager manager) {
        show(manager, SWEET_DIALOG_TAG);
    }

    @Override
    public void show(@NonNull FragmentManager manager, @Nullable String tag) {
        if (manager == null || manager.isDestroyed() || manager.isStateSaved()) {
            // do nothing!!!
            return;
        }
        try {
            super.show(manager, tag);
        } catch (IllegalStateException e) {
            // 防御：java.lang.IllegalStateException: Can not perform this action after onSaveInstanceState
            e.printStackTrace();
        }
    }


    public void showAllowingStateLoss(FragmentManager manager) {
        showAllowingStateLoss(manager, SWEET_DIALOG_TAG);
    }

    //用于避免出现：java.lang.IllegalStateException: Can not perform this action after onSaveInstanceState错误
    public void showAllowingStateLoss(FragmentManager manager, String tag) {
        manager.beginTransaction().add(this, tag).commitAllowingStateLoss();
    }


    protected <T extends View> T findViewById(@IdRes int id) {
        return getDialog().findViewById(id);
    }

    /**
     * 自定义AlertDialog.Builder用来构建自己的参数,在此重写需要包装的方法
     *
     * @param <T>
     */
    protected abstract static class BaseBuilder<T extends BaseBuilder> extends AlertDialog.Builder {
        //主题资源id
        private int themeResId;
        //Activity重建时是否保留DialogFragment实例，(默认销毁重建)
        private boolean isRetainInstance = false;
        //是否创建一个底部显示的Dialog
        private boolean isBottomDialog = false;

        public BaseBuilder(@NonNull Context context) {
            this(context, resolveDialogTheme(context, 0));
        }

        public BaseBuilder(@NonNull Context context, int themeResId) {
            super(context, themeResId);
            this.themeResId = themeResId;
        }


        //设置图标
        @Override
        public T setIcon(int iconId) {
            return (T) super.setIcon(iconId);
        }

        @Override
        public T setIcon(@Nullable Drawable icon) {
            return (T) super.setIcon(icon);
        }

        //设置标题
        @Override
        public T setTitle(int titleId) {
            return (T) super.setTitle(titleId);
        }

        @Override
        public T setTitle(@Nullable CharSequence title) {
            return (T) super.setTitle(title);
        }

        //设置消息文本
        @Override
        public T setMessage(int messageId) {
            return (T) super.setMessage(messageId);
        }

        @Override
        public T setMessage(@Nullable CharSequence message) {
            return (T) super.setMessage(message);
        }

        //确定按钮
        @Override
        public T setPositiveButton(int textId, DialogInterface.OnClickListener listener) {
            return (T) super.setPositiveButton(textId, listener);
        }

        @Override
        public T setPositiveButton(CharSequence text, DialogInterface.OnClickListener listener) {
            return (T) super.setPositiveButton(text, listener);
        }

        @Override
        public T setPositiveButtonIcon(Drawable icon) {
            return (T) super.setPositiveButtonIcon(icon);
        }

        //取消按钮
        @Override
        public T setNegativeButton(int textId, DialogInterface.OnClickListener listener) {
            return (T) super.setNegativeButton(textId, listener);
        }

        @Override
        public T setNegativeButton(CharSequence text, DialogInterface.OnClickListener listener) {
            return (T) super.setNegativeButton(text, listener);
        }

        @Override
        public T setNegativeButtonIcon(Drawable icon) {
            return (T) super.setNegativeButtonIcon(icon);
        }

        //中立按钮
        @Override
        public T setNeutralButton(int textId, DialogInterface.OnClickListener listener) {
            return (T) super.setNeutralButton(textId, listener);
        }

        @Override
        public T setNeutralButton(CharSequence text, DialogInterface.OnClickListener listener) {
            return (T) super.setNeutralButton(text, listener);
        }

        @Override
        public T setNeutralButtonIcon(Drawable icon) {
            return (T) super.setNeutralButtonIcon(icon);
        }

        //单选
        @Override
        public T setSingleChoiceItems(int itemsId, int checkedItem, DialogInterface.OnClickListener listener) {
            return (T) super.setSingleChoiceItems(itemsId, checkedItem, listener);
        }

        @Override
        public T setSingleChoiceItems(ListAdapter adapter, int checkedItem, DialogInterface.OnClickListener listener) {
            return (T) super.setSingleChoiceItems(adapter, checkedItem, listener);
        }

        @Override
        public T setSingleChoiceItems(CharSequence[] items, int checkedItem, DialogInterface.OnClickListener listener) {
            return (T) super.setSingleChoiceItems(items, checkedItem, listener);
        }

        @Override
        public T setSingleChoiceItems(Cursor cursor, int checkedItem, String labelColumn, DialogInterface.OnClickListener listener) {
            return (T) super.setSingleChoiceItems(cursor, checkedItem, labelColumn, listener);
        }

        //多选
        @Override
        public T setMultiChoiceItems(int itemsId, boolean[] checkedItems, DialogInterface.OnMultiChoiceClickListener listener) {
            return (T) super.setMultiChoiceItems(itemsId, checkedItems, listener);
        }

        @Override
        public T setMultiChoiceItems(CharSequence[] items, boolean[] checkedItems, DialogInterface.OnMultiChoiceClickListener listener) {
            return (T) super.setMultiChoiceItems(items, checkedItems, listener);
        }

        @Override
        public T setMultiChoiceItems(Cursor cursor, String isCheckedColumn, String labelColumn, DialogInterface.OnMultiChoiceClickListener listener) {
            return (T) super.setMultiChoiceItems(cursor, isCheckedColumn, labelColumn, listener);
        }


        //简单列表
        @Override
        public T setItems(int itemsId, DialogInterface.OnClickListener listener) {
            return (T) super.setItems(itemsId, listener);
        }

        @Override
        public T setItems(CharSequence[] items, DialogInterface.OnClickListener listener) {
            return (T) super.setItems(items, listener);
        }


        //设置点击返回键是否关闭Dialog
        @Override
        public T setCancelable(boolean cancelable) {
            return (T) super.setCancelable(cancelable);
        }

        @Override
        public T setIconAttribute(int attrId) {
            return (T) super.setIconAttribute(attrId);
        }

        @Override
        public T setAdapter(ListAdapter adapter, DialogInterface.OnClickListener listener) {
            return (T) super.setAdapter(adapter, listener);
        }

        //各种事件

        @Override
        public T setOnCancelListener(DialogInterface.OnCancelListener onCancelListener) {
            return (T) super.setOnCancelListener(onCancelListener);
        }

        @Override
        public T setOnDismissListener(DialogInterface.OnDismissListener onDismissListener) {
            return (T) super.setOnDismissListener(onDismissListener);
        }

        @Override
        public T setOnItemSelectedListener(AdapterView.OnItemSelectedListener listener) {
            return (T) super.setOnItemSelectedListener(listener);
        }

        @Override
        public T setOnKeyListener(DialogInterface.OnKeyListener onKeyListener) {
            return (T) super.setOnKeyListener(onKeyListener);
        }


        /**
         * Activity重建时是否保留DialogFragment实例
         *
         * @param retain
         * @return
         */
        public T setRetainInstance(boolean retain) {
            isRetainInstance = retain;
            return (T) this;
        }

        /**
         * 是否创建一个底部弹出的Dialog
         *
         * @param inBottom
         * @return
         */
        public T setIsBottomDialog(boolean inBottom) {
            isBottomDialog = inBottom;
            return (T) this;
        }

        /**
         * 通过Fragment的setArguments()方法装配属性参数，并构建SweetDialog
         *
         * @param <U> 默认返回SweetDialog实例，子类需要使用继承自BaseBuilder来builder自身的实例
         * @return
         */
        public <U extends SweetDialog> U builder() {
            SweetDialog dialog = createBuilderDialog();
            AlertParams alertParams = getParams();
            Bundle bundle = new Bundle();
            bundle.putSerializable(SWEET_PARAMS_KEY, alertParamsToSweetParams(alertParams));
            bundle.putBoolean(IS_RETAIN_INSTANCE_KEY, isRetainInstance);
            bundle.putBoolean(IS_BOTTOM_DIALOG_KEY, isBottomDialog);
            dialog.setArguments(bundle);
            dialog.mPositiveListener = alertParams.mPositiveButtonListener;
            dialog.mNegativeListener = alertParams.mNegativeButtonListener;
            dialog.mNeutralListener = alertParams.mNeutralButtonListener;
            dialog.mClickListener = alertParams.mOnClickListener;
            dialog.mMultiClickListener = alertParams.mOnCheckboxClickListener;
            dialog.mDismissListener = alertParams.mOnDismissListener;
            dialog.mCancelListener = alertParams.mOnCancelListener;
            return (U) dialog;
        }

        /**
         * 将AlertParams包装成SweetDialogParams
         *
         * @param alertParams
         * @return
         */
        private SweetDialogParams alertParamsToSweetParams(AlertParams alertParams) {
            SweetDialogParams sweetParams = new SweetDialogParams();
            sweetParams.themeResId = themeResId;
            sweetParams.mIconId = alertParams.mIconId;
            sweetParams.mIcon = alertParams.mIcon;
            sweetParams.mTitle = alertParams.mTitle;
            sweetParams.mMessage = alertParams.mMessage;
            sweetParams.mPositiveButtonText = alertParams.mPositiveButtonText;
            sweetParams.mPositiveButtonIcon = alertParams.mPositiveButtonIcon;
            sweetParams.mNegativeButtonText = alertParams.mNegativeButtonText;
            sweetParams.mNegativeButtonIcon = alertParams.mNegativeButtonIcon;
            sweetParams.mNeutralButtonText = alertParams.mNeutralButtonText;
            sweetParams.mNeutralButtonIcon = alertParams.mNeutralButtonIcon;
            sweetParams.mCancelable = alertParams.mCancelable;
            sweetParams.mItems = alertParams.mItems;
            sweetParams.mCheckedItems = alertParams.mCheckedItems;
            sweetParams.mIsMultiChoice = alertParams.mIsMultiChoice;
            sweetParams.mIsSingleChoice = alertParams.mIsSingleChoice;
            sweetParams.mCheckedItem = alertParams.mCheckedItem;
            return sweetParams;
        }


        //创建一个需要Builder的SweetDialog，也可在此方法内装配自己的参数
        protected abstract SweetDialog createBuilderDialog();


        /**
         * 获取当前AlertDialog.Builder设置的AlertParams
         *
         * @return
         */
        AlertParams getParams() {
            AlertParams P = null;
            try {
                Field field = AlertDialog.Builder.class.getDeclaredField("P");
                field.setAccessible(true);
                P = (AlertParams) field.get(this);
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            return P;
        }
    }


    public SweetDialogParams getParams() {
        return params;
    }

    @Override
    public void onDestroyView() {
        Dialog dialog = getDialog();

        // Work around bug: http://code.google.com/p/android/issues/detail?id=17423
        if (dialog != null && getRetainInstance()) {
            dialog.setDismissMessage(null);
        }
        super.onDestroyView();
    }
}
