package com.example.voicerecorder;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import java.io.File;
import java.io.IOException;

public class List_Activity extends AppCompatActivity implements AdapterView.OnItemClickListener
{
    Voice_Recorder vr;
    File list_files[];

    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_);

        init_obj();
        list_files = get_list_files();
        if(list_files != null)
        {
            init_listview();
        }
        else
        {
            AlertDialog.Builder dialog = new AlertDialog.Builder(this);
            dialog.setTitle("warning ...");
            dialog.setMessage("you don't record any voice");
            dialog.setCancelable(false);
            dialog.setNeutralButton("ok", new DialogInterface.OnClickListener()
            {
                @Override
                public void onClick(DialogInterface dialogInterface, int i)
                {
                    finish();
                }
            });
            dialog.show();
        }
    }

    public void init_obj()
    {
        vr = new Voice_Recorder(this);
        lv = findViewById(R.id.lv_list);
    }

    public File[] get_list_files()
    {
        File files[] = null;
        File f = new File(vr.get_Directory_path());
        files = f.listFiles();
        return files;
    }

    public void init_listview()
    {
        List_Adapter adapter = new List_Adapter(this , list_files);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
    {
        MediaPlayer mp = new MediaPlayer();
        try
        {
            mp.setDataSource(list_files[i].getPath());
            mp.prepare();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        mp.start();

        Dialog_Fragment_Play dialog = new Dialog_Fragment_Play(this , list_files[i] , mp);
        dialog.show(this.getSupportFragmentManager() , "dialog");
    }
}
