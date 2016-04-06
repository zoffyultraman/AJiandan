package com.panshen.ajiandan.ajiandan;

import android.app.Application;

import com.jude.beam.Beam;
import com.jude.beam.Utils;
import com.jude.utils.JUtils;

import config.Config;

public class APP extends Application{
    public  static APP instance;
    public static  APP getInstance(){
        return instance;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        instance=this;
        Beam.init(this);
        JUtils.initialize(this);
        JUtils.setDebug(Config.DEBUG, "response");
    }
}
