
package satyajitnets.thequizapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.AsyncTask;

import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import android.widget.ListView;

import android.widget.TextView;
import android.widget.Toast;


import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


import dmax.dialog.SpotsDialog;

import static com.github.mikephil.charting.components.Legend.LegendPosition.BELOW_CHART_RIGHT;
import static com.github.mikephil.charting.components.Legend.LegendPosition.PIECHART_CENTER;

public class results extends Activity implements OnChartValueSelectedListener {
    Animation animVert;
    Typeface fn;
    Toast toast;
    NodeList nList;
    LayoutInflater inflater;
    View layout;
    TextView text,anal;
String keys="";
int correct=0,correct_t,no=0,wrong=0;
    ArrayList<DataModel> dataModels;
    ListView listView;
    private static CustomAdapter adapter;
    AlertDialog dialog;
    String link2,username,password;
    int total;
Context c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result);

c=this;

get();

        PieChart pieChart = findViewById(R.id.piechart);

        pieChart.setUsePercentValues(true);


        fn = Typeface.createFromAsset(getAssets(), "fonts/fnt.ttf");


//here
        CustomDialogClass cdd=new CustomDialogClass(this,"Complete!","Congrats "+username+" you have completed the quiz.",1); //back is pressed
        cdd.show();

        try {
            InputStream is = null;
            is=openFileInput("q.xml");

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(is);

            Element element = doc.getDocumentElement();
            element.normalize();

            nList = doc.getElementsByTagName("question");

        }
        catch (Exception e) {Toast.makeText(this,"Something went wrong",Toast.LENGTH_LONG).show();}










            //ends here











            listView=findViewById(R.id.list);

        dataModels= new ArrayList<>();


        Intent in= getIntent();
        keys=in.getStringExtra("keys");


String ans="NA",ent="NA";

        for (int i=0; i<nList.getLength(); i++) {

            final Node node = nList.item(i); //i

            if (node.getNodeType() == Node.ELEMENT_NODE) {

                Element element2 = (Element) node;


ans=getOpn(Integer.parseInt(getValue("correct", element2)),element2);
ent=getOpn(Integer.parseInt(String.valueOf(keys.charAt(i))),element2);

if(ans.contains(ent)) correct++;
else if(Integer.parseInt(String.valueOf(keys.charAt(i)))==0) no++;
else wrong++;


                dataModels.add(new DataModel(getValue("que", element2), ans,ent));








            }

        }












        adapter= new CustomAdapter(dataModels,getApplicationContext());

        listView.setAdapter(adapter);







         inflater = getLayoutInflater();

         layout = inflater.inflate(R.layout.toast,
                (ViewGroup) findViewById(R.id.custom_toast_container));

         text = layout.findViewById(R.id.TMsg);





        TextView title;

        title=findViewById(R.id.rest);
        anal=findViewById(R.id.anal);

        title.setTypeface(fn);
        title.setText("Quiz CompleteD!"); //results page

        text.setTypeface(fn);
anal.setTypeface(fn);


         toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.BOTTOM, 0, 100);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(layout);


float pC=(correct*100)/nList.getLength();

float pW=(wrong*100)/nList.getLength();

total=correct+wrong;
correct_t=correct;

float pN=100-pC-pW;


        animVert= AnimationUtils.loadAnimation(this, R.anim.anim_translate_in);
        anal.startAnimation(animVert);
title.startAnimation(animVert);
        pieChart.startAnimation(animVert);

        ArrayList<Entry> yvalues = new ArrayList<Entry>();
        yvalues.add(new Entry(pC, 0));
        yvalues.add(new Entry(pW, 1));
        yvalues.add(new Entry(pN, 2));


        //recieve answers

new upds().execute(("idiotic one"));






        PieDataSet dataSet = new PieDataSet(yvalues, null);

        ArrayList<String> xVals = new ArrayList<>(); //string

        xVals.add("Right");
        xVals.add("Wrong");
        xVals.add("NA");

        pieChart.setDrawSliceText(false);
        pieChart.getLegend().setTextColor(Color.rgb(207,0,99));
        pieChart.getLegend().setPosition(BELOW_CHART_RIGHT);

        PieData data = new PieData(xVals, dataSet);
        data.setValueTypeface(fn);


        data.setValueFormatter(new PercentFormatter());

        pieChart.setData(data);

        pieChart.getLegend().setTypeface(fn);

