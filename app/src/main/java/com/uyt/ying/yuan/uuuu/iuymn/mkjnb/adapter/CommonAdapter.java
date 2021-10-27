package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.CommonModel;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.CommonHolder;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;

public abstract class CommonAdapter<H extends CommonHolder, M extends CommonModel> extends RecyclerView.Adapter<CommonHolder> implements View.OnClickListener {
    protected View VIEW_FOOTER;
    protected View VIEW_HEADER;
    //Type
    private int TYPE_NORMAL = 1000;
    private int TYPE_HEADER = 1001;
    private int TYPE_FOOTER = 1002;
    private RecyclerView mRecyclerView;
    public ArrayList<M> list = new ArrayList<>();
    private Class<H> clazz;

    public Class<H> getClazz() {
        return clazz;
    }

    public CommonAdapter() {
        init();
    }

    public CommonAdapter(ArrayList<M> list) {
        this.list = list;
        init();
    }

    //constructor=classz.getConstructor(String.class,int.class);
//6         constructor.newInstance("Java",30);
    protected void init() {
        ParameterizedType type = (ParameterizedType) this.getClass()
                .getGenericSuperclass();
        this.clazz = (Class<H>) type.getActualTypeArguments()[0];
    }

    ;

    @NonNull
    @Override
    public H onCreateViewHolder(@NonNull ViewGroup viewGroup, int itemViewType) {
//        System.out.println("onCreateViewHolder/itemViewType = " + itemViewType);
        View view = null;
        boolean headerView = isHeaderView(itemViewType, 0);
        boolean footerView = isFooterView(itemViewType, 0);
        if (headerView) {
            view = VIEW_HEADER;
        } else if (footerView) {
            view = VIEW_FOOTER;
        } else {
            view = LayoutInflater.from(viewGroup.getContext()).inflate(getLayOutRes(), viewGroup, false);
        }

        try {
            Constructor<?>[] constructors = clazz.getConstructors();

            Constructor<H> constructor = clazz.getConstructor(View.class);
            return constructor.newInstance(view);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull CommonHolder commonHolder, int position) {
        if (!isHeaderView(null, position) && !isFooterView(null, position)) {
            if(VIEW_HEADER!=null){
                position-=1;
            }
            handleViewHolder((H) commonHolder, position);
        }
    }

    public abstract void handleViewHolder(H commonHolder, int position);

    @Override
    public int getItemCount() {
        int size = list.size();
        if (VIEW_HEADER != null) {
            size++;
        }
        if (VIEW_FOOTER != null) {
            size++;
        }
        return size;
    }

    public M getItemModel(int position) {
        if (position < list.size())
            return list.get(position);
        return null;

    }


    public abstract int getLayOutRes();

    /*头尾开始*/
    @Override
    public int getItemViewType(int position) {
        if (isHeaderView(null, position)) {
            return TYPE_HEADER;
        } else if (isFooterView(null, position)) {
            return TYPE_FOOTER;
        } else {
            return TYPE_NORMAL;
        }
    }

    public boolean haveHeaderView() {
        return VIEW_HEADER != null;
    }

    public boolean haveFooterView() {
        return VIEW_FOOTER != null;
    }

    public boolean isHeaderView(Integer itemViewType, int position) {
        if (itemViewType != null)
            return haveHeaderView() && itemViewType == TYPE_HEADER;//onCreateViewHolder
        return haveHeaderView() && position == 0;//onBindViewHolder
    }

    public boolean isFooterView(Integer itemViewType, int position) {
        if (itemViewType != null)
            return haveFooterView() && itemViewType == TYPE_FOOTER;//onCreateViewHolder
        return haveFooterView() && position == getItemCount() - 1;//onBindViewHolder
    }

    public void addHeaderView(View headerView) {
        if (haveHeaderView()) {
            throw new IllegalStateException("hearview has already exists!");
        } else {
            //避免出现宽度自适应
            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            headerView.setLayoutParams(params);
            VIEW_HEADER = headerView;
            ifGridLayoutManager();
            notifyItemInserted(0);
        }

    }

    public void addFooterView(View footerView) {
        if (haveFooterView()) {
            throw new IllegalStateException("footerView has already exists!");
        } else {
            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            footerView.setLayoutParams(params);
            VIEW_FOOTER = footerView;
            ifGridLayoutManager();
            notifyItemInserted(getItemCount() - 1);
        }
    }

    public void ifGridLayoutManager() {
        if (mRecyclerView == null) return;
        final RecyclerView.LayoutManager layoutManager = mRecyclerView.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            final GridLayoutManager.SpanSizeLookup originalSpanSizeLookup =
                    ((GridLayoutManager) layoutManager).getSpanSizeLookup();
            ((GridLayoutManager) layoutManager).setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    return (isHeaderView(null, position) || isFooterView(null, position)) ?
                            ((GridLayoutManager) layoutManager).getSpanCount() : 1;
                }
            });
        }
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        try {
            if (mRecyclerView == null && mRecyclerView != recyclerView) {
                mRecyclerView = recyclerView;
            }
            ifGridLayoutManager();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*头尾结束*/


    public static interface OnRecyclerViewItemClickListener {
        void onItemClick(View view, int position);
    }

    public CommonAdapter.OnRecyclerViewItemClickListener mOnItemClickListener = null;

    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    @Override
    public void onClick(View v) {
        if (mOnItemClickListener != null) {
            mOnItemClickListener.onItemClick(v, (Integer) v.getTag());
        }
    }
}
