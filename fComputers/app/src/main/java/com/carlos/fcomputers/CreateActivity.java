package com.carlos.fcomputers;

import android.content.Intent;
import android.os.Bundle;

import com.carlos.fcomputers.models.ComputerModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.firestore.DocumentReference;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class CreateActivity extends BaseActivity {

    FloatingActionButton fab_save,fab_clear,fab_back;
    ImageView iv_create;
    TextView tv_image;
    EditText et_serial, et_brand, et_description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        super.init();
        init();

        fab_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String serial, description, brand;
                boolean active;

                serial = et_serial.getText().toString();
                description = et_description.getText().toString();
                brand = et_brand.getText().toString();

                if(serial.isEmpty() || description.isEmpty() || brand.isEmpty())
                    makeSimpleAlertDialog("Error", "Please fill all fields");
                else {
                    model = new ComputerModel();
                    model.setActive(true);
                    model.setBrand(brand);
                    model.setDescription(description);
                    model.setSerial(serial);

                    save(model);
                }

            }

            private void save(ComputerModel model) {

                if(collectionReference != null){
                    collectionReference.add(model)
                            .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                @Override
                                public void onSuccess(DocumentReference documentReference) {
                                    makeSimpleAlertDialog("Success", "Computer saved");
                                            clear();
                                }
                            });
                }
                else
                {
                    makeSimpleAlertDialog("Error", "Saving error");
                }
            }
        });
        fab_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clear();
            }
        });
        fab_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               goToList();
            }
        });
    }


    protected void init(){
        fab_save = findViewById(R.id.fab_create_save);
        fab_clear = findViewById(R.id.fab_create_clear);
        fab_back = findViewById(R.id.fab_create_back);
        iv_create = findViewById(R.id.iv_create_image);
        tv_image = findViewById(R.id.tv_create_click_image);
        et_serial = findViewById(R.id.et_create_serial);
        et_brand = findViewById(R.id.et_create_brand);
        et_description = findViewById(R.id.et_create_description);
    }

    private void clear(){
        et_serial.setText("");
        et_brand.setText("");
        et_description.setText("");

        et_serial.requestFocus();

        iv_create.setImageResource(R.drawable.ic_computer_black_18dp);
    }
}
