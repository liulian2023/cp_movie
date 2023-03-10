package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.live_adapter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.LiveMessageModel;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.myView.MyClickableSpan;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.myView.MyImageSpan;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.myView.UrlImageSpan;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.StringMyUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.CommonStr;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.Utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

public class LiveChatAdapter  extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements MyClickableSpan.OnClickSpanLintener, View.OnClickListener {
    private Fragment fragment;
    private ArrayList<LiveMessageModel> liveMessageModelArrayList = new ArrayList<>();
    public static  int QUEYUE_RED=1;
    public static int ZHUANXIANG_RED=2;
    public static int PUTONG_RED=3;
    public static int TJ_RED =4;
    public static int NORMAL_RED =5;

    public LiveChatAdapter(Fragment fragment, ArrayList<LiveMessageModel> liveMessageModelArrayList) {
        this.fragment = fragment;
        this.liveMessageModelArrayList = liveMessageModelArrayList;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        RecyclerView.ViewHolder viewHolder=null;
        //1 ??????  2 ??????  3 ???????????? 4 ???????????? 5 ???????????? 6 ??????/?????????????????????
        switch (viewType){
            case 1:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.live_text_message_item,null);
                viewHolder = new TextHolder(view);
                break;
            case 2:
                view=LayoutInflater.from(parent.getContext()).inflate(R.layout.live_red_message_item,null);
                viewHolder=new RedHolder(view);
                break;
            case 3:
                view=LayoutInflater.from(parent.getContext()).inflate(R.layout.live_notice_message_item,null);
                viewHolder=new NoticeHolder(view);
                break;
            case 4:
                view=LayoutInflater.from(parent.getContext()).inflate(R.layout.live_share_message_item,null);
                viewHolder=new ShareHolder(view);
                break;
            case 5:
                view=LayoutInflater.from(parent.getContext()).inflate(R.layout.live_gift_message_item,null);
                viewHolder=new GiftHolder(view);
                break;
            //
            case 6:
                view=LayoutInflater.from(parent.getContext()).inflate(R.layout.live_exit_join_message_item,null);
                viewHolder=new ExitJoinHolder(view);
                break;
            case 7:
                view=LayoutInflater.from(parent.getContext()).inflate(R.layout.live_exit_join_message_item,null);
                viewHolder=new ShareZjHolder(view);
                break;
            case 8:
                view=LayoutInflater.from(parent.getContext()).inflate(R.layout.live_exit_join_message_item,null);
                viewHolder=new FollowHolder(view);
                break;
            case 9:
                view=LayoutInflater.from(parent.getContext()).inflate(R.layout.live_exit_join_message_item,null);
                viewHolder=new ForbiddenHolder(view);
                break;
            case 10:
                view=LayoutInflater.from(parent.getContext()).inflate(R.layout.live_exit_join_message_item,null);
                viewHolder=new RoomManagerHolder(view);
                break;
            case 11:
                view=LayoutInflater.from(parent.getContext()).inflate(R.layout.live_notice_message_item,null);
                viewHolder=new ChangeRoomTypeHolder(view);
                break;
            case 12:
                view=LayoutInflater.from(parent.getContext()).inflate(R.layout.live_notice_message_item,null);
                viewHolder=new RongConnectHolder(view);
                break;
            case 13:
                view=LayoutInflater.from(parent.getContext()).inflate(R.layout.live_notice_message_item,null);
                viewHolder=new LiveSystemMessageHolder(view);
                break;
            case 14:
                view=LayoutInflater.from(parent.getContext()).inflate(R.layout.live_red_message_item,null);
                viewHolder=new NormalRedHolder(view);
                break;
            case 15:
                view=LayoutInflater.from(parent.getContext()).inflate(R.layout.live_exit_join_message_item,null);
                viewHolder=new RewardAnchorHolder(view);
                break;
            default:
                break;
        }
        return viewHolder;
    }
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        try{
            int itemViewType = getItemViewType(position);
            LiveMessageModel liveMessageModel = liveMessageModelArrayList.get(position);
            String userName = liveMessageModel.getUserName();
            String textContent = liveMessageModel.getTextContent();
            String managerType= liveMessageModel.getManagerType();
            String levelIcon = liveMessageModel.getLevelIcon();
            String medalIcon = liveMessageModel.getMedalIcon();
            String titleIcon = liveMessageModel.getTitleIcon();
            /**
             * ????????????colorSpan
             */
            ForegroundColorSpan defaultColorSpan = new ForegroundColorSpan(Color.WHITE);
            /**
             * ?????????colorSpan
             */
            ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(Color.parseColor("#FFCB14"));
            switch (itemViewType){
                case 1:
                    TextHolder textHolder= (TextHolder) holder;
                    TextView textNameContentTv = textHolder.textNameContentTv;

                    SpannableStringBuilder spannableStringBuilder = initLevelEquipmentIcon( managerType, levelIcon, medalIcon, titleIcon, textNameContentTv);

                    spannableStringBuilder.append(userName ,foregroundColorSpan,Spanned.SPAN_INCLUSIVE_INCLUSIVE);
                    spannableStringBuilder.append(" "+textContent,defaultColorSpan,Spanned.SPAN_INCLUSIVE_INCLUSIVE);

                    textNameContentTv.setText(spannableStringBuilder);
                    textNameContentTv.setTag(position);
                    textNameContentTv.setOnClickListener(this);
                    break;
                case 2:
                    RedHolder redHolder =(RedHolder) holder;
                    TextView redContenttv = redHolder.redContenttv;
                    String redPrice = liveMessageModel.getRedPrice();
                    switch (liveMessageModel.getRedType()){
                        //1 ??????  2 ?????? 3 ??????
                        case 1:
                            setRedItem(QUEYUE_RED,userName,redPrice,textContent,redContenttv,position);
                            break;
                        case 2:
                            setRedItem(ZHUANXIANG_RED,userName,redPrice,textContent,redContenttv,position);
                            break;
                        case 3:
                            setRedItem(PUTONG_RED,userName,redPrice,textContent,redContenttv,position);
                            break;
                        case 4:
                            setRedItem(TJ_RED,userName,redPrice,textContent,redContenttv,position);
                            break;
                        default:
                            break;
                    }
                    break;
                case 3:
                    NoticeHolder noticeHolder = (NoticeHolder) holder;
                    noticeHolder.noticeContenttv.setText(textContent);
                    break;
                case 4:
                    ShareHolder shareHolder = (ShareHolder) holder;
                    SpannableStringBuilder shareSpanBuilder = initLevelEquipmentIcon( managerType, levelIcon, medalIcon, titleIcon, shareHolder.shareContenttv);
                    shareSpanBuilder.append(userName,foregroundColorSpan,Spanned.SPAN_INCLUSIVE_INCLUSIVE);
                    shareSpanBuilder.append(Utils.getString(R.string.?????????)+liveMessageModel.getTypeName()+Utils.getString(R.string.????????????)+liveMessageModel.getBetAmount()+Utils.getString(R.string.????????????),defaultColorSpan,Spanned.SPAN_INCLUSIVE_INCLUSIVE);
                    SpannableString gentouSpan = new SpannableString("   ");
                    MyImageSpan iconSpanPt = new MyImageSpan(fragment.getContext(), Utils.getNewBitmap(BitmapFactory.decodeResource(fragment.getResources(), R.drawable.gt_btn), 40, 18));
                    MyClickableSpan shareClickSpan = new MyClickableSpan(textContent, fragment.getContext(), position);
                    gentouSpan.setSpan(iconSpanPt,0,gentouSpan.length()-1,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    gentouSpan.setSpan(shareClickSpan,0,gentouSpan.length()-1,Spanned.SPAN_INCLUSIVE_INCLUSIVE);
                    shareSpanBuilder.append(gentouSpan);
//                initLevelIcon(level,string,userName,managerType,true);
                    shareHolder.shareContenttv.setText(shareSpanBuilder);
                    //????????????
                    shareClickSpan.setOnClickSpanLintener(this);
                    //??????????????????????????????
                    shareHolder.shareContenttv.setMovementMethod(LinkMovementMethod.getInstance());
                    shareHolder.shareContenttv.setTag(position);
                    break;
                //????????????
                case 5:
                    GiftHolder giftHolde =(GiftHolder)holder;
                    String giftId = liveMessageModel.getGiftId();
                    String giftName = liveMessageModel.getGiftName();
                    String giftNum = liveMessageModel.getGiftNum();
                    SpannableStringBuilder giftSpanBuilder = initLevelEquipmentIcon( managerType, levelIcon, medalIcon, titleIcon, giftHolde.giftContenttv);
                    giftSpanBuilder.append(userName+Utils.getString(R.string.???????????????) + giftName + " x" + giftNum,foregroundColorSpan,Spanned.SPAN_INCLUSIVE_INCLUSIVE);
                    giftHolde.giftContenttv.setText(giftSpanBuilder);
                    break;
                //?????????????????????
                case 6:
                    ExitJoinHolder exitJoinHolder= (ExitJoinHolder) holder;
                    TextView exitJoinCotentTv = exitJoinHolder.exitJoinCotentTv;
                    SpannableStringBuilder joinSpanBuilder = initLevelEquipmentIcon( managerType, levelIcon, medalIcon, titleIcon, exitJoinCotentTv);
                    String s ;
                    if(liveMessageModel.getStatus().equals("0")){
                        s=Utils.getString(R.string.???????????????);
                    }else {
                        s=Utils.getString(R.string.???????????????);
                    }
                    joinSpanBuilder.append(liveMessageModel.getUserName(),foregroundColorSpan,Spanned.SPAN_INCLUSIVE_INCLUSIVE);
                    joinSpanBuilder.append(s,defaultColorSpan,Spanned.SPAN_INCLUSIVE_INCLUSIVE);
//                SpannableString exitSpan = new SpannableString("    "+liveMessageModel.getUserName() + s);
//                initLevelIcon(level, exitSpan,userName,managerType,true);
                    exitJoinCotentTv.setTextColor(Color.WHITE);
                    exitJoinCotentTv.setText(joinSpanBuilder);
                    break;
                case 7:
                    ShareZjHolder shareZjHolder= (ShareZjHolder) holder;
                    TextView zjContentTv = shareZjHolder.zjContentTv;
                    SpannableStringBuilder zjStringBuilder = initLevelEquipmentIcon( managerType, levelIcon, medalIcon, titleIcon, zjContentTv);
                    Bitmap bitmap = BitmapFactory.decodeResource(fragment.getResources(), R.drawable.zj_icon);
                    MyImageSpan iconSpan = new MyImageSpan(fragment.getContext(), Utils.getNewBitmap(bitmap, 35, 15));
                    zjStringBuilder.append(" ",iconSpan,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    zjStringBuilder.append(Utils.getString(R.string.???????????????),defaultColorSpan,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    zjStringBuilder.append(liveMessageModel.getUserNickName(),foregroundColorSpan,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    zjStringBuilder.append(Utils.getString(R.string.???)+liveMessageModel.getTypeName()+Utils.getString(R.string.?????????),defaultColorSpan,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

                    zjContentTv.setText(zjStringBuilder);
                    break;
                case 8:
                    FollowHolder followHolder = (FollowHolder) holder;
                    TextView followContentTv = followHolder.followContentTv;
                    SpannableString followSpanStr = new SpannableString(liveMessageModel.getUserName()+Utils.getString(R.string.?????????????????????));
                    ForegroundColorSpan followColorSpan = new ForegroundColorSpan(Color.parseColor("#FFCB14"));
                    followSpanStr.setSpan(followColorSpan,0,userName.length()+1,Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
                    followContentTv.setText(followSpanStr);
                    break;
                case 9:
                    ForbiddenHolder forbiddenHolder = (ForbiddenHolder) holder;
/*                if(managerType.equals("2")){
                    //???????????????????????????
                    forbiddenHolder.forbidden_wrap_linear.setVisibility(View.GONE);
                    return;
                }
                forbiddenHolder.forbidden_wrap_linear.setVisibility(View.VISIBLE);*/
                    TextView forbiddenContentTv = forbiddenHolder.forbiddenContentTv;
                    String isForbidden = liveMessageModel.getIsForbidden();//  1 ?????? 0??????
                    String targetNickName = liveMessageModel.getTargetNickName();//?????????????????????
                    SpannableStringBuilder forbiddenStringBuilder = initLevelEquipmentIcon( managerType, levelIcon, medalIcon, titleIcon, forbiddenContentTv);
                    String forbiddenStatus = isForbidden.equals("1")?Utils.getString(R.string.??????):Utils.getString(R.string.??????);
                    forbiddenStringBuilder.append(" "+userName,foregroundColorSpan,Spanned.SPAN_INCLUSIVE_INCLUSIVE);
                    forbiddenStringBuilder.append(Utils.getString(R.string.???),defaultColorSpan,Spanned.SPAN_INCLUSIVE_INCLUSIVE);
                    forbiddenStringBuilder.append(targetNickName,foregroundColorSpan,Spanned.SPAN_INCLUSIVE_INCLUSIVE);
                    forbiddenStringBuilder.append(forbiddenStatus+Utils.getString(R.string.???),defaultColorSpan,Spanned.SPAN_INCLUSIVE_INCLUSIVE);

                    forbiddenContentTv.setText(forbiddenStringBuilder);
                    break;
                case 10:
                    RoomManagerHolder roomManagerHolder = (RoomManagerHolder) holder;
                    TextView roomManagerContentTv = roomManagerHolder.roomManagerContentTv;
                    String nickName = liveMessageModel.getTargetNickName();
                    String isRoomManager = liveMessageModel.getIsRoomManager();
                    SpannableStringBuilder roomManagerStringBuilder = initLevelEquipmentIcon( managerType, levelIcon, medalIcon, titleIcon, roomManagerContentTv);
                    roomManagerStringBuilder.append(" "+userName,foregroundColorSpan,Spanned.SPAN_INCLUSIVE_INCLUSIVE);
                    roomManagerStringBuilder.append(Utils.getString(R.string.???),defaultColorSpan,Spanned.SPAN_INCLUSIVE_INCLUSIVE);
                    roomManagerStringBuilder.append(nickName,foregroundColorSpan,Spanned.SPAN_INCLUSIVE_INCLUSIVE);
                    String type=isRoomManager.equals("1")?Utils.getString(R.string.??????):Utils.getString(R.string.??????);
                    roomManagerStringBuilder.append(type+Utils.getString(R.string.??????),defaultColorSpan,Spanned.SPAN_INCLUSIVE_INCLUSIVE);
                    roomManagerContentTv.setText(roomManagerStringBuilder);
                    break;
                case 11:
                    ChangeRoomTypeHolder changeRoomTypeHolder = (ChangeRoomTypeHolder) holder;
                    String changeContent=Utils.getString(R.string.?????????????????????);
                    if(liveMessageModel.getType().equals("1")){
                        changeContent+=Utils.getString(R.string.??????);
                    }else {
                        changeContent+=Utils.getString(R.string.??????);
                    }
                    changeRoomTypeHolder.changeContent_tv.setText(changeContent);
                    break;
                case 12:
                    RongConnectHolder rongConnectHolder = (RongConnectHolder) holder;
                    String connectStatus;
                    if(liveMessageModel.isRongConnect()){
                        connectStatus=Utils.getString(R.string.??????????????????);
                    }else {
                        connectStatus=Utils.getString(R.string.????????????????????????????????????);
                    }
                    rongConnectHolder.connectStatusTv.setText(connectStatus);
                    break;
                case 13:
                    /**
                     * ????????????/????????????
                     */
                    LiveSystemMessageHolder systemHolder = (LiveSystemMessageHolder) holder;
                    SpannableStringBuilder systemSpanBuilder = new SpannableStringBuilder();


                    String fieldTxt = "";
                    String field = liveMessageModel.getField();
                    if (field.equals("forbiddenStatus")) {
                        fieldTxt = Utils.getString(R.string.??????);
                    }else if (field.equals("blockStatus")){
                        fieldTxt = Utils.getString(R.string.??????);
                    }else if (field.equals("maintainStatus")){
                        fieldTxt = Utils.getString(R.string.??????);
                    }
                    String cancel = "";
                    if (liveMessageModel.getSystemStatus().equals("0")) {
                        cancel = Utils.getString(R.string.??????);
                    }else{
                        cancel = "";
                    }
                    ForegroundColorSpan redColorSpan = new ForegroundColorSpan(Color.RED);
                    systemSpanBuilder.append(liveMessageModel.getUserNickName(),foregroundColorSpan,Spanned.SPAN_INCLUSIVE_INCLUSIVE);
                    systemSpanBuilder.append(String.format(Utils.getString(R.string.???????????????),cancel, fieldTxt),redColorSpan,Spanned.SPAN_INCLUSIVE_INCLUSIVE);
                    systemHolder.contentTv.setText(systemSpanBuilder);
                    break;
                case 14:
                    NormalRedHolder normalRedHolder =(NormalRedHolder) holder;
                    TextView normalRedContentTv = normalRedHolder.redContenttv;
                    String normalRedPrice = liveMessageModel.getRedPrice();
                    setRedItem(NORMAL_RED,userName,normalRedPrice,textContent,normalRedContentTv,position);
                    break;
                case 15:
                    RewardAnchorHolder rewardAnchorHolder = (RewardAnchorHolder) holder;
                    TextView reward_content_tv = rewardAnchorHolder.reward_content_tv;
                    reward_content_tv.setTextColor(Color.parseColor("#FFCB14"));
                    reward_content_tv.setText(String.format(Utils.getString(R.string.???????????????),userName,liveMessageModel.getRewardPrice()));
                    break;
                default:
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @NotNull
    private SpannableStringBuilder initLevelEquipmentIcon(String managerType, String levelIcon, String medalIcon, String titleIcon, TextView textNameContentTv) {
        managerType = StringMyUtil.isEmptyString(managerType)?"":managerType;
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        int drawType = R.drawable.saixuan1;
        if(managerType.equals("1")){
            //??????????????????icon
            drawType=R.drawable.jinyan_zb_icon;
        }else if(managerType.equals("2")){
//            drawType=R.drawable.chaoguang;//????????????
        }else if(managerType.equals("3")){
            drawType=R.drawable.fangguang;
        }else {
            //????????????????????????icon
        }
        if(drawType!=R.drawable.saixuan1){
            //????????????icon
            Bitmap bitmapType = BitmapFactory.decodeResource(fragment.getResources(), drawType);
            MyImageSpan typeSpan = new MyImageSpan(fragment.getContext(), Utils.getNewBitmap(bitmapType, 35, 15));
            spannableStringBuilder.append(" ",typeSpan, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        if(!managerType.equals("1")){
            //????????????, ????????????icon ??????icon medalIcon
            if(StringMyUtil.isNotEmpty(levelIcon)){
                /**
                 * ??????icon
                 */
                UrlImageSpan levelUrlImageSpan = new UrlImageSpan(fragment.getContext(), Utils.checkImageUrl(levelIcon), textNameContentTv,35,15,R.drawable.touming);
                spannableStringBuilder.append(" ", levelUrlImageSpan, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                spannableStringBuilder.append(" ");
            }
            if(StringMyUtil.isNotEmpty(medalIcon)){
                /**
                 * ??????icon
                 */
                UrlImageSpan medalUrlImageSpan = new UrlImageSpan(fragment.getContext(), Utils.checkImageUrl(medalIcon), textNameContentTv,15,15,R.drawable.touming2);
                spannableStringBuilder.append(" ", medalUrlImageSpan, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                spannableStringBuilder.append(" ");
            }

            if(StringMyUtil.isNotEmpty(titleIcon)){
                /**
                 * ?????????icon
                 */
                List<String> titleIconList = Arrays.asList(titleIcon.split(","));
                for (int i = 0; i < titleIconList.size(); i++) {
                    UrlImageSpan titleUrlImageSpan = new UrlImageSpan(fragment.getContext(), Utils.checkImageUrl(titleIconList.get(i)), textNameContentTv,35,15,R.drawable.touming);
                    spannableStringBuilder.append(" ", titleUrlImageSpan, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);//?????????icon
                    spannableStringBuilder.append(" ");
                }
            }
        }


        return spannableStringBuilder;
    }

    /**
     * ??????????????????span  ????????????????????????
     * @param forbiddenSpanStr
     * @param startIndex )
     * @param endIndex
     */
    private void setColorForbidden(SpannableString forbiddenSpanStr, int startIndex, int endIndex) {
        ForegroundColorSpan forBiddenColorSpan = new ForegroundColorSpan(Color.parseColor("#FFCB14"));
        forbiddenSpanStr.setSpan(forBiddenColorSpan, startIndex, endIndex, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
    }

    /**
     * ???????????? bindview??????
     * @param redType ????????????
     * @param userName ?????????
     * @param redPrice ????????????
     * @param textContent ????????????
     * @param contentTv holder.textView
     */
    public void setRedItem(int redType,String userName,String redPrice,String textContent,TextView contentTv,int position){
        if(redType==QUEYUE_RED||
           redType==ZHUANXIANG_RED||
           redType == TJ_RED||
           redType == NORMAL_RED){

            SpannableString redString;
            int drawble;
            if(redType==QUEYUE_RED){
                redString = new SpannableString(Utils.getString(R.string.??????????????????????????????)+userName+Utils.getString(R.string.?????????)+redPrice+Utils.getString(R.string.?????????????????????????????????));
                drawble = R.drawable.flhb;
            }else if(redType == ZHUANXIANG_RED) {
                redString = new SpannableString(Utils.getString(R.string.??????????????????????????????)+userName+Utils.getString(R.string.?????????)+redPrice+Utils.getString(R.string.?????????????????????????????????));
                drawble = R.drawable.zxhb;
            }else if(redType == TJ_RED){
                //??????
                redString = new SpannableString(Utils.getString(R.string.??????????????????????????????)+userName+Utils.getString(R.string.?????????)+redPrice+Utils.getString(R.string.???????????????));
                drawble = R.drawable.tjhb_xs;
            }else {
                //todo ?????????????????????????????????(??????????????????)
                redString = new SpannableString(Utils.getString(R.string.??????????????????????????????)+userName+Utils.getString(R.string.?????????)+redPrice+Utils.getString(R.string.???????????????));
                drawble = R.drawable.pthb;
            }

            //?????????????????????span
            ForegroundColorSpan userNameSpan = new ForegroundColorSpan(Color.parseColor("#FFCB14"));
            redString.setSpan(userNameSpan,8,8+userName.length(),Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            //??????icon span
            //??????????????????bitmap, ??????????????????????????????span
            Bitmap bitmap = BitmapFactory.decodeResource(fragment.getResources(), drawble);
//                        MyImageSpan iconSpan = new MyImageSpan(fragment.getContext(),R.drawable.flhb);
            MyImageSpan iconSpan = new MyImageSpan(fragment.getContext(), Utils.getNewBitmap(bitmap, 64, 16));
            redString.setSpan(iconSpan,0,1,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            if(redType == QUEYUE_RED||redType == ZHUANXIANG_RED){
                //??????????????????????????????span
                ForegroundColorSpan getRedSpan = new ForegroundColorSpan(Color.parseColor("#F73D3D"));
                redString.setSpan(getRedSpan,redString.length()-7,redString.length(),Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                //???????????????????????? span
                MyClickableSpan redClickSpan = new MyClickableSpan(textContent, fragment.getContext(),position);
                redString.setSpan(redClickSpan,redString.length()-7,redString.length(),Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                redClickSpan.setOnClickSpanLintener(this);
            }
            contentTv.setText(redString);
            //????????????????????????????????????
            contentTv.setMovementMethod(LinkMovementMethod.getInstance());
            contentTv.setTag(position);
        }else {
            SpannableString redStringPt = new SpannableString(" "+userName+Utils.getString(R.string.????????????????????????????????????????????????));
            //??????????????????????????????span
            ForegroundColorSpan getRedSpanPt = new ForegroundColorSpan(Color.parseColor("#F73D3D"));
            redStringPt.setSpan(getRedSpanPt,redStringPt.length()-7,redStringPt.length(),Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            //?????????????????????span
            ForegroundColorSpan userNameSpanPt = new ForegroundColorSpan(Color.parseColor("#FFCB14"));
            redStringPt.setSpan(userNameSpanPt,1,1+userName.length(),Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            //??????icon span
            MyImageSpan iconSpanPt = new MyImageSpan(fragment.getContext(), Utils.getNewBitmap(BitmapFactory.decodeResource(fragment.getResources(), R.drawable.pthb), 64, 16));
            redStringPt.setSpan(iconSpanPt,0,1,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            //???????????????????????? span
            MyClickableSpan redClickSpanPt = new MyClickableSpan(textContent, fragment.getContext(),position);
            redStringPt.setSpan(redClickSpanPt,redStringPt.length()-7,redStringPt.length(),Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            redClickSpanPt.setOnClickSpanLintener(this);
            contentTv.setText(redStringPt);
            //????????????????????????????????????
            contentTv.setMovementMethod(LinkMovementMethod.getInstance());
            contentTv.setTag(position);
        }
    }
    @Override
    public int getItemViewType(int position) {
        //1 ??????  2 ??????  3 ???????????? 4 ???????????? 5???????????? 6 ?????????????????? 7 ???????????? 8 ????????????
        LiveMessageModel liveMessageModel = liveMessageModelArrayList.get(position);
        String objectName = liveMessageModel.getObjectName();
        if(objectName.equals(CommonStr.TEXT_MESSAGE)){
            return 1;
        }else if(objectName.equals(CommonStr.RED_MESSAGE)){
            return 2;
        }else if(objectName.equals(CommonStr.NOTICE_MESSAGE)){
            return 3;
        }else if(objectName.equals(CommonStr.SHARE_MESSAGE)){
            return 4;
        }else if(objectName.equals(CommonStr.GIFT_MESSAGE)){
            return 5;
        } else if(objectName.equals(CommonStr.EXIT_JOIN_MESSAGE)){
            return 6;
        }else if(objectName.equals(CommonStr.SHARE_ZJ_MESSAGE)){
            return 7;
        }else if(objectName.equals(CommonStr.FOLLOW_MESSAGE)){
            return 8;
        }else if(objectName.equals(CommonStr.FORBIDDEN_MESSAGE)){
            return 9;
        } else if(objectName.equals(CommonStr.ROOM_MANAGER_MESSAGE)){
            return 10;
        } else if(objectName.equals(CommonStr.CHANGE_ROOM_TYPE_MESSAGE)) {
            return 11;
        }else if(objectName.endsWith(CommonStr.RONG_CONNECT_STATUS)){
            return 12;
        }else if(objectName.endsWith(CommonStr.LIVE_SYSTEM_MESSAGE)){
            return 13;
        } else if(objectName.endsWith(CommonStr.NORMAL_RED_MESSAGE)){
            return 14;
        } else if(objectName.endsWith(CommonStr.REWARD_ANCHOR)){
            return 15;
        }else {
            return 16;
        }

    }
    @Override
    public int getItemCount() {
        return liveMessageModelArrayList.size();
    }


    @Override
    public void onClickSpanLintener(View view, int position) {
        if(onClickSpanLintener!=null){
            onClickSpanLintener.onClickSpanLintener(view,position);
        }
    }

    @Override
    public void onClick(View v) {
        if(myOnItemClickLintener!=null){
            myOnItemClickLintener.onItemClickLintener(v, (Integer) v.getTag());
        }
    }

    public class TextHolder extends RecyclerView.ViewHolder{
        TextView textNameContentTv;
        public TextHolder(@NonNull View itemView) {
            super(itemView);
            textNameContentTv=itemView.findViewById(R.id.text_name_content_tv);
        }
    }
    public class NoticeHolder extends RecyclerView.ViewHolder{
        TextView noticeContenttv;
        public NoticeHolder(@NonNull View itemView) {
            super(itemView);
            noticeContenttv=itemView.findViewById(R.id.notice_name_content_tv);
        }
    }

    public class ShareHolder extends RecyclerView.ViewHolder{
        TextView shareContenttv;
        public ShareHolder(@NonNull View itemView) {
            super(itemView);
            shareContenttv=itemView.findViewById(R.id.share_name_content_tv);
        }
    }

    public class RedHolder extends RecyclerView.ViewHolder{
        TextView redContenttv;
        public RedHolder(@NonNull View itemView) {
            super(itemView);
            redContenttv=itemView.findViewById(R.id.red_name_content_tv);
        }
    }

    public class GiftHolder extends RecyclerView.ViewHolder{
        TextView giftContenttv;
        public GiftHolder(@NonNull View itemView) {
            super(itemView);
            giftContenttv=itemView.findViewById(R.id.gift_content_tv);
        }
    }

    public class ExitJoinHolder extends RecyclerView.ViewHolder{
        TextView exitJoinCotentTv;
        public ExitJoinHolder(@NonNull View itemView) {
            super(itemView);
            exitJoinCotentTv=itemView.findViewById(R.id.exit_join_content_tv);
        }
    }
    public class ShareZjHolder extends RecyclerView.ViewHolder{
        TextView zjContentTv;
        public ShareZjHolder(@NonNull View itemView) {
            super(itemView);
            zjContentTv=itemView.findViewById(R.id.exit_join_content_tv);
        }
    }
    public class FollowHolder extends RecyclerView.ViewHolder{
        TextView followContentTv;
        public FollowHolder(@NonNull View itemView) {
            super(itemView);
            followContentTv=itemView.findViewById(R.id.exit_join_content_tv);
        }
    }
    public class ForbiddenHolder extends RecyclerView.ViewHolder{
        TextView forbiddenContentTv;
        LinearLayout forbidden_wrap_linear;
        public ForbiddenHolder(@NonNull View itemView) {
            super(itemView);
            forbiddenContentTv=itemView.findViewById(R.id.exit_join_content_tv);
            forbidden_wrap_linear=itemView.findViewById(R.id.forbidden_wrap_linear);
        }
    }
    public class RoomManagerHolder extends RecyclerView.ViewHolder{
        TextView roomManagerContentTv;
        public RoomManagerHolder(@NonNull View itemView) {
            super(itemView);
            roomManagerContentTv=itemView.findViewById(R.id.exit_join_content_tv);
        }
    }
    public class ChangeRoomTypeHolder extends RecyclerView.ViewHolder{
        TextView changeContent_tv;
        public ChangeRoomTypeHolder(@NonNull View itemView) {
            super(itemView);
            changeContent_tv =itemView.findViewById(R.id.notice_name_content_tv);
        }
    }
    public class RongConnectHolder extends RecyclerView.ViewHolder{
        TextView connectStatusTv;
        public RongConnectHolder(@NonNull View itemView) {
            super(itemView);
            connectStatusTv =itemView.findViewById(R.id.notice_name_content_tv);
        }
    }
    public class LiveSystemMessageHolder extends RecyclerView.ViewHolder{
        TextView contentTv;
        public LiveSystemMessageHolder(@NonNull View itemView) {
            super(itemView);
            contentTv =itemView.findViewById(R.id.notice_name_content_tv);
        }
    }

    public class NormalRedHolder extends RecyclerView.ViewHolder{
        TextView redContenttv;
        public NormalRedHolder(@NonNull View itemView) {
            super(itemView);
            redContenttv=itemView.findViewById(R.id.red_name_content_tv);
        }
    }
    public class RewardAnchorHolder extends RecyclerView.ViewHolder{
        TextView reward_content_tv;
        public RewardAnchorHolder(@NonNull View itemView) {
            super(itemView);
            reward_content_tv =itemView.findViewById(R.id.exit_join_content_tv);
        }
    }
    public interface  MyOnItemClickLintener {
        void  onItemClickLintener(View view ,int position);

    }
    public MyOnItemClickLintener myOnItemClickLintener;

    public void setMyOnItemClickLintener(MyOnItemClickLintener myOnItemClickLintener) {
        this.myOnItemClickLintener = myOnItemClickLintener;
    }

    public interface OnClickSpanLintener{
        void onClickSpanLintener(View view,int position);
    }
    public OnClickSpanLintener onClickSpanLintener;

    public void setOnClickSpanLintener(OnClickSpanLintener onClickSpanLintener) {
        this.onClickSpanLintener = onClickSpanLintener;
    }
}
