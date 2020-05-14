package com.example.cattyperi.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.cattyperi.Activity.Details.DetailNewsActivity;
import com.example.cattyperi.Activity.DonationActivity;
import com.example.cattyperi.Activity.NewsActivity;
import com.example.cattyperi.Model.News;
import com.example.cattyperi.R;

import java.util.ArrayList;


public class NewsListAdapter extends RecyclerView.Adapter<NewsListAdapter.NewsListViewHolder> {
    private ArrayList<News> dataList;

    public NewsListAdapter(ArrayList<News> dataList) {
        this.dataList = dataList;
    }

    @Override
    public NewsListAdapter.NewsListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.card_viewer, parent, false);
        return new NewsListAdapter.NewsListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(NewsListAdapter.NewsListViewHolder holder, final int position) {
        //int res = Context.getResources().getIdentifier(dataList.get(position).getNameCat(), "drawable", Context.getPackageName());
//        holder.txtImg.setImageResource(R.drawable.logo);
        holder.txtTitle.setText(dataList.get(position).getTitles());
        holder.txtBody1.setText(dataList.get(position).getDate());
        holder.txtBody2.setText(dataList.get(position).getIdNews());
        holder.btn.setText("See Detail");
        holder.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent details = new Intent(v.getContext(), DetailNewsActivity.class);
                details.putExtra("id_news",dataList.get(position).getIdNews());
                v.getContext().startActivity(details);
//                String content = dataList.get(position).getContent();
//                Toast.makeText(v.getContext(), content, Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return (dataList != null) ? dataList.size() : 0;
    }

    public class NewsListViewHolder extends RecyclerView.ViewHolder{
        private TextView txtTitle, txtBody1, txtBody2;
        private ImageView txtImg;
        private Button btn;

        public NewsListViewHolder(View itemView) {
            super(itemView);
//            txtImg   = itemView.findViewById(R.id.txt_img);
            txtTitle =  itemView.findViewById(R.id.txt_title);
            txtBody1 =  itemView.findViewById(R.id.txt_body1);
            txtBody2 =  itemView.findViewById(R.id.txt_body2);
            btn      =  itemView.findViewById(R.id.txt_btn);
        }
    }
}
