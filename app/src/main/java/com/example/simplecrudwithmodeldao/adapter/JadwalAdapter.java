package com.example.simplecrudwithmodeldao.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.simplecrudwithmodeldao.R;
import com.example.simplecrudwithmodeldao.helper.OnItemClickCallback;
import com.example.simplecrudwithmodeldao.model.Jadwal;

import java.util.List;

public class JadwalAdapter extends RecyclerView.Adapter<JadwalAdapter.ViewHolder> {
    private Context context;
    private List<Jadwal> list;

    private OnItemClickCallback onItemClickCallback;

    public Context getContext() {
        return context;
    }

    public void setOnItemClickCallback(OnItemClickCallback onItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback;
    }

    public JadwalAdapter(Context context, List<Jadwal> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_jadwal_backfore, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Jadwal jadwal = list.get(position);

        holder.bindView(jadwal);
        holder.bindListener(position, jadwal);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void removeItem(int position){
        list.remove(position);

        notifyItemRemoved(position);
    }

    public void recoverItem(int position, Jadwal jadwal){
        list.add(position, jadwal);

        notifyItemInserted(position);
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvHari, tvMatkul, tvRuangan, tvJam, tvDosen;
        public RelativeLayout viewBackground;
        public LinearLayout viewForeground;

        ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvHari = itemView.findViewById(R.id.tvHari);
            tvMatkul = itemView.findViewById(R.id.tvMatkul);
            tvJam = itemView.findViewById(R.id.tvJam);
            tvRuangan = itemView.findViewById(R.id.tvRuang);
            tvDosen = itemView.findViewById(R.id.tvDosen);

            viewBackground = itemView.findViewById(R.id.view_background);
            viewForeground = itemView.findViewById(R.id.view_foreground);
        }

        void bindView(Jadwal jadwal){
            tvHari.setText(jadwal.getHari());
            tvMatkul.setText(jadwal.getMatkul());
            tvJam.setText(jadwal.getJam());
            tvRuangan.setText(jadwal.getRuang());
            tvDosen.setText(jadwal.getDosen());
        }

        void bindListener(final int position, final Jadwal jadwal){
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickCallback.onItemClick(position, jadwal);
                }
            });
        }
    }
}
