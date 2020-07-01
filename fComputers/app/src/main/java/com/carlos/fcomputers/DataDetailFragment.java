package com.carlos.fcomputers;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.carlos.fcomputers.models.ComputerModel;

public class DataDetailFragment extends Fragment {
    static private String serial, description, brand;
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

        tv_serial = view.findViewById(R.id.et_create_serial);
        tv_brand = view.findViewById(R.id.et_create_brand);
        tv_description = view.findViewById(R.id.et_create_description);

        tv_serial.setText(serial);
        tv_brand.setText(brand);
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
        ComputerModel model = (ComputerModel)bundle.getSerializable("model");
        if(model != null){
            serial = model.getSerial();
            brand = model.getBrand();
            description = model.getDescription();
        }
    }
}
