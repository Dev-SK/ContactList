package com.example.contact;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONException;

public class Dialer extends BottomSheetDialogFragment {

    String mString;

    static Dialer newInstance(String string) {
        Dialer f = new Dialer();
        Bundle args = new Bundle();
        args.putString("string", string);
        f.setArguments(args);
        return f;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mString = getArguments().getString("string");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.dialer, container, false);
        final TextView dial = (TextView) v.findViewById(R.id.dial);
        dial.setText("");
        Button button1 = (Button) v.findViewById(R.id.button1);
        Button button2 = (Button) v.findViewById(R.id.button2);
        Button button3 = (Button) v.findViewById(R.id.button3);
        Button button4 = (Button) v.findViewById(R.id.button4);
        Button button5 = (Button) v.findViewById(R.id.button5);
        Button button6 = (Button) v.findViewById(R.id.button6);
        Button button7 = (Button) v.findViewById(R.id.button7);
        Button button8 = (Button) v.findViewById(R.id.button8);
        Button button9 = (Button) v.findViewById(R.id.button9);
        Button button0 = (Button) v.findViewById(R.id.button0);
        Button buttonstar = (Button) v.findViewById(R.id.buttonstar);
        Button buttonhash = (Button) v.findViewById(R.id.buttonhash);
        Button buttonclear = (Button) v.findViewById(R.id.buttonclear);
        Button buttonback = (Button) v.findViewById(R.id.buttonback);
        FloatingActionButton buttoncall = (FloatingActionButton) v.findViewById(R.id.buttoncall);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dial.setText(dial.getText() + "1");
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dial.setText(dial.getText() + "2");
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dial.setText(dial.getText() + "3");
            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dial.setText(dial.getText() + "4");
            }
        });
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dial.setText(dial.getText() + "5");
            }
        });
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dial.setText(dial.getText() + "6");
            }
        });
        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dial.setText(dial.getText() + "7");
            }
        });
        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dial.setText(dial.getText() + "8");
            }
        });
        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dial.setText(dial.getText() + "9");
            }
        });
        button0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dial.setText(dial.getText() + "0");
            }
        });
        buttonstar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dial.setText(dial.getText() + "*");
            }
        });
        buttonhash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dial.setText(dial.getText() + "#");
            }
        });
        buttonclear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dial.setText("");
            }
        });
        buttonback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(dial.getText().length()>0)
                dial.setText(dial.getText().toString().substring(0,dial.getText().length()-1));
            }
        });
        buttoncall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:"+Uri.encode(dial.getText().toString())));
                startActivity(callIntent);
            }
        });

        return v;
    }
}