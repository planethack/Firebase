package com.carlos.fpets;

import android.os.Bundle;

import com.carlos.fpets.R;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        super.goToCreate();
    }
}
