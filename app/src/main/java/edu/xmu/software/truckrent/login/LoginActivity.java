package edu.xmu.software.truckrent.login;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.HandlerThread;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import edu.xmu.software.truckrent.recognition.ActivityOffline;
import edu.xmu.software.truckrent.recognition.HiActivity;
import edu.xmu.software.truckrent.MapActivity;
import edu.xmu.software.truckrent.R;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    //define compoment
    private EditText text_username; //输入的用户名控件
    private EditText text_password; //输入的密码控件
    private Button btn_login;       //登陆按钮
    private Button btn_register;    //注册按钮 注册按钮
    private ImageView view_forget;  //忘记密码

    //define variable
    private String username;
    private String password;

    /**后期将登陆放在后面时候，用clickString记录之前触发需要登陆的逻辑
     private String clickString;
     */
    private Dialog waitDialog;//等待后台程序执行的动画

    //开启一个线程专门用来执行等待后台程序的动画waitDialog
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(android.os.Message msg){
            super.handleMessage(msg);
            if(msg.what == 100){    //人为设定的值，100表示后台程序执行完毕
                waitDialog.dismiss();
            }
        }
    };

    private Handler doLoginHandler;

    Runnable doLoginRunable=new Runnable(){
        @Override
        public void run() {
            runLogin();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

       /* try{
            intent = new Intent(this, MyService.class);
            startService(intent);
        }catch (Exception e){

            new AlertDialog.Builder(this).setTitle("标题").setMessage(e.toString())
                    .setPositiveButton("确定", null).show();
        }*/
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_login);
        /** 用于获取clickString，定位是哪个地方触发了登陆事件
         clickString = getIntent().getExtras().getString("click");
         */
        waitDialog = DialogFactory.createRoundProcessDialog(this);
        findViews();
        init();
        setListeners();
    }

    protected void findViews() {
        text_username = (EditText)findViewById(R.id.username_text);
        text_password = (EditText)findViewById(R.id.password_text);
        btn_login = (Button)findViewById(R.id.login_button);
        btn_register = (Button)findViewById(R.id.register_button);
        view_forget = (ImageView)findViewById(R.id.forget_icon_view);
    }

    //server端的初始化
    protected void init() {
        //这里填入服务端初始化代码
    }

    protected void setListeners() {
        btn_login.setOnClickListener(this);
        btn_register.setOnClickListener(this);
        view_forget.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.forget_icon_view:

                Intent intent1 = new Intent(LoginActivity.this,ActivityOffline.class);
                startActivity(intent1);
                break;
            case R.id.register_button:
                Intent intent2 = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent2);
                break;
            case R.id.login_button:
                username = text_username.getText().toString();
                password = text_password.getText().toString();


                //if后填上validate()，则正常。。
                if(true) {
                    waitDialog.show();
                    waitDialog.setContentView(R.layout.loading_process_dialog_anim);

                    HandlerThread thread = new HandlerThread("Login");
                    thread.start();

                    doLoginHandler = new Handler(thread.getLooper());
                    doLoginHandler.post(doLoginRunable);
                }
                break;
            default:
                break;
        }
    }

    private void runLogin(){

        /** 登陆链接数据库处理
         //boolean result = cWeb.userLogin(username,password);
         */

        //以下是暂时的登陆名与密码的验证
        boolean result;
        if (username.equals("admin") && password.equals("123456")) {
            result = true;
        } else {
            result = false;
        }

        //正常应该为result == true
        if (true) {
            //保存用户登陆信息给主界面
            Intent data = new Intent();
            data.putExtra("username", username);
            setResult(100, data);
            //保存用户登陆信息入SharedPreferences
            SharedPreferences settings = getSharedPreferences("userLogin", 0);
            SharedPreferences.Editor editor = settings.edit();
            editor.putBoolean("State", true);
            editor.putString("username", username);
            editor.commit();
            //告知线程handler，等待动画dialog隐藏掉
            handler.sendEmptyMessage(100);
            Intent intent = new Intent(LoginActivity.this, MapActivity.class);
            startActivity(intent);
            finish();
        } else {
            handler.sendEmptyMessage(100);
            Toast.makeText(LoginActivity.this, "用户名或密码错误!", Toast.LENGTH_SHORT).show();
        }
    }

    //监听用户的物理的返回键 是否被按下
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub
        if(keyCode == KeyEvent.KEYCODE_BACK)
        {
            setResult(0);     //如果点击返回键，那么返回结果代码是0，不执行任何操作
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }

    // 验证方法
    private boolean validate(){
        if(username.equals("")){
            Toast.makeText(LoginActivity.this, "用户名不能为空!", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(password.equals("")){
            Toast.makeText(LoginActivity.this, "用户密码不能为空！", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

//    //提示框
//    private void showDialog(String msg){
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        builder.setMessage(msg)
//                .setCancelable(false)
//                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int id) {
//                    }
//                });
//        AlertDialog alert = builder.create();
//        alert.show();
//    }
}
