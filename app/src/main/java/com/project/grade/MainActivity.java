package com.project.grade;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.UnsupportedEncodingException;
import java.util.regex.Matcher;

public class MainActivity extends AppCompatActivity {
    private EditText account;
    private EditText password;
    private String accountStr = "";
    private String passwordStr = "";
    int result;

    private Handler handler = new Handler();
    private ProgressDialog dialog = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        account=(EditText)findViewById(R.id.account);
        password=(EditText)findViewById(R.id.password);
    }

    public void Login(View view) throws UnsupportedEncodingException {
        accountStr = account.getText().toString();
        passwordStr = password.getText().toString();

        if (accountStr.equals("") || passwordStr.equals("")) {
            Toast.makeText(this, "学号或密码不能为空", Toast.LENGTH_SHORT).show();
        } else {
            //dialog = ProgressDialog.show(MainActivity.this, "请稍等", "正在连接", true);
            LoginServer loginServer = new LoginServer();
            HttpDatasend httpDatasend = new HttpDatasend();
            result = loginServer.Connect(accountStr, passwordStr);
                   if (result == 1) {
                        Toast.makeText(MainActivity.this, Value.studentName + ",欢迎", Toast.LENGTH_SHORT).show();
                        //Intent intent = new Intent(MainActivity.this,Activity.class);
                        //MainActivity.this.startActivity(intent);
                        //MainActivity.this.finish();
                    }
                    if (result != 1) {
                        //Toast.makeText(MainActivity.this, "登陆失败，请检查用户名和密码", Toast.LENGTH_SHORT).show();
                        Toast.makeText(MainActivity.this,String.valueOf(result),Toast.LENGTH_SHORT).show();
                    }
        }
    }
}
