package com.example.sua_btl_2;

import android.content.Intent;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Database database;
    TextView txtTitle, txtTimeTotal, txtTimeSong;
    SeekBar skSong;
    ImageView imgHinh;
    ImageButton btnPrev,btnPlay,btnNext,imageButtonfv,btnNexttoFv;
    ArrayList<Song> arraySong;
    int position  ;
    MediaPlayer mediaPlayer;
    Animation  animation ;
    int id1;

    private static final String tableName = "fvlist";
    String tenbh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // tao database
        database = new Database(this,"baihat.sqlite",null,1);
        //tao bang
        database.QueryData("CREATE TABLE  IF NOT EXISTS fvlist (id INTEGER PRIMARY KEY AUTOINCREMENT,tenbhfv VARCHAR(200))");

        AnhXa();
        nhandataSonglist();
        AddSong();
        KhoiTaoMediaplayer();
          Settimetotal();
//Nextfv();
       imageButtonfv.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               if (mediaPlayer.isPlaying()){
                   Intent intent=new Intent(MainActivity.this,fv_Activity.class);
                   Bundle bundle=new Bundle();
                   bundle.putInt("vitrifv",arraySong.get(position).getFile());
                   bundle.putString("tenbaihatfv","'"+arraySong.get(position).getTitle()+"'");
                   intent.putExtras(bundle);
                   startActivity(intent);
                   mediaPlayer.stop();
                   finish();
               }
//               String deleteQuery = "DELETE FROM " +tableName ;
//               database.QueryData(deleteQuery);
               Cursor databaihat1 =database.GetData("SELECT * FROM fvlist");

               while (databaihat1.moveToNext()){

                 String tenbh1  =databaihat1.getString(1);
                   database.QueryData("SELECT FROM fvlist WHERE tenbaihatfv = '"+tenbh1+"'");
                   if(arraySong.get(position).getTitle() !=tenbh1 ) {
                       database.QueryData("INSERT INTO fvlist VALUES(null,'" + arraySong.get(position).getTitle() + "' )");
                   }else {
                       Toast.makeText(MainActivity.this,"bai hat da co",Toast.LENGTH_LONG).show();
                   }
               }

           }
       });

        animation = AnimationUtils.loadAnimation(this,R.anim.disc_rotale);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                position++;
                if(position >arraySong.size()-1){
                    position =0;
                }
                if(mediaPlayer.isPlaying()){
                    mediaPlayer.stop();
                }
                KhoiTaoMediaplayer();
                mediaPlayer.start();
                btnPlay.setImageResource(R.drawable.pause1);
                Settimetotal();
                UpdateTimeSong();
                imgHinh.startAnimation(animation);
            }
        });
        btnPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                position--;
                if(position<0){
                    position=arraySong.size()-1;
                }
                if(mediaPlayer.isPlaying()){
                    mediaPlayer.stop();
                }
                KhoiTaoMediaplayer();
                mediaPlayer.start();
                btnPlay.setImageResource(R.drawable.pause1);
                Settimetotal();
                UpdateTimeSong();
                imgHinh.startAnimation(animation);
            }
        });
        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mediaPlayer.isPlaying()){
                    // neu dang phat->dung->doi hinh
                    mediaPlayer.pause();
                    btnPlay.setImageResource(R.drawable.play);
                    imgHinh.clearAnimation();
                }else{
                    //dang ngung-> phat-> doi hinh
                    mediaPlayer.start();
                    btnPlay.setImageResource(R.drawable.pause1);
                    imgHinh.startAnimation(animation);
                }
                Settimetotal();
                UpdateTimeSong();

            }
        });
        skSong.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

                mediaPlayer.seekTo(skSong.getProgress());
            }
        });

    }
    public  void  nhandataSonglist(){
        Intent intent =getIntent();
        Bundle bundle = intent.getExtras();
        position = bundle.getInt("vitri");
    }

    private void UpdateTimeSong(){
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                SimpleDateFormat  dinhdangGio = new SimpleDateFormat("mm:ss");
                txtTimeSong.setText(dinhdangGio.format(mediaPlayer.getCurrentPosition()));
                //updete
                skSong.setProgress(mediaPlayer.getCurrentPosition());
                // kiem tra xem bai hat ket thuc -> chuyen bai khac
                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        position++;
                        if(position >arraySong.size()-1){
                            position =0;
                        }
                        if(mediaPlayer.isPlaying()){
                            mediaPlayer.stop();
                        }
                        KhoiTaoMediaplayer();
                        mediaPlayer.start();
                        btnPlay.setImageResource(R.drawable.pause1);
                        Settimetotal();
                        UpdateTimeSong();

                    }
                });
                handler.postDelayed(this,500);

            }
        },100);
    }
    private void Settimetotal(){
        SimpleDateFormat dinhdangGio = new SimpleDateFormat("mm:ss");
        txtTimeTotal.setText(dinhdangGio.format(mediaPlayer.getDuration()));
        // gan max skSong = mediaPlayer.getDuration()
        skSong.setMax(mediaPlayer.getDuration());
    }
    private  void KhoiTaoMediaplayer(){
        mediaPlayer = MediaPlayer.create(MainActivity.this,arraySong.get(position).getFile());
        txtTitle.setText(arraySong.get(position).getTitle());

    }
    private  void AddSong(){
        arraySong = new ArrayList<>();
        arraySong.add(new Song(R.raw.loinho,"Lối Nhỏ"));
        arraySong.add(new Song(R.raw.demngayxaem,"Đếm Ngày Xa Em"));
        arraySong.add(new Song(R.raw.tetdongday,"Tết Dong Đầy"));
        arraySong.add(new Song(R.raw.ucoanhday,"Ừ Có Anh Dây"));
        arraySong.add(new Song(R.raw.waitingforyou,"Waiting for you"));
        arraySong.add(new Song(R.raw.somethingjustlikethis,"SomeThing Just Like This "));

    }
    private  void AnhXa(){
        txtTitle = findViewById(R.id.textviewTitle);
        txtTimeSong = findViewById(R.id.textViewTimeSong);
        txtTimeTotal =findViewById(R.id.textViewTimeTotal);
        skSong = findViewById(R.id.seekBarSong);
        btnPrev = findViewById(R.id.imageButtonPrev);
        btnPlay = findViewById(R.id.imageButtonPlay);
        btnNext = findViewById(R.id.imageButtonNext);
        imgHinh =findViewById(R.id.imageViewCD);
        imageButtonfv = findViewById(R.id.imageButtonFv);
//        btnNexttoFv = findViewById(R.id.imgnexttoFv);
    }
//public  void Nextfv(){
//    Intent intent = new Intent(MainActivity.this,fv_Activity.class);
//    startActivity(intent);
//    mediaPlayer.pause();
//    finish();
//}
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(MainActivity.this,List_Song_Activity.class);
        startActivity(intent);
        mediaPlayer.pause();
        finish();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mediaPlayer.start();
        btnPlay.setImageResource(R.drawable.pause1);
        Settimetotal();
        UpdateTimeSong();

    }
}