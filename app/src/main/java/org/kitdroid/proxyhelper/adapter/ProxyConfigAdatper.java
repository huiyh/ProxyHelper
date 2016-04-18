package org.kitdroid.proxyhelper.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import org.kitdroid.proxyhelper.R;

/**
 * Created by huiyh on 2016/4/18.
 */
public class ProxyConfigAdatper extends BaseAdapter {
    private LayoutInflater inflater;

    public ProxyConfigAdatper(LayoutInflater inflater) {
        this.inflater = inflater;
    }

    @Override
    public int getCount() {
        return 50;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = inflater.inflate(R.layout.item_proxy_info,null);
        }
        return convertView;
    }
}
