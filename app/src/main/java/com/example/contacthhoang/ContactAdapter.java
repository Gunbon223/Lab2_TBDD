package com.example.contacthhoang;

import static java.security.AccessController.getContext;
import android.widget.CheckBox;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class ContactAdapter extends BaseAdapter
{
    private List<Contact> lst;
    private Context context;
    public ContactAdapter(@NonNull Context context, @NonNull List<Contact> objects) {
        this.context = context;
        lst = objects;
    }

    @Override
    public int getCount() {
        return lst.size();
    }

    @Override
    public Object getItem(int position) {
        return lst.get(position);
    }

    @Override
    public long getItemId(int position) {
        return lst.get(position).getId();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            v = LayoutInflater.from(context).inflate(R.layout.contact_detail, parent, false);
        }

        Contact contact = (Contact) getItem(position);
        v.setClickable(true);

        CheckBox checkBox = v.findViewById(R.id.cker);
        checkBox.setChecked(contact.isStatus());
        checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            contact.setStatus(isChecked);

        });

        TextView phone = v.findViewById(R.id.txt2), name = v.findViewById(R.id.txt1) ;
        phone.setText(contact.getPhone());
        name.setText(contact.getName());

        return v;
    }


}