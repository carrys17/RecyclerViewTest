package com.example.shang.recyclerviewtest;

import android.content.Context;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shang on 2017/6/26.
 */

// 直接继承前面的adapter，不用再写那个回调接口，而且代码也变简洁了
public class StaggeredAdapter extends MyViewAdapter{


    private List<Integer>mHeights;

    public StaggeredAdapter(Context context, List<String>datas){
        super(context,datas);

        mHeights = new ArrayList<>();
        for (int i = 0; i<mDatas.size();i++){
            mHeights.add((int) (100+Math.random()*300));
        }
    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.mItemTX.setText(mDatas.get(position));
        ViewGroup.LayoutParams lp = holder.itemView.getLayoutParams();
        lp.height = mHeights.get(position);
        holder.itemView.setLayoutParams(lp);


        setUpItemEvent(holder);
    }



}
