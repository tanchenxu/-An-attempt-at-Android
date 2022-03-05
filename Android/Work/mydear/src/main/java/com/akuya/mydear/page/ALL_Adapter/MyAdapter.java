package com.akuya.mydear.page.ALL_Adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.akuya.mydear.ImageUtil;
import com.akuya.mydear.R;
import com.akuya.mydear.bean.FoodBean;
import com.akuya.mydear.bean.LoginUserBean;

import org.litepal.LitePal;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{

    private List<LoginUserBean> loginUserBeans =LitePal.findAll(LoginUserBean.class);
    private LoginUserBean loginUserBean;
    private List<FoodBean> data;
    private Context context;


    public MyAdapter(List<FoodBean> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view =View.inflate(context, R.layout.list_item,null);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder,int position) {
        holder.tv1.setText(data.get(position).getname());
        holder.tv2.setText(data.get(position).getcontent());
        holder.tv3.setText(data.get(position).getFunction());
        holder.tv4.setText(data.get(position).getTypes());
        if(data.get(position).getPicture()!=null) {
            holder.imageView.setImageBitmap(ImageUtil.base64ToImage(data.get(position).getPicture()));
        }
        holder.bt_decline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for(int i=0;i<loginUserBeans.size();i++){
                    if(loginUserBeans.get(i).getFoodBeans()==data){
                        loginUserBean=loginUserBeans.get(i);
                        Log.e("akuya", "onCreate: i find it"+loginUserBean.getUserName()+loginUserBean.getId());
                    }
                }
                removeData(loginUserBean,position);

            }
        });

    }

    @Override
    public int getItemCount() {
        return data== null? 0:data.size();
    }





    public void removeData(LoginUserBean loginUserBean ,int position) {

        LitePal.delete(FoodBean.class,data.get(position).getId());
        data.remove(position);
        //删除动画
        notifyItemRemoved(position);
        notifyDataSetChanged();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
         TextView tv1;
         TextView tv2;
         Button bt_decline;
         TextView tv3;
         ImageView imageView;
         TextView tv4;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tv1 =itemView.findViewById(R.id.textView);
            tv2 =itemView.findViewById(R.id.textView2);
            bt_decline =itemView.findViewById(R.id.bt_decline);
            tv3 =itemView.findViewById(R.id.textView3);
            imageView =itemView.findViewById(R.id.imageView3);
            tv4 =itemView.findViewById(R.id.textView4);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mOnItemClickListener!=null){
                    mOnItemClickListener.onRecyclerItemClick(getAdapterPosition());
                }
            }
        });
        }
    }

    private OnRecyclierItemClickListener mOnItemClickListener;

    public void setRecyclerItemClickListener(OnRecyclierItemClickListener listener){
        mOnItemClickListener =listener;
    }

    public interface OnRecyclierItemClickListener{
        void onRecyclerItemClick(int position);

    }

}
