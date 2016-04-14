package com.panshen.ajiandan.ajiandan;

import android.app.Application;

import com.jude.beam.Beam;
import com.jude.utils.JUtils;

public class APP extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        Beam.init(this);
        JUtils.initialize(this);
        JUtils.setDebug(Config.DEBUG, "DEBUG");
    }
}
