package com.z.zdialog.dialog;

import android.content.Context;
import android.content.DialogInterface;
import android.util.SparseArray;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import java.util.HashMap;

/*
    去掉public不让外面使用
 */
class ZDialogCtl {

    private ZDialog mDialog;
    private Window mWindow;

    public ZDialogCtl(ZDialog dialog, Window window) {
        this.mDialog = dialog;
        this.mWindow = window;
    }

    public ZDialog getDialog() {
        return mDialog;
    }

    public Window getWindow() {
        return mWindow;
    }

    public static class ZDialogParams {

        public Context mContext;
        public int mThemeResId;
        /*
            点击空白区域是否可以隐藏dialog
         */
        public boolean mCancelable = true;
        /*
            取消
         */
        public DialogInterface.OnCancelListener mOnCancelListener;
        /*
            隐藏
         */
        public DialogInterface.OnDismissListener mOnDismissListener;
        /*
            按键
         */
        public DialogInterface.OnKeyListener mOnKeyListener;
        /*
            布局视图
         */
        public View mView;
        /*
            布局id
         */
        public int mViewLayoutResId;

        public SparseArray<CharSequence> mTextArray = new SparseArray<>();

        public SparseArray<View.OnClickListener> mClickArray = new SparseArray<>();

        /*
            对话框的宽度
         */
        public int mWidth = ViewGroup.LayoutParams.WRAP_CONTENT;
        /*
            对话框的高度
         */
        public int mHeigth = ViewGroup.LayoutParams.WRAP_CONTENT;

        /*
            对话框显示动画
         */
        public int mAnimations = 0;
        /*
            对话框显示位置
         */
        public int mGravity = Gravity.CENTER;


        public ZDialogParams(Context context, int themeResId) {
            this.mContext = context;
            this.mThemeResId = themeResId;
        }

        /*
            将P中的参数设置到dialog中
         */
        public void apply(ZDialogCtl mCtl) {

            // 设置布局
            ZDialogViewHelper viewHelper = null;
            if (mViewLayoutResId != 0) {
                viewHelper = new ZDialogViewHelper(mContext, mViewLayoutResId);
            }

            if (mView != null) {
                viewHelper = new ZDialogViewHelper(mView);
                //viewHelper.setLayoutView
            }

            if (viewHelper == null) {
                throw new RuntimeException("请先设置布局: ZDialog.Builder#setView");
            }

            // 给dialog设置布局
            mCtl.getDialog().setContentView(viewHelper.getContentView());

            // 设置文本
            int textArraySize = mTextArray.size();
            for (int x = 0; x < textArraySize; x++) {
                viewHelper.setText(mTextArray.keyAt(x), mTextArray.valueAt(x));
            }

            // 设置点击
            int clickArraySize = mClickArray.size();
            for (int x = 0; x < clickArraySize; x++) {
                viewHelper.setOnClickListener(mClickArray.keyAt(x), mClickArray.valueAt(x));
            }

            // 设置自定义效果
            Window window = mCtl.getWindow();
            window.setGravity(mGravity);
            if (mAnimations != 0) {
                window.setWindowAnimations(mAnimations);
            }

            WindowManager.LayoutParams lp = window.getAttributes();
            lp.width = mWidth;
            lp.height = mHeigth;
            window.setAttributes(lp);
        }
    }

}
