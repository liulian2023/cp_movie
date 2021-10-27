package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.Fragment.home_fragment;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.uyt.ying.yuan.R;

public class OpenRewordFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_open_reword, container, false);
        return view;
    }

    public static OpenRewordFragment newInstance(int position){
        Bundle bundle = new Bundle();
        OpenRewordFragment openRewordFragment = new OpenRewordFragment();
        bundle.putInt("position",position);
        openRewordFragment.setArguments(bundle);
        return openRewordFragment;
    }
}
