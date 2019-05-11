package com.example.contact;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Random;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    JSONArray dataSet = new JSONArray();
    private Context context;
    public MyAdapter(JSONArray dataSet, Context context) {
        this.dataSet = dataSet;
        this.context = context;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item,viewGroup,false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        try {
            final JSONObject obj = (JSONObject) dataSet.get(i);
            Random rand = new Random();
            myViewHolder.logo.getBackground().setTint(Color.rgb(rand.nextInt(240),rand.nextInt(240),rand.nextInt(240)));
            myViewHolder.name.setText(obj.get("name").toString());
            myViewHolder.logo.setText(obj.get("name").toString().charAt(0)+"");
            myViewHolder.number.setText(obj.get("number").toString());
            myViewHolder.callbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent callIntent = new Intent(Intent.ACTION_CALL);
                    try {
                        callIntent.setData(Uri.parse("tel:"+Uri.encode(obj.get("number").toString())));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    context.startActivity(callIntent);
                }
            });
            myViewHolder.messagebtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent sendIntent = new Intent(Intent.ACTION_VIEW);
                    try {
                        sendIntent.setData(Uri.parse("sms:"+obj.get("number").toString()));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    context.startActivity(sendIntent);
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return dataSet.length();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView logo;
        public TextView name;
        public TextView number;
        public ImageView callbtn;
        public ImageView messagebtn;
        public MyViewHolder(@NonNull View v) {
            super(v);
            logo = (TextView) v.findViewById(R.id.logo);
            name = (TextView) v.findViewById(R.id.name);
            number = (TextView) v.findViewById(R.id.phone);
            callbtn = (ImageView) v.findViewById(R.id.callbtn);
            messagebtn = (ImageView) v.findViewById(R.id.messagebtn);
        }
    }
}
