package com.example.shang.recyclerviewtest;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by shang on 2017/6/26.
 */

public class MyHorizontalViewAdapter extends RecyclerView.Adapter<MyHorizontalViewAdapter.MyViewHolder>{

    private Context mContext;
    protected List<String> mDatas;
    private LayoutInflater mLayoutInflater;
    private OnMyItemClickListenter mOnItemClickListener;

    public interface OnMyItemClickListenter{
        void OnItemClick(View v, int pos);
        void OnItemLongClick(View v, int pos);
    }

    public void setOnItemClick(OnMyItemClickListenter mOnItemClickListener ){
        this.mOnItemClickListener = mOnItemClickListener;
    }

    public MyHorizontalViewAdapter(Context context, List<String>datas){
        mContext = context;
        mDatas = datas;
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.itemhorizontallayout,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        holder.mItemTX.setText(mDatas.get(position));
        setUpItemEvent(holder);

    }


    // 把方法提取出来是为了给子类使用
    protected void setUpItemEvent(final MyViewHolder holder) {
        if (mOnItemClickListener!=null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // 通过这样的方式，点击了添加之后的position就会正确了
                    int pos = holder.getAdapterPosition();
                    mOnItemClickListener.OnItemClick(holder.itemView,pos);
                }
            });

            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    int pos = holder.getAdapterPosition();
                    mOnItemClickListener.OnItemLongClick(holder.itemView,pos);
                    return false;
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    public void addData(int pos){
        mDatas.add(pos,"insert one");
        notifyItemInserted(pos);
    }

    public void deleteData(int pos){
        mDatas.remove(pos);
        notifyItemRemoved(pos);
    }




    class MyViewHolder extends RecyclerView.ViewHolder{

        //  不能加private，否则调用不了
        TextView mItemTX;
        public MyViewHolder(View itemView) {
            super(itemView);
            mItemTX = (TextView) itemView.findViewById(R.id.id_itemtx2);

        }
    }
}


