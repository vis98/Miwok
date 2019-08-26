package com.vishal.miwok;

import android.app.Activity;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.view.LayoutInflater;

import java.util.ArrayList;

import android.widget.ImageView;
import android.widget.TextView;

public class WordAdapter extends ArrayAdapter<Word> {
    private int rid;

    public WordAdapter(Activity context, ArrayList<Word> words,int Resourceid) {
        // Here, we initialize the ArrayAdapter's internal storage for the context and the list.
        // the second argument is used when the ArrayAdapter is populating a single TextView.
        // Because this is a custom adapter for two TextViews and an ImageView, the adapter is not
        // going to use this second argument, so it can be any value. Here, we used 0.
        super(context, 0, words);
        rid=Resourceid;
    }

    @Override
    public View getView(int position,  View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        // Get the {@link AndroidFlavor} object located at this position in the list
        Word wo= getItem(position);

        // Find the TextView in the list_item.xml layout with the ID version_name
        TextView miwokTextView = (TextView) listItemView.findViewById(R.id.miwokname);
        // Get the version name from the current AndroidFlavor object and
        // set this text on the name TextView
        miwokTextView.setText(((Word) wo).getMiwokTranslation());

        // Find the TextView in the list_item.xml layout with the ID version_number
       // TextView numberTextView = (TextView) listItemView.findViewById(R.id.version_number);
        // Get the version number from the current AndroidFlavor object and
        // set this text on the number TextView

        TextView nameTextView1 = (TextView) listItemView.findViewById(R.id.engname);

        nameTextView1.setText(((Word) wo).getDefaultTranslation());
        ImageView iconView = (ImageView) listItemView.findViewById(R.id.miwimg);
        // Get the image resource ID from the current AndroidFlavor object and
        // set the image to iconView
        if(wo.hasimage()) {
            iconView.setImageResource(wo.getmImageResourceId());
        }
        else {
            iconView.setVisibility(View.GONE);
        }
        View textContainer = listItemView.findViewById(R.id.text_container);
        // Find the color that the resource ID maps to
        int color = ContextCompat.getColor(getContext(), rid);
        //numberTextView.setText(((Word) wo).getMiwokTranslation());
        textContainer.setBackgroundColor(color);
        // Return the whole list item layout (containing 2 TextViews and an ImageView)
        // so that it can be shown in the ListView
        return listItemView;
    }


}

