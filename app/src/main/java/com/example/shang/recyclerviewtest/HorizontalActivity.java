package com.example.shang.recyclerviewtest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shang on 2017/6/26.
 */

public class HorizontalActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private List<String> mDatas;
    private MyHorizontalViewAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initDatas();
        initView();
        myAdapter = new MyHorizontalViewAdapter(this,mDatas);

        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(7,StaggeredGridLayoutManager.HORIZONTAL));

        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        mRecyclerView.setAdapter(myAdapter);
    }

    private void initView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.id_recyclerView);
    }

    private void initDatas() {
        mDatas = new ArrayList<>();
        for (int i = 'A';i<='z';i++){
            mDatas.add(""+(char)i);
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        switch (id){
            case R.id.action_add:
                myAdapter.addData(1);
                break;
            case R.id.action_delete:
                myAdapter.deleteData(1);
                break;

            case R.id.action_list:
                mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
                break;
            case R.id.action_grid:
                mRecyclerView.setLayoutManager(new GridLayoutManager(this,3));
                break;
            case R.id.action_grad_hor:
                Intent i = new Intent(this,HorizontalActivity.class);
                startActivity(i);
                break;
            case R.id.action_staggered:
                Intent ii = new Intent(this,StaggeredActivity.class);
                startActivity(ii);
                break;

        }
        return super.onOptionsItemSelected(item);


    }

}
