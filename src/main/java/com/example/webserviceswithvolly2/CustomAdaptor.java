package com.example.webserviceswithvolly2;

import android.content.Context;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.webserviceswithvolly2.MainActivity;
import com.example.webserviceswithvolly2.R;

import java.util.ArrayList;
import java.util.HashMap;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

class  CustomAdaptor extends RecyclerView.Adapter<CustomAdaptor.ViewHolder> {
    Context c;
    ArrayList<HashMap> data = new ArrayList<>();
    public CustomAdaptor(MainActivity mainActivity, ArrayList<HashMap> data) {
        Log.d("data2", data+"");
        c=mainActivity;
        this.data = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Context c = parent.getContext();

        //Inflater the custom layout
        View contactView = LayoutInflater.from(c).inflate(R.layout.custom , parent, false);

        //Return the new Holder instance
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.d("data1", data.get(position).get("name")+"" );
        Glide.with(c).load(data.get(position).get("imgUrl")+"").into(holder.img);
        holder.text1.setText(data.get(position).get("name")+"");
        holder.text2.setText(data.get(position).get("price")+"");
        Log.d("aaja", data.get(position).get("price")+"");
    }


    @Override
    public int getItemCount() {
        int size = data.size();
        return size;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView img;
        TextView text1;
        TextView text2;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.imageView);
            text1 = itemView.findViewById(R.id.textView1);
            text2 = itemView.findViewById(R.id.textView2);
        }
    }
}