pieChart.setDescription(null);

        pieChart.setDrawHoleEnabled(true);

        pieChart.setTransparentCircleRadius(25f);

        pieChart.setHoleRadius(25f);


        final int[] MY_COLORS = {Color.rgb(0,176,24), Color.rgb(202  ,0,42), Color.rgb(94,39,40),
                };
        ArrayList<Integer> colors = new ArrayList<>(); //integer

        for(int c: MY_COLORS) colors.add(c);

        dataSet.setColors(colors);
dataSet.setDrawValues(false);

        data.setValueTextSize(13f);
        data.setValueTextColor(Color.WHITE);
        pieChart.setOnChartValueSelectedListener(this);






        pieChart.animateXY(1400, 1400);

    }

    @Override
    public void onValueSelected(Entry e, int dataSetIndex, Highlight h) {

        if (e == null)
            return;
        Log.i("VAL SELECTED",
                "Value: " + e.getVal() + ", xIndex: " + e.getXIndex()
                        + ", DataSet index: " + dataSetIndex);
if(e.getXIndex()==0) msg("You have given "+e.getVal()+"% correct answers");
else if(e.getXIndex()==1) msg("You have given "+e.getVal()+"% wrong answers");
else msg("You have not attempted "+e.getVal()+"% of the questions");

    }

    @Override
    public void onNothingSelected() {
        Log.i("PieChart", "nothing selected");
    }

    void msg(String message){

        text.setText(message);


        toast.show();
    }







    private static String getValue(String tag, Element element) {
        NodeList nodeList = element.getElementsByTagName(tag).item(0).getChildNodes();
        Node node = nodeList.item(0);
        return node.getNodeValue();
    }

String getOpn(int opn,Element element2){
        String ans="";
    switch(opn){
        case 1:
            ans=getValue("option1", element2);
            break;
        case 2:
            ans=getValue("option2", element2);
            break;
        case 3:
            ans=getValue("option3", element2);
            break;
        case 4:
            ans=getValue("option4", element2);
            break;
            default:
                ans="NA";
    }
    return ans;
}



    String dec(String s){
        String text="";
        byte[] data = Base64.decode(s, Base64.DEFAULT);
        try {
            text = new String(data, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return text;
    }



    void get(){



        SharedPreferences shared = getSharedPreferences("First_Run", MODE_PRIVATE);

        username = dec(dec((shared.getString("user",""))));
        password = dec(dec(dec((shared.getString("ps","")))));


        password=password.trim();
        username=username.trim();


    }




    private class upds extends AsyncTask<String, Integer, String> {


        // Runs in UI before background thread is called
        @Override
        protected void onPreExecute() {


            dialog =new SpotsDialog.Builder()
                    .setContext(c)
                    .setTheme(R.style.upld)
                    .build();

            dialog.show();


        }

        // This is run in a background thread
        @Override
        protected String doInBackground(String... params) {

            StringBuffer sb2=new StringBuffer("");

            link2 = "http://satyajiit.xyz/quiz/upd.php?user="+username+"&pass="+password+"&flag=1&total="+total+"&cor="+correct_t;

            try {

                URL url2 = new URL(link2);
                HttpClient client = new DefaultHttpClient();
                HttpGet request = new HttpGet();
                request.setURI(new URI(link2));
                HttpResponse response = client.execute(request);
                BufferedReader in = new BufferedReader(new
                        InputStreamReader(response.getEntity().getContent()));


                String line2 = "";

                while ((line2 = in.readLine())!=null) {
                    sb2.append(line2);
                    break;
                }

                in.close();



            }
            catch(Exception e){
                sb2=new StringBuffer("test");

            }





            return String.valueOf(sb2);
        }

        // This is called from background thread but runs in UI
        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);

            // Do things like update the progress bar
        }

        // This runs in UI when background thread finishes
        @Override
        protected void onPostExecute(String result2) {


            super.onPostExecute(result2);

            if (dialog.isShowing()) dialog.dismiss();

            //msg(result2);

            if(result2.contains("updated")) { msg("Updated!");  }

            else msg("Network Error or Server Down..please Try Again");




            // Do things like hide the progress bar or change a TextView
        }
    }







}