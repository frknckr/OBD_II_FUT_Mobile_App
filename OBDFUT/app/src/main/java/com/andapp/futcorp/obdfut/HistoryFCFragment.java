package com.andapp.futcorp.obdfut;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


/**
 * A simple {@link Fragment} subclass.
 */
public class HistoryFCFragment extends ListFragment {

    String[] items = {
            "P0101 - Marş",
            "P0102 - Motor",
            "P0103 - Yakıt Deposu",
            "P0104 - Rot-Balans",
            "P0105 - Şase",
            "P0101 - Marş",
            "P0102 - Motor",
            "P0103 - Yakıt Deposu",
            "P0104 - Rot-Balans",
            "P0105 - Şase"
    };


    public HistoryFCFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_history_fc, container, false);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        String part;
        part = getActivity().getIntent().getExtras().getString("part");
        DBHelper dbHelper = new DBHelper(getActivity().getApplicationContext());
        ImageView imageView;
        imageView = (ImageView) getActivity().findViewById(R.id.imageView9);
        if (dbHelper.isThereOldFault(part)){
            imageView.setImageResource(0);
            CustomListAdapter3 adapter = new CustomListAdapter3(this.getActivity(), dbHelper.getOldCodes(part), dbHelper.getOldDescription(part));
            this.setListAdapter(adapter);
        }else {
            imageView.setImageResource(R.drawable.ic_no_error);
        }

    }

}
