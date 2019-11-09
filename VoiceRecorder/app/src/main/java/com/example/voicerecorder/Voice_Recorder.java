package com.example.voicerecorder;

import android.content.Context;
import android.media.MediaRecorder;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;
import java.io.File;
import java.io.IOException;

public class Voice_Recorder
{
    private MediaRecorder mr ;
    private Context context;
    private String Directory_path;

    public Voice_Recorder(Context context)
    {
        this.context = context;

        Create_Directory_path();
    }

    private void Create_Directory_path()
    {
        String app_name = context.getResources().getString(R.string.app_name);
        this.Directory_path = Environment.getExternalStorageDirectory().getAbsolutePath()+"/"+app_name;
        File f = new File(this.Directory_path);
        if(! f.exists())
        {
            f.mkdirs();
        }
    }

    public String get_Directory_path()
    {
        return this.Directory_path;
    }

    private void init_media_recorder(String file_name)
    {
        this.mr = new MediaRecorder();
        mr.setAudioSource(MediaRecorder.AudioSource.MIC);
        mr.setOutputFormat(MediaRecorder.OutputFormat.AAC_ADTS);
        mr.setAudioEncoder(MediaRecorder.OutputFormat.AMR_NB);
        mr.setOutputFile(Directory_path+"/"+file_name+".mp3");
    }

    public boolean start_record(String file_name)
    {
        boolean enable = false;

        init_media_recorder(file_name);
        try
        {
            mr.prepare();
            mr.start();
            enable = true;
        }
        catch(IllegalStateException e)
        {
            Log.i("tag" , "start_record_IllegalStateException  :: "+e.getMessage());
            Toast.makeText(context , "هشدار : صدا ضبط نمیشود !!!" , Toast.LENGTH_SHORT).show();
        }
        catch (IOException e)
        {
            Log.i("tag" , "start_record_IOException  :: "+e.getMessage());
            Toast.makeText(context , "هشدار : صدا ضبط نمیشود !!!" , Toast.LENGTH_SHORT).show();
        }

        return enable;
    }

    public boolean stop_record()
    {
        boolean enable = false;

        if(mr != null)
        {
            try
            {
                mr.stop();
                mr = null;
                enable = true;
            }
            catch(IllegalStateException e)
            {
                Log.i("tag" , "stop_record_IllegalStateException :: "+e.getMessage());
                Toast.makeText(context , "هشدار : ضبط صدا دچار مشکل میباشد !!!" , Toast.LENGTH_SHORT).show();
            }
        }

        return enable;
    }

    public boolean puse_recprd()
    {
        boolean enable = false;

        if(mr != null)
        {
            mr.pause();
            enable = true;
        }

        return enable;
    }

    public boolean resume_recored()
    {
        boolean enable = false;

        if(mr != null)
        {
            mr.resume();

            enable = true;
        }

        return enable;
    }
}
