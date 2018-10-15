package com.ahmedmaghawry.ahmedezzat2048;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Random;
import java.util.zip.Inflater;

public class MainActivity extends AppCompatActivity {
    float x1, x2, y1, y2;
    String result = new String();
    boolean khalas = true;
    ArrayList<String> board = new ArrayList<String>(16);
    Random rand = new Random();
    int nowScore = 0;
    int highScore;
    String highName;
    SharedPreferences saved;
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR1)
    private void goRight(int row, int col) {
        if (khalas) {
            GridLayout grid = (GridLayout) findViewById(R.id.grid);
            TextView now = (TextView) findViewById(R.id.nowScore);
            int curr = Integer.parseInt(now.getText() + "");
            int min = 4;
            Log.i("wfhgfkgfhgkfig","khjkhjk");
            if (isFinish())
                endIt();
            else {
                for (int i = col + 1; i < min; i++) {
                    if ((board.get(4 * row + i)).equals("")) {
                        board.set(4 * row + i, board.get(4 * row + i - 1));
                        TextView hold = new TextView(getApplication());
                        TextView te = (TextView) grid.getChildAt(4 * row + i);
                        te.setText(board.get(4 * row + i - 1));
                        board.set(4 * row + i - 1, "");
                        TextView te2 = (TextView) grid.getChildAt(4 * row + i - 1);
                        te2.setText("");
                    } else if ((board.get(4 * row + i)).equals(board.get(4 * row + i - 1))) {
                        board.set(4 * row + i, "" + (Integer.parseInt(board.get(4 * row + i - 1))) * 2);
                        TextView te = (TextView) grid.getChildAt(4 * row + i);
                        te.setText("" + (Integer.parseInt(board.get(4 * row + i - 1))) * 2);
                        board.set(4 * row + i - 1, "");
                        TextView te2 = (TextView) grid.getChildAt(4 * row + i - 1);
                        te2.setText("");
                        int plus = Integer.parseInt(te.getText() + "");
                        now.setText("" + (curr + plus));
                        min--;
                    }
                }
            }
        }
    }

    private void goLeft(int row, int col) {
        if (khalas) {
            GridLayout grid = (GridLayout) findViewById(R.id.grid);
            TextView now = (TextView) findViewById(R.id.nowScore);
            int curr = Integer.parseInt(now.getText() + "");
            int pul = 0;
            if (isFinish())
                endIt();
            else {
                for (int i = col - 1; i >= pul; i--) {
                    if ((board.get(4 * row + i)).equals("")) {
                        board.set(4 * row + i, board.get(4 * row + i + 1));
                        TextView te = (TextView) grid.getChildAt(4 * row + i);
                        te.setText(board.get(4 * row + i + 1));
                        board.set(4 * row + i + 1, "");
                        TextView te2 = (TextView) grid.getChildAt(4 * row + i + 1);
                        te2.setText("");
                    } else if ((board.get(4 * row + i)).equals(board.get(4 * row + i + 1))) {
                        board.set(4 * row + i, "" + (Integer.parseInt(board.get(4 * row + i + 1))) * 2);
                        TextView te = (TextView) grid.getChildAt(4 * row + i);
                        te.setText("" + (Integer.parseInt(board.get(4 * row + i + 1))) * 2);
                        board.set(4 * row + i + 1, "");
                        TextView te2 = (TextView) grid.getChildAt(4 * row + i + 1);
                        te2.setText("");
                        int plus = Integer.parseInt(te.getText() + "");
                        now.setText("" + (curr + plus));
                        pul++;
                    }
                }
            }
        }
    }

    private void goDown(int colm, int roww) {
        if (khalas) {
            GridLayout grid = (GridLayout) findViewById(R.id.grid);
            TextView now = (TextView) findViewById(R.id.nowScore);
            int curr = Integer.parseInt(now.getText() + "");
            int min = 4;
            if (isFinish())
                endIt();
            else {
                for (int i = roww + 1; i < min; i++) {
                    if ((board.get(4 * i + colm)).equals("")) {
                        board.set(4 * i + colm, board.get(4 * i + colm - 4));
                        TextView te = (TextView) grid.getChildAt(4 * i + colm);
                        te.setText(board.get(4 * i + colm - 4));
                        board.set(4 * i + colm - 4, "");
                        TextView te2 = (TextView) grid.getChildAt(4 * i + colm - 4);
                        te2.setText("");
                    } else if ((board.get(4 * i + colm)).equals(board.get(4 * i + colm - 4))) {
                        board.set(4 * i + colm, "" + (Integer.parseInt(board.get(4 * i + colm - 4))) * 2);
                        TextView te = (TextView) grid.getChildAt(4 * i + colm);
                        te.setText("" + (Integer.parseInt(board.get(4 * i + colm - 4))) * 2);
                        board.set(4 * i + colm - 4, "");
                        TextView te2 = (TextView) grid.getChildAt(4 * i + colm - 4);
                        te2.setText("");
                        int plus = Integer.parseInt(te.getText() + "");
                        now.setText("" + (curr + plus));
                        min--;
                    }
                }
            }
        }
    }

    private void goUp(int colm, int roww) {
        if (khalas) {
            GridLayout grid = (GridLayout) findViewById(R.id.grid);
            TextView now = (TextView) findViewById(R.id.nowScore);
            int curr = Integer.parseInt(now.getText() + "");
            int pul = 0;
            if (isFinish())
                endIt();
            else {
                for (int i = roww - 1; i >= pul; i--) {
                    if ((board.get(4 * i + colm)).equals("")) {
                        board.set(4 * i + colm, board.get(4 * i + colm + 4));
                        TextView te = (TextView) grid.getChildAt(4 * i + colm);
                        te.setText(board.get(4 * i + colm + 4));
                        board.set(4 * i + colm + 4, "");
                        TextView te2 = (TextView) grid.getChildAt(4 * i + colm + 4);
                        te2.setText("");
                    } else if ((board.get(4 * i + colm)).equals(board.get(4 * i + colm + 4))) {
                        board.set(4 * i + colm, "" + (Integer.parseInt(board.get(4 * i + colm + 4))) * 2);
                        TextView te = (TextView) grid.getChildAt(4 * i + colm);
                        te.setText("" + (Integer.parseInt(board.get(4 * i + colm + 4))) * 2);
                        board.set(4 * i + colm + 4, "");
                        TextView te2 = (TextView) grid.getChildAt(4 * i + colm + 4);
                        te2.setText("");
                        int plus = Integer.parseInt(te.getText() + "");
                        now.setText("" + (curr + plus));
                        pul++;
                    }
                }
            }
        }
    }

    private void Generate() {
        if (khalas) {
            int theChoices[] = {2, 2, 2, 4, 2, 2, 2, 4, 2, 2, 4};
            int Chosen = theChoices[rand.nextInt(theChoices.length)];
            int placeIt = rand.nextInt(16);
            if (board.get(placeIt) == "") {
                board.set(placeIt, Chosen + "");
                GridLayout grid = (GridLayout) findViewById(R.id.grid);
                TextView te = (TextView) grid.getChildAt(placeIt);
                te.setText(Chosen + "");
            } else {
                Generate();
            }
        }
    }

    private void drawit() {
        if (khalas) {
            GridLayout grid = (GridLayout) findViewById(R.id.grid);
            for (int i = 0; i < 16; i++) {
                if ((board.get(i)).equals("")) {
                    TextView te = (TextView) grid.getChildAt(i);
                    te.setBackgroundColor(ContextCompat.getColor(getBaseContext(), R.color.empty));
                    te.invalidate();
                    te.setTextColor(ContextCompat.getColor(getBaseContext(), R.color.for1024));
                    te.invalidate();
                } else if ((board.get(i)).equals("2")) {
                    TextView te = (TextView) grid.getChildAt(i);
                    te.setBackgroundColor(ContextCompat.getColor(getBaseContext(), R.color.for2));
                    te.invalidate();
                    te.setTextColor(ContextCompat.getColor(getBaseContext(), R.color.for1024));
                    te.invalidate();
                } else if ((board.get(i)).equals("4")) {
                    TextView te = (TextView) grid.getChildAt(i);
                    te.setBackgroundColor(ContextCompat.getColor(getBaseContext(), R.color.for4));
                    te.invalidate();
                    te.setTextColor(ContextCompat.getColor(getBaseContext(), R.color.for1024));
                    te.invalidate();
                } else if ((board.get(i)).equals("8")) {
                    TextView te = (TextView) grid.getChildAt(i);
                    te.setBackgroundColor(ContextCompat.getColor(getBaseContext(), R.color.for8));
                    te.invalidate();
                    te.setTextColor(ContextCompat.getColor(getBaseContext(), R.color.text2));
                    te.invalidate();
                } else if ((board.get(i)).equals("16")) {
                    TextView te = (TextView) grid.getChildAt(i);
                    te.setBackgroundColor(ContextCompat.getColor(getBaseContext(), R.color.for16));
                    te.invalidate();
                    te.setTextColor(ContextCompat.getColor(getBaseContext(), R.color.text2));
                    te.invalidate();
                } else if ((board.get(i)).equals("32")) {
                    TextView te = (TextView) grid.getChildAt(i);
                    te.setBackgroundColor(ContextCompat.getColor(getBaseContext(), R.color.for32));
                    te.invalidate();
                    te.setTextColor(ContextCompat.getColor(getBaseContext(), R.color.text2));
                    te.invalidate();
                } else if ((board.get(i)).equals("64")) {
                    TextView te = (TextView) grid.getChildAt(i);
                    te.setBackgroundColor(ContextCompat.getColor(getBaseContext(), R.color.for64));
                    te.invalidate();
                    te.setTextColor(ContextCompat.getColor(getBaseContext(), R.color.text2));
                    te.invalidate();
                } else if ((board.get(i)).equals("128")) {
                    TextView te = (TextView) grid.getChildAt(i);
                    te.setBackgroundColor(ContextCompat.getColor(getBaseContext(), R.color.for128));
                    te.invalidate();
                    te.setTextColor(ContextCompat.getColor(getBaseContext(), R.color.text2));
                    te.invalidate();
                } else if ((board.get(i)).equals("256")) {
                    TextView te = (TextView) grid.getChildAt(i);
                    te.setBackgroundColor(ContextCompat.getColor(getBaseContext(), R.color.for128));
                    te.invalidate();
                    te.setTextColor(ContextCompat.getColor(getBaseContext(), R.color.text2));
                    te.invalidate();
                } else if ((board.get(i)).equals("512")) {
                    TextView te = (TextView) grid.getChildAt(i);
                    te.setBackgroundColor(ContextCompat.getColor(getBaseContext(), R.color.for128));
                    te.invalidate();
                    te.setTextColor(ContextCompat.getColor(getBaseContext(), R.color.text2));
                    te.invalidate();
                } else {
                    TextView te = (TextView) grid.getChildAt(i);
                    te.setBackgroundColor(ContextCompat.getColor(getBaseContext(), R.color.for1024));
                    te.invalidate();
                    te.setTextColor(ContextCompat.getColor(getBaseContext(), R.color.text2));
                    te.invalidate();
                }
            }
        }
    }

    private boolean isFinish() {
        boolean finish = true;
        for (int i = 0; i < 4; i++) {
            for (int j = 1; j < 4; j++) {
                if (((board.get(4 * i + j)).equals(board.get(4 * i + j - 1))))
                    return false;
            }

        }
        for (int j = 0; j < 4; j++) {
            for (int i = 1; i < 4; i++) {
                if ((board.get(4 * i + j)).equals(board.get(4 * (i - 1) + j)))
                    return false;
            }

        }
        for (int i = 0; i < 16; i++) {
            if ((board.get(i)).equals(""))
                return false;
        }

        return finish;
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR1)
    private void endIt() {
        GridLayout grid = (GridLayout) findViewById(R.id.grid);
        TextView now = (TextView) findViewById(R.id.nowScore);
        TextView highS = (TextView) findViewById(R.id.bestScore);
        TextView highN = (TextView) findViewById(R.id.bestName);
        TextView End = (TextView) findViewById(R.id.Ended);
        TextView high1 = (TextView) findViewById(R.id.newHighS);
        TextView high2 = (TextView) findViewById(R.id.newHighS2);
        EditText edit = (EditText) findViewById(R.id.EditS);
        Button btn = (Button) findViewById(R.id.button);
        int Now = Integer.parseInt(now.getText() + "");
        int HighS = Integer.parseInt(highS.getText() + "");
        End.setVisibility(View.VISIBLE);
        End.setAlpha(0f);
        End.animate().alpha(0.5f).setDuration(1000);
        khalas = false;
        if (Now >= HighS) {
            grid.setClickable(false);
            grid.setEnabled(false);
            high1.setVisibility(View.VISIBLE);
            high1.setAlpha(0f);
            high1.animate().alpha(0.5f).setDuration(1000);
            high2.setVisibility(View.VISIBLE);
            high2.setAlpha(0f);
            high2.animate().alpha(0.5f).setDuration(1000);
            edit.setVisibility(View.VISIBLE);
            edit.setAlpha(0f);
            edit.animate().alpha(0.5f).setDuration(1000);
            btn.setVisibility(View.VISIBLE);
            btn.setAlpha(0f);
            btn.animate().alpha(0.5f).setDuration(1000);
        }
    }

    public void newData(View view) {
        EditText edit = (EditText) findViewById(R.id.EditS);
        TextView newName = (TextView) findViewById(R.id.bestName);
        TextView newScore = (TextView) findViewById(R.id.bestScore);
        TextView currScore = (TextView) findViewById(R.id.nowScore);
        newName.setText(edit.getText());
        newScore.setText(currScore.getText());
        saved.edit().putString("Name", edit.getText() + "").apply();
        saved.edit().putInt("Score", Integer.parseInt(currScore.getText() + "")).apply();
        TextView high2 = (TextView) findViewById(R.id.newHighS2);
        Button btn = (Button) findViewById(R.id.button);
        edit.setVisibility(View.INVISIBLE);
        high2.setVisibility(View.INVISIBLE);
        btn.setVisibility(View.INVISIBLE);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        load();
    }
    public void load(){
        saved = this.getSharedPreferences("com.ahmedmaghawry.ahmedezzat2048", Context.MODE_PRIVATE);
        highName = saved.getString("Name", "");
        highScore = saved.getInt("Score", 0);
        TextView highna = (TextView) findViewById(R.id.bestName);
        TextView highnu = (TextView) findViewById(R.id.bestScore);
        highna.setText(highName + "");
        highnu.setText(highScore + "");
        for (int i = 0; i < 16; i++) {
            board.add("");
        }
        int first_index = rand.nextInt(16);
        board.set(first_index, "2");
        GridLayout grid = (GridLayout) findViewById(R.id.grid);
        TextView te = (TextView) grid.getChildAt(first_index);
        te.setText("2");
        drawit();
    }

    public boolean onTouchEvent(MotionEvent touchevent) {
        if (khalas) {
            switch (touchevent.getAction()) {
                // when user first touches the screen we get x and y coordinate
                case MotionEvent.ACTION_DOWN: {
                    x1 = touchevent.getX();
                    y1 = touchevent.getY();
                    break;
                }
                case MotionEvent.ACTION_UP: {
                    x2 = touchevent.getX();
                    y2 = touchevent.getY();

                    Log.i("X1 , X2 , Y1 , Y2", "" + x1 + "  " + x2 + "  " + y1 + "  " + y2);
                    //if left to right sweep event on screen
                    ArrayList<String> copy = new ArrayList<String>(16);
                    for (int i = 0; i < 16; i++) {
                        copy.add(board.get(i));
                    }
                    if (x2 > x1 && x2 - x1 > y1 - y2 && x2 - x1 > y2 - y1) {
                        for (int i = 0; i < 4; i++) {
                            for (int j = 2; j >= 0; j--) {
                                if (board.get(i * 4 + j) != "")
                                    goRight(i, j);
                            }
                        }
                        Log.i("The board Right", board.toString());
                    }
                    // if right to left sweep event on screen
                    else if (x1 > x2 && x1 - x2 > y1 - y2 && x1 - x2 > y2 - y1) {
                        for (int i = 0; i < 4; i++) {
                            for (int j = 1; j < 4; j++) {
                                if (board.get(i * 4 + j) != "")
                                    goLeft(i, j);
                            }
                        }
                        Log.i("The board Left", board.toString());
                    }

                    // if UP to Down sweep event on screen
                    else if (y2 > y1 && y2 - y1 > x1 - x2 && y2 - y1 > x2 - x1) {
                        for (int i = 0; i < 4; i++) {
                            for (int j = 2; j >= 0; j--) {
                                if (board.get(j * 4 + i) != "")
                                    goDown(i, j);
                            }
                        }
                        Log.i("The board Down", board.toString());
                    }

                    //if Down to UP sweep event on screen
                    else if (y1 > y2 && y1 - y2 > x1 - x2 && y1 - y2 > x2 - x1) {
                        for (int i = 0; i < 4; i++) {
                            for (int j = 1; j < 4; j++) {
                                if (board.get(j * 4 + i) != "")
                                    goUp(i, j);
                            }
                        }
                        Log.i("The board UP", board.toString());
                    }
                    Log.i("root", board + "");
                    Log.i("copy", copy + "");
                    boolean gener = false;
                    for (int i = 0; i < 16; i++) {
                        if (!((copy.get(i)).equals(board.get(i)))) {
                            gener = true;
                            break;
                        }
                    }
                    if (gener) {
                        Generate();
                        drawit();
                    }
                    break;
                }
            }

        }
        return false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        View view = null;
        if (id == R.id.restart)
            restart();
        else if (id == R.id.undo)
            undo();
        return super.onOptionsItemSelected(item);
    }

    public void restart() {
        setContentView(R.layout.activity_main);
        GridLayout grid = (GridLayout) findViewById(R.id.grid);
        TextView now = (TextView) findViewById(R.id.nowScore);
        TextView End = (TextView) findViewById(R.id.Ended);
        TextView high1 = (TextView) findViewById(R.id.newHighS);
        TextView high2 = (TextView) findViewById(R.id.newHighS2);
        EditText edit = (EditText) findViewById(R.id.EditS);
        Button btn = (Button) findViewById(R.id.button);
        End.setVisibility(View.INVISIBLE);
        grid.setClickable(true);
        grid.setEnabled(true);
        high1.setVisibility(View.INVISIBLE);
        high2.setVisibility(View.INVISIBLE);
        edit.setVisibility(View.INVISIBLE);
        btn.setVisibility(View.INVISIBLE);
        khalas = true;
        board.clear();
        nowScore = 0;
        now.setText("0");
        for (int i = 0; i < 16; i++) {
            TextView textView = (TextView) grid.getChildAt(i);
            textView.setText("");
        }
        Intent i = getBaseContext().getPackageManager()
                .getLaunchIntentForPackage( getBaseContext().getPackageName() );
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
    }

    public void undo() {

    }

}
