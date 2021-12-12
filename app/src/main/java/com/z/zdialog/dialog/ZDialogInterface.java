package com.z.zdialog.dialog;

import android.view.View;

/*
    拦截系统View#onClickListener
 */
public interface ZDialogInterface {

    /*
     * dismiss the dialog
     *
     * invoke dialog#dismiss()
     *
     * when A class extend B class and implement a C interface,
     * if B have methods implementation which are required from C,
     * then A have no need to implement methods required from C again
     */
    void dismiss();

    /**
     * Interface used to allow the creator of a dialog to run some code when an
     * item on the dialog is clicked.
     */
    interface OnClickListener {
        /**
         * This method will be invoked when a button in the dialog is clicked.
         *
         * @param dialog the dialog that received the click
         */
        void onClick(ZDialogInterface dialog);
    }

    interface OnSubmitListener {
        void onSubmit(ZDialogInterface dialog, View content);
    }
}
