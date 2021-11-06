package com.example.roomapp;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
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
    AppDatabase appDatabase;

    public void addToList(View view){
        if (editText.getText().toString().equals("")){
            Toast.makeText(this, "Please Enter a Text", Toast.LENGTH_SHORT).show();
        }
        else {

            appDatabase = AppDatabase.getINSTANCE(this.getApplicationContext());
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
                editName(position);
            }

            @Override
            public void onDeleteClicked(int position) {
                Log.i(TAG, "onDeleteClicked: ");
                deleteName(position);
            }
        });

    }
    private void loadNames(){
        appDatabase = AppDatabase.getINSTANCE(this.getApplicationContext());
        usernames = appDatabase.nameDao().getNames();
        adapter.setNames(usernames);
    }

    private void deleteName(int position){
        Names name = usernames.get(position);
        appDatabase = AppDatabase.getINSTANCE(this.getApplicationContext());
        appDatabase.nameDao().deleteName(name);
        adapter.notifyItemChanged(position);
        loadNames();

    }

    private void editName(int position){
        Names name = usernames.get(position);
        Intent intent = new Intent(getApplicationContext(),EditNameActivity.class);
        intent.putExtra("index",position);
        intent.putExtra("name",name.name);
        intent.putExtra("uid",name.uid);
        startActivity(intent);
        loadNames();



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