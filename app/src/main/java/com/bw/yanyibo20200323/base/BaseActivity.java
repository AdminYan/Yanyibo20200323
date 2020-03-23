package com.bw.yanyibo20200323.base;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 *
 * 作者：闫艺博
 * 时间：2020/3/23
 * 功能：抽象类BaseActivity
 *
 * */
public abstract class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layoutid());
        initView(savedInstanceState);
        initData();
    }

    public abstract int layoutid();
    public abstract void initView( Bundle savedInstanceState);
    public abstract void initData();
}
