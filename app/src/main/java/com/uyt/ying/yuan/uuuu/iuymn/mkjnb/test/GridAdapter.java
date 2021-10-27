package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.test;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.uyt.ying.yuan.R;

import java.util.List;

public class GridAdapter extends BaseAdapter {

    private List<String> provinceBeanList;
    private LayoutInflater layoutInflater;

    public GridAdapter(Context context, List<String> provinceBeanList) {
        this.provinceBeanList = provinceBeanList;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return provinceBeanList.size();
    }

    @Override
    public Object getItem(int position) {
        return provinceBeanList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.test_grid_item, null);
            holder = new ViewHolder();
            holder.text = (TextView) convertView.findViewById(R.id.test_grid_item_tv);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        String provinceBean = provinceBeanList.get(position);
        if (provinceBean != null) {
            holder.text.setText(provinceBean);

        }
        return convertView;
    }

    class ViewHolder {
        TextView text;
    }

}
