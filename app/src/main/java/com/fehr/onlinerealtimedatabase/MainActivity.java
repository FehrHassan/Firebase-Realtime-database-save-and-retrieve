package com.fehr.onlinerealtimedatabase;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    EditText wordEditText, numberEditText;
    Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FirebaseApp.initializeApp(MainActivity.this);

        listView = findViewById(R.id.list_view);
        wordEditText = findViewById(R.id.word_edit_text);
        numberEditText = findViewById(R.id.number_edit_text);
        submitButton = findViewById(R.id.submit_button);

        final ArrayList<WordNumber> wordNumberArrayList = new ArrayList<>();
        final WordAdapter wordAdapter = new WordAdapter(MainActivity.this,0, wordNumberArrayList);
        listView.setAdapter(wordAdapter);


        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference("Numbers");

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WordNumber wordNumber = new WordNumber();

                if (wordEditText.getText().toString().isEmpty() || numberEditText.getText().toString().isEmpty() ){
                    Toast.makeText(MainActivity.this, "Please fill all the required data", Toast.LENGTH_SHORT).show();
                } else {
                    wordNumber.setWord(wordEditText.getText().toString());
                    wordNumber.setNumber((Integer.valueOf(numberEditText.getText().toString())));
                    myRef.push().setValue(wordNumber);
                    wordEditText.setText("");
                    numberEditText.setText("");
                }
            }
        });

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                wordNumberArrayList.clear();
                wordAdapter.clear();
                for (DataSnapshot childDataSnapshot : dataSnapshot.getChildren()) {
                    WordNumber wordNumberObject = childDataSnapshot.getValue(WordNumber.class);
                    wordNumberArrayList.add(wordNumberObject);
                }
                wordAdapter.addAll(wordNumberArrayList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
