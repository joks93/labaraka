package com.example.nacim.labaraka;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by nacim on 18/04/17.
 */

public class SubcategoriesRecyclerViewAdapter extends RecyclerView.Adapter<SubcategoriesRecyclerViewAdapter.SubcategoryViewHolder> {

    private Intent intent;
    private Context context;
    private LayoutInflater layoutInflater;
    private int category;
    private ArrayList<String> data;

    public SubcategoriesRecyclerViewAdapter(Context context, int category, Intent intent) {
        this.intent = intent;
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
        this.category = category;
        initSubcategoriesData();
    }

    private void initSubcategoriesData() {
        if (category == Constants.WOMAN_CATEGORY_ID)
            this.data = new ArrayList<>(Arrays.asList(context.getResources().getStringArray(R.array.WOMAN)));
        else if (category == Constants.MAN_CATEGORY_ID)
            this.data = new ArrayList<>(Arrays.asList(context.getResources().getStringArray(R.array.MAN)));
        else if (category == Constants.GIRL_CATEGORY_ID)
            this.data = new ArrayList<>(Arrays.asList(context.getResources().getStringArray(R.array.GIRL)));
        else if (category == Constants.BOY_CATEGORY_ID)
            this.data = new ArrayList<>(Arrays.asList(context.getResources().getStringArray(R.array.BOY)));
        else if (category == Constants.HOME_CATEGORY_ID)
            this.data = new ArrayList<>(Arrays.asList(context.getResources().getStringArray(R.array.HOME)));
    }

    @Override
    public SubcategoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View subcategoryView = layoutInflater.inflate(R.layout.subcategory_list_item, parent, false);
        return new SubcategoryViewHolder(subcategoryView);
    }

    @Override
    public void onBindViewHolder(SubcategoryViewHolder holder, int position) {
        holder.nameTextView.setText(data.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("KEY_INIT_PAGE", Constants.PRODUCTS_FRAGMENT_ID);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class SubcategoryViewHolder extends RecyclerView.ViewHolder {

        private TextView nameTextView;

        public SubcategoryViewHolder(View itemView) {
            super(itemView);
            this.nameTextView = (TextView) itemView.findViewById(R.id.subcategory_list_item_name);
        }
    }
}
