package android.example.com;


import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    int A=0, B=0;
    public void threeptsA(View view)
    {
        A+=3;
        displayForTeamA(A);
    }

     public void twoptsA(View view)
     {
         A+=2;
         displayForTeamA(A);
     }

     public void oneptA(View view)
     {
         A+=1;
         displayForTeamA(A);
     }

     public void threeptsB(View view)
     {
         B+=3;
         displayForTeamB(B);
     }

    public void twoptsB(View view)
    {
        B+=2;
        displayForTeamB(B);
    }

    public void oneptB(View view)
    {
        B+=1;
        displayForTeamB(B);
    }

    public void reset(View view)
    {
        A=0;
        B=0;
        displayForTeamA(A);
        displayForTeamB(B);
    }


    public void displayForTeamA(int score)
    {
        TextView scoreView = findViewById(R.id.team_a_score);
        scoreView.setText(String.valueOf(score));
    }

    public void displayForTeamB(int score)
    {
        TextView scoreView = findViewById(R.id.team_b_score);
        scoreView.setText(String.valueOf(score));
    }

}
