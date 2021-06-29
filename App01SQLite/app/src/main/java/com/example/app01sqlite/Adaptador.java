package com.example.app01sqlite;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import java.util.ArrayList;

public class Adaptador extends ArrayAdapter<Item> {

    ArrayList<Item> animalList = new ArrayList<>();

    public Adaptador(Context context, int textViewResourceId, ArrayList<Item> objects) {
        super(context, textViewResourceId, objects);
        animalList = objects;
    }

    @Override
    public int getCount() {
        return super.getCount();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        v = inflater.inflate(R.layout.grid_view_items, null);
        ImageView imageView = (ImageView) v.findViewById(R.id.imageView);
        imageView.setImageBitmap(animalList.get(position).getAnimalImage());
        return v;
    }

}