package com.nszx.bbs.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nszx.bbs.bean.List_Bean;
import com.nszx.bbs.R;

import java.util.List;

public class ListViewAdapter extends ArrayAdapter<List_Bean> {
    private int resourceId;

    public ListViewAdapter(Context context, int textViewResourceId, List<List_Bean> objects) {
        super(context, textViewResourceId, objects);
        resourceId = textViewResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        List_Bean list_bean = getItem(position);
        View view;
        ViewHolder viewHolder ;

        if (convertView == null){
            view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.user_head = view.findViewById(R.id.image_userhead);
            viewHolder.star = view.findViewById(R.id.image_star);
            viewHolder.title = view.findViewById(R.id.text_title);
            viewHolder.massage = view.findViewById(R.id.text_message);
            view.setTag(viewHolder);
        }else {
            view =convertView;
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.user_head.setImageResource(list_bean.getUse_head());
        viewHolder.star.setImageResource(list_bean.getStar());
        viewHolder.title.setText(list_bean.getItem_title());
        viewHolder.massage.setText(list_bean.getItem_message());
        return view;
    }
    class ViewHolder{
        ImageView user_head;
        ImageView star;
        TextView title;
        TextView massage;
    }
}
