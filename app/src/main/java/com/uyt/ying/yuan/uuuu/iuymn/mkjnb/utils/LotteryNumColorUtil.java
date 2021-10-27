package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils;

import android.graphics.Color;
import android.widget.TextView;

import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.myView.MyCornerTextView;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.myView.RoundTextView;

/*
赛车开奖结果背景颜色设置
 */
public class LotteryNumColorUtil {
    public static void initLotteryNumColor(MyCornerTextView textView) {
        String text = textView.getText().toString();
        switch (text){
            case "1":
                textView.setfilColor(Color.parseColor("#cec317"));
                break;
            case "2":
                textView.setfilColor(Color.parseColor("#008bf9"));
                break;
            case "3":
                textView.setfilColor(Color.parseColor("#4d4d4d"));
                break;
            case "4":
                textView.setfilColor(Color.parseColor("#f47a00"));
                break;
            case "5":
                textView.setfilColor(Color.parseColor("#12acac"));

                break;
            case "6":
                textView.setfilColor(Color.parseColor("#420aff"));
                break;
            case "7":
                textView.setfilColor(Color.parseColor("#a3a3a3"));
                break;
            case "8":
                textView.setfilColor(Color.parseColor("#ff0000"));
                break;
            case "9":
                textView.setfilColor(Color.parseColor("#a3201f"));
                break;
            case "10":
                textView.setfilColor(Color.parseColor("#2bc610"));
                break;
        }
    }
    public static void initLotteryNumColor(RoundTextView textView) {
        String text = textView.getText().toString();
        switch (text){
            case "1":
                textView.setBackgroungColor(Color.parseColor("#cec317"));
                break;
            case "2":
                textView.setBackgroungColor(Color.parseColor("#008bf9"));
                break;
            case "3":
                textView.setBackgroungColor(Color.parseColor("#4d4d4d"));
                break;
            case "4":
                textView.setBackgroungColor(Color.parseColor("#f47a00"));
                break;
            case "5":
                textView.setBackgroungColor(Color.parseColor("#12acac"));

                break;
            case "6":
                textView.setBackgroungColor(Color.parseColor("#420aff"));
                break;
            case "7":
                textView.setBackgroungColor(Color.parseColor("#a3a3a3"));
                break;
            case "8":
                textView.setBackgroungColor(Color.parseColor("#ff0000"));
                break;
            case "9":
                textView.setBackgroungColor(Color.parseColor("#a3201f"));
                break;
            case "10":
                textView.setBackgroungColor(Color.parseColor("#2bc610"));
                break;
        }
    }
        public static void setRaceNumColor(TextView textView) {
            String text = textView.getText().toString();
            switch (text){
                case "1":
//                        textView.setfilColor(R.color.numOne);
                    textView.setBackgroundColor(Color.parseColor("#cec317"));
                    break;
                case "2":
//                        textView.setfilColor(R.color.numTwo);
                    textView.setBackgroundColor(Color.parseColor("#008bf9"));
                    break;
                case "3":
//                        textView.setfilColor(R.color.numThree);
                    textView.setBackgroundColor(Color.parseColor("#4d4d4d"));
                    break;
                case "4":
//                        textView.setfilColor(R.color.numFour);
                    textView.setBackgroundColor(Color.parseColor("#f47a00"));
                    break;
                case "5":
//                        textView.setfilColor(R.color.numFive);
                    textView.setBackgroundColor(Color.parseColor("#12acac"));

                    break;
                case "6":
//                        textView.setfilColor(R.color.numSix);
                    textView.setBackgroundColor(Color.parseColor("#420aff"));
                    break;
                case "7":
//                        textView.setfilColor(R.color.numSeven);
                    textView.setBackgroundColor(Color.parseColor("#a3a3a3"));
                    break;
                case "8":
//                        textView.setfilColor(Color.RED);
                    textView.setBackgroundColor(Color.parseColor("#ff0000"));
                    break;
                case "9":
//                        textView.setfilColor(R.color.numNine);
                    textView.setBackgroundColor(Color.parseColor("#a3201f"));
                    break;
                case "10":
//                        textView.setfilColor(R.color.numTen);
                    textView.setBackgroundColor(Color.parseColor("#2bc610"));
                    break;
            }
        }
}
