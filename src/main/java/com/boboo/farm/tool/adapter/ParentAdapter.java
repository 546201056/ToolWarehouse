package com.boboo.farm.tool.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 *
 *
 * Created by ²©²© on 2015/7/14.
 */
public abstract class ParentAdapter extends BaseAdapter
{
    private final Context context;
    private final List<?> objs;

    public ParentAdapter(Context context, List<?> objs)
    {
        this.context = context;
        this.objs = objs;
    }

    @Override
    public int getCount()
    {
        return objs.size();
    }

    @Override
    public Object getItem(int position)
    {
        return objs.get(position);
    }

    @Override
    public long getItemId(int position)
    {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        return null;
    }
}
