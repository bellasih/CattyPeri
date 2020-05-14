package com.example.cattyperi.Adapter;

import androidx.cardview.widget.CardView;

public interface PageAdapter {
    int MAX_ELEVATION_FACTOR = 8;

    float getBaseElevation();

    CardView getCardViewAt(int position);

    int getCount();
}
