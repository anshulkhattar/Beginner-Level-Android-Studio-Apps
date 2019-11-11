package com.example.voicerecorder;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import java.io.File;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class Dialog_Fragment_Play extends DialogFragment implements  View.OnClickListener, SeekBar.OnSeekBarChangeListener {
    File f;
    Context context;

    TextView tv_name;
    SeekBar sb;
    Button btn_play_puse;

    MediaPlayer mp;

    public Dialog_Fragment_Play(Context context , File f , MediaPlayer mp)
    {
        this.f = f;
        this.context = context;
        this.mp = mp;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater , @Nullable ViewGroup container , @Nullable Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.dialog_fragment_play , null , false);

        init_obj(v);

        return v;
    }

    @Override
    public void onResume()
    {
        super.onResume();

        this.getDialog().getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT , ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();
        if(mp.isPlaying())
        {
            mp.stop();
        }
    }

    public void init_obj(View v)
    {
        tv_name = v.findViewById(R.id.dilaog_tv_name);
        sb = v.findViewById(R.id.sb_videocontrol);
        btn_play_puse = v.findViewById(R.id.btn_dialog_puse_play);

        tv_name.setText(f.getName());
        btn_play_puse.setOnClickListener(this);
        sb.setOnSeekBarChangeListener(this);

        sb.setMax(mp.getDuration());
        new Timer().scheduleAtFixedRate(new TimerTask()
        {
            @Override
            public void run()
            {
                sb.setProgress(mp.getCurrentPosition());
            }
        }, 0, 1000);
    }

    @Override
    public void onClick(View view)
    {
        if(mp != null)
        {
            if(mp.isPlaying())
            {
                mp.pause();
                btn_play_puse.setText("play");
            }
            else
            {
                mp.start();
                btn_play_puse.setText("puse");
            }
        }
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b)
    {
        if(b == true)
        {
            mp.seekTo(i);
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar)
    {
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar)
    {
    }
}
