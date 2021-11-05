package com.example.roomapp;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    LinearLayoutManager layoutManager;
    RecyclerViewAdapter adapter ;
    EditText editText;
    List<Names> usernames;

    public void addToList(View view){
        if (editText.getText().toString().equals("")){
            Toast.makeText(this, "Please Enter a Text", Toast.LENGTH_SHORT).show();
        }
        else {

            AppDatabase appDatabase = AppDatabase.getINSTANCE(this.getApplicationContext());
            Names names = new Names();
            names.name = editText.getText().toString();
            appDatabase.nameDao().addName(names);
            adapter.notifyDataSetChanged();
            editText.setText("");
            loadNames();
        }

    }

    private void initRecyclerView(){
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new RecyclerViewAdapter(this);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new RecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onEditClicked(int position) {
                Log.i(TAG, "onEditClicked: ");
            }

            @Override
            public void onDeleteClicked(int position) {
                Log.i(TAG, "onDeleteClicked: ");
            }
        });

    }
    private void loadNames(){
        AppDatabase appDatabase = AppDatabase.getINSTANCE(this.getApplicationContext());
        usernames = appDatabase.nameDao().getNames();
        adapter.setNames(usernames);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = (EditText) findViewById(R.id.editText);

        /*names.add("Yashwanth");
        names.add("bharath");
        names.add("Sujan");
        names.add("Prajwal");
        names.add("Ganesh Pativada");*/

        initRecyclerView();
        loadNames();
    }
}