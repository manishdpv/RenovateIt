package io.groupproject.renovateit.ViewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;


import io.groupproject.renovateit.Interface.ItemClickListener;
import io.groupproject.renovateit.R;


public class WorkViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public TextView work_name;
    public ImageView work_image;
    private ItemClickListener itemClickListener;

    public WorkViewHolder( View itemView) {
        super(itemView);

        work_name = itemView.findViewById(R.id.menu_name);
        work_image = itemView.findViewById(R.id.menu_image);

        itemView.setOnClickListener(this);
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {

        this.itemClickListener = itemClickListener;
    }




    @Override
    public void onClick(View view) {
        itemClickListener.onClick(view, getAdapterPosition(), false);
    }
}
