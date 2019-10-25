package com.example.yohan.facebookfirebasesdk;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.login.LoginManager;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class profile extends AppCompatActivity {

    Button btnFBLogout;
    private FirebaseAuth mAuth;
    TextView tvid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        btnFBLogout = findViewById(R.id.btnlogout);
        tvid = findViewById(R.id.tvId);

        mAuth = FirebaseAuth.getInstance();

        FirebaseUser user = mAuth.getCurrentUser();
        //String id = user.
       // tvid.setText(id);

        btnFBLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();//firebase
                LoginManager.getInstance().logOut();//facebook
                updateUI();

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user = mAuth.getCurrentUser();
        if(user == null){
            updateUI();
        }
    }

    private void updateUI() {
        Toast.makeText(profile.this,"you are logout!",Toast.LENGTH_LONG).show();
        Intent i = new Intent(profile.this,MainActivity.class);
        startActivity(i);
        finish();
    }
}
