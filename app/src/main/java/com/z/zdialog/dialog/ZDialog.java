package com.z.zdialog.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.res.Resources;
import android.view.ContextThemeWrapper;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.z.zdialog.R;

/*
    继承自Dialog
 */
public class ZDialog extends Dialog {

    private ZDialogCtl mCtl;

    /*
        protected不让外界new
     */
    protected ZDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
        mCtl = new ZDialogCtl(this, getWindow());
    }

    public static class Builder {

        private final ZDialogCtl.ZDialogParams P;

        public Builder(Context context) {
            this(context, R.style.zdialog);
        }

        public Builder(Context context, int themeResId) {
            P = new ZDialogCtl.ZDialogParams(context, themeResId);
        }

        public ZDialog create() {
            final ZDialog dialog = new ZDialog(P.mContext, P.mThemeResId);
            P.apply(dialog.mCtl);
            dialog.setCancelable(P.mCancelable);
            if (P.mCancelable) {
                dialog.setCanceledOnTouchOutside(true);
            }
            dialog.setOnCancelListener(P.mOnCancelListener);
            dialog.setOnDismissListener(P.mOnDismissListener);
            if (P.mOnKeyListener != null) {
                dialog.setOnKeyListener(P.mOnKeyListener);
            }
            return dialog;
        }

        public ZDialog show() {
            final ZDialog dialog = create();
            dialog.show();
            return dialog;
        }

        // 设置布局
        public ZDialog.Builder setView(int layoutResId) {
            P.mView = null;
            P.mViewLayoutResId = layoutResId;
            return this;
        }

        public ZDialog.Builder setView(View view) {
            P.mView = view;
            P.mViewLayoutResId = 0;
            return this;
        }

        // 设置监听
        public ZDialog.Builder setOnCancelListener(OnCancelListener onCancelListener) {
            P.mOnCancelListener = onCancelListener;
            return this;
        }

        public ZDialog.Builder setOnDismissListener(OnDismissListener onDismissListener) {
            P.mOnDismissListener = onDismissListener;
            return this;
        }

        public ZDialog.Builder setOnKeyListener(OnKeyListener onKeyListener) {
            P.mOnKeyListener = onKeyListener;
            return this;
        }

        // 设置其它
        public ZDialog.Builder setText(int viewId, CharSequence text) {
            P.mTextArray.put(viewId, text);
            return this;
        }

        public ZDialog.Builder setOnClickListener(int viewId, View.OnClickListener listener) {
            P.mClickArray.put(viewId, listener);
            return this;
        }

        public ZDialog.Builder fullScreenWidth() {
            P.mWidth = ViewGroup.LayoutParams.MATCH_PARENT;
            return this;
        }

        public ZDialog.Builder fromBottom(boolean enableAnimcation) {
            if (enableAnimcation) {
                P.mAnimations = R.style.dialog_from_bottom_anim;
            }
            P.mGravity = Gravity.BOTTOM;
            return this;
        }

        public ZDialog.Builder fromTop(boolean enableAnimcation) {
            if (enableAnimcation) {
                P.mAnimations = R.style.dialog_from_top_anim;
            }
            P.mGravity = Gravity.TOP;
            return this;
        }

        // width / height
        public ZDialog.Builder setDimension(int width, int height) {
            P.mWidth = width;
            P.mHeigth = height;
            return this;
        }

        public ZDialog.Builder enableDefaultAnimation() {
            P.mAnimations = R.style.dialog_alpha_anim;
            return this;
        }

        public ZDialog.Builder setAnimation(int animationStyleResId) {
            P.mAnimations = animationStyleResId;
            return this;
        }

        public ZDialog.Builder setCancelable(boolean cancelable) {
            P.mCancelable = cancelable;
            return this;
        }
    }
}
