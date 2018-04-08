package com.prasasd.nikhil.nss2k17;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Nikhil08 on 1/10/2018.
 */

public class DonerList extends ArrayAdapter<Doners>{

    private Activity context;
    private List<Doners> donersList;
    public DonerList(Activity context,List<Doners> donersList){

        super(context, R.layout.list_layout,donersList);
        this.context = context;
        this.donersList = donersList;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflator = context.getLayoutInflater();

        View listViewItem = inflator.inflate(R.layout.list_layout,null,true);
        TextView bloodGroup = (TextView) listViewItem.findViewById(R.id.bloodGroup);
        TextView donerName = (TextView) listViewItem.findViewById(R.id.donerName);
        //TextView donerNum = (TextView) listViewItem.findViewById(R.id.donerNum);



        Doners doner = donersList.get(position);

        bloodGroup.setText(doner.getDonerBloodGroup());
        donerName.setText(doner.getDonerName());
        //donerNum.setText(doner.getDonerNum());

        return listViewItem;
    }
}
