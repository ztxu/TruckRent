package edu.xmu.software.truckrent.chatrecord;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.xmu.software.truckrent.R;
import edu.xmu.software.truckrent.module.ChatRecord;

/**
 * Created by DELL on 2016/10/21.
 */

public class ChatRecordActivity extends Activity implements View.OnClickListener{
    private Button btnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        //自定义标题
        requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        setContentView(R.layout.activity_chat_record);
        //设置标题为某个layout
        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.activity_chat_titlebar);

        btnBack=(Button)findViewById(R.id.btn_back);
        btnBack.setOnClickListener(this);

        ListView listView=(ListView)findViewById(R.id.recordList);
        listView.setOnItemClickListener(new ItemClickListener());//针对条目的点击事件
        showChatRecord((get()));
    }
    /**
     * 针对条目的点击事件
     */
    private final class ItemClickListener implements AdapterView.OnItemClickListener {

        /**
         * view:当前所点击条目的view对象
         * position:当前所点击的条目它所绑定的数据在集合中的索引值
         */
        public void onItemClick(AdapterView<?> parent, View view, int position,
                                long id) {
            ListView lView = (ListView) parent;
            ChatRecord cr = (ChatRecord) lView.getItemAtPosition(position);
            //进入实时聊天页面
        }

    }
    /*
    * 使用简单适配器显示聊天记录
    * */
    private void showChatRecord(List<ChatRecord> chatRecords){
        List<Map<String,Object>> l=new ArrayList<>();
        Map<String,Object> chatRecord=null;//<属性名，属性值>
        for(ChatRecord cr:chatRecords){
            chatRecord=new HashMap<>();
            chatRecord.put("id",cr.getId());
            chatRecord.put("imgurl",cr.getImagUrl());
            chatRecord.put("chattingfriend",cr.getChattingFre());
            chatRecord.put("lastrecord",cr.getLastRecord());
            chatRecord.put("lasttime",cr.getLastTime());
            l.add(chatRecord);
        }
        ListView listView=(ListView)findViewById(R.id.recordList);
        ListAdapter adapter=new LazyAdapter(this,l);
        listView.setAdapter(adapter);
    }
    /*
    * 模拟数据
    * */
    private List<ChatRecord> get(){
        List<ChatRecord> chatRecords=new ArrayList<>();
        ChatRecord chatRecord=new ChatRecord(1,"陈先生",R.drawable.user_img1,"明天15点35分到思明南路取车...","7月10日 15:30:00");
        chatRecords.add(chatRecord);
        chatRecord=new ChatRecord(2,"林先生",R.drawable.user_img2,"明天15点35分到思明南路取车...","6月18日 16:30:00");
        chatRecords.add(chatRecord);
//        for(int i=3;i<10;i++){
//            chatRecord=new ChatRecord(i,i+"先生","@drawable/user_img"+i,"明天15点35分到思明南路取车...","6月18日 16:30:00");
//            chatRecords.add(chatRecord);
//        }
        return chatRecords;
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
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
