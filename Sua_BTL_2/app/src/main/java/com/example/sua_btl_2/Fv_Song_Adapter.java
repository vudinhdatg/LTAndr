package com.example.sua_btl_2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;

public class Fv_Song_Adapter extends BaseAdapter {


    private fv_Activity context;
    private  int layout;
    private List<Song_fv> song_fv_array ;
Database database;
    public Fv_Song_Adapter(fv_Activity context, int layout, List<Song_fv> song_fv_array) {
        this.context = context;
        this.layout = layout;
        this.song_fv_array = song_fv_array;
    }

    @Override
    public int getCount() {
        return song_fv_array.size();
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
        TextView txtTenfv;
        ImageButton btnXoaFV;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
          ViewHolder holder;
        if(convertView == null){
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layout,null);
            holder.txtTenfv = convertView.findViewById(R.id.tvTenBH_Fv_Song);
            holder.btnXoaFV = convertView.findViewById(R.id.imageButtonXoaFv);
            convertView.setTag(holder);

        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        Song_fv song_fv  =song_fv_array.get(position);
        holder.txtTenfv.setText(song_fv.getTenfvSong());

        //xoa
        holder.btnXoaFV.setOnClickListener((View v) -> {
            context.DialogXoa(song_fv.getIdfv(), song_fv.getTenfvSong());
        });
        return convertView;

    }
}
