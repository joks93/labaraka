package com.example.nacim.labaraka;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by nacim on 24/04/17.
 */

public class ImagesRecyclerViewAdapter extends RecyclerView.Adapter<ImagesRecyclerViewAdapter.ImageViewHolder>{

    private Context context;
    private LayoutInflater layoutInflater;
    private ArrayList<String> data;

    public ImagesRecyclerViewAdapter(Context context, ArrayList<String> data) {
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
        this.data = data;
    }

    @Override
    public ImagesRecyclerViewAdapter.ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View productView = layoutInflater.inflate(R.layout.product_sheet_image, parent, false);
        return new ImagesRecyclerViewAdapter.ImageViewHolder(productView);
    }

    @Override
    public void onBindViewHolder(final ImagesRecyclerViewAdapter.ImageViewHolder holder, final int position) {
        holder.imageView.setImageDrawable(context.getDrawable(R.drawable.jupe));
        //holder.imageView.setText(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    public class ImageViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageView;

        public ImageViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.product_sheet_image);

        }
    }
}
