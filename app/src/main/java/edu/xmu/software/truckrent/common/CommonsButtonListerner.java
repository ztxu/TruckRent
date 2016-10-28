package edu.xmu.software.truckrent.common;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.Button;

import edu.xmu.software.truckrent.R;
import edu.xmu.software.truckrent.chatrecord.ChatRecordActivity;
import edu.xmu.software.truckrent.PersonCenterPopupWindow;

/**
 * Created by DELL on 2016/10/21.
 */

public class CommonsButtonListerner{
    public static void btnPersonListerner(final Button button,final Activity v){
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                PersonCenterPopupWindow popWindow = new PersonCenterPopupWindow(v,R.layout.activity_person_center);
                popWindow.showPopupWindow(button);
            }
        });
    }
    public static void btnChatListerner(final Button button,final Activity v){
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent intent2 = new Intent(v,ChatRecordActivity.class);
                v.startActivity(intent2);
            }
        });
    }
}
