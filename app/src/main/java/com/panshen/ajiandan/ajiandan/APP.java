package com.panshen.ajiandan.ajiandan;

import android.app.Application;
import android.os.Environment;

import com.facebook.cache.disk.DiskCacheConfig;
import com.facebook.common.logging.FLog;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.core.ImagePipelineConfig;
import com.jude.beam.Beam;
import com.jude.beam.expansion.BeamBaseActivity;
import com.jude.beam.expansion.list.ListConfig;
import com.jude.beam.expansion.overlay.ViewExpansionDelegate;
import com.jude.beam.expansion.overlay.ViewExpansionDelegateProvider;
import com.jude.http.RequestManager;
import com.jude.utils.JUtils;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiscCache;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.UsingFreqLimitedMemoryCache;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;
import com.nostra13.universalimageloader.utils.StorageUtils;

import java.io.File;

public class APP extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        JUtils.initialize(this);
        JUtils.setDebug(true, "BeamTest");
        Beam.init(this);
        FLog.setMinimumLoggingLevel(FLog.VERBOSE);
        DiskCacheConfig diskCacheConfig = DiskCacheConfig.newBuilder(this)
                .setBaseDirectoryPath(new File(Environment.getExternalStorageDirectory().getAbsoluteFile(),"AJandan"))
                .setBaseDirectoryName("AJandan")
                .setMaxCacheSize(200*1024*1024)//200MB
                .build();
        ImagePipelineConfig imagePipelineConfig = ImagePipelineConfig.newBuilder(this)
                .setMainDiskCacheConfig(diskCacheConfig)
                .build();
        Fresco.initialize(this);
//        Beam.setViewExpansionDelegateProvider(new ViewExpansionDelegateProvider() {
//            @Override
//            public ViewExpansionDelegate createViewExpansionDelegate(BeamBaseActivity activity) {
//                return null;
//            }
//        });
//        File cacheDir = StorageUtils.getOwnCacheDirectory(this, "imageloader/Cache");
//        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this)
//                .memoryCacheExtraOptions(480, 800) // max width, max height，即保存的每个缓存文件的最大长宽
//                .threadPoolSize(3) //线程池内加载的数量
//                .threadPriority(Thread.NORM_PRIORITY - 2)
//                .denyCacheImageMultipleSizesInMemory()
//                .diskCacheFileNameGenerator(new Md5FileNameGenerator()) //将保存的时候的URI名称用MD5 加密
//                .memoryCache(new UsingFreqLimitedMemoryCache(2 * 1024 * 1024)) // You can pass your own memory cache implementation/你可以通过自己的内存缓存实现
//                .memoryCacheSize(2 * 1024 * 1024) // 内存缓存的最大值
//                .diskCacheSize(50 * 1024 * 1024)  // 50 Mb sd卡(本地)缓存的最大值
//                .tasksProcessingOrder(QueueProcessingType.LIFO)
//                // 由原先的discCache -> diskCache
//                .diskCache(new UnlimitedDiscCache(cacheDir))//自定义缓存路径
//                .imageDownloader(new BaseImageDownloader(this, 5 * 1000, 30 * 1000)) // connectTimeout (5 s), readTimeout (30 s)超时时间
//                .writeDebugLogs() // Remove for release app
//                .build();
//        //全局初始化此配置
//        ImageLoader.getInstance().init(config);

        RequestManager.getInstance().init(this);
    }
}
