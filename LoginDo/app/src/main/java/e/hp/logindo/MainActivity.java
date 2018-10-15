package e.hp.logindo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private EditText name,password,email,phone;
    private Button signup;
    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name=(EditText)findViewById(R.id.name);
        password=(EditText)findViewById(R.id.password);
        email=(EditText)findViewById(R.id.email);
        phone=(EditText)findViewById(R.id.phn);
        signup=(Button)findViewById(R.id.signup);
        FirebaseApp.initializeApp(this);
        firebaseAuth= FirebaseAuth.getInstance();
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validate()){
                String user_email= email.getText().toString().trim();
                String User_password=password.getText().toString().trim();

                firebaseAuth.createUserWithEmailAndPassword(user_email,User_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(MainActivity.this, "Registration successful", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(MainActivity.this,LoginPage.class));
                        } else {
                            Toast.makeText(MainActivity.this, "Registration failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                }
            }
        });

    }
    public boolean validate()
    {
        Boolean result=false;

        String Username=name.getText().toString();
        String Userpass=name.getText().toString();
        String Useremail=name.getText().toString();
        String Userphn=name.getText().toString();

        if(Username.isEmpty() || Userpass.isEmpty() || Useremail.isEmpty() || Userphn.isEmpty() )
        {
            Toast.makeText(this,"Invalid input",Toast.LENGTH_SHORT).show();
        }
        else
            result=true;

        return result;
    }

}

