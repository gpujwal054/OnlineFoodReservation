package com.example.smartrestro;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class MenuAdapter extends FragmentPagerAdapter {
    int numOfTabs;
    public MenuAdapter(FragmentManager fm,int numOfTabs){
        super(fm);
        this.numOfTabs = numOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new FoodFragment();
            case 1:
                return new DessertFragment();
            case 2:
                return new CafeFragment();
            case 3:
                return new BarFragment();
                default:
                    return null;
        }
    }

    @Override
    public int getCount() {
        return numOfTabs;
    }
}
