package hundredthirtythree.boxca;

import android.Manifest;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import android.app.Activity;
import android.app.ProgressDialog;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.LinearInterpolator;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import com.github.florent37.viewanimator.AnimationListener;
import com.github.florent37.viewanimator.ViewAnimator;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int PICK_FILE_REQUEST = 1;
    private static final String TAG = MainActivity.class.getSimpleName();
    private String selectedFilePath;
    private String SERVER_URL = "https://file.io/?expires=1d";
    ImageView ivAttachment;
    Button bUpload;
    static ProgressDialog dialog;
    RelativeLayout uploadToServer, uploadPopup, successWindow;
    Window window;
    Button uploadBtn, cancelBtn;
    ImageView closeSuccess, shareSuccess;
    int statusBarColor;
    TextView tvfileName, fileNamePopup, textSuccess, link, termOfService;
    Spinner spinner;
    Handler mainHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_to_server);
        mainHandler = new Handler(getApplicationContext().getMainLooper());
        findViews();
        requestPermission();
        ViewAnimator
                .animate(ivAttachment)
                .duration(1000)
                .bounce().interpolator(new LinearInterpolator())
                .accelerate()
                .repeatCount(2)
                .start();
    }

    void findViews() {
        window = this.getWindow();
        statusBarColor = this.getResources().getColor(R.color.colorAccent);
        ivAttachment = (ImageView) findViewById(R.id.ivAttachment);
        tvfileName = (TextView) findViewById(R.id.textView5);
        closeSuccess = (ImageView) findViewById(R.id.closeSuccessPopup);
        textSuccess = (TextView) findViewById(R.id.textSuccess);
        termOfService = (TextView) findViewById(R.id.textView);
        link = (TextView) findViewById(R.id.link);
        spinner = (Spinner) findViewById(R.id.spinner);
        uploadBtn = (Button) findViewById(R.id.uploadButton);
        cancelBtn = (Button) findViewById(R.id.cancel_action);
        bUpload = (Button) findViewById(R.id.b_upload);
        fileNamePopup = (TextView) findViewById(R.id.fileName);
        uploadToServer = (RelativeLayout) findViewById(R.id.uploadToServer);
        uploadPopup = (RelativeLayout) findViewById(R.id.uploadPopup);
        successWindow = (RelativeLayout) findViewById(R.id.success);
        shareSuccess = (ImageView) findViewById(R.id.shareSuccessPopup);
        ivAttachment.setOnClickListener(this);
        bUpload.setOnClickListener(this);
        tvfileName.setOnClickListener(this);
        cancelBtn.setOnClickListener(this);
        uploadBtn.setOnClickListener(this);
        link.setOnClickListener(this);
        closeSuccess.setOnClickListener(this);
        termOfService.setOnClickListener(this);
        shareSuccess.setOnClickListener(this);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.days_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 0:
                        SERVER_URL = "https://file.io/?expires=1d";
                        return;
                    case 1:
                        SERVER_URL = "https://file.io/?expires=3d";
                        return;
                    case 2:
                        SERVER_URL = "https://file.io/?expires=5d";
                        return;
                    case 3:
                        SERVER_URL = "https://file.io/?expires=1w";
                        return;
                    case 4:
                        SERVER_URL = "https://file.io/?expires=1m";
                        return;
                    case 5:
                        SERVER_URL = "https://file.io/?expires=1y";
                        return;
                    default:
                        SERVER_URL = "https://file.io/?expires=1d";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                SERVER_URL = "https://file.io/?expires=1d";
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        if (grantResults.length > 0) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //Do the stuff that requires permission...
                tvfileName.setText(R.string.choose_file);
            } else if (grantResults[0] == PackageManager.PERMISSION_DENIED) {
                // Should we show an explanation?
                if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                    //Show permission explanation dialog...
                } else {
                    tvfileName.setText(R.string.permission_required);
                    changeBackgroundColor(uploadToServer, R.color.backgroundColor, R.color.errorColor);
                    changeStatusBarColor(R.color.errorColor);
                    //Never ask again selected, or device policy prohibits the app from having that permission.
                    //So, disable that feature, or fall back to another situation...
                }
            }
        }
    }

    void requestPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
            } else {
                //Do the stuff that requires permission...
            }
        }
    }

    @Override
    public void onClick(View v) {
        if (v == ivAttachment || v == tvfileName) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (shouldShowRequestPermissionRationale(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                    requestPermission();
                } else {
                    showFileChooser();
                    Log.e(TAG, "onClick: not given");
                }
            } else {
                showFileChooser();
            }
        }

        if (v == uploadBtn) {
            //on upload button Click
            if (selectedFilePath != null) {
                dialog = ProgressDialog.show(MainActivity.this, "", getString(R.string.uploading_file), true);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        //creating new thread to handle Http Operations
                        uploadFile(selectedFilePath);
                    }
                }).start();
            } else {
                Toast.makeText(MainActivity.this, R.string.choose_file_first, Toast.LENGTH_SHORT).show();
            }
        }

        if (v == cancelBtn) {
            uploadPopup.animate()
                    .translationY(uploadPopup.getHeight())
                    .alpha(0.0f)
                    .setDuration(300)
                    .setListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            super.onAnimationEnd(animation);
                            uploadPopup.setVisibility(View.GONE);
                        }
                    });
            changeBackgroundColor(uploadToServer, R.color.steadyColor, R.color.backgroundColor);
            changeStatusBarColor(R.color.backgroundColor);
            termOfService.setVisibility(View.VISIBLE);
        }

        if (v == closeSuccess) {
            uploadPopup.animate()
                    .translationY(successWindow.getHeight())
                    .alpha(0.0f)
                    .setDuration(300)
                    .setListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            super.onAnimationEnd(animation);
                            successWindow.setVisibility(View.GONE);
                            uploadToServer.setVisibility(View.VISIBLE);
                            changeBackgroundColor(uploadToServer, R.color.successColor, R.color.backgroundColor);
                            changeStatusBarColor(R.color.backgroundColor);
                        }
                    });
            termOfService.setVisibility(View.VISIBLE);
        }

        if (v == shareSuccess) {
            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.putExtra(Intent.EXTRA_TEXT, link.getText());
            shareIntent.setType("text/plain");
            startActivity(shareIntent);
        }

        if (v == link) {
            ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
            ClipData clip = ClipData.newPlainText(link.getText().toString(), link.getText().toString());
            clipboard.setPrimaryClip(clip);
            Toast.makeText(this, R.string.link_copied, Toast.LENGTH_SHORT).show();
        }

        if (v == termOfService) {
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse("https://www.file.io/tos.html"));
            startActivity(i);
        }
    }

    void changeMessage(int progre) {
            //Log.v(TAG, strCharacters);
//            dialog.setMessage(getString(R.string.uploading_file)+" "+progre+"%");
    }

    private void showFileChooser() {
        Intent intent = new Intent();
        //sets the select file to all types of files
        intent.setType("*/*");
        //allows to select data and return it
        intent.setAction(Intent.ACTION_GET_CONTENT);
        //starts new activity to select file and return data
        startActivityForResult(Intent.createChooser(intent, getString(R.string.choose_file_to_upload)), PICK_FILE_REQUEST);
    }

    public void changeBackgroundColor(final View view, int from, int to) {
        int colorFrom = ContextCompat.getColor(this, from);
        int colorTo = ContextCompat.getColor(this, to);
        ValueAnimator colorAnimation = ValueAnimator.ofObject(new ArgbEvaluator(), colorFrom, colorTo);
        colorAnimation.setDuration(250); // milliseconds
        colorAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

            @Override
            public void onAnimationUpdate(ValueAnimator animator) {
                view.setBackgroundColor((int) animator.getAnimatedValue());
            }

        });
        colorAnimation.start();
    }

    public void changeStatusBarColor(int color) {
        int a = this.getResources().getColor(color);
        if (android.os.Build.VERSION.SDK_INT >= 21) {
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(a);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == PICK_FILE_REQUEST) {
                if (data == null) {
                    //no data present
                    return;
                }
                Uri selectedFileUri = data.getData();
                selectedFilePath = FilePath.getPath(this, selectedFileUri);
                Log.i(TAG, "Selected File Path:" + selectedFilePath);

                if (selectedFilePath != null && !selectedFilePath.equals("")) {
//                    tvfileName.setText(selectedFilePath);
                    fileNamePopup.setText(selectedFilePath);
                    termOfService.setVisibility(View.GONE);
                    changeBackgroundColor(uploadToServer, R.color.backgroundColor, R.color.steadyColor);
                    changeStatusBarColor(R.color.steadyColor);
                    ViewAnimator
                            .animate(uploadPopup)
                            .onStart(new AnimationListener.Start() {
                                @Override
                                public void onStart() {
                                    uploadPopup.setVisibility(View.VISIBLE);
                                }
                            })
                            .translationY(1000, 0)
                            .alpha(0, 1)
                            .accelerate()
                            .duration(700)
                            .start();
                } else {
                    Toast.makeText(this, R.string.cannot_upload_to_server, Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    //android upload file to server
    public int uploadFile(final String selectedFilePath) {

        int serverResponseCode = 0;

        HttpURLConnection connection;
        DataOutputStream dataOutputStream;
        String lineEnd = "\r\n";
        String twoHyphens = "--";
        String boundary = "*****";


        int bytesRead, bytesAvailable, bufferSize;
        byte[] buffer;
        int maxBufferSize = 1 * 1024 * 1024;
        final File selectedFile = new File(selectedFilePath);
        final StringBuilder result = new StringBuilder();

        String[] parts = selectedFilePath.split("/");
        final String fileName = parts[parts.length - 1];

        if (!selectedFile.isFile()) {
            dialog.dismiss();

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
//                    tvfileName.setText("Source File Doesn't Exist: " + selectedFilePath);
                }
            });
            return 0;
        } else {
            try {
                FileInputStream fileInputStream = new FileInputStream(selectedFile);
                URL url = new URL(SERVER_URL);
                connection = (HttpURLConnection) url.openConnection();
                connection.setDoInput(true);//Allow Inputs
                connection.setDoOutput(true);//Allow Outputs
                connection.setUseCaches(false);//Don't use a cached Copy
                connection.setRequestMethod("POST");
                connection.setRequestProperty("Connection", "Keep-Alive");
                connection.setRequestProperty("ENCTYPE", "multipart/form-data");
                connection.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + boundary);
                connection.setRequestProperty("file", selectedFilePath);

                //creating new dataoutputstream
                dataOutputStream = new DataOutputStream(connection.getOutputStream());

                //writing bytes to data outputstream
                dataOutputStream.writeBytes(twoHyphens + boundary + lineEnd);
//                dataOutputStream.writeBytes("Content-Disposition: form-data; name=\"uploaded_file\";filename=\""
//                        + selectedFilePath + "\"" + lineEnd);

                dataOutputStream.writeBytes("Content-Disposition: form-data; name=\"file\";filename=\""
                        + selectedFilePath + "\"" + lineEnd);

                dataOutputStream.writeBytes(lineEnd);

                //returns no. of bytes present in fileInputStream
                bytesAvailable = fileInputStream.available();
                //selecting the buffer size as minimum of available bytes or 1 MB
                bufferSize = Math.min(bytesAvailable, maxBufferSize);
                //setting the buffer as byte array of size of bufferSize
                buffer = new byte[bufferSize];
                //reads bytes from FileInputStream(from 0th index of buffer to buffersize)
                bytesRead = fileInputStream.read(buffer, 0, bufferSize);
                //loop repeats till bytesRead = -1, i.e., no bytes are left to read

                int progress=0;

                while (bytesRead > 0) {
                    //write the bytes read from inputstream
                    progress+=bytesRead;
                    dataOutputStream.write(buffer, 0, bufferSize);
                    bytesAvailable = fileInputStream.available();
//                    changeMessage((int)((progress*100)/(selectedFile.length())));
                    final int finalProgress = progress;
                    mainHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            dialog.setMessage(getString(R.string.uploading_file)+" "+((int)((finalProgress *100)/(selectedFile.length()))) +"%");
                        }
                    });
                    Log.e(TAG, "uploadFile: "+(int)((finalProgress *100)/(selectedFile.length())) );
//                    dialog.setMessage(getString(R.string.uploading_file)+" "+((int)((progress*100)/(selectedFile.length()))) +"%");
                    bufferSize = Math.min(bytesAvailable, maxBufferSize);
                    bytesRead = fileInputStream.read(buffer, 0, bufferSize);
                }

                dataOutputStream.writeBytes(lineEnd);
                dataOutputStream.writeBytes(twoHyphens + boundary + twoHyphens + lineEnd);

                serverResponseCode = connection.getResponseCode();
                final String serverResponseMessage = connection.getResponseMessage();

                InputStream in = new BufferedInputStream(connection.getInputStream());

                BufferedReader reader = new BufferedReader(new InputStreamReader(in));

                String line;
                while ((line = reader.readLine()) != null) {
                    result.append(line);
                }

                Log.i(TAG, "Server Response is: " + serverResponseMessage + ": " + serverResponseCode + ": " + result);

                //response code of 200 indicates the server status OK
                if (serverResponseCode == 200) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
