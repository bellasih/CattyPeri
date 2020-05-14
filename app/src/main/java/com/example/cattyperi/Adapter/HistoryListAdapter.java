package com.example.cattyperi.Adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.cattyperi.Model.History;
import com.example.cattyperi.R;

import org.json.JSONArray;

import java.util.ArrayList;


public class HistoryListAdapter extends RecyclerView.Adapter<HistoryListAdapter.HistoryListViewHolder> {
    private ArrayList<History> dataList;

    public HistoryListAdapter(ArrayList<History> dataList) {
        this.dataList = dataList;
    }

    @Override
    public HistoryListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.card_viewer, parent, false);
        return new HistoryListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(HistoryListViewHolder holder, final int position) {
        holder.txtTitle.setText(dataList.get(position).getUser());
        holder.txtBody1.setText(dataList.get(position).getMsg());
        holder.txtBody2.setText(dataList.get(position).getDate());
        holder.btn.setText("Hide");
        holder.btn.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  RequestQueue queue = Volley.newRequestQueue(v.getContext());
                  String url      = "http://192.168.1.5/FP_TEKBER/getHistory.php?id_notif="+dataList.get(position).getIdHistory()+"&user="+dataList.get(position).getUser();

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
              }
        });
    }

    @Override
    public int getItemCount() {
        return (dataList != null) ? dataList.size() : 0;
    }

    public class HistoryListViewHolder extends RecyclerView.ViewHolder{
        private TextView txtTitle, txtBody1, txtBody2;
        private Button btn;

        public HistoryListViewHolder(View itemView) {
            super(itemView);
            txtTitle =  itemView.findViewById(R.id.txt_title);
            txtBody1 =  itemView.findViewById(R.id.txt_body1);
            txtBody2 =  itemView.findViewById(R.id.txt_body2);
            btn      =  itemView.findViewById(R.id.txt_btn);
        }
    }
}
