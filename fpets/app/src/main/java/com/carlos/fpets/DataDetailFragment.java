package com.carlos.fpets;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.carlos.fpets.R;
import com.carlos.fpets.models.PetModel;

public class DataDetailFragment extends Fragment {
    static private String name, description, breed;
    private boolean active;


    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_data_detail, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView tv_serial, tv_brand, tv_description;

        tv_serial = view.findViewById(R.id.et_create_name);
        tv_brand = view.findViewById(R.id.et_create_breed);
        tv_description = view.findViewById(R.id.et_create_description);

        tv_serial.setText(name);
        tv_brand.setText(breed);
        tv_description.setText(description);

        view.findViewById(R.id.button_first).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(DataDetailFragment.this)
                        .navigate(R.id.action_FirstFragment_to_SecondFragment);
            }
        });
    }

    static void receiveData(Bundle bundle){
        PetModel model = (PetModel)bundle.getSerializable("model");
        if(model != null){
            name = model.getName();
            breed = model.getBreed();
            description = model.getDescription();
        }
    }
}
