package com.example.shang.recyclerviewtest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private List<String> mDatas;
    private MyViewAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initDatas();
        initView();
        myAdapter = new MyViewAdapter(this,mDatas);
        // 记得recycleView需要layoutManager的支持，否则应用虽不会崩溃，但是显示空白
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        myAdapter.setOnItemClick(new MyViewAdapter.OnMyItemClickListenter() {
            @Override
            public void OnItemClick(View v, int pos) {
                Toast.makeText(MainActivity.this,"click: "+pos,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void OnItemLongClick(View v, int pos) {
                Toast.makeText(MainActivity.this,"long click: "+pos,Toast.LENGTH_SHORT).show();
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
