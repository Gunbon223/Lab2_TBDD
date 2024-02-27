package com.example.contacthhoang;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView contactListView;

    private Button ThemBtn, XoaBtn;

    private EditText hoVaTenEdit, sdtEdit;

    private List<Contact> contactList;

    private ContactAdapter adapter;

    private int staticInt = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initComponent();
        contactList.add(new Contact(1,"Hoang","21300321"));
        contactList.add(new Contact(2,"Long","123134124"));
        contactList.add(new Contact(1,"Huy","421040124"));
        ThemBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contactList.add(new Contact(staticInt++,hoVaTenEdit.getText().toString(), sdtEdit.getText().toString()));
                adapter.notifyDataSetChanged();
            }
        });

        XoaBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Contact> contactsToRemove = new ArrayList<>();
                for (Contact c : contactList) {
                    if (c.isStatus()) {
                        contactsToRemove.add(c);
                    }
                }
                contactList.removeAll(contactsToRemove);
                adapter.notifyDataSetChanged();
            }
        });


    }

    private void initComponent ()
    {
        contactListView = findViewById(R.id.list1);
        ThemBtn = findViewById(R.id.btthem);
        XoaBtn = findViewById(R.id.btxoa);
        hoVaTenEdit = findViewById(R.id.hoten);
        sdtEdit = findViewById(R.id.soDienThoai);
        contactList = new ArrayList<Contact>();
        adapter = new ContactAdapter(this,contactList);
        contactListView.setAdapter(adapter);
        contactListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, "true", Toast.LENGTH_SHORT).show();
            }
        });
    }
}