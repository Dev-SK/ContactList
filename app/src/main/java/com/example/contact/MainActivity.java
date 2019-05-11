package com.example.contact;

import android.support.design.widget.BottomSheetDialogFragment;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    private RecyclerView rv_word;
    private JSONArray contactarr = new JSONArray();
    private FloatingActionButton dialerbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fetchContacts();
        rv_word = (RecyclerView) findViewById(R.id.rv_word);
        dialerbtn = (FloatingActionButton) findViewById(R.id.dialerbtn);
        MyAdapter myAdapter = new MyAdapter(contactarr,this);
        rv_word.setAdapter(myAdapter);
        final BottomSheetDialogFragment myBottomSheet = Dialer.newInstance("Modal Bottom Sheet");

        dialerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myBottomSheet.show(getSupportFragmentManager(), myBottomSheet.getTag());

            }
        });
    }
    public void fetchContacts() {

        Uri CONTENT_URI = ContactsContract.Contacts.CONTENT_URI;
        //content://com.android.contacts/contacts
        String _ID = ContactsContract.Contacts._ID;
        //_id
        String DISPLAY_NAME = ContactsContract.Contacts.DISPLAY_NAME;
        //display_name
        String HAS_PHONE_NUMBER = ContactsContract.Contacts.HAS_PHONE_NUMBER;
        //has_phone_number

        Uri PhoneCONTENT_URI = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
        //content://com.android.contacts/data/phones
        String Phone_CONTACT_ID = ContactsContract.CommonDataKinds.Phone.CONTACT_ID;
        //contact_id
        String NUMBER = ContactsContract.CommonDataKinds.Phone.NUMBER;
        //data1

        ContentResolver contentResolver = getContentResolver();
        //to access content model

        Cursor cursor = contentResolver.query(CONTENT_URI, null,null, null, DISPLAY_NAME);
        //query to content_uri
        // Loop for every contact in the phone
        if (cursor.getCount() > 0) {

            while (cursor.moveToNext()) {

                String contact_id = cursor.getString(cursor.getColumnIndex( _ID ));
                //getting contact id
                String name = cursor.getString(cursor.getColumnIndex( DISPLAY_NAME ));
                //getting name of contacts

                int hasPhoneNumber = Integer.parseInt(cursor.getString(cursor.getColumnIndex( HAS_PHONE_NUMBER )));
                //if have number then value is 1 else 0

                if (hasPhoneNumber > 0) {


                    // Query and loop for every phone number of the contact
                    Cursor phoneCursor = contentResolver.query(PhoneCONTENT_URI, null, Phone_CONTACT_ID + " = ?", new String[]{contact_id}, null);

                    while (phoneCursor.moveToNext()) {
                        String phoneNumber = phoneCursor.getString(phoneCursor.getColumnIndex(NUMBER));
                        JSONObject contact = new JSONObject();
                        try {
                            contact.put("name",name);
                            contact.put("number",phoneNumber);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        contactarr.put(contact);
                    }
                    //one contact can have multiple numbers so loop through all of them
                    phoneCursor.close();

                }
            }
        }
    }
}
