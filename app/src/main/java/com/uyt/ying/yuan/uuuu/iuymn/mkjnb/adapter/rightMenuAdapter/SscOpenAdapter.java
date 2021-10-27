package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.rightMenuAdapter;

import android.graphics.Color;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.SscOpenResultModel;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.myView.MyCornerTextView;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.Utils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class SscOpenAdapter extends RecyclerView.Adapter<SscOpenAdapter.MyHolder> {
    private ArrayList<SscOpenResultModel>sscOpenResultModelArrayList =new ArrayList<>();//外层适配器数据
    private ArrayList<MyCornerTextView>myCornerTextViewArrayList;
    private ArrayList<String> stringArrayList;
    private int game;

    public SscOpenAdapter(ArrayList<SscOpenResultModel> sscOpenResultModelArrayList, int game) {
        this.sscOpenResultModelArrayList = sscOpenResultModelArrayList;
        this.game = game;
    }

    @NonNull
    @Override
    public SscOpenAdapter.MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        //外ceng适配器item视图
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.ssc_open_recycle_item,viewGroup,false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SscOpenAdapter.MyHolder myHolder, int position) {
        myCornerTextViewArrayList=new ArrayList<>();
        stringArrayList=new ArrayList<>();
        stringArrayList.add("0");
        stringArrayList.add("1");
        stringArrayList.add("2");
        stringArrayList.add("3");
        stringArrayList.add("4");
        stringArrayList.add("5");
        stringArrayList.add("6");
        stringArrayList.add("7");
        stringArrayList.add("8");
        stringArrayList.add("9");
        stringArrayList.add("10");
        stringArrayList.add("11");
        myCornerTextViewArrayList.add(myHolder.numOneMtv);
        myCornerTextViewArrayList.add(myHolder.numTwoMtv);
        myCornerTextViewArrayList.add(myHolder.numThreeMtv);
        myCornerTextViewArrayList.add(myHolder.numFourMtv);
        myCornerTextViewArrayList.add(myHolder.numFiveMtv);
        SscOpenResultModel sscOpenResultModel = sscOpenResultModelArrayList.get(position);//外层recycleView数据been类
        myHolder.qishu.setText(sscOpenResultModel.getQishu());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
        String shijian = sscOpenResultModel.getShijian();
        Date date = new Date(Long.parseLong(shijian));
        String format = simpleDateFormat.format(date);

        myHolder.shijian.setText(format);

        if(sscOpenResultModel.isGuanYa()){
            if(game!=9){
                myHolder.lineartwo.setVisibility(View.VISIBLE);
                myHolder.linearOne.setVisibility(View.GONE);
                myHolder.linearThree.setVisibility(View.GONE);

                String hezhi = sscOpenResultModel.getHezhi();
                myHolder.hezhiTv.setText(hezhi);
                String daxiao = sscOpenResultModel.getDaxiao();
                myHolder.daxiaoMtv.setText(daxiao);
                myHolder.daxiaoMtv.setCornerSize(50);
                String danshuang = sscOpenResultModel.getDanshuang();
                myHolder.danshuangMtv.setText(danshuang);
                myHolder.danshuangMtv.setCornerSize(50);
                String longhu = sscOpenResultModel.getLonghu();
                myHolder.longhuMtv.setText(longhu);
                myHolder.longhuMtv.setCornerSize(50);
                String markhs = sscOpenResultModel.getMarkhs();
                myHolder.markhsTv.setText(markhs);
                String markqs = sscOpenResultModel.getMarkqs();
                myHolder.markqsTv.setText(markqs);
                String markzs = sscOpenResultModel.getMarkzs();
                myHolder.markzsTv.setText(markzs);

                if(daxiao.equals(Utils.getString(R.string.小))){
                    myHolder.daxiaoMtv.setfilColor(Color.parseColor("#FF9000"));
                }else {
                    myHolder.daxiaoMtv.setfilColor(Color.parseColor("#3174ff"));
                }
                if(danshuang.equals(Utils.getString(R.string.双))){
                    myHolder.danshuangMtv.setfilColor(Color.parseColor("#FF9000"));
                }else if(danshuang.equals(Utils.getString(R.string.单))){
                    myHolder.danshuangMtv.setfilColor(Color.parseColor("#3174ff"));
                }else {//和
                    myHolder.danshuangMtv.setfilColor(Color.parseColor("#EB3E9C"));
                }
                if(longhu.equals(Utils.getString(R.string.龙))){
                    myHolder. longhuMtv.setfilColor(Color.parseColor("#3174ff"));
                }
                else if(longhu.equals(Utils.getString(R.string.和))){
                    myHolder. longhuMtv.setfilColor(Color.parseColor("#E73F3F"));
                }
                else{
                    myHolder.longhuMtv.setfilColor(Color.parseColor("#FF9000"));
                }
                if(markhs.equals(Utils.getString(R.string.半顺))){
                    myHolder.markhsTv.setTextColor(Color.parseColor("#FFA926"));
                }else if(markhs.equals(Utils.getString(R.string.顺子))){
                    myHolder.markhsTv.setTextColor(Color.parseColor("#287ED4"));
                }else if(markhs.equals(Utils.getString(R.string.对子))){
                    myHolder.markhsTv.setTextColor(Color.parseColor("#13DF43"));
                }else if(markhs.equals(Utils.getString(R.string.豹子))){
                    myHolder.markhsTv.setTextColor(Color.parseColor("#FF1F1F"));
                }else{
                    myHolder.markhsTv.setTextColor(Color.parseColor("#000000"));
                }
                if(markqs.equals(Utils.getString(R.string.半顺))){
                    myHolder.markqsTv.setTextColor(Color.parseColor("#FFA926"));
                }else if(markqs.equals(Utils.getString(R.string.顺子))){
                    myHolder.markqsTv.setTextColor(Color.parseColor("#287ED4"));
                }else if(markqs.equals(Utils.getString(R.string.对子))){
                    myHolder.markqsTv.setTextColor(Color.parseColor("#13DF43"));
                }else if(markqs.equals(Utils.getString(R.string.豹子))){
                    myHolder.markqsTv.setTextColor(Color.parseColor("#FF1F1F"));
                }else{
                    myHolder.markqsTv.setTextColor(Color.parseColor("#000000"));
                }
                if(markzs.equals(Utils.getString(R.string.半顺))){
                    myHolder.markzsTv.setTextColor(Color.parseColor("#FFA926"));
                }else if(markzs.equals(Utils.getString(R.string.顺子))){
                    myHolder.markzsTv.setTextColor(Color.parseColor("#287ED4"));
                }else if(markzs.equals(Utils.getString(R.string.对子))){
                    myHolder.markzsTv.setTextColor(Color.parseColor("#13DF43"));
                }else if(markzs.equals(Utils.getString(R.string.豹子))){
                    myHolder.markzsTv.setTextColor(Color.parseColor("#FF1F1F"));
                }else{
                    myHolder.markzsTv.setTextColor(Color.parseColor("#000000"));
                }

            }else {
                myHolder.lineartwo.setVisibility(View.GONE);
                myHolder.linearOne.setVisibility(View.GONE);
                myHolder.linearThree.setVisibility(View.VISIBLE);
                //11选5 形态数据处理
                String hezhi = sscOpenResultModel.getHezhi();
                myHolder.hezhi5Tv.setText(hezhi);
                String daxiao = sscOpenResultModel.getDaxiao();
                myHolder.daxiao5Mtv.setText(daxiao);
                myHolder.daxiao5Mtv.setCornerSize(50);
                String danshuang = sscOpenResultModel.getDanshuang();
                myHolder.danshuang5Mtv.setText(danshuang);
                myHolder.danshuang5Mtv.setCornerSize(50);
                String markwdx = sscOpenResultModel.getMarkwdx();
                myHolder.weida5Tv.setText(markwdx);
                String longhu = sscOpenResultModel.getLonghu();
                myHolder.longhu5Mtv.setText(longhu);
                myHolder.longhu5Mtv.setCornerSize(50);


                if(daxiao.equals(Utils.getString(R.string.小))){
                    myHolder.daxiao5Mtv.setfilColor(Color.parseColor("#FF9000"));
                }else if(daxiao.equals(Utils.getString(R.string.大))){
                    myHolder.daxiao5Mtv.setfilColor(Color.parseColor("#3174ff"));
                }else {//和
                    myHolder.daxiao5Mtv.setfilColor(Color.parseColor("#E73F3F"));

                }
                if(danshuang.equals(Utils.getString(R.string.双))){
                    myHolder.danshuang5Mtv.setfilColor(Color.parseColor("#FF9000"));
                }else if(danshuang.equals(Utils.getString(R.string.单))){
                    myHolder.danshuang5Mtv.setfilColor(Color.parseColor("#3174ff"));
                }else {//和
                    myHolder.danshuang5Mtv.setfilColor(Color.parseColor("#EB3E9C"));
                }
                if(longhu.equals(Utils.getString(R.string.龙))){
                    myHolder. longhu5Mtv.setfilColor(Color.parseColor("#3174ff"));
                }else{
                    myHolder.longhu5Mtv.setfilColor(Color.parseColor("#FF9000"));
                }
            }

        }
        else{
            myHolder.lineartwo.setVisibility(View.GONE);
            myHolder.linearOne.setVisibility(View.VISIBLE);
            myHolder.linearThree.setVisibility(View.GONE);
            String numList = sscOpenResultModel.getNumList();
            String[] split = numList.split(",");
            List<String> strings = Arrays.asList(split);
            for (int i = 0; i < strings.size(); i++) {
                MyCornerTextView myCornerTextView = myCornerTextViewArrayList.get(i);
                myCornerTextView.setText(strings.get(i));
                myCornerTextView.setCornerSize(50);
                myCornerTextView.setTextColor(Color.parseColor("#ffffff"));
                String openNum = myCornerTextView.getText().toString();
                if(openNum.equals(Utils.getString(R.string.单))|| openNum.equals(Utils.getString(R.string.大))|| openNum.equals(Utils.getString(R.string.龙))){
                    myCornerTextView.setfilColor(Color.parseColor("#3174ff"));
                }else if(stringArrayList.contains(openNum)){
                    myCornerTextView.setfilColor(Color.parseColor("#0091de"));
                }else if(openNum.equals(Utils.getString(R.string.和))){//和
                    myCornerTextView.setfilColor(Color.parseColor("#E73F3F"));
                }
                else{
                    myCornerTextView.setfilColor(Color.parseColor("#ff7604"));
                }
            }

        }
    }

    @Override
    public int getItemCount() {
        return sscOpenResultModelArrayList.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        private TextView qishu;
        private TextView shijian;
        private MyCornerTextView numOneMtv;
        private MyCornerTextView numTwoMtv;
        private MyCornerTextView numThreeMtv;
        private MyCornerTextView numFourMtv;
        private MyCornerTextView numFiveMtv;
        private TextView hezhiTv;
        private MyCornerTextView daxiaoMtv;
        private MyCornerTextView danshuangMtv;
        private MyCornerTextView longhuMtv;
        private TextView markhsTv;//markhs
        private TextView markqsTv;//markqs
        private TextView markzsTv;//markzs
        private LinearLayout linearOne;
        private LinearLayout lineartwo;
        private LinearLayout linearThree;
        private TextView hezhi5Tv;
        private MyCornerTextView daxiao5Mtv;
        private MyCornerTextView danshuang5Mtv;
        private TextView weida5Tv;
        private MyCornerTextView longhu5Mtv;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            qishu=itemView.findViewById(R.id.qishu);
            shijian=itemView.findViewById(R.id.time);
            numOneMtv=itemView.findViewById(R.id.num_one);
            numTwoMtv=itemView.findViewById(R.id.num_two);
            numThreeMtv=itemView.findViewById(R.id.num_three);
            numFourMtv=itemView.findViewById(R.id.num_four);
            numFiveMtv=itemView.findViewById(R.id.num_five);
            hezhiTv=itemView.findViewById(R.id.hezhi);
            daxiaoMtv=itemView.findViewById(R.id.daxiao);
            danshuangMtv=itemView.findViewById(R.id.danshuang);
            longhuMtv=itemView.findViewById(R.id.zonghe);
            markhsTv=itemView.findViewById(R.id.xingtai_three);
            markqsTv=itemView.findViewById(R.id.xingtai_one);
            markzsTv=itemView.findViewById(R.id.xingtai_two);
            hezhi5Tv=itemView.findViewById(R.id.hezhi_5);
            daxiao5Mtv=itemView.findViewById(R.id.daxiao_5);
            danshuang5Mtv=itemView.findViewById(R.id.danshuang_5);
            weida5Tv=itemView.findViewById(R.id.weida_5);
            longhu5Mtv=itemView.findViewById(R.id.longhu_5);
            linearOne=itemView.findViewById(R.id.linear1);
            lineartwo=itemView.findViewById(R.id.linear2);
            linearThree=itemView.findViewById(R.id.linear3);
        }
    }
}
