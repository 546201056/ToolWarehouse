package com.boboo.farm.tool.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.boboo.farm.tool.R;

import java.util.List;

/**
 * 基础适配器，重新封装后的适配器目的在于让我们更专注并接近业务开发
 *
 * Created by 博博 on 2015/7/14.
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

    /**
     * 获取运行中的上下文对象
     *
     * @return 返回上下文对象
     */
    public Context getContext()
    {
        return context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        ParentViewHolder mHolder;
        if (null == convertView)
        {
            convertView = View.inflate(context, getLayoutRes(position), null);
            mHolder = getViewHolder(convertView);
            convertView.setTag(R.id.adapter_viewholder_tag, mHolder);
        } else
            mHolder = (ParentViewHolder) convertView.getTag(R.id.adapter_viewholder_tag);
        this.fillAmmunition(mHolder, position);
        return convertView;
    }

    /**
     * 获取创建item views所需的布局资源文件
     *
     * @param position 当前item在列表中的位置
     * @return 返回资源文件的唯一编号
     */
    protected abstract int getLayoutRes(int position);

    /**
     * 获取在派生类中创建并实例化的{@link ParentViewHolder}的子类对象，并由子类中声明和初始化相关控件
     *
     * @param convertView 当前可以看到的item view
     * @return 返回已被实例化并初始所有控件之后的{@link ParentViewHolder}的派生类对象
     */
    protected abstract ParentViewHolder getViewHolder(View convertView);

    /**
     * 基础视图（Views）支架，与生俱来的使命是用来支撑列表视图中所有的控件，并负责对其初始化。
     * <br/>
     * 派生类可通过调用先天具有的{@link #setupViews(View)}方法来为相关控件进行初始化工作。<i>为提高统一性，目前该方法是强制性的</i>
     */
    protected abstract class ParentViewHolder
    {
        /**
         * 负责控件的初始化工作
         *
         * @param convertView 当前列表中可以看到的item view，由它进行对控件的实例化。
         */
        protected abstract void setupViews(View convertView);
    }

    /**
     * 在视图创建，控件支架等工作全部调配完成之后，具体的数据装配工作在此进行。
     *
     * @param mParentHolder 实际上参数值正确的应该是view支架的派生类，因此参数<code>mParentHolder</code>应该被强制转换为实际的派生类对象。
     * @param position      当前所看到视图在列表中的所处位置
     * @param <T>           它代表的实际上是基础控件支架的派生类对象，即具体的实现者创建的派生控件支架
     */
    protected abstract <T extends ParentViewHolder> void fillAmmunition(T mParentHolder, int position);

    /**
     * 获取当前列表所用的数据集合
     *
     * @return 返回当前正在使用中的列表数据
     */
    public List<?> getListDatas()
    {
        return objs;
    }
}