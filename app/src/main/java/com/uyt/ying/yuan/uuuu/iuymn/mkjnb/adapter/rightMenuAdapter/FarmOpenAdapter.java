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
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.FarmOpenResultModel;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.myView.MyCornerTextView;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.Utils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class FarmOpenAdapter extends RecyclerView.Adapter<FarmOpenAdapter.MyHolder> {
    private ArrayList<FarmOpenResultModel>farmOpenResultModelArrayList =new ArrayList<>();//外层适配器数据
    private ArrayList<MyCornerTextView>myCornerTextViewArrayList;
    private ArrayList<MyCornerTextView> longhuMtvList;

    public FarmOpenAdapter(ArrayList<FarmOpenResultModel> farmOpenResultModelArrayList) {
        this.farmOpenResultModelArrayList = farmOpenResultModelArrayList;
    }

    @NonNull
    @Override
    public FarmOpenAdapter.MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        //外ceng适配器item视图
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.farm_open_recycle_item,viewGroup,false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FarmOpenAdapter.MyHolder myHolder, int position) {
        myCornerTextViewArrayList=new ArrayList<>();
        longhuMtvList=new ArrayList<>();
        myCornerTextViewArrayList.add(myHolder.numOneMtv);
        myCornerTextViewArrayList.add(myHolder.numTwoMtv);
        myCornerTextViewArrayList.add(myHolder.numThreeMtv);
        myCornerTextViewArrayList.add(myHolder.numFourMtv);
        myCornerTextViewArrayList.add(myHolder.numFiveMtv);
        myCornerTextViewArrayList.add(myHolder.numSixMtv);
        myCornerTextViewArrayList.add(myHolder.numSevenMtv);
        myCornerTextViewArrayList.add(myHolder.numEightMtv);
        longhuMtvList.add(myHolder.longhuOneMtv);
        longhuMtvList.add(myHolder.longhuTwoMtv);
        longhuMtvList.add(myHolder.longhuThreeMtv);
        longhuMtvList.add(myHolder.longhuFourMtv);
        FarmOpenResultModel farmOpenResultModel = farmOpenResultModelArrayList.get(position);//外层recycleView数据been类
        myHolder.qishu.setText(farmOpenResultModel.getQishu());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
        String shijian = farmOpenResultModel.getShijian();
        Date date = new Date(Long.parseLong(shijian));
        String format = simpleDateFormat.format(date);
        myHolder.shijian.setText(format);
        if(farmOpenResultModel.isGuanYa()){
            myHolder.lineartwo.setVisibility(View.VISIBLE);
            myHolder.linearOne.setVisibility(View.GONE);
            String hezhi = farmOpenResultModel.getHezhi();
            myHolder.hezhiTv.setText(hezhi);
            String daxiao = farmOpenResultModel.getDaxiao();
            myHolder.daxiaoMtv.setText(daxiao);
            myHolder.daxiaoMtv.setCornerSize(50);
            String danshuang = farmOpenResultModel.getDanshuang();
            myHolder.danshuangMtv.setText(danshuang);
            myHolder.danshuangMtv.setCornerSize(50);
            String weida = farmOpenResultModel.getWeida();
            myHolder.weidaTv.setText(Utils.getString(R.string.尾)+weida);
            String marklh = farmOpenResultModel.getMarklh();//龙虎数组数据
            String[] split = marklh.split(",");
            List<String> strings = Arrays.asList(split);
            for (int i = 0; i < strings.size(); i++) {
                String text = strings.get(i);
                longhuMtvList.get(i).setText(text);
                if(text.equals(Utils.getString(R.string.龙))){
                    longhuMtvList.get(i).setfilColor(Color.parseColor("#3174ff"));
                }else{
                    longhuMtvList.get(i).setfilColor(Color.parseColor("#ff7604"));
                }
                longhuMtvList.get(i).setCornerSize(50);
            }
            if(weida.equals(Utils.getString(R.string.尾大))){
                myHolder.weidaTv.setTextColor(Color.parseColor("#0066CC"));
            }else{
                myHolder.weidaTv.setTextColor(Color.parseColor("#FF9B00"));
            }
            if(daxiao.equals(Utils.getString(R.string.小))){
                myHolder.daxiaoMtv.setfilColor(Color.parseColor("#ff7604"));
            }else {
                myHolder.daxiaoMtv.setfilColor(Color.parseColor("#3174ff"));
            }
            if(danshuang.equals(Utils.getString(R.string.双))){
                myHolder.danshuangMtv.setfilColor(Color.parseColor("#ff7604"));
            }else{
                myHolder.danshuangMtv.setfilColor(Color.parseColor("#3174ff"));
            }
        }
        else{
            myHolder.lineartwo.setVisibility(View.GONE);
            myHolder.linearOne.setVisibility(View.VISIBLE);
            String numList = farmOpenResultModel.getNumList();
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
                }else if(openNum.equals(Utils.getString(R.string.双))|| openNum.equals(Utils.getString(R.string.小))|| openNum.equals(Utils.getString(R.string.虎))){
                    myCornerTextView.setfilColor(Color.parseColor("#ff7604"));
                }else{
                    myCornerTextView.setfilColor(Color.parseColor("#377EFF"));
                }
            }

        }
    }

    @Override
    public int getItemCount() {
        return farmOpenResultModelArrayList.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        private TextView qishu;
        private TextView shijian;
        private MyCornerTextView numOneMtv;
        private MyCornerTextView numTwoMtv;
        private MyCornerTextView numThreeMtv;
        private MyCornerTextView numFourMtv;
        private MyCornerTextView numFiveMtv;
        private MyCornerTextView numSixMtv;
        private MyCornerTextView numSevenMtv;
        private MyCornerTextView numEightMtv;
        private TextView hezhiTv;
        private MyCornerTextView daxiaoMtv;
        private MyCornerTextView danshuangMtv;
        private TextView weidaTv;
        private MyCornerTextView longhuOneMtv;
        private MyCornerTextView longhuTwoMtv;
        private MyCornerTextView longhuThreeMtv;
        private MyCornerTextView longhuFourMtv;
        private LinearLayout linearOne;
        private LinearLayout lineartwo;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            qishu=itemView.findViewById(R.id.qishu);
            shijian=itemView.findViewById(R.id.time);
            numOneMtv=itemView.findViewById(R.id.num_one);
            numTwoMtv=itemView.findViewById(R.id.num_two);
            numThreeMtv=itemView.findViewById(R.id.num_three);
            numFourMtv=itemView.findViewById(R.id.num_four);
            numFiveMtv=itemView.findViewById(R.id.num_five);
            numSixMtv=itemView.findViewById(R.id.num_six);
            numSevenMtv=itemView.findViewById(R.id.num_seven);
            numEightMtv=itemView.findViewById(R.id.num_eight);
            hezhiTv=itemView.findViewById(R.id.hezhi);
            daxiaoMtv=itemView.findViewById(R.id.daxiao);
            danshuangMtv=itemView.findViewById(R.id.danshuang);
            weidaTv=itemView.findViewById(R.id.weida);

            longhuOneMtv=itemView.findViewById(R.id.longhu_one);
            longhuTwoMtv=itemView.findViewById(R.id.longhu_two);
            longhuThreeMtv=itemView.findViewById(R.id.longhu_three);
            longhuFourMtv=itemView.findViewById(R.id.longhu_four);

            linearOne=itemView.findViewById(R.id.linear1);
            lineartwo=itemView.findViewById(R.id.linear2);
        }
    }
}
