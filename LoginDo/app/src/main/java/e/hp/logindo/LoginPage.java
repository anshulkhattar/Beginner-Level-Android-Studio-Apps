package e.hp.logindo;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginPage extends AppCompatActivity {

    private EditText email,password;
    private Button login;
    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        email=(EditText)findViewById(R.id.email);
        password=(EditText)findViewById(R.id.password);
        login=(Button)findViewById(R.id.login);
        FirebaseApp.initializeApp(this);
        firebaseAuth= FirebaseAuth.getInstance();
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validate()) {
                    String user_email = email.getText().toString().trim();
                    String user_password = password.getText().toString().trim();
                    firebaseAuth.signInWithEmailAndPassword(user_email, user_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(LoginPage.this, "Registration successful", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(LoginPage.this, LoginSuccess.class));
                            } else {
                                Toast.makeText(LoginPage.this, "Registration failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                    Toast.makeText(LoginPage.this, "Login Successful", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
    public boolean validate()
    {
        Boolean result=false;

        String Useremail=email.getText().toString();
        String Userphn=email.getText().toString();

        if(Useremail.isEmpty() || Userphn.isEmpty() )
        {
            Toast.makeText(this,"Invalid input",Toast.LENGTH_SHORT).show();
        }
        else
            result=true;

        return result;
    }
}
