package com.example.cattyperi.Adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.viewpager.widget.PagerAdapter;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.cattyperi.Model.Adoption;
import com.example.cattyperi.R;

import java.util.ArrayList;
import java.util.List;

public class AdoptionPagerAdapter extends PagerAdapter implements PageAdapter {

    private List<CardView> mViews;
    private List<Adoption> mData;
    private float mBaseElevation;
    String user;

    public AdoptionPagerAdapter() {
        mData = new ArrayList<>();
        mViews = new ArrayList<>();
    }

    public void addCardItem(Adoption item, String username) {
        mViews.add(null);
        mData.add(item);
        user = username;
    }

    public float getBaseElevation() {
        return mBaseElevation;
    }

    public CardView getCardViewAt(int position) {
        return mViews.get(position);
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = LayoutInflater.from(container.getContext())
            .inflate(R.layout.adoption_adapter, container, false);
        container.addView(view);
        bind(mData.get(position), view, position);
        CardView cardView = view.findViewById(R.id.cardView);

        if (mBaseElevation == 0) {
            mBaseElevation = cardView.getCardElevation();
        }

        cardView.setMaxCardElevation(mBaseElevation * MAX_ELEVATION_FACTOR);
        mViews.set(position, cardView);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
        mViews.set(position, null);
    }

    private void bind(final Adoption item, View view, final int position) {
        TextView titleTextView = view.findViewById(R.id.titleTextView);
        final TextView contentTextView = view.findViewById(R.id.contentTextView);
        final Button btnAdopt = view.findViewById(R.id.btnAdopt);
        titleTextView.setText(item.getNameCat());
        contentTextView.setText("Location found : " + item.getLocFound());
        final String coba = item.getIdCat();
        btnAdopt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RequestQueue queue = Volley.newRequestQueue(v.getContext());
                String url      = "http://192.168.1.5/FP_TEKBER/getAvailableCat.php?id_cat="+coba+"&user="+user;

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
                btnAdopt.setText("Adopted");
                Toast.makeText(v.getContext(), "Cat is adopted successfully", Toast.LENGTH_LONG).show();
            }
        });
    }
}
