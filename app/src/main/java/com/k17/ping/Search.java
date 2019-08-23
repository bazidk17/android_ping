package com.k17.ping;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bazid on 21-01-2018.
 */

public class Search extends Fragment {
    private ListView lvPosts;
    private PostListAdapter plAdapter;
    private List<Post> liPost;
    EditText searchNumber;
    String phone;
    Button btnsearch;
    boolean valid;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab_search, container, false);

        lvPosts=(ListView)rootView.findViewById(R.id.lvSearchPosts);
        btnsearch=(Button)rootView.findViewById(R.id.btnSearch);
        searchNumber=(EditText) rootView.findViewById(R.id.txtSearchNumber);


        btnsearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                valid=true;
                liPost=new ArrayList<>();
                phone=""+searchNumber.getText().toString().trim();
                if (phone.length()<10){
                    searchNumber.setError("Phone Number must contain 10 digits");
                    valid=false;
                }
                if(valid) {
                    new MyPostActivity(getContext(), liPost, plAdapter, lvPosts).execute(phone);
                }
            }
        });
        return rootView;
    }
}
