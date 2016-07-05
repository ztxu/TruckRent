package edu.xmu.software.truckrent.login;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.KeyEvent;

/**
 * Created by Administrator on 2016/7/3.
 * 产生各种不同的Dialog
 */
public class DialogFactory {
    /**
     *
     *打开相机或者相册
     * @param title 标题,无标题为null
     *@param items 每一项的文字 只允许两项
     *  @param  context 创建的环境
     * @return
     */
    public  static AlertDialog createPosNeuAndNegAlertDialog(String title, String[] items, Context context){

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        if(title!=null){
            builder.setTitle(title);
        }

        builder.setPositiveButton(items[0],null);
        builder.setNeutralButton(items[1],null);
        builder.setNegativeButton("取消", null);
//        builder.setItems(items,null);
        return builder.create();
    }

    public static Dialog createRoundProcessDialog(Context context) {

        Dialog mDialog;
        DialogInterface.OnKeyListener keyListener = new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode,
                                 KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_HOME
                        || keyCode == KeyEvent.KEYCODE_SEARCH) {
                    return true;
                }
                return false;
            }
        };
        mDialog = new AlertDialog.Builder(context).create();
        mDialog.setCanceledOnTouchOutside(false);
        mDialog.setOnKeyListener(keyListener);
        return mDialog;
    }
}
