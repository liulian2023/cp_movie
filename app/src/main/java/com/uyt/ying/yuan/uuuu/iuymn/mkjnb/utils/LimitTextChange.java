package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

public class LimitTextChange {
    // 限制输入框不能输入汉字
    public static void StringWatcher(final EditText editText){
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() > 0) {
                    for (int i = 0; i < s.length(); i++) {
                        char c = s.charAt(i);
                        if (c >= 0x4e00 && c <= 0X9fff) {
                            s.delete(i,i+1);
                        }
                    }
                }
            }
        });
    }


}