//                            tvfileName.setText("File Upload completed.\n\n You can see the uploaded file here: \n\n" + serverResponseMessage);
                            try {
                                JSONObject jsonObject = new JSONObject(result.toString());
                                if (jsonObject.getBoolean("success")) {
                                    ViewAnimator
                                            .animate(successWindow)
                                            .onStart(new AnimationListener.Start() {
                                                @Override
                                                public void onStart() {
                                                    successWindow.setVisibility(View.VISIBLE);
                                                    uploadToServer.setVisibility(View.GONE);
                                                }
                                            })
                                            .translationY(1000, 0)
                                            .alpha(0, 1)
                                            .accelerate()
                                            .duration(500)
                                            .start()
                                            .onStop(new AnimationListener.Stop() {
                                                @Override
                                                public void onStop() {
                                                    changeStatusBarColor(R.color.successColor);
                                                }
                                            });
                                    link.setText(jsonObject.getString("link"));
                                    textSuccess.setText(String.format(textSuccess.getText().toString(), jsonObject.getString("expiry")));
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }

                //closing the input and output streams
                fileInputStream.close();
                dataOutputStream.flush();
                dataOutputStream.close();

            } catch (FileNotFoundException e) {
                e.printStackTrace();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(MainActivity.this, R.string.cannot_reach_to_file, Toast.LENGTH_SHORT).show();
                    }
                });
            } catch (MalformedURLException e) {
                e.printStackTrace();
                Toast.makeText(MainActivity.this, R.string.url_error, Toast.LENGTH_SHORT).show();

            } catch (IOException e) {
                e.printStackTrace();
                Toast.makeText(MainActivity.this, R.string.cannot_read_write, Toast.LENGTH_SHORT).show();
            }
            dialog.dismiss();
            return serverResponseCode;
        }

    }
}