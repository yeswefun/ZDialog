package com.z.zdialog;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.z.zdialog.dialog.ZDialog;

/*
    AlertDialog不能够直接new, 因为构造方法的修饰符是protected

    可以通过AlertDialog里面的内部类Builder的方法实例化AlertDialog对象

    AlertDialog
        setXxx方法 - 相当于购买电脑配件
            setTitle
            setIcon

        create方法 - 组装电脑
            P.apply(dialog.mAlert); - 配件的参数

        show方法 - 完成组装

    参数应用 - apply
    控件显示 - show

    Builder设计模式
        设置参数
        组装配件
        完成创建

    特点:
        参数设置与顺序无关
        延迟分配内存
 */
public class MainActivity extends AppCompatActivity {

    public static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void handleShowDialogCenter(View view) {
        new ZDialog.Builder(this)
                .setView(R.layout.zdialog_view)
                .setText(R.id.submit_btn, "发送")
                .setOnClickListener(R.id.account_icon_weibo, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(MainActivity.this, "微博分享", Toast.LENGTH_SHORT).show();
                    }
                })
                .show();
    }

    public void handleShowDialogFromBottom(View view) {
        ZDialog.Builder dialogBuilder = new ZDialog.Builder(this)
                .setView(R.layout.zdialog_view)
                .setText(R.id.submit_btn, "发送")
                .setOnClickListener(R.id.account_icon_weibo, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(MainActivity.this, "新浪微博分享", Toast.LENGTH_SHORT).show();
                    }
                })
                .setOnClickListener(R.id.account_icon_tencent, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(MainActivity.this, "腾讯微博分享", Toast.LENGTH_SHORT).show();
                    }
                })
                .setCancelable(false)
                .fromBottom(true)
                .fullScreenWidth();

        // TODO: dialog没有办法在builder设置参数时设置取消对话框, 即隐藏对话框(此时正在初始化)
        // TODO: dialogBuilder自P.apply()之后就不能再设置事件了呀
        ZDialog dialog = dialogBuilder.create();
        dialogBuilder.setOnClickListener(R.id.cancel_btn, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e(TAG, "cancel btn was clicked!");
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    public void handleShowDialogFromTop(View view) {
        new ZDialog.Builder(this)
                .setView(R.layout.zdialog_view)
                .setText(R.id.submit_btn, "发送")
                .setOnClickListener(R.id.account_icon_weibo, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(MainActivity.this, "微博分享", Toast.LENGTH_SHORT).show();
                    }
                })
                .fromTop(true)
                .fullScreenWidth()
                .show();
    }
}