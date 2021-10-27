package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.myView;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.Utils;

public class NetImageView extends androidx.appcompat.widget.AppCompatImageView {
    private Context context;
    public NetImageView(@NonNull Context context) {
        super(context);
        init(context,null);
    }

    private void init(Context context, AttributeSet attrs) {
        if(attrs!=null){
            //从项目style中文件中取出样式数组
            TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.NetImageView);
            //取到xml布局文件中配置的字符串
            String string = typedArray.getString(R.styleable.NetImageView_imageUrl);
            Utils.setXmlImage(this,string);

        }

    }
    public NetImageView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public NetImageView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs);
    }

}
