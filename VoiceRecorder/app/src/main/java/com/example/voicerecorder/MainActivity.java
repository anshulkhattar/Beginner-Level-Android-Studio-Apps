package com.example.voicerecorder;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Date;

public class MainActivity extends AppCompatActivity implements View.OnClickListener
{

    Voice_Recorder vr;

    Button btn_record;
    Button btn_stop;

    MenuItem menu_list;

    String current_state_btn_record = "IDLE";

    private final String STATE_IDLE     = "IDLE";
    private final String STATE_RECORD   = "RECORD";
    private final String STATE_PUSE     = "PUSE";
    private final String STATE_RESUME   = "RESUME";
    private final String STATE_STOP     = "STOP";


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        runtime_permission();
        init_obj();
    }

    public void runtime_permission()
    {
        String perm[] = {Manifest.permission.READ_EXTERNAL_STORAGE , Manifest.permission.WRITE_EXTERNAL_STORAGE , Manifest.permission.RECORD_AUDIO};

        if(  (this.checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)  != PackageManager.PERMISSION_GRANTED) ||
             (this.checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) ||
             (this.checkSelfPermission(Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED)  )
        {
            requestPermissions(perm,3);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)
    {
        if(requestCode == 3)
        {
            boolean enable = true;

            for(int i=0 ; i<permissions.length ; i++)
            {
                if(grantResults[i] != PackageManager.PERMISSION_GRANTED)
                {
                    enable = false;
                }
            }

            if(enable == false)
            {
                AlertDialog.Builder dialog = new AlertDialog.Builder(this);
                dialog.setTitle("warning");
                dialog.setMessage("you don't give require permission to this app !!");
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
    }

    public void init_obj()
    {
        btn_record = findViewById(R.id.btn_record);
        btn_stop = findViewById(R.id.btn_stop);

        btn_record.setOnClickListener(this);
        btn_stop.setOnClickListener(this);
    }

    @Override
    public void onClick(View view)
    {
        if(view.getId() == btn_record.getId())
        {
            if(current_state_btn_record.equals(STATE_IDLE))
            {
                vr = new Voice_Recorder(this);
                vr.start_record(String.valueOf(new Date().hashCode()));
                current_state_btn_record = STATE_RECORD;
                Toast.makeText(this , "start record" , Toast.LENGTH_SHORT).show();

                btn_stop.setVisibility(View.VISIBLE);
            }
            else if(current_state_btn_record.equals(STATE_RECORD) || current_state_btn_record.equals(STATE_RESUME))
            {
                vr.puse_recprd();
                current_state_btn_record = STATE_PUSE;
            }
            else if(current_state_btn_record.equals(STATE_PUSE))
            {
                vr.resume_recored();
                current_state_btn_record = STATE_RESUME;
            }

        }
        else if(view.getId() == btn_stop.getId())
        {
            if((! current_state_btn_record.equals(STATE_IDLE)) && (vr != null))
            {
                vr.stop_record();
                vr = null;
                current_state_btn_record = STATE_IDLE;

                Toast.makeText(this , "stop record" , Toast.LENGTH_SHORT).show();
                btn_stop.setVisibility(View.INVISIBLE);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        menu_list = menu.add("LSIT");
        menu_list.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item)
    {
        if(item == menu_list)
        {
            startActivity(new Intent(MainActivity.this , List_Activity.class));
        }

        return super.onOptionsItemSelected(item);
    }
}
