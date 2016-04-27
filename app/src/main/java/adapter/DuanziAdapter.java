package adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.panshen.ajiandan.ajiandan.R;

import java.util.List;

import model.bean.duanzi;

public class DuanziAdapter extends ArrayAdapter {
    Context mContext;
    LayoutInflater mInflater;
    List mlist;

    public DuanziAdapter(Context context, List list) {
        super(context, 0, list);
        mContext = context;
        mInflater = LayoutInflater.from(context);
        mlist = list;
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        duanzi duanzi = (duanzi) getItem(position);
        DuanziViewholder viewholder;
        if (convertView == null) {
            viewholder = new DuanziViewholder();
            convertView = mInflater.inflate(R.layout.fragment_duanzi_item, null);
            viewholder.tv_content = (TextView) convertView.findViewById(R.id.tv_content);
            viewholder.tv_author = (TextView) convertView.findViewById(R.id.tv_author);
            convertView.setTag(viewholder);
        } else {
            viewholder = (DuanziViewholder) convertView.getTag();
        }
        viewholder.tv_content.setText(duanzi.getContent());
        viewholder.tv_author.setText(duanzi.getAuthor());
        return convertView;
    }

    public void ondatachange(List list) {
        mlist.addAll(list);
        notifyDataSetChanged();
    }
}
