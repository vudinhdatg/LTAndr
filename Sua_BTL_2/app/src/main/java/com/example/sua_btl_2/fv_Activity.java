package com.example.sua_btl_2;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class fv_Activity extends AppCompatActivity {
    Database database;
    ListView lv_fv_Song;
    ArrayList<Song_fv> arrayFV_Song;
    Fv_Song_Adapter adapter_fv_song;
    int iFv;
    String TenbhFv;
    ImageButton btnbackMain;
    private static final String tableName = "fvlist";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fv);
        database = new Database(this,"baihat.sqlite",null,1);

        lv_fv_Song = findViewById(R.id.lvFVSong);
        arrayFV_Song = new ArrayList<>();
        adapter_fv_song = new Fv_Song_Adapter(this,R.layout.dong_fv_song,arrayFV_Song);
        lv_fv_Song.setAdapter(adapter_fv_song);
        GetDataBaiHat();

    }

public  void ClickBaihat_fv(){
        lv_fv_Song.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position==0){
                    Intent intent=new Intent(fv_Activity.this,MainActivity.class);
                    Bundle bundle=new Bundle();
                    bundle.putInt("vitri",position);
                    bundle.putString("ten bai hat","Lối Nhỏ");
                    intent.putExtras(bundle);
                    startActivity(intent);
                    finish();
                }
            }
        });
}
    private  void GetDataBaiHat(){
        Cursor databaihat =database.GetData("SELECT * FROM fvlist");
        arrayFV_Song.clear();
        while (databaihat.moveToNext()){
            TenbhFv =databaihat.getString(1);
           iFv = databaihat.getInt(0);
            arrayFV_Song.add(new Song_fv( iFv,"'"+TenbhFv+"'" ));
        }
       adapter_fv_song.notifyDataSetChanged();
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(fv_Activity.this,List_Song_Activity.class);
        startActivity(intent);
        finish();

    }
    public  void DialogXoa( int id,String tenbh ){


        AlertDialog.Builder dialogxoa = new AlertDialog.Builder(this);
        dialogxoa.setMessage("Ban co muon xoa bai hat "+ tenbh+" nay ko ");

        dialogxoa.setPositiveButton("Co", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
//                               String deleteQuery = "DELETE FROM " +tableName ;
//               database.QueryData(deleteQuery);
                database.QueryData("DELETE FROM fvlist WHERE id = '"+id+"'");
                Toast.makeText(fv_Activity.this,"Da Xoa"+"'"+id+"'",Toast.LENGTH_LONG).show();
                GetDataBaiHat();
            }
        });
        dialogxoa.setNegativeButton("Khong", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        dialogxoa.show();
    }
}