package com.google.zxing.intergration.android;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Tc2r on 1/6/2017.
 */

public class IntentIntegrator {

    public static final int REQUEST_CODE = 0X0000c0de; // only use bottom 16 bits
    private static final String TAG = IntentIntegrator.class.getSimpleName();

    public static final String DEFAULT_TITLE = "Install Barcode Scanner";
    public static final String DEFAULT_MESSAGE = "This application requires Barcode Scanner. Would you like to install it?";
    public static final String DEFAULT_YES = "Yes";
    public static final String DEFAULT_NO = "No";

    public static final String BS_PACKAGE = "com.google.zxing.client.android";
    public static final String BSPLUS_Package = "com.srowen.bs.android";


    // Supported Barcode Formats

    public static final Collection<String> PRODUCT_CODE_TYPES = list("UPC_A", "UPC_B", "EAN_8", "EAN_13", "RSS_14");
    public static final Collection<String> ONE_D_CODE_TYPES = list("UPC_A", "UPC_B", "EAN_8", "EAN_13", "RSS_14", "CODE_39", "CODE_93", "CODE_128", "ITF", "RSS_EXPANDED");
    public static final Collection<String> QR_CODE_TYPES = Collections.singleton("QR_CODE");
    public static final Collection<String> DATA_MATRIX_TYPES = Collections.singleton("DATA_MATRIX");
    public static final Collection<String> ALL_CODE_TYPES = null;
    public static final List<String> TARGET_BARCODE_SCANNER_ONLY = Collections.singletonList(BS_PACKAGE);
    public static final List<String> TARGET_ALL_KNOWN = list(
            BS_PACKAGE, // Barcode Scanner
            BSPLUS_Package, // Barcode Scanner+
            BSPLUS_Package + ".simple" // BARCODE SCANNER + SIMPLE
            // what else supports this intent?
    );

    // Variables

    private final Activity activity;
    private String title;
    private String message;
    private String buttonYes;
    private String buttonNo;
    private List<String> targetApplications;
    private final Map<String,Object> moreExtras;

    // method
    public IntentIntegrator(Activity activity){
        this.activity = activity;
        title = DEFAULT_TITLE;
        message = DEFAULT_MESSAGE;
        buttonYes = DEFAULT_YES;
        buttonNo = DEFAULT_NO;
        targetApplications = TARGET_ALL_KNOWN;
        moreExtras = new HashMap<String, Object>(3);
    }

