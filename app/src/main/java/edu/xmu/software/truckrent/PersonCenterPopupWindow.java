package edu.xmu.software.truckrent;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.PopupWindow;

import edu.xmu.software.truckrent.MyOrderActivity;
import edu.xmu.software.truckrent.R;

/**
 * Created by DELL on 2016/10/19.
 */

public class PersonCenterPopupWindow extends PopupWindow implements View.OnClickListener{
    private View conentView;
    private Activity activity;
    private Button btn_order;
    private Button btn_wallet;
    private Button btn_setup;
    private Button btn_operate_guid;
    public PersonCenterPopupWindow(final Activity context, final int layout) {
        activity=context;
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        conentView = inflater.inflate(layout, null);
//        int h = context.getWindowManager().getDefaultDisplay().getHeight();
//        int w = context.getWindowManager().getDefaultDisplay().getWidth();
        // 设置SelectPicPopupWindow的View
        this.setContentView(conentView);
        // 设置SelectPicPopupWindow弹出窗体的宽
        this.setWidth(LayoutParams.WRAP_CONTENT);
        // 设置SelectPicPopupWindow弹出窗体的高
        this.setHeight(LayoutParams.MATCH_PARENT);
        // 设置SelectPicPopupWindow弹出窗体可点击
        this.setFocusable(true);
        this.setOutsideTouchable(true);
        // 设置SelectPicPopupWindow弹出窗体动画效果
        this.setAnimationStyle(R.style.AnimationLeftFade);
        // 实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable();
        // 点back键和其他地方使其消失,设置了这个才能触发OnDismisslistener ，设置其他控件变化等操作
        this.setBackgroundDrawable(dw);
        // 刷新状态
        this.update();
        backgroundAlpha(0.5f);
        //关闭事件
        this.setOnDismissListener(new popupDismissListener());
        conentView.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                showPopupWindow(v);
                return true;
            }
        });
        init_btn();

    }
    public void init_btn(){
        btn_order=(Button)conentView.findViewById(R.id.btn_order);
        btn_order.setOnClickListener(this);
        btn_wallet=(Button)conentView.findViewById(R.id.btn_wallet);
        btn_wallet.setOnClickListener(this);
        btn_setup=(Button)conentView.findViewById(R.id.btn_setup);
        btn_setup.setOnClickListener(this);
        btn_operate_guid=(Button)conentView.findViewById(R.id.btn_operate_guid);
        btn_operate_guid.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_order:
                Intent intent2 = new Intent(conentView.getContext(),MyOrderActivity.class);
                conentView.getContext().startActivity(intent2);
                break;
            case R.id.btn_wallet:
            case R.id.btn_setup:
            case R.id.btn_operate_guid:
            default:break;
        }
    }
    public void showPopupWindow(View parent) {
        if (!this.isShowing()) {

            this.showAtLocation(parent,Gravity.LEFT,0,50);
        } else {
            backgroundAlpha(1f);
            this.dismiss();
        }
    }

    /**
     * 添加新笔记时弹出的popWin关闭的事件，主要是为了将背景透明度改回来
     *
     */
    class popupDismissListener implements OnDismissListener{

        @Override
        public void onDismiss() {
            backgroundAlpha(1f);
        }

    }
    public void backgroundAlpha(float bgAlpha)
    {
        WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        if (bgAlpha == 1) {
            activity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);//不移除该Flag的话,在有视频的页面上的视频会出现黑屏的bug
        } else {
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);//此行代码主要是解决在华为手机上半透明效果无效的bug
        }
        activity.getWindow().setAttributes(lp);
    }
}
