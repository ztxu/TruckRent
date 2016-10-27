package edu.xmu.software.truckrent;


import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.AndroidRuntimeException;
import android.util.Log;
import android.widget.TextView;

import com.baidu.speech.EventListener;
import com.baidu.speech.EventManager;
import com.baidu.speech.EventManagerFactory;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class MyService extends Service {
    private String data = "默认消息";
    private boolean serviceRunning = false;
    private static final String TAG = "MyService";
    private EventManager mWpEventManager;


    // 必须实现的方法，用于返回Binder对象
    @Override
    public IBinder onBind(Intent intent) {
        System.out.println("--onBind()--");
        return new MyBinder();
    }

    public class MyBinder extends Binder {
        MyService getService() {
            return MyService.this;
        }

        public void setData(String data) {
            MyService.this.data = data;
        }
    }

    // 创建Service时调用该方法，只调用一次
    static String str ="  234";
    @Override
    public void onCreate() {
        super.onCreate();
        //System.out.println("--onCreate()--");
        Log.d(TAG, "--onCreate()--");

        serviceRunning = true;
        new Thread() {
            @Override
            public void run() {
                int n = 0;
                //=====================================================
                // 唤醒功能打开步骤
                // 1) 创建唤醒事件管理器
                mWpEventManager = EventManagerFactory.create(MyService.this, "wp");

                // 2) 注册唤醒事件监听器
                mWpEventManager.registerListener(new EventListener() {
                    @Override
                    public void onEvent(String name, String params, byte[] data, int offset, int length) {
                        Log.d(TAG, String.format("event: name=%s, params=%s", name, params));
                        try {
                            JSONObject json = new JSONObject(params);
                            if ("wp.data".equals(name)) { // 每次唤醒成功, 将会回调name=wp.data的时间, 被激活的唤醒词在params的word字段
                                String word = json.getString("word");
                                if (dataCallback != null) {
                                    str=word+"  235";
                                    dataCallback.dataChanged(str);
                                }
                                if(word.matches("百度一下")){
                                    Intent intent=new Intent(MyService.this,Main2Activity.class);
                                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                    startActivity(intent);
                                }else if(word.matches("小度你好")){
                                    Intent intent=new Intent(MyService.this,Main3Activity.class);
                                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                    startActivity(intent);
                                }
                            } else if ("wp.exit".equals(name)) {
                                if (dataCallback != null) dataCallback.dataChanged("唤醒已经停止: " + params + "\r\n");
                            }else{
                                if (dataCallback != null) dataCallback.dataChanged(str+" 233");
                            }

                        } catch (JSONException e) {
                            throw new AndroidRuntimeException(e);
                        }
                    }
                });

                // 3) 通知唤醒管理器, 启动唤醒功能
                HashMap params = new HashMap();
                params.put("kws-file", "assets:///WakeUp.bin"); // 设置唤醒资源, 唤醒资源请到 http://yuyin.baidu.com/wake#m4 来评估和导出
                System.out.println("new JSONObject(params).toString():"+new JSONObject(params).toString());
                mWpEventManager.send("wp.start", new JSONObject(params).toString(), null, 0, 0);
                //=====================================================


                while (serviceRunning) {
                    n++;
                    // str = n + "创建唤醒事件管理器";

                    //       System.out.println(n+str);
                    if (dataCallback != null) {
                        dataCallback.dataChanged(n+str);
                    }
                    try {
                        sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };
        }.start();
    }

    // 每次启动Servcie时都会调用该方法
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        System.out.println("--onStartCommand()--");
        data = intent.getStringExtra("data");
        return super.onStartCommand(intent, flags, startId);
    }

    // 解绑Servcie调用该方法
    @Override
    public boolean onUnbind(Intent intent) {
        System.out.println("--onUnbind()--");

        return super.onUnbind(intent);
    }

    // 退出或者销毁时调用该方法
    @Override
    public void onDestroy() {
        // 停止唤醒监听
        mWpEventManager.send("wp.stop", null, null, 0, 0);
        serviceRunning = false;
        System.out.println("--onDestroy()--");
        super.onDestroy();
    }

    DataCallback dataCallback = null;

    public DataCallback getDataCallback() {
        return dataCallback;
    }

    public void setDataCallback(DataCallback dataCallback) {
        this.dataCallback = dataCallback;
    }

    // 通过回调机制，将Service内部的变化传递到外部
    public interface DataCallback {
        void dataChanged(String str);
    }

}
