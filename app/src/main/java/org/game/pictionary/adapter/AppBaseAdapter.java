package org.game.pictionary.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public abstract class AppBaseAdapter <T> extends ArrayAdapter {
     private LayoutInflater layoutInflater;
     private Context context;
     private List<T> itemList;
    public abstract View getAppView(int position, View convertView, ViewGroup parent, T t, LayoutInflater layoutInflater);

    public AppBaseAdapter(@NonNull Context context, int resource, List<T> itemList) {
        super(context, resource,itemList);
        this.context = context;
        this.itemList = itemList;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
         if(itemList == null)
             return  0;
         return itemList.size();
    }


    @Nullable
    @Override
    public Object getItem(int position) {
        return itemList.get(position);
    }


    @Override
    public long getItemId(int position) {
        return position;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return getAppView(position, convertView, parent,itemList.get(position),layoutInflater);
    }
}
