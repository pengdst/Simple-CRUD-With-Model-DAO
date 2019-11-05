package com.example.simplecrudwithmodeldao.helper;

import androidx.recyclerview.widget.RecyclerView;

public interface JadwalItemTouchHelperListener {
    void onSwiped(RecyclerView.ViewHolder viewHolder, int direction, int position);
}
