package com.example.recyclerview;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;

import android.view.Menu;
import android.view.MenuItem;

import java.util.LinkedList;

public class MainActivity extends AppCompatActivity {
    //creamos un linkedlist
    private final LinkedList<String> mWordList = new LinkedList<>();
    //creamos nuestro RecyclerView
    private RecyclerView mRecyclerView;
    //creamos nuestro WordListAdapter
    private WordListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int wordListSize = mWordList.size();
                // añadimo una nueva palabra a nuestra lista
                mWordList.addLast("+ Word " + wordListSize);
                // verificamos que se haya cambiado
                mRecyclerView.getAdapter().notifyItemInserted(wordListSize);
                // hacemos un scroll
                mRecyclerView.smoothScrollToPosition(wordListSize);
            }
        });

        for (int i = 0; i < 20; i++) {
            mWordList.addLast("Word " + i);
        }

        // Creamos un recycleView
        mRecyclerView = findViewById(R.id.recyclerview);
        // creamos un adpatador y lo añadimos a nuestra pantalla
        mAdapter = new WordListAdapter(this, mWordList);
        // realizamos la conexión con nuestro recyclerview.
        mRecyclerView.setAdapter(mAdapter);
        //obtenemos el recyclerview
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}