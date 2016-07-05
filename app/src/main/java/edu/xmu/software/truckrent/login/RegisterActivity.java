package edu.xmu.software.truckrent.login;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import edu.xmu.software.truckrent.R;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener{

    //define compoment
    private EditText text_username;
    private EditText text_checkword;
    private EditText text_password;
    private EditText text_repassword;
    private Button btn_register;
    private Button btn_check;
    private Button btn_back;

    //define variable
    private String username;
    private String checkword;
    private String password;
    private String repassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_register);
        findViews();
        init();
        setListeners();
    }

    protected void findViews(){
        text_username = (EditText)findViewById(R.id.text_username);
        text_password = (EditText)findViewById(R.id.text_password);
        text_repassword = (EditText)findViewById(R.id.text_repassword);
        text_checkword = (EditText)findViewById(R.id.text_checkword);
        btn_register = (Button)findViewById(R.id.btn_register);
        btn_check = (Button)findViewById(R.id.btn_check);
        btn_back = (Button)findViewById(R.id.btn_back);
    }

    //数据初始化
    protected void init(){

    }

    protected  void setListeners(){
        btn_register.setOnClickListener(this);
        btn_check.setOnClickListener(this);
        btn_back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btn_register:
                if(text_password.getText().toString().
                        equals(text_repassword.getText().toString()))
                {
                    //这里是注册链接数据库
                    username = text_username.getText().toString();
                    password = text_password.getText().toString();
                    repassword = text_repassword.getText().toString();
                    checkword = text_checkword.getText().toString();

                    Toast.makeText(RegisterActivity.this, "恭喜您，注册成功!", Toast.LENGTH_SHORT).show();
                    finish();
                }
                else{
                    Toast.makeText(RegisterActivity.this, "两次输入的密码不一致!", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btn_check:
                //短信验证代码
                break;
            case R.id.btn_back:
                finish();
                break;
            default:
                break;
        }
    }

    //监听用户的物理的返回键 是否被按下
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK)
        {
            setResult(0);     //如果点击返回键，那么返回结果代码是0，不执行任何操作
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }
}
