package com.example.sua_btl_2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class List_Song_Adapter extends BaseAdapter {
private Context context;
  private  int layout;
  private List<Song_list> song_list_array ;

    public List_Song_Adapter(Context context, int layout, List<Song_list> song_list_array) {
        this.context = context;
        this.layout = layout;
        this.song_list_array = song_list_array;
    }

    @Override
    public int getCount() {
        return  song_list_array.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
private  class  ViewHolder{
TextView txtTen;
}
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
ViewHolder holder;
if(convertView == null){
    holder = new ViewHolder();
    LayoutInflater  inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    convertView = inflater.inflate(layout,null);
    holder.txtTen = convertView.findViewById(R.id.tvTenBH_Fv_Song);
    convertView.setTag(holder);

}else {
    holder = (ViewHolder) convertView.getTag();
}
Song_list song_list  =song_list_array.get(position);
holder.txtTen.setText(song_list.getTitle());
        return convertView;
    }
}
