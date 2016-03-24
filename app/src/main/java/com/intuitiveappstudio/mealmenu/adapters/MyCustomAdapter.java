package com.intuitiveappstudio.mealmenu.adapters;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.intuitiveappstudio.mealmenu.R;
import com.intuitiveappstudio.mealmenu.common.AppLog;
import com.intuitiveappstudio.mealmenu.models.ListModel;

import java.util.List;

public class MyCustomAdapter extends ArrayAdapter<ListModel> {
    int res;
    public MyCustomAdapter(Context context, int resource, List<ListModel> items) {
        super(context, resource, items);
         res=resource;
    }

    private class ViewHolder {
        TextView tv_title;
        ImageView image;
        RelativeLayout rl_main;
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        final ViewHolder holder;
        AppLog.logInfo("ConvertView", String.valueOf(position));

        if (convertView == null) {
            LayoutInflater vi = (LayoutInflater) getContext().getSystemService(
                    Context.LAYOUT_INFLATER_SERVICE);
            convertView = vi.inflate(res, null);
            holder = new ViewHolder();
            holder.tv_title = (TextView) convertView.findViewById(R.id.tv_title);
            holder.image = (ImageView) convertView.findViewById(R.id.iv_image);
            holder.rl_main=(RelativeLayout)convertView.findViewById(R.id.rl_main);
            holder.image.setTag(position);

            holder.tv_title.setTag(position);
            holder.image.setSelected(true);
            holder.rl_main.setTag(position);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
            holder.tv_title.setText("");

        }
        ListModel listModel = getItem(position);

        holder.tv_title.setText(listModel.getTitle());


        return convertView;

    }

}
