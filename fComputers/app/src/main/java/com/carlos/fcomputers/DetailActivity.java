package com.carlos.fcomputers;

import android.os.Bundle;

import com.carlos.fcomputers.models.ComputerModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailActivity extends BaseActivity {
    FloatingActionButton fab_list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        super.init();
        init();

        model = (ComputerModel) getIntent().getSerializableExtra("model");

        if(model != null){
            Bundle bundle = new Bundle();
            bundle.putSerializable("model", model);
            DataDetailFragment.receiveData(bundle);
        }
        else
            makeSimpleAlertDialog("Error", "Empty element");
        fab_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToList();
            }
        });
    }

    protected void init(){
        fab_list = findViewById(R.id.fab_list);

    }

}
