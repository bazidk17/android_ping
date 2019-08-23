package com.k17.ping;

import android.content.ContentResolver;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import android.widget.Button;
import android.widget.ListView;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by bazid on 21-01-2018.
 */

public class MyPost extends Fragment {
    private ListView lvPosts;
    private PostListAdapter plAdapter;
    private List<Post> liPost;
    String userLoggedIn;
    Button btnrefresh;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab_my_post, container, false);
        lvPosts=(ListView)rootView.findViewById(R.id.lvMyPosts);
        btnrefresh=(Button)rootView.findViewById(R.id.btnRefreshMyPost);

        Intent i=getActivity().getIntent();
        userLoggedIn=i.getStringExtra("loggedInUser");

        btnrefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                liPost=new ArrayList<>();
                new MyPostActivity(getContext(),liPost,plAdapter,lvPosts).execute(userLoggedIn);
            }
        });


        return rootView;
    }
}