package com.carlos.fpets;

import android.os.Bundle;

import com.carlos.fpets.R;
import com.carlos.fpets.models.PetModel;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.DocumentReference;

import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class CreateActivity extends BaseActivity {

    FloatingActionButton fab_save,fab_clear,fab_back;
    ImageView iv_create;
    TextView tv_image;
    EditText et_name, et_breed, et_description;

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
                String name, description, breed;
                boolean active;

                name = et_name.getText().toString();
                description = et_description.getText().toString();
                breed = et_breed.getText().toString();

                if(name.isEmpty() || description.isEmpty() || breed.isEmpty())
                    makeSimpleAlertDialog("Error", "Please fill all fields");
                else {
                    model = new PetModel();
                    model.setActive(true);
                    model.setBreed(breed);
                    model.setDescription(description);
                    model.setName(name);

                    save(model);
                }

            }

            private void save(PetModel model) {

                if(collectionReference != null){
                    collectionReference.add(model)
                            .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                @Override
                                public void onSuccess(DocumentReference documentReference) {
                                    makeSimpleAlertDialog("Success", "Pet saved");
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
        et_name = findViewById(R.id.et_create_name);
        et_breed = findViewById(R.id.et_create_breed);
        et_description = findViewById(R.id.et_create_description);
    }

    private void clear(){
        et_name.setText("");
        et_breed.setText("");
        et_description.setText("");

        et_name.requestFocus();

        iv_create.setImageResource(R.drawable.ic_computer_black_18dp);
    }
}
