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
import com.akuya.mydear.bean.LoginUserBean;
import com.akuya.mydear.bean.ReduceFoodBean;
import com.akuya.mydear.bean.SuggestBean;

import org.litepal.LitePal;

import java.util.List;

public class ThirAdapter extends RecyclerView.Adapter<ThirAdapter.MyViewHolder> {

    private List<LoginUserBean> loginUserBeans = LitePal.findAll(LoginUserBean.class);
    private LoginUserBean loginUserBean;
    private List<ReduceFoodBean> data;
    private Context context;

    public ThirAdapter(List<ReduceFoodBean> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=View.inflate(context, R.layout.recycler_item_t,null);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.Food_name.setText(data.get(position).getUserName());
        holder.Food_harm.setText(data.get(position).getHarm());
        holder.Food_time.setText(data.get(position).getLasttime());
        if(data.get(position).getPicture()!=null) {

            holder.Food_pic.setImageBitmap(ImageUtil.base64ToImage(data.get(position).getPicture()));
        }
        holder.Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for(int i=0;i<loginUserBeans.size();i++){
                    if(loginUserBeans.get(i).getReduceFoodBeans()==data){
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

        LitePal.delete(ReduceFoodBean.class,data.get(position).getId());
        data.remove(position);
        //删除动画
        notifyItemRemoved(position);
        notifyDataSetChanged();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        private Button Delete;
        private TextView Food_name,Food_time,Food_harm;
        private ImageView Food_pic;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            Delete=itemView.findViewById(R.id.btn_delete);
            Food_name=itemView.findViewById(R.id.tv_food_name3);
            Food_harm=itemView.findViewById(R.id.tv_harm);
            Food_time=itemView.findViewById(R.id.tv_time);
            Food_pic=itemView.findViewById(R.id.iv_foodpic3);
        }
    }
}
