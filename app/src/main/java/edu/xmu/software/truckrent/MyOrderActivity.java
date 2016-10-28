package edu.xmu.software.truckrent;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by DELL on 2016/10/25.
 */

public class MyOrderActivity extends Activity implements View.OnClickListener{
    private Button btnBack;
    private Button btnAllDelivery;
    private Button btnNoPaidOrder;
    private Button btnExecutingOrder;
    private Button btnExecutedOrder;
    private Button btnCarrentalOrder;
    private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //自定义标题
        requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        setContentView(R.layout.activity_my_order);
        //设置标题为某个layout
        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.activity_common_titlebar);
        initBtn();
        TextView tvTitle=(TextView)findViewById(R.id.tv_title);
        tvTitle.setText("我的订单");
        listView=(ListView)findViewById(R.id.orderList);
        btnAllDelivery.setBackgroundDrawable(this.getResources().getDrawable(R.drawable.order_get_button));
        show(getAllDeliveryOrder());
    }
    private void initBtn(){
        btnBack=(Button)findViewById(R.id.btn_back);
        btnBack.setOnClickListener(this);
        btnAllDelivery=(Button)findViewById(R.id.btn_alldelivery);
        btnAllDelivery.setOnClickListener(this);
        btnCarrentalOrder=(Button)findViewById(R.id.btn_carrentalorder);
        btnCarrentalOrder.setOnClickListener(this);
        btnExecutedOrder=(Button)findViewById(R.id.btn_executedorder);
        btnExecutedOrder.setOnClickListener(this);
        btnExecutingOrder=(Button)findViewById(R.id.btn_executingorder);
        btnExecutingOrder.setOnClickListener(this);
        btnNoPaidOrder=(Button)findViewById(R.id.btn_nopaidorder);
        btnNoPaidOrder.setOnClickListener(this);
    }
    private void show(List<Map<String,Object>> orders){
        SimpleAdapter adapter = new SimpleAdapter(this, orders, R.layout.my_order_item,
                new String[]{"No", "getTime","origin", "destination",
                        "state","cargo_name","cargo_weight","cargo_volume","price"},
                new int[]{R.id.tv_order_number, R.id.tv_order_get_time,R.id.tv_order_origin, R.id.tv_order_destination,
                            R.id.tv_order_state,R.id.tv_order_name,R.id.tv_order_weight,R.id.tv_order_volume});
        listView.setAdapter(adapter);
    }
    /*
    * 获取所有订单数据
    * */
    private List<Map<String,Object>> getAllDeliveryOrder(){
        List<Map<String,Object>> datas=new ArrayList<>();
        Map<String,Object> map= new HashMap<>();
        map.put("No","E1750607");
        map.put("getTime","7月10日");
        map.put("origin","厦门");
        map.put("destination","广州");
        map.put("state","已完成");
        map.put("cargo_name","日用品");
        map.put("cargo_weight","2.00吨");
        map.put("cargo_volume","38.00立方");
        map.put("price","￥355.00");
        datas.add(map);
        map=new HashMap<>();
        map.put("No","D1750607");
        map.put("getTime","8月9日");
        map.put("origin","北京");
        map.put("destination","上海");
        map.put("state","已完成");
        map.put("cargo_name","工地设备");
        map.put("cargo_weight","15.00吨");
        map.put("cargo_volume","--");
        map.put("price","￥4250.00");
        datas.add(map);
        return datas;
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
            /*针对有实体对象的显示
            Person person = (Person) lView.getItemAtPosition(position);
            进入订单详情界面
            */

        }

    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_back:
                finish();
                break;
            case R.id.btn_alldelivery:
                btnNoPaidOrder.getBackground().setAlpha(0);
                btnExecutedOrder.getBackground().setAlpha(0);
                btnExecutingOrder.getBackground().setAlpha(0);
                btnAllDelivery.setBackground(this.getResources().getDrawable(R.drawable.order_get_button));
                show(getAllDeliveryOrder());
                break;
            case R.id.btn_carrentalorder:

            case R.id.btn_nopaidorder:
                btnAllDelivery.getBackground().setAlpha(0);
                btnExecutedOrder.getBackground().setAlpha(0);
                btnExecutingOrder.getBackground().setAlpha(0);
                btnNoPaidOrder.setBackground(this.getResources().getDrawable(R.drawable.order_get_button));
                show(null);
                break;
            case R.id.btn_executedorder:
                btnNoPaidOrder.getBackground().setAlpha(0);
                btnAllDelivery.getBackground().setAlpha(0);
                btnExecutingOrder.getBackground().setAlpha(0);
                btnExecutedOrder.setBackground(this.getResources().getDrawable(R.drawable.order_get_button));
                show(getAllDeliveryOrder());
                break;
            case R.id.btn_executingorder:
                btnNoPaidOrder.getBackground().setAlpha(0);
                btnExecutedOrder.getBackground().setAlpha(0);
                btnAllDelivery.getBackground().setAlpha(0);
                btnExecutingOrder.setBackground(this.getResources().getDrawable(R.drawable.order_get_button));
                show(null);
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
