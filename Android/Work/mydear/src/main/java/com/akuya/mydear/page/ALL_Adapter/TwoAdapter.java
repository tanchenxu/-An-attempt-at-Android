package com.akuya.mydear.page.ALL_Adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.akuya.mydear.ImageUtil;
import com.akuya.mydear.R;
import com.akuya.mydear.bean.FoodBean;
import com.akuya.mydear.bean.LoginUserBean;
import com.akuya.mydear.bean.SuggestBean;

import org.litepal.LitePal;

import java.util.List;

public class TwoAdapter extends RecyclerView.Adapter<TwoAdapter.MyViewHolder> {

    private List<LoginUserBean> loginUserBeans = LitePal.findAll(LoginUserBean.class);
    private LoginUserBean loginUserBean;
    private List<SuggestBean> data;
    private Context context;

    public TwoAdapter(List<SuggestBean> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=View.inflate(context, R.layout.recycler_item,null);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.Foodname.setText(data.get(position).getFoodName());
        holder.Foodtype.setText(data.get(position).getInto());
        if(data.get(position).getPicture()!=null) {
            holder.Foodpic.setImageBitmap(ImageUtil.base64ToImage(data.get(position).getPicture()));
        }
        holder.More.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setIcon(R.mipmap.ic_launcher_round)
               .setTitle(data.get(position).getFoodName())
               .setMessage(data.get(position).getNutri()+"\n"+data.get(position).getKaluli()+"\n"+data.get(position).getBinifit())
                        .create()
                        .show();
            }
        });

        holder.Delite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for(int i=0;i<loginUserBeans.size();i++){
                    if(loginUserBeans.get(i).getSuggestBeans()==data){
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
        return data==null?0:data.size();
    }

    public void removeData(LoginUserBean loginUserBean ,int position) {

        LitePal.delete(SuggestBean.class,data.get(position).getId());
        data.remove(position);
        //删除动画
        notifyItemRemoved(position);
        notifyDataSetChanged();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private Button Delite,More;
        private TextView Foodname,Foodtype;
        private ImageView Foodpic;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            Foodname=itemView.findViewById(R.id.tv_food_name2);
            Foodtype=itemView.findViewById(R.id.tv_foodtype2);
            Foodpic=itemView.findViewById(R.id.iv_foodpic2);
            Delite=itemView.findViewById(R.id.bt_delete);
            More=itemView.findViewById(R.id.bt_more);



            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(mOnItemClickListener !=null){
                        mOnItemClickListener.onRecyclierItemClick(getAdapterPosition());
                    }
                }
            });
        }
    }

    private OnRecyclierItemClickListener mOnItemClickListener;

    public void setRecyclierItemClickListener(OnRecyclierItemClickListener listener){
        mOnItemClickListener=listener;

    }

    public  interface  OnRecyclierItemClickListener{
        void onRecyclierItemClick(int position);
    }


}

