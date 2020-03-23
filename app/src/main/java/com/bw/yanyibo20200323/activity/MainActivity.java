package com.bw.yanyibo20200323.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.bw.yanyibo20200323.R;
import com.bw.yanyibo20200323.base.BaseActivity;
import com.bw.yanyibo20200323.util.NetUtil;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

/**
 *
 * 作者：闫艺博
 * 时间：2020/3/23
 * 功能：主类继承BaseActivity
 *
 * */
public class MainActivity extends BaseActivity {
    private PullToRefreshListView listView;
    private int page = 1;

    @Override
    public int layoutid() {
        return R.layout.activity_main;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        listView=findViewById(R.id.listview);

        listView.setMode(PullToRefreshBase.Mode.BOTH);

        listView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {

            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {

            }
        });
    }

    @Override
    public void initData() {
        if(NetUtil.getInstance().hasNet(this)){
            NetUtil.getInstance().doget("http://blog.zhaoliang5156.cn/api/shop/shop1.json");
            Gson gson = new Gson();
        }else{
            Toast.makeText(MainActivity.this,"请检查网络",Toast.LENGTH_SHORT).show();
        }
    }
}
