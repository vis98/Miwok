package com.vishal.miwok;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class ColorsActivity extends AppCompatActivity {
    MediaPlayer mMediaplayer;
    private AudioManager am;
    private AudioManager.OnAudioFocusChangeListener afChangeListener = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {
            if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT ||
                    focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
                // The AUDIOFOCUS_LOSS_TRANSIENT case means that we've lost audio focus for a
                // short amount of time. The AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK case means that
                // our app is allowed to continue playing sound but at a lower volume. We'll treat
                // both cases the same way because our app is playing short sound files.

                // Pause playback and reset player to the start of the file. That way, we can
                // play the word from the beginning when we resume playback.
                mMediaplayer.pause();
                mMediaplayer.seekTo(0);
            } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                // The AUDIOFOCUS_GAIN case means we have regained focus and can resume playback.
                mMediaplayer.start();
            } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                // The AUDIOFOCUS_LOSS case means we've lost audio focus and
                // Stop playback and clean up resources
                releaseMediaPlayer();
            }
        }
    };
    private MediaPlayer.OnCompletionListener mcompletion = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            releaseMediaPlayer();
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);
            final ArrayList<Word> words=new ArrayList<Word>();
        am= (AudioManager)getSystemService(Context.AUDIO_SERVICE);

            words.add(new Word("red", "weṭeṭṭi",R.drawable.color_red,R.raw.color_red));
            words.add(new Word("green", "chokokki",R.drawable.color_green,R.raw.color_green));
            words.add(new Word("brown", "ṭakaakki",R.drawable.color_brown,R.raw.color_brown));
            words.add(new Word("gray", "ṭopoppi",R.drawable.color_gray,R.raw.color_gray));
            words.add(new Word("black", "kululli",R.drawable.color_black,R.raw.color_black));
            words.add(new Word("white", "kelelli",R.drawable.color_white,R.raw.color_white));
            words.add(new Word("dusty yellow", "ṭopiisә",R.drawable.color_dusty_yellow,R.raw.color_dusty_yellow));
            words.add(new Word("mustard yellow", "chiwiiṭә",R.drawable.color_mustard_yellow,R.raw.color_mustard_yellow));
            //LinearLayout rootView=(LinearLayout)findViewById(R.id.rootView);
            // Create a variable to keep track of the current index position

                WordAdapter itemsAdapter = new WordAdapter(this, words, R.color.category_colors);

            // Find the {@link ListView} object in the view hierarchy of the {@link Activity}.
            // There should be a {@link ListView} with the view ID called list, which is declared in the
            ListView listView = (ListView) findViewById(R.id.list);

            // Make the {@link ListView} use the {@link ArrayAdapter} we created above, so that the
            // {@link ListView} will display list items for each word in the list of words.
            // Do this by calling the setAdapter method on the {@link ListView} object and pass in
            // 1 argument, which is the {@link ArrayAdapter} with the variable name itemsAdapter.
            listView.setAdapter(itemsAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Word w = words.get(position);
                releaseMediaPlayer();

                int result = am.requestAudioFocus(afChangeListener,
                        // Use the music stream.
                        AudioManager.STREAM_MUSIC,
                        // Request permanent focus.
                        AudioManager.AUDIOFOCUS_GAIN);


                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    //am.unregisterMediaButtonEventReceiver(RemoteControlReceiver);

                    mMediaplayer = MediaPlayer.create(ColorsActivity.this, w.getAudio_id());
                    mMediaplayer.start();
                    mMediaplayer.setOnCompletionListener(mcompletion);
                }

            }
        });

        }
    protected void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }

    private void releaseMediaPlayer() {
        // If the media player is not null, then it may be currently playing a sound.
        if (mMediaplayer != null) {
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            mMediaplayer.release();

            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            mMediaplayer = null;
            am.abandonAudioFocus(afChangeListener);

        }

    }

}
