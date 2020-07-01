package com.carlos.fpets;

import android.os.Bundle;

import com.carlos.fpets.R;
import com.carlos.fpets.adapters.PetAdapter;
import com.carlos.fpets.models.PetModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class ListActivity extends BaseActivity {

    private FloatingActionButton fab_list_add;
    private ListView lv_list_pets;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        super.init();
        init();

        getPets();
        fab_list_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToCreate();
            }
        });

        lv_list_pets.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                model = modelArrayList.get(position);
                goToDetail(model);
                makeSimpleAlertDialog("Openning","Openning pet " + model.getName() );
            }
        });
    }

    protected void init(){
        fab_list_add = findViewById(R.id.fab_list_add);
        lv_list_pets = findViewById(R.id.lv_list_pets);
    }

    protected void getPets(){
        if(collectionReference != null){
            collectionReference.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if(task.isSuccessful()) {
                        if(task.getResult() != null) {
                            modelArrayList = new ArrayList<>();
                            for (QueryDocumentSnapshot snapshot : task.getResult()) {
                                model = snapshot.toObject(PetModel.class);
                                modelArrayList.add(model);
                            }
                            if(modelArrayList.size() > 0){
                                paintPets(modelArrayList);
                            }
                        }
                        else
                        {
                            makeSimpleAlertDialog("Warning", "Pet doesnt found");
                        }
                    }
                    else
                    {
                        makeSimpleAlertDialog("Error", "Database connection error");
                    }
                }


            });
        }
        else {
            makeSimpleAlertDialog("Error", "Database connection error");
        }
    }

    private void paintPets(ArrayList<PetModel> modelArrayList) {
        adapter = new PetAdapter(this, modelArrayList);
        lv_list_pets.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        getPets();
    }
}
