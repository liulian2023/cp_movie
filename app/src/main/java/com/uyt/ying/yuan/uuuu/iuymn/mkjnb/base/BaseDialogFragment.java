package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.base;

import androidx.fragment.app.DialogFragment;

public class BaseDialogFragment extends DialogFragment {
    protected String TAG = this.getClass().getSimpleName();
    private static final String SAVED_DIALOG_STATE_TAG = "android:savedDialogState";

    @Override
    public void onPause() {
        super.onPause();
    }


    /*    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        *//**
         * 重写onActivityCreated 解决内存泄漏问题
         *//*
        if (getShowsDialog()) {
            setShowsDialog(false);
        }
        super.onActivityCreated(savedInstanceState);
        setShowsDialog(true);

        View view = getView();
        if (view != null) {
            if (view.getParent() != null) {
                throw new IllegalStateException(
                        "DialogFragment can not be attached to a container view");
            }
            getDialog().setContentView(view);
        }
        final Activity activity = getActivity();
        if (activity != null) {
            getDialog().setOwnerActivity(activity);
        }
        if (savedInstanceState != null) {
            Bundle dialogState = savedInstanceState.getBundle(SAVED_DIALOG_STATE_TAG);
            if (dialogState != null) {
                getDialog().onRestoreInstanceState(dialogState);
            }
        }
    }*/
    public void errorRefresh(){
    }
}
