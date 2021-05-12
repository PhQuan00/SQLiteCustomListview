package com.example.sqlitecustomlistview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private DataBaseHelper db;
    private ArrayAdapter<Student> listViewAdapter;
    private Button btnAdd;
    private Button btnRemove;
    private Button btnCancel;
    private EditText editText;
    private String ten;
    private String temp;
    private String ho;
    private int index;
    private List<Student> list1;
    private List<Student> list2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView= findViewById(R.id.listView);


        list1= db.getAllStudents();

        listViewAdapter= new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,android.R.id.text1,list1);
        listView.setAdapter(listViewAdapter);

        btnAdd= findViewById(R.id.btnAdd);
        btnRemove=findViewById(R.id.btnRemove);
        btnCancel=findViewById(R.id.btnCancel);
        editText=findViewById(R.id.editTextS1);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                temp= String.valueOf(editText.getText());
                if(temp.contains(" ")==true){
                    ten=temp.substring(temp.lastIndexOf(' ') + 1);
                    ho=temp.substring(0,temp.lastIndexOf(' ') );
                    db.addStudent(new Student(ho,ten));

                    List<Student> list= db.getAllStudents();

                    listViewAdapter= new ArrayAdapter<>(MainActivity.this,
                            android.R.layout.simple_list_item_1,android.R.id.text1,list);

                    listView.setAdapter(listViewAdapter);
                }
                else{
                    editText.setText("");
                    Toast.makeText(getApplicationContext(),"You have type FirstName and LastName",
                            Toast.LENGTH_LONG).show();
                }


            }
        });

        btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                index =i;
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setText("");
            }
        });

    }


}