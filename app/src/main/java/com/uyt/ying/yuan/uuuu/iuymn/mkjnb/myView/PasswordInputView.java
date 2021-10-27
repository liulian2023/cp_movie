package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.myView;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;

import com.uyt.ying.yuan.R;

public class PasswordInputView extends androidx.appcompat.widget.AppCompatEditText {
    private String TAG = "PasswordInputView";
    private Context mContext;
    private int passwordSize = 6;//密码的个数这里是默认值，也可以通过自定义属性来设置其他数值
    private int borderWidth = 6;//密码输入框的边框宽度px
    private int borderRadius = 6;//密码输入框矩形圆角的半径
    private int borderColor = 0xFF444444;//密码输入框边框的颜色
    private int passwordWidth = 6;//密码的宽度
    private int passwordColor = 0xFF333333;//密码的颜色
    private int defaultSplitLineWidth = 2; //px

    private int allPasswordWidth;//控件的总宽度
    private int allPasswordHight;//控件的总高度

    private Paint mPaint;//边框画笔
    private Paint pwPaint;//密码画笔

    private int passwordTextSize;//输入密码的个数
    private String password;//密码

    private onPasswordChangedListener onPasswordChangedListener;

    public void setOnPasswordChangedListener(PasswordInputView.onPasswordChangedListener onPasswordChangedListener) {
        this.onPasswordChangedListener = onPasswordChangedListener;
    }


    //获取密码
    public String getPassword() {
        return password;
    }

    //注意 ：只能复写这个构造函数，切只能在这里初始化，不然你会发现EditText的特性就没有来
    public PasswordInputView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }


    private void init(Context context, AttributeSet attrs) {
        mContext = context;
        /**
         * 初始化各种自定义属性
         * */
        TypedArray typedArray = mContext.obtainStyledAttributes(attrs,R.styleable.PasswordInputView);
        passwordSize = typedArray.getInt(R.styleable.PasswordInputView_passwordLength, passwordSize);
        borderWidth = typedArray.getDimensionPixelSize(R.styleable.PasswordInputView_borderWidth, borderWidth);
        borderRadius = typedArray.getDimensionPixelSize(R.styleable.PasswordInputView_borderRadius, borderRadius);
        borderColor = typedArray.getColor(R.styleable.PasswordInputView_borderColor, borderColor);
        passwordWidth = typedArray.getDimensionPixelSize(R.styleable.PasswordInputView_passwordWidth, passwordWidth);
        passwordColor = typedArray.getColor(R.styleable.PasswordInputView_passwordColor, passwordColor);
        Log.d(TAG,"borderRadius:"+borderRadius);
        Log.d(TAG,"passwordSize:"+passwordSize);
        typedArray.recycle();

        //初始化边框画笔
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeWidth(borderWidth);

        //初始化密码画笔
        pwPaint = new Paint();
        pwPaint.setAntiAlias(true);
        pwPaint.setStyle(Paint.Style.FILL);
        pwPaint.setColor(passwordColor);
        pwPaint.setStrokeWidth(passwordWidth);

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        Log.d(TAG, "onSizeChanged");
        allPasswordWidth = w;
        allPasswordHight = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.d(TAG, "onDraw");


        Log.d(TAG, "allPasswordWidth:" + allPasswordWidth);
        Log.d(TAG, "borderRadius:" + borderRadius);
        //画一个矩形遮住显示的文字
        mPaint.setColor(borderColor);
        canvas.drawRoundRect(0, 0, allPasswordWidth, allPasswordHight, borderRadius, borderRadius, mPaint);
        //再画一个白色矩形这种上面的矩形内部，形成假的边框
        mPaint.setColor(Color.WHITE);
        canvas.drawRoundRect(defaultSplitLineWidth, defaultSplitLineWidth,
                allPasswordWidth - defaultSplitLineWidth, allPasswordHight - defaultSplitLineWidth,borderRadius,borderRadius, mPaint);

        //画分割线
        int lineStartAndEndx = allPasswordWidth / passwordSize;
        int lineStarty = 0;
        int lineendy = allPasswordHight;
        mPaint.setColor(borderColor);
        mPaint.setStrokeWidth(defaultSplitLineWidth);
        for (int i = 1; i < passwordSize; i++) {
            canvas.drawLine(lineStartAndEndx, lineStarty, lineStartAndEndx, lineendy, mPaint);
            lineStartAndEndx += allPasswordWidth / passwordSize;
        }

        //画密码圆
        int circleX = allPasswordWidth / passwordSize / 2;
        int circleY = allPasswordHight / 2;
        if (passwordTextSize > 0) {
            for (int i = 0; i < passwordTextSize; i++) {
                canvas.drawCircle(circleX, circleY, passwordWidth, pwPaint);
                circleX += allPasswordWidth / passwordSize;
            }

        }
    }

    @Override
    protected void onTextChanged(CharSequence text, int start, int lengthBefore, int lengthAfter) {
        super.onTextChanged(text, start, lengthBefore, lengthAfter);
        //监听文字变化大于密码设定长度不记录
        Log.d(TAG,"onTextChanged:"+passwordSize);
        if (text.length() <= passwordSize){
            passwordTextSize = text.length();
            password = text.toString();
            if (onPasswordChangedListener != null)
                onPasswordChangedListener.setPasswordChanged(passwordTextSize);
        }else {
            text = password;
        }
        invalidate();

    }

    //设置监听密码变化
    public interface onPasswordChangedListener {
        public void setPasswordChanged(int passwordTextSize);
    }

}
