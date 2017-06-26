package com.example.shang.recyclerviewtest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class StaggeredActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private List<String> mDatas;
    private StaggeredAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initDatas();
        initView();
        myAdapter = new StaggeredAdapter(this,mDatas);
        // 记得recycleView需要layoutManager的支持，否则应用虽不会崩溃，但是显示空白
        //mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL));

        myAdapter.setOnItemClick(new MyViewAdapter.OnMyItemClickListenter() {
            @Override
            public void OnItemClick(View v, int pos) {

            }

            @Override
            public void OnItemLongClick(View v, int pos) {
                myAdapter.deleteData(pos);
            }
        });

        //Context context, int spanCount(数量), int orientation（方向）, boolean reverseLayout（是否反转）
         //  mRecyclerView.setLayoutManager(new GridLayoutManager(this,3, GridLayout.VERTICAL,false));
        //DividerItemDecoration在包package android.support.v7.widget已经有了，不用再去复制了
        //mRecyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
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
                mRecyclerView.setLayoutManager(new GridLayoutManager(this,7,GridLayoutManager.HORIZONTAL,false));
                break;
            case R.id.action_staggered:
                Intent i = new Intent(this,StaggeredActivity.class);
                startActivity(i);
                break;

        }

        return super.onOptionsItemSelected(item);


    }
}
