package com.example.zqx.zplayer;

import android.media.MediaPlayer;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.File;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    private ListView songsLv;
    private String path;
    private String[] fileList;
    private MediaPlayer player;
    private String currentSong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        songsLv= (ListView) findViewById(R.id.songs_lv);
        path= Environment.getExternalStorageDirectory().getPath()+"/mp3/aMusic";
        fileList=new File(path).list();
        ArrayAdapter<String>adapter=new ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1,fileList);
        songsLv.setAdapter(adapter);
        player=new MediaPlayer();
        songsLv.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                currentSong=path+"/"+fileList[position];
            }
        });
    }

    public void play(View view){
        player.reset();
        try {
            player.setDataSource(currentSong);
            player.prepare();
            player.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void pause(View view){
        player.pause();
    }

}
