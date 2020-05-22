package com.example.cattyperi.Adapter;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.cattyperi.Activity.Details.DetailAdoptionActivity;
import com.example.cattyperi.Model.Adoption;
import com.example.cattyperi.R;


import java.util.ArrayList;


public class AdoptionListAdapter extends RecyclerView.Adapter<AdoptionListAdapter.AdoptionListViewHolder> {
    private ArrayList<Adoption> dataList;
    String username;
    ImageLoader imgLoader;
//    NetworkImageView previewImage;

    public AdoptionListAdapter(ArrayList<Adoption> dataList, String username) {
        this.dataList = dataList;
        this.username = username;
    }

    @Override
    public AdoptionListAdapter.AdoptionListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.card_view_image, parent, false);

        return new AdoptionListAdapter.AdoptionListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AdoptionListViewHolder holder, final int position) {
        //int res = Context.getResources().getIdentifier(dataList.get(position).getNameCat(), "drawable", Context.getPackageName());
        String photo_name = dataList.get(position).getImgCat();
        String url_img = "httl://192.168.1.5/FP_TEKBER/" + photo_name;
//        previewImage = (NetworkImageView)rootView.findViewById(R.id.previewImage);

        imgLoader.get(url_img, ImageLoader.getImageListener(holder.txtImg
            ,0,android.R.drawable
                .ic_dialog_alert));
        holder.txtImgUrl.setImageUrl(url_img,imgLoader);
        holder.txtTitle.setText("Cat's name : " + dataList.get(position).getNameCat());
        holder.txtBody1.setText("");
        holder.txtBody2.setText("Location found : " + dataList.get(position).getLocFound());
        holder.btn.setText("See Detail");
        holder.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 Intent details = new Intent(v.getContext(), DetailAdoptionActivity.class);
                 details.putExtra("id_cat",dataList.get(position).getIdCat());
                 v.getContext().startActivity(details);
            }
        });
        holder.btn2.setText("Adopt");
        holder.btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RequestQueue queue = Volley.newRequestQueue(v.getContext());
                String url      = "http://192.168.1.5/FP_TEKBER/getAvailableCat.php?id_cat="+dataList.get(position).getIdCat()+"&user="+username;

                StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.d("Error Response",error.toString());
                        }
                    }
                );
                queue.add(stringRequest);

                dataList.remove(dataList.get(position));
                notifyDataSetChanged();
                Toast.makeText(v.getContext(), "Cat is adopted successfully", Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return (dataList != null) ? dataList.size() : 0;
    }

    public class AdoptionListViewHolder extends RecyclerView.ViewHolder{
        private TextView txtTitle, txtBody1, txtBody2;
        private ImageView txtImg;
        private NetworkImageView txtImgUrl;
        private Button btn, btn2;

        public AdoptionListViewHolder(View itemView) {
            super(itemView);
            txtImgUrl = itemView.findViewById(R.id.previewImage);
            txtTitle =  itemView.findViewById(R.id.txt_title_img);
            txtBody1 =  itemView.findViewById(R.id.txt_body1_img);
            txtBody2 =  itemView.findViewById(R.id.txt_body2_img);
            btn      =  itemView.findViewById(R.id.txt_btn_img);
            btn2     =  itemView.findViewById(R.id.txt_btn2_img);
        }
    }
}
