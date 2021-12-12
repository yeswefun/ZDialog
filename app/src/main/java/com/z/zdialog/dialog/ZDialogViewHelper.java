package com.z.zdialog.dialog;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import java.lang.ref.WeakReference;

/*
    去掉public不让外面使用

    view辅助处理类
 */
class ZDialogViewHelper {

    private View mContentView;
    private SparseArray<WeakReference<View>> mCacheViews;

    public ZDialogViewHelper(Context context, int layoutResId) {
        this(LayoutInflater.from(context).inflate(layoutResId, null));
    }

    public ZDialogViewHelper(View view) {
        mContentView = view;
        mCacheViews = new SparseArray<>();
    }

    /*
        每次findViewById会有性能问题
     */
    private <T extends View> T findView(int viewId) {
        View view = null;
        WeakReference<View> viewWeakReference = mCacheViews.get(viewId);
        if (viewWeakReference != null) {
            view = viewWeakReference.get();
        }
        if (view == null) {
            view = mContentView.findViewById(viewId);
            if (view != null) {
                mCacheViews.put(viewId, new WeakReference<>(view));
            }
        }
        return (T) view;
    }

    public void setText(int viewId, CharSequence text) {
        TextView textView = findView(viewId);
        if (textView != null) {
            textView.setText(text);
        }
    }

    public void setOnClickListener(int viewId, ZDialogInterface.OnClickListener listener, ZDialog dialog) {
        View view = findView(viewId);
        if (view != null && listener != null) {
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onClick(dialog);
                }
            });
        }
    }

    public void setOnSubmitListener(int viewId, ZDialogInterface.OnSubmitListener listener, ZDialog dialog) {
        View view = findView(viewId);
        if (view != null && listener != null) {
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onSubmit(dialog, mContentView);
                }
            });
        }
    }

    public View getContentView() {
        return mContentView;
    }
}
