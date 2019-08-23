package com.k17.ping;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by bazid on 18-03-2018.
 */

public class PostListAdapter extends BaseAdapter {

    private Context context;
    private List<Post> mPostList;

    public PostListAdapter(Context context, List<Post> mPostList) {
        this.context = context;
        this.mPostList = mPostList;
    }

    @Override
    public int getCount() {
        return mPostList.size();
    }

    @Override
    public Object getItem(int position) {
        return mPostList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v=View.inflate(context,R.layout.item_post,null);
        TextView tvText=(TextView)v.findViewById(R.id.lblPostText);
        TextView tvDate=(TextView)v.findViewById(R.id.lblPostDate);
        TextView tvTime=(TextView)v.findViewById(R.id.lblPostTime);
        ImageView ivPost=(ImageView)v.findViewById(R.id.ivShownImage);

        tvText.setText(mPostList.get(position).getText());
        tvDate.setText(mPostList.get(position).getDate());
        tvTime.setText(mPostList.get(position).getTime());
        ivPost.setImageBitmap(mPostList.get(position).getImage());
        v.setTag(mPostList.get(position).getId());
        return v;
    }
}
