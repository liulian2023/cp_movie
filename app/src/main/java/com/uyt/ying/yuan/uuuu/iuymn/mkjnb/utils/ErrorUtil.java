

package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils;

import android.app.Activity;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.base.BaseActivity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.base.BaseDialogFragment;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.base.BaseFragment;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.base.BasePopupWindow;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.base.BasePopupWindow2;


public class ErrorUtil {
    public  static void  hideErrorLayout(View recyclerView, LinearLayout errorLinear){
        if(recyclerView!=null&&errorLinear!=null){
            if(recyclerView.getVisibility()!=View.VISIBLE){
                recyclerView.setVisibility(View.VISIBLE);
            }
            if(errorLinear.getVisibility()!=View.GONE){
                errorLinear.setVisibility(View.GONE);
            }
        }
    }
    public static void showErrorLayout(Activity activity, View recyclerView, LinearLayout errorLinear, TextView reloadTv){
        if(errorLinear!=null){
            if(errorLinear.getVisibility()!=View.VISIBLE){
                errorLinear.setVisibility(View.VISIBLE);
            }
        }
        if(recyclerView!=null){
            if(recyclerView.getVisibility()!=View.GONE){
                recyclerView.setVisibility(View.GONE);
            }
        }
        if(reloadTv!=null){
            reloadTv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(activity instanceof BaseActivity){
                        ((BaseActivity) activity).errorRefresh();
                    }
                }
            });
        }
    }
    public static void headShowErrorLayout(Activity activity,   LinearLayout errorLinear, TextView reloadTv){
        if(errorLinear!=null){
            if(errorLinear.getVisibility()!=View.VISIBLE){
                errorLinear.setVisibility(View.VISIBLE);
            }
        }

        if(reloadTv!=null){
            reloadTv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(activity instanceof BaseActivity){
                        ((BaseActivity) activity).errorRefresh();
                    }
                }
            });
        }
    }
    public static void showErrorLayout(PopupWindow popupWindow, View recyclerView, LinearLayout errorLinear, TextView reloadTv){
        if(errorLinear!=null){
            if(errorLinear.getVisibility()!=View.VISIBLE){
                errorLinear.setVisibility(View.VISIBLE);
            }
        }
        if(recyclerView!=null){
            if(recyclerView.getVisibility()!=View.GONE){
                recyclerView.setVisibility(View.GONE);
            }
        }
        if(reloadTv!=null){
            reloadTv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(popupWindow instanceof BasePopupWindow){
                        ((BasePopupWindow) popupWindow).errorRefresh();
                    }
                    if(popupWindow instanceof BasePopupWindow2){
                        ((BasePopupWindow2) popupWindow).errorRefresh();
                    }
                }
            });
        }
    }

    public static void showErrorLayout(Fragment fragment, View recyclerView, LinearLayout errorLinear, TextView reloadTv){
        if(errorLinear!=null){
            if(errorLinear.getVisibility()!=View.VISIBLE){

                errorLinear.setVisibility(View.VISIBLE);
            }
        }
        if(recyclerView!=null){
            if(recyclerView.getVisibility()!=View.GONE){
                recyclerView.setVisibility(View.GONE);
            }
        }
        if(reloadTv!=null){
            reloadTv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(fragment!=null){
                        if(fragment instanceof BaseFragment){
                            ((BaseFragment) fragment).errorRefresh();
                        }
                    }

                }
            });
        }
    }
    public static void headShowErrorLayout(Fragment fragment,   LinearLayout errorLinear, TextView reloadTv){
        if(errorLinear!=null){
            if(errorLinear.getVisibility()!=View.VISIBLE){
                errorLinear.setVisibility(View.VISIBLE);
            }
        }

        if(reloadTv!=null){
            reloadTv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(fragment!=null){
                        if(fragment instanceof BaseFragment){
                            ((BaseFragment) fragment).errorRefresh();
                        }
                    }

                }
            });
        }
    }
    public static void showErrorLayout(DialogFragment dialogFragment, View recyclerView, LinearLayout errorLinear, TextView reloadTv){
        if(errorLinear!=null){
            if(errorLinear.getVisibility()!=View.VISIBLE){

                errorLinear.setVisibility(View.VISIBLE);
            }
        }
        if(recyclerView!=null){
            if(recyclerView.getVisibility()!=View.GONE){

                recyclerView.setVisibility(View.GONE);
            }
        }
        if(reloadTv!=null){
            reloadTv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(dialogFragment!=null){
                        if(dialogFragment instanceof BaseDialogFragment){
                            ((BaseDialogFragment) dialogFragment).errorRefresh();
                        }
                    }

                }
            });
        }
    }
}



