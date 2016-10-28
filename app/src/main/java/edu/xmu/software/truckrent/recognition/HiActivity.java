package edu.xmu.software.truckrent.recognition;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import edu.xmu.software.truckrent.R;

public class HiActivity extends Activity implements View.OnClickListener {
    private static final String TAG = "HiActivity";

    private Intent intent = null;
    private Button btn_start_service;
    private Button btn_stop_service;
    private TextView tv_out;
    MyServiceConn myServiceConn;
    MyService.MyBinder binder = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hi);
        Log.d(TAG, "--onCreate()-- new Intent(this, MyService.class)");
        intent = new Intent(this, MyService.class);
        myServiceConn = new MyServiceConn();
        setOnClick();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_start_service:
                //用intent启动Service并传值
                Log.d(TAG, "--onCreate()-- startService(intent);");

                startService(intent);

                bindService(intent, myServiceConn, Context.BIND_AUTO_CREATE);
                Log.d(TAG, "--onCreate()-- bindServiceService(intent);");

                break;
            case R.id.btn_stop_service:
                //停止Service
                //解绑Service
                if (binder != null) {
                    unbindService(myServiceConn);
                }
                stopService(intent);
                break;

            default:
                break;
        }
    }

    class MyServiceConn implements ServiceConnection {
        // 服务被绑定成功之后执行
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            // IBinder service为onBind方法返回的Service实例
            binder = (MyService.MyBinder) service;
            binder.getService().setDataCallback(new MyService.DataCallback() {
                //执行回调函数
                @Override
                public void dataChanged(String str) {
                    Message msg = new Message();
                    Bundle bundle = new Bundle();
                    bundle.putString("str", str);
                    msg.setData(bundle);
                    //发送通知
                    handler.sendMessage(msg);
                }
            });
        }

        @SuppressLint("HandlerLeak")
        Handler handler = new Handler() {
            public void handleMessage(android.os.Message msg) {
                //在handler中更新UI
                tv_out.setText(msg.getData().getString("str"));
            };
        };

        // 服务奔溃或者被杀掉执行
        @Override
        public void onServiceDisconnected(ComponentName name) {
            binder = null;
        }
    }

    private void loadUI() {
        btn_start_service = (Button) findViewById(R.id.btn_start_service);
        btn_stop_service = (Button) findViewById(R.id.btn_stop_service);

        tv_out = (TextView) findViewById(R.id.tv_out);
    }

    private void setOnClick() {
        loadUI();
        if(btn_start_service==null){
            System.out.println("btn_start_service==null");
        }
        btn_start_service.setOnClickListener(this);
        btn_stop_service.setOnClickListener(this);
    }

}