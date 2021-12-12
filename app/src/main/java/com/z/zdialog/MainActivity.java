package com.z.zdialog;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.z.zdialog.dialog.ZDialog;
import com.z.zdialog.dialog.ZDialogInterface;

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

    TODO: 添加更多自定义对话框布局

    TODO: 设置文字与设置监听器是否可以简化成一步

    TODO: dialogBuilder自P.apply()之后就不能再设置事件了呀
 */
public class MainActivity extends AppCompatActivity {

    public static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void handleShowSystemDialog(View view) {
        new AlertDialog.Builder(this)
                .setIcon(R.mipmap.ic_launcher)
                .setTitle("对话框的标题呀!")       // Builder
                .setMessage("对话框的消息呀")      // Builder
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this, "PositiveButton", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this, "NegativeButton", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNeutralButton("稍后再问", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this, "NeutralButton", Toast.LENGTH_SHORT).show();
                    }
                })
                //.setView(R.layout.view_dialog)
                .setCancelable(false)
                .create()                       // AlertDialog
                .show();                        // void
    }

    public void handleShowDialogCenter(View view) {
        new ZDialog.Builder(this)
                .setView(R.layout.zdialog_view)
                .setText(R.id.submit_btn, "发送")
                .setOnClickListener(R.id.account_icon_weibo, new ZDialogInterface.OnClickListener() {
                    @Override
                    public void onClick(ZDialogInterface dialog) {
                        Toast.makeText(MainActivity.this, "微博分享", Toast.LENGTH_SHORT).show();
                    }
                })
                .show();
    }

    public void handleShowDialogFromBottom(View view) {
        new ZDialog.Builder(this)
                .setView(R.layout.v_comment_box)
                .setText(R.id.submit_btn, "发送")
                .setOnClickListener(R.id.cancel_btn, new ZDialogInterface.OnClickListener() {
                    @Override
                    public void onClick(ZDialogInterface dialog) {
                        dialog.dismiss();
                    }
                })
                .setOnSubmitListener(R.id.submit_btn, new ZDialogInterface.OnSubmitListener() {
                    @Override
                    public void onSubmit(ZDialogInterface dialog, View content) {
                        EditText editText = content.findViewById(R.id.comment_editor);
                        String text = editText.getText().toString();
                        if (TextUtils.isEmpty(text)) {
                            Toast.makeText(MainActivity.this, "提交内容不能为空", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        Toast.makeText(MainActivity.this, "提交: " + editText.getText(), Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                })
                .setCancelable(false)
                .fromBottom(true)
                .fullScreenWidth()
                .show();
    }

    public void handleShowDialogFromTop(View view) {
        new ZDialog.Builder(this)
                .setView(R.layout.v_comment_box)
                .setText(R.id.submit_btn, "发送")
                .fromTop(true)
                .fullScreenWidth()
                .show();
    }
}