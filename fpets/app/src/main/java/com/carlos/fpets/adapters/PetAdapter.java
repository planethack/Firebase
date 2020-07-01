package com.carlos.fpets.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.carlos.fpets.R;
import com.carlos.fpets.models.PetModel;

import java.util.ArrayList;

public class PetAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<PetModel> modelArrayList;

    public PetAdapter(Context context, ArrayList<PetModel> modelArrayList) {
        this.context = context;
        this.modelArrayList = modelArrayList;
    }

    @Override
    public int getCount() {
        return modelArrayList.size();
    }

    @Override
    public PetModel getItem(int position) {
        return modelArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater layoutInflater = LayoutInflater.from(this.context);
            convertView = layoutInflater.inflate(R.layout.pet_list_item, parent, false);
        }

        TextView tv_pet_list_item_description = convertView.findViewById(R.id.tv_pet_list_item_description);
        TextView tv_pet_list_item_name = convertView.findViewById(R.id.tv_pet_list_item_name);
        TextView tv_pet_list_item_breed = convertView.findViewById(R.id.tv_pet_list_item_breed);

        tv_pet_list_item_description.setText(getItem(position).getDescription());
        tv_pet_list_item_name.setText(getItem(position).getName());
        tv_pet_list_item_breed.setText(getItem(position).getBreed());

        return convertView;
    }
}
