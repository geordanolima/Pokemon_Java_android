package com.example.geordano.xxxxxxx;

import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.MediaController;
import android.widget.VideoView;

public class VideoActivity extends AppCompatActivity {

    private VideoView videoView;
    private MediaController mediaController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        videoView = findViewById(R.id.id_videoview);

        //inicializa o controlador do video
        mediaController = new MediaController(VideoActivity.this);

        //ancora o contralador na view
        mediaController.setAnchorView(videoView);
        videoView.setMediaController(mediaController);

        try {
            //Log.e("teste","Start video");
            int id = this.getResources().getIdentifier("video",
                    "raw",
                    getPackageName());
            videoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + id));

            //videoView.setVideoURI(Uri.parse("https://static.videezy.com/system/resources/" +
            //      "                        previews/000/020/675/original/P1033753.mp4"));

        } catch (Exception e) {
            e.printStackTrace();

        }

        videoView.requestFocus();


        //método para quando o vídeo estiver pronto para iniciar
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {

            public void onPrepared(MediaPlayer mediaPlayer) {
                videoView.start();


                //adapta o video as mudanças de visualização
                mediaPlayer.setOnVideoSizeChangedListener(new MediaPlayer
                        .OnVideoSizeChangedListener() {
                    @Override
                    public void onVideoSizeChanged(MediaPlayer mp, int width, int height) {
                        mediaController.setAnchorView(videoView);
                    }
                });
            }
        });

        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                finish();
                MediaPlayer player = MediaPlayer.create(VideoActivity.this, R.raw.musiquinha);
                player.start();
            }
        });


    }

}
