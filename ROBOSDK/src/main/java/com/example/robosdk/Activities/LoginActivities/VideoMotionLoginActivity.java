package com.example.robosdk.Activities.LoginActivities;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.VideoView;

import com.example.robosdk.R;

public class VideoMotionLoginActivity extends AppCompatActivity {
    private static final String VIDEO_SAMPLE =
            "https://beta.roborewards.net/images/DefaultImages/DefaultVideo.mp4";

    LinearLayout liVideo;
    private VideoView mVideoView;
    Uri uri ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_motion_login);
        mVideoView = findViewById(R.id.videoViewLogin);
        liVideo = findViewById(R.id.liVideo);
        uri =Uri.parse(VIDEO_SAMPLE);
        mVideoView.setVideoURI(uri);

        startPlayer();

      /*  MediaController controller = new MediaController(this);
        controller.setMediaPlayer(mVideoView);
        mVideoView.setMediaController(controller);*/
    }

    private void startPlayer() {


        mVideoView.start();
        mVideoView.setOnPreparedListener(
                new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mediaPlayer) {

                        liVideo.setVisibility(View.GONE);
                    }
                });

        mVideoView.setOnCompletionListener(
                new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        startPlayer();

                        // Return the video position to the start.
//                        mVideoView.seekTo(0);
                    }
                });
    }

  /*  @Override
    protected void onStart() {
        super.onStart();

        // Load the media each time onStart() is called.
        initializePlayer();
    }

    @Override
    protected void onPause() {
        super.onPause();

        // In Android versions less than N (7.0, API 24), onPause() is the
        // end of the visual lifecycle of the app.  Pausing the video here
        // prevents the sound from continuing to play even after the app
        // disappears.
        //
        // This is not a problem for more recent versions of Android because
        // onStop() is now the end of the visual lifecycle, and that is where
        // most of the app teardown should take place.
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
            mVideoView.pause();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();

        // Media playback takes a lot of resources, so everything should be
        // stopped and released at this time.
        releasePlayer();
    }
    private void initializePlayer() {
        // Show the "Buffering..." message while the video loads.

        // Buffer and decode the video sample.
        Uri videoUri = getMedia(VIDEO_SAMPLE);
        mVideoView.setVideoURI(videoUri);
        mVideoView.setMediaController(null);

        // Listener for onPrepared() event (runs after the media is prepared).
        mVideoView.setOnPreparedListener(
                new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mediaPlayer) {

                        Toast.makeText(VideoMotionLoginActivity.this, "", Toast.LENGTH_SHORT).show();
                        // Start playing!
                        mVideoView.start();
                        mVideoView.setVisibility(View.VISIBLE);
                    }
                });

        // Listener for onCompletion() event (runs after media has finished
        // playing).
        mVideoView.setOnCompletionListener(
                new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        initializePlayer();

                        // Return the video position to the start.
//                        mVideoView.seekTo(0);
                    }
                });
    }


    // Release all media-related resources. In a more complicated app this
    // might involve unregistering listeners or releasing audio focus.
    private void releasePlayer() {
        mVideoView.stopPlayback();
    }

    // Get a Uri for the media sample regardless of whether that sample is
    // embedded in the app resources or available on the internet.
    private Uri getMedia(String mediaName) {
        if (URLUtil.isValidUrl(mediaName)) {
            // Media name is an external URL.
            return Uri.parse(mediaName);
        } else {

            // you can also put a video file in raw package and get file from there as shown below

            return Uri.parse("android.resource://" + getPackageName() +
                    "/raw/" + mediaName);


        }
    }*/

}