package adapter;

import android.content.Context;
import android.graphics.drawable.Animatable;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.controller.BaseControllerListener;
import com.facebook.drawee.controller.ControllerListener;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.image.ImageInfo;
import com.facebook.imagepipeline.image.QualityInfo;
import com.jude.utils.JUtils;
import com.panshen.ajiandan.ajiandan.R;

import java.util.List;

import model.bean.boring;
import utils.JandanUtils;

public class PicAdapter extends ArrayAdapter {
    Context mContext;
    LayoutInflater mInflater;
    List mlist;

    public PicAdapter(Context context, List list) {
        super(context, 0, list);
        mContext = context;
        mInflater = LayoutInflater.from(context);
        mlist = list;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final boring g = (boring) getItem(position);
        final PicViewholder picViewholder;
        if (convertView == null) {
            picViewholder = new PicViewholder();
            convertView = mInflater.inflate(R.layout.fragment_pic_listview_item, null);
            picViewholder.pic_tv_like = (TextView) convertView.findViewById(R.id.pic_tv_like);
            picViewholder.pic_tv_unlike = (TextView) convertView.findViewById(R.id.pic_tv_dislike);
            picViewholder.pic_iv_thumbimg = (SimpleDraweeView) convertView.findViewById(R.id.pic_draweeview);
            picViewholder.pic_frame = (LinearLayout) convertView.findViewById(R.id.pic_fl);
            convertView.setTag(picViewholder);
        } else {
            picViewholder = (PicViewholder) convertView.getTag();
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
                    LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) picViewholder.pic_iv_thumbimg.getLayoutParams();
                    layoutParams.height = imageInfo.getHeight() * 2;
                    layoutParams.width = JUtils.getScreenWidth();
                    picViewholder.pic_frame.updateViewLayout(picViewholder.pic_iv_thumbimg, layoutParams);
                    picViewholder.pic_tv_like.setText(JandanUtils.TrimNumber(g.getLike()));
                }
            }

            @Override
            public void onIntermediateImageSet(String id, @Nullable ImageInfo imageInfo) {

            }

            @Override
            public void onFailure(String id, Throwable throwable) {
            }
        };


        //加载gif动画
        DraweeController controller = Fresco.newDraweeControllerBuilder()
                .setUri(Uri.parse(g.getThumburl()))
                //.setAutoPlayAnimations(true)
                .setTapToRetryEnabled(true)
                .setControllerListener(controllerListener)
                .build();
        picViewholder.pic_iv_thumbimg.setController(controller);

        // picViewholder.pic_iv_thumbimg.setImageURI(Uri.parse(g.getThumburl()));
        //picViewholder.pic_iv_thumbimg.setHierarchy(mSimpleGDHBuilder.getInstance(mContext).getHierarchy());
//        picViewholder.pic_tv_like.setText(g.getLike());
//        picViewholder.pic_tv_unlike.setText(g.getDislike());
        return convertView;
    }

    public void ondatachange(List list) {
        mlist.addAll(list);
        notifyDataSetChanged();
    }
}
//ImageLoader.getInstance().loadImage(g.getThumburl(), options, new SimpleImageLoadingListener() {
//            @Override
//            public void onLoadingComplete(String imageUri, View view, final Bitmap loadedImage) {
//                super.onLoadingComplete(imageUri, view, loadedImage);
//                ViewGroup.LayoutParams params = picViewholder.pic_iv_thumbimg.getLayoutParams();
//                params.width = JUtils.getScreenWidth();
//                picViewholder.pic_iv_thumbimg.setLayoutParams(params);
//                picViewholder.pic_iv_thumbimg.setMaxWidth(JUtils.getScreenWidth());
//                picViewholder.pic_iv_thumbimg.setMaxHeight(JUtils.getScreenWidth() * 5);
//                PropertyValuesHolder propertyValuesHolder = PropertyValuesHolder.ofFloat("alpha", 0.5f, 1.0f);
//                ObjectAnimator objanim = ObjectAnimator.ofPropertyValuesHolder(picViewholder.pic_iv_thumbimg, propertyValuesHolder);
//                objanim.setDuration(500);
//                objanim.addListener(new AnimatorListenerAdapter() {
//                    @Override
//                    public void onAnimationEnd(Animator animation) {
//                        super.onAnimationEnd(animation);
//                        picViewholder.pic_iv_thumbimg.setImageBitmap(loadedImage);
//                    }
//                });
//                //造成卡顿
//                //objanim.start();
//                picViewholder.pic_iv_thumbimg.setImageBitmap(loadedImage);
//            }
//        });