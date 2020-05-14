package com.example.cattyperi.Fragment;

import android.view.ViewGroup;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class AdoptionFragmentAdapter extends FragmentStatePagerAdapter {

    private List<AdoptionFragment> mFragments;
    private float mBaseElevation;

    public AdoptionFragmentAdapter(FragmentManager fm, float baseElevation) {
        super(fm);
        mFragments = new ArrayList<>();
        mBaseElevation = baseElevation;

        for(int i = 0; i< 5; i++){
            addCardFragment(new AdoptionFragment());
        }
    }

//    @Override
    public float getBaseElevation() {
        return mBaseElevation;
    }

//    @Override
    public CardView getCardViewAt(int position) {
        return mFragments.get(position).getCardView();
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Object fragment = super.instantiateItem(container, position);
        mFragments.set(position, (AdoptionFragment) fragment);
        return fragment;
    }

    public void addCardFragment(AdoptionFragment fragment) {
        mFragments.add(fragment);
    }

}
