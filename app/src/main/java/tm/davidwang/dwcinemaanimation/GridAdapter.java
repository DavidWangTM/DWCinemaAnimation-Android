package tm.davidwang.dwcinemaanimation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

/**
 * Created by DavidWang on 15/11/12.
 */
public class GridAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<GridModel> data;

    public GridAdapter(Context context,ArrayList<GridModel> data){
        this.context = context;
        this.data = data;

    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        GridModel info = data.get(position);
        if(convertView == null)
        {
            holder = new ViewHolder();
            LayoutInflater mInflater = LayoutInflater.from(context);
            convertView = mInflater.inflate(R.layout.grid_item, null);
            holder.item_view = (View)convertView.findViewById(R.id.item_view);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder)convertView.getTag();
        }
        holder.item_view.setVisibility(View.VISIBLE);
        if (info.type_index == 0){
            holder.item_view.setBackgroundColor(0xffDED1A9);
        }else if(info.type_index == 1){
            holder.item_view.setBackgroundColor(0xff4B9CF0);
        }else if (info.type_index == 2) {
            holder.item_view.setVisibility(View.GONE);
        }

        return convertView;
    }

    //ViewHolder静态类
    private class ViewHolder
    {
        public View item_view;
    }
}