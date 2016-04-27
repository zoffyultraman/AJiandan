package adapter;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Animatable;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.controller.BaseControllerListener;
import com.facebook.drawee.controller.ControllerListener;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.image.ImageInfo;
import com.facebook.imagepipeline.image.QualityInfo;
import com.jude.utils.JUtils;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;
import com.panshen.ajiandan.ajiandan.R;
import com.panshen.ajiandan.ajiandan.mSimpleGDHBuilder;

import java.util.List;

import model.bean.girl;

public class MeiziAdapter extends ArrayAdapter {
    Context mContext;
    LayoutInflater mInflater;
    List mlist;
    DisplayImageOptions options;

    public MeiziAdapter(Context context, List list) {
        super(context, 0, list);
        mContext = context;
        mInflater = LayoutInflater.from(context);
        mlist = list;
        options = new DisplayImageOptions.Builder()
                .showImageOnFail(R.drawable.loadfaild)
                .showImageOnLoading(R.drawable.loading)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .build();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        girl g = (girl) getItem(position);
        final MeiziViewholder picViewholder;
        if (convertView == null) {
            picViewholder = new MeiziViewholder();
            convertView = mInflater.inflate(R.layout.fragment_meizi_listview_item, null);
            picViewholder.tv_like = (TextView) convertView.findViewById(R.id.tv_like);
            picViewholder.tv_unlike = (TextView) convertView.findViewById(R.id.tv_dislike);
            picViewholder.iv_thumbimg = (SimpleDraweeView) convertView.findViewById(R.id.meizi_draweeview);
            picViewholder.fl_fram = (FrameLayout) convertView.findViewById(R.id.meizi_fl);
            convertView.setTag(picViewholder);
        } else {
            picViewholder = (MeiziViewholder) convertView.getTag();
        }

        ControllerListener controllerListener = new BaseControllerListener<ImageInfo>() {
            @Override
            public void onFinalImageSet(String id, @Nullable ImageInfo imageInfo, @Nullable Animatable anim) {
                if (imageInfo == null) {
                    return;
                }
                QualityInfo qualityInfo = imageInfo.getQualityInfo();
                if (qualityInfo.isOfGoodEnoughQuality()) {
                    Log.i("image height ", imageInfo.getHeight() + "");
                    FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) picViewholder.iv_thumbimg.getLayoutParams();
                    layoutParams.height = imageInfo.getHeight() * 2;
                    layoutParams.width = JUtils.getScreenWidth();
                    picViewholder.fl_fram.updateViewLayout(picViewholder.iv_thumbimg, layoutParams);
                }
            }

            @Override
            public void onIntermediateImageSet(String id, @Nullable ImageInfo imageInfo) {

            }

            @Override
            public void onFailure(String id, Throwable throwable) {
            }
        };

        DraweeController controller = Fresco.newDraweeControllerBuilder()
                .setUri(Uri.parse(g.getThumburl()))
                //.setAutoPlayAnimations(true)
                .setTapToRetryEnabled(true)
                .setControllerListener(controllerListener)
                .build();

        picViewholder.iv_thumbimg.setController(controller);

        //picViewholder.iv_thumbimg.setHierarchy(mSimpleGDHBuilder.getInstance(mContext).getHierarchy());
//        picViewholder.tv_like.setText(g.getLike());
//        picViewholder.tv_unlike.setText(g.getDislike());

        return convertView;
    }

    public void ondatachange(List list) {
        mlist.addAll(list);
        notifyDataSetChanged();
    }
}
