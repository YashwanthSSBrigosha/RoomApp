package com.example.roomapp;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EditNameActivity extends AppCompatActivity {

    AppDatabase appDatabase;
    EditText txtEditName;
    Button btnEditName;
    RecyclerViewAdapter adapter ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_name);

        txtEditName = (EditText)findViewById(R.id.txtEditName);
        btnEditName = (Button)findViewById(R.id.btnEditName);

        Intent intent = getIntent();
        int uid = intent.getIntExtra("uid",0);
        String name = intent.getStringExtra("name");
        int position = intent.getIntExtra("index",0);

        txtEditName.setText(name);
        
        btnEditName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String updateName = txtEditName.getText().toString();
                Log.i(TAG, "onClick: "+updateName);
                appDatabase = AppDatabase.getINSTANCE(getApplicationContext());
                appDatabase.nameDao().editDbName(updateName,uid);
                adapter.notifyItemChanged(position);
                finish();
            }
        });




    }
}