package com.oscar.nat_at.MVPCore.View;

import android.app.Activity;

public interface BaseView {
    void initView();
    void setPresenter();
    Activity getActivityInstance();
}
