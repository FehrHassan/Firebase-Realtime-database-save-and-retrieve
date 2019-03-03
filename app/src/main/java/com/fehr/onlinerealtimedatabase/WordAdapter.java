package com.fehr.onlinerealtimedatabase;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class WordAdapter extends ArrayAdapter<WordNumber> {

    Activity activity;
    ArrayList<WordNumber> wordNumberArrayList;

    public WordAdapter(Activity activity, int resource, ArrayList<WordNumber> wordNumberArrayList) {
        super(activity, 0);
        this.activity = activity;
        this.wordNumberArrayList = wordNumberArrayList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView == null)
            convertView = LayoutInflater.from(activity).inflate(R.layout.list_view_item,parent,false);

        WordNumber wordNumber = wordNumberArrayList.get(position);

        TextView word = convertView.findViewById(R.id.word);
        word.setText(wordNumber.getWord());

        TextView number = convertView.findViewById(R.id.number);
        number.setText(wordNumber.getNumber() + "");

        return convertView;
    }
}
