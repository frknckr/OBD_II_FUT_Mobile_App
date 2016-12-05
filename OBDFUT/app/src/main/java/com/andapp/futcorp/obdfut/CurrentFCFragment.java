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
public class CurrentFCFragment extends ListFragment {

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

    public CurrentFCFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_current_fc, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        String part;
        part = getActivity().getIntent().getExtras().getString("part");
        DBHelper dbHelper = new DBHelper(getActivity().getApplicationContext());
        ImageView imageView;
        imageView = (ImageView) getActivity().findViewById(R.id.imageView99);

        if (dbHelper.isThereCurrentFault(part)) {
            imageView.setImageResource(0);
            CustomListAdapter2 adapter = new CustomListAdapter2(this.getActivity(), dbHelper.getCurrentCodes(part), dbHelper.getCurrentDescription(part));
            this.setListAdapter(adapter);
        }else {
            imageView.setImageResource(R.drawable.ic_no_error);
        }
    }
}
