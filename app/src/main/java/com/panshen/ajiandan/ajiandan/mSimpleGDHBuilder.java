package com.panshen.ajiandan.ajiandan;

import android.content.Context;

import com.facebook.drawee.drawable.ProgressBarDrawable;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;

/**
 * Created by Zhou7 on 2016/4/19.
 */
public class mSimpleGDHBuilder {
    private volatile static mSimpleGDHBuilder instance;
    private static Context mContext;

    private mSimpleGDHBuilder(Context mContext) {
        this.mContext = mContext;
    }

    static GenericDraweeHierarchyBuilder builder;

    public static mSimpleGDHBuilder getInstance(Context context) {

        if (instance == null) {
            synchronized (mSimpleGDHBuilder.class) {
                if (instance == null) {
                    instance = new mSimpleGDHBuilder(context);
                    builder = new GenericDraweeHierarchyBuilder(mContext.getResources());
                }

            }
        }
        return instance;
    }
    public GenericDraweeHierarchy getHierarchy() {
       return  builder
                .setFadeDuration(300)
               .setProgressBarImage(new ProgressBarDrawable())
                .build();
    }

}
