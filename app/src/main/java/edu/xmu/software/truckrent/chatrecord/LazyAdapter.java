package edu.xmu.software.truckrent.chatrecord;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.xmu.software.truckrent.R;

/**
 * Created by DELL on 2016/10/22.
 */

public class LazyAdapter extends BaseAdapter{
    private Activity activity;
    private List<Map<String, Object>> data;
    private static LayoutInflater inflater=null;
    public LazyAdapter(Activity a,List<Map<String, Object>> d) {
        activity = a;
        data=d;
        inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View vi=convertView;
        if(convertView==null)
            vi = inflater.inflate(R.layout.chat_record_item, null);

        TextView chattingFriend = (TextView)vi.findViewById(R.id.tv_chat_user_name);
        TextView lastRecord = (TextView)vi.findViewById(R.id.tv_last_chat_imf);
        TextView lastTime = (TextView)vi.findViewById(R.id.tv_last_chat_time);
        ImageView img=(ImageView)vi.findViewById(R.id.iv_chat_user_img);

        Map<String, Object> chatRecords = new HashMap<String, Object>();
        chatRecords = data.get(position);

        // 设置ListView的相关值
        chattingFriend.setText((String)chatRecords.get("chattingfriend"));
        lastRecord.setText((String)chatRecords.get("lastrecord"));
        lastTime.setText((String)chatRecords.get("lasttime"));
        img.setImageResource((int)chatRecords.get("imgurl"));
        return vi;
    }
}
