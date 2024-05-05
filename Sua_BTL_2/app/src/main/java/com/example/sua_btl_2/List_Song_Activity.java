package com.example.sua_btl_2;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class List_Song_Activity extends AppCompatActivity {
ListView lvList_Song;
ArrayList<Song_list> arrayList_Song;
List_Song_Adapter adapter_list_song;
    ImageButton btnNextMain;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_song);


        lvList_Song = findViewById(R.id.lvFVSong);
        arrayList_Song = new ArrayList<>();
        AddSongtoSongList();
        adapter_list_song=new List_Song_Adapter(this,R.layout.dong_list_song,arrayList_Song);
        lvList_Song.setAdapter(adapter_list_song);
            Click_Baihat();

    }

//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.add_bh,menu);
//
//        return super.onCreateOptionsMenu(menu);
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        if(item.getItemId()== R.id.menuAdd){
//DialogThem();
//        }
//        return super.onOptionsItemSelected(item);
//    }

    private  void DialogThem(){
        Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_add_bh);
        EditText editText = dialog.findViewById(R.id.edittextThemBh);
        Button buttonThem = dialog.findViewById(R.id.buttonThembh);
        Button buttonHuy = dialog.findViewById(R.id.buttonHuybh);


        buttonHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.dismiss();
            }
        });
        buttonThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tenbh = editText.getText().toString();
                if(tenbh.equals("")){
                    Toast.makeText(List_Song_Activity.this,"vui long nhap ten bai hat",Toast.LENGTH_LONG).show();
                }else {
//                    database.QueryData("INSERT INTO tenbhsonglist VALUES( null,'"+tenbh+"' )");
                    Toast.makeText(List_Song_Activity.this,"Da them",Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }
            }
        });
        dialog.show();
    }
    private  void Click_Baihat(){
        lvList_Song.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                if(i==0){
                    Intent intent=new Intent(List_Song_Activity.this,MainActivity.class);
                    Bundle bundle=new Bundle();
                    bundle.putInt("vitri",i);
                   bundle.putString("ten bai hat","Lối Nhỏ");
                    intent.putExtras(bundle);
                    startActivity(intent);
                    finish();
                }
                if(i==1){
                    Intent intent=new Intent(List_Song_Activity.this,MainActivity.class);
                    Bundle bundle=new Bundle();
                    bundle.putInt("vitri",i);
                    bundle.putString("tenbaihat", "Đếm Ngày Xa Em");
                    intent.putExtras(bundle);
                    startActivity(intent);
                    finish();
                }
                if(i==2){
                    Intent intent=new Intent(List_Song_Activity.this,MainActivity.class);
                    Bundle bundle=new Bundle();
                    bundle.putInt("vitri",i);
                    bundle.putString("tenbaihat", "Tết Dong Đầy");
                    intent.putExtras(bundle);
                    startActivity(intent);
                    finish();
                }if(i==3){
                    Intent intent=new Intent(List_Song_Activity.this,MainActivity.class);
                    Bundle bundle=new Bundle();
                    bundle.putInt("vitri",i);
                    bundle.putString("tenbaihat", "Ừ Có Anh Dây");
                    intent.putExtras(bundle);
                    startActivity(intent);
                    finish();
                }if(i==4){
                    Intent intent=new Intent(List_Song_Activity.this,MainActivity.class);
                    Bundle bundle=new Bundle();
                    bundle.putInt("vitri",i);
               bundle.putString("tenbaihat", "Waiting for you");
                    intent.putExtras(bundle);
                    startActivity(intent);
                    finish();
                }if(i==5){
                    Intent intent=new Intent(List_Song_Activity.this,MainActivity.class);
                    Bundle bundle=new Bundle();
                    bundle.putInt("vitri",i);
                bundle.putString("tenbaihat", "SomeThing Just Like This ");
                    intent.putExtras(bundle);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }
    private  void AddSongtoSongList(){

        arrayList_Song.add(new Song_list("Lối Nhỏ",R.drawable.disk1));
        arrayList_Song.add(new Song_list("Đếm Ngày Xa Em",R.drawable.disk1));
        arrayList_Song.add(new Song_list("Tết Dong Đầy",R.drawable.disk1));
        arrayList_Song.add(new Song_list("Ừ Có Anh Dây",R.drawable.disk1));
        arrayList_Song.add(new Song_list("Waiting for you",R.drawable.disk1));
        arrayList_Song.add(new Song_list("SomeThing Just Like This ",R.drawable.disk1));

    }


}