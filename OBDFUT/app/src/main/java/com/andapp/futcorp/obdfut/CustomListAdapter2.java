package com.andapp.futcorp.obdfut;

import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class CustomListAdapter2 extends ArrayAdapter<String> {

    private final FragmentActivity context;
    private final List<String> itemname;
    private final List<String> itemDescription;

    public CustomListAdapter2(FragmentActivity context, List<String> itemname, List<String> itemDescription) {
        super(context, R.layout.mylist, itemname);
        this.context = context;
        this.itemname = itemname;
        this.itemDescription = itemDescription;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.mylist, null,true);

        TextView txtTitle = (TextView) rowView.findViewById(R.id.itemname);
        TextView txtDesc = (TextView) rowView.findViewById(R.id.itemname_description);

        txtTitle.setText(itemname.get(position));
        txtDesc.setText(itemDescription.get(position));

        return rowView;

    }
}