    // Getters and Setters
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public void setTitleByID(int titleID){
        title = activity.getString(titleID);
    }

    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }

    public void setMessageByID(int  messageID) {
        message = activity.getString(messageID);
    }
    public String getButtonYes() {
        return buttonYes;
    }
    public void setButtonYesByID(int buttonYesID){
        buttonNo = activity.getString(buttonYesID);
    }
    public void setButtonYes(String buttonYes) {
        this.buttonYes = buttonYes;
    }
    public String getButtonNo() {
        return buttonNo;
    }
    public void setButtonNo(String buttonNo) {
        this.buttonNo = buttonNo;
    }
    public void setButtonNoByID(int buttonNoID){
        buttonNo = activity.getString(buttonNoID);
    }

    public Collection<String> getTargetApplications(){
        return targetApplications;
    }
    public final void setTargetApplications(List<String> targetApplications){
        if(targetApplications.isEmpty()){
            throw new IllegalArgumentException("No Target Applications");
        }
        this.targetApplications = targetApplications;
    }
    public void setSingleTargetApplication(String targetApplication){
        this.targetApplications = Collections.singletonList(targetApplication);
    }

    public Map<String, ?> getMoreExtras(){
        return moreExtras;
    }

    public final void addExtra(String key, Object value){
        moreExtras.put(key, value);
    }

    public final AlertDialog initiateScan(){
        return initiateScan(ALL_CODE_TYPES);
    }

    public final AlertDialog initiateScan(Collection<String> desiredBarcodeFormats) {

        Intent intentScan = new Intent(BS_PACKAGE + ".SCAN");
        intentScan.addCategory(Intent.CATEGORY_DEFAULT);

        // Check which types of codes to scan for

        if (desiredBarcodeFormats != null) {
            // set the desired barcode types

            StringBuilder joinedByComma = new StringBuilder();
            for (String format : desiredBarcodeFormats) {
                if (joinedByComma.length() > 0) {
                    joinedByComma.append(',');
                }
                joinedByComma.append(format);
            }
            intentScan.putExtra("SCAN_FORMATS", joinedByComma.toString());
        }

        String targetAppPackage = findTargetAppPackage(intentScan);
        if(targetAppPackage == null){
            return showDownloadDialog();

        }
        intentScan.setPackage(targetAppPackage);
        intentScan.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intentScan.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
        attachMoreExtras(intentScan);
        startActivityforResult(intentScan, REQUEST_CODE);
        return null;

    }

    protected void startActivityforResult(Intent intent, int code){
        activity.startActivityForResult(intent, code);
    }

    private String findTargetAppPackage(Intent intent){
        PackageManager pm = activity.getPackageManager();
        List<ResolveInfo> availableApps = pm.queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);
        if(availableApps != null){
            for(ResolveInfo availableApp : availableApps){
                String packageName = availableApp.activityInfo.packageName;
                if (targetApplications.contains(packageName)){
                    return packageName;
                }
            }
        }
        return null;
    }

    private AlertDialog showDownloadDialog(){
        AlertDialog.Builder downloadDialog = new AlertDialog.Builder(activity);
        downloadDialog.setTitle(title);
        downloadDialog.setMessage(message);
        downloadDialog.setPositiveButton(buttonYes, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                String packageName = targetApplications.get(0);
                Uri uri = Uri.parse("market://details?id=" + packageName);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                try{
                    activity.startActivity(intent);

                }catch(ActivityNotFoundException anfe){
                    // Market is not installed
                    Log.w(TAG, "Google Play Is Not Installed; Cannot Install " + packageName);
                }
            }
        });
        downloadDialog.setNegativeButton(buttonNo, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {

            }
        });
        return downloadDialog.show();
    }

    public static IntentResult parseActivityResult(int requestCode, int resultCode, Intent intent){
        if(requestCode == REQUEST_CODE){
            if(resultCode == Activity.RESULT_OK){
                String contents = intent.getStringExtra("SCAN_RESULT");
                String formatName = intent.getStringExtra("SCAN_RESULT_FORMAT");
                byte[] rawBytes = intent.getByteArrayExtra("SCAN_RESULT_BYTES");
                int intentOrientation = intent.getIntExtra("SCAN_RESULT_ORIENTATION", Integer.MIN_VALUE);
                Integer orientation = intentOrientation == Integer.MIN_VALUE ? null : intentOrientation;
                String errorCorrectionLevel = intent.getStringExtra("SCAN_RESULT_ERROR_CORRECTION_LEVEL");
                return new IntentResult(contents,
                        formatName,
                        rawBytes,
                        orientation,
                        errorCorrectionLevel);
            }
            return new IntentResult();
        }
        return null;
    }

    public final AlertDialog shareText(CharSequence text, CharSequence type){
        Intent intent = new Intent();
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        intent.setAction(BS_PACKAGE + ".ENCODE");
        intent.putExtra("ENCODE_TYPE", type);
        intent.putExtra("ENCODE_DATA", text);
        String targetAppPackage = findTargetAppPackage(intent);
        if(targetAppPackage == null){
            return showDownloadDialog();
        }
        intent.setPackage(targetAppPackage);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
        attachMoreExtras(intent);
        activity.startActivity(intent);
        return null;
    }

    private static List<String> list(String... values){
        return Collections.unmodifiableList(Arrays.asList(values));
    }

    private void attachMoreExtras(Intent intent){
        for(Map.Entry<String, Object> entry : moreExtras.entrySet()){
            String key = entry.getKey();
            Object value = entry.getValue();

            // Kindof iffy

            if(value instanceof Integer){
                intent.putExtra(key, (Integer) value);
            } else if (value instanceof Long) {
                intent.putExtra(key, (Long) value);
            } else if (value instanceof Boolean) {
                intent.putExtra(key, (Boolean) value);
            } else if (value instanceof Double) {
                intent.putExtra(key, (Double) value);
            } else if (value instanceof Float) {
                intent.putExtra(key, (Float) value);
            } else if (value instanceof Bundle) {
                intent.putExtra(key, (Bundle) value);
            } else {
                intent.putExtra(key, value.toString());
            }
        }
    }
}
