package com.example.contacthhoang;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
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
    private ImageView avatarImageView;


    private int staticInt = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initComponent();
        contactList.add(new Contact(1,"Hoang","21300321"));
        contactList.add(new Contact(2,"Long","123134124"));
        contactList.add(new Contact(1,"Huy","421040124"));
        //them btn
        ThemBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contactList.add(new Contact(staticInt++,hoVaTenEdit.getText().toString(), sdtEdit.getText().toString()));
                adapter.notifyDataSetChanged();
            }
        });
        //xoa btn
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
        //image view
        avatarImageView = findViewById(R.id.avatarImageView);

        avatarImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, REQUEST_CODE_SELECT_IMAGE);
            }

        });
    }
    private static final int REQUEST_CODE_SELECT_IMAGE = 100;
    private static final String PREFS_NAME = "MyPrefsFile";
    private static final String AVATAR_IMAGE_PATH = "avatarImagePath";

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_SELECT_IMAGE && resultCode == RESULT_OK && data != null) {
            Uri selectedImageUri = data.getData();
            if (selectedImageUri != null) {
                avatarImageView.setImageURI(selectedImageUri);
            }
        }
    }

    private void saveAvatarImagePath(String imagePath) {
        SharedPreferences.Editor editor = getSharedPreferences(PREFS_NAME, MODE_PRIVATE).edit();
        editor.putString(AVATAR_IMAGE_PATH, imagePath);
        editor.apply();
    }
    private String getAvatarImagePath() {
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        return prefs.getString(AVATAR_IMAGE_PATH, null);
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