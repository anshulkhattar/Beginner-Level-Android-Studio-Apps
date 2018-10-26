package com.google.zxing.intergration.android;

/**
 * Created by Tc2r on 1/6/2017.
 */

public final class IntentResult {
    private final String contents;
    private final String formatName;
    private final byte[] rawBytes;
    private final Integer orientation;
    private final String errorCorrectionLevel;

    IntentResult(){
        this(null, null, null, null, null);
    }

    public IntentResult(String contents,
                        String formatName,
                        byte[] rawBytes,
                        Integer orientation,
                        String errorCorrectionLevel) {
        this.contents = contents;
        this.formatName = formatName;
        this.rawBytes = rawBytes;
        this.orientation = orientation;
        this.errorCorrectionLevel = errorCorrectionLevel;
    }

    public String getContents() {
        return contents;
    }

    public String getErrorCorrectionLevel() {
        return errorCorrectionLevel;
    }

    public String getFormatName() {
        return formatName;
    }

    public Integer getOrientation() {
        return orientation;
    }

    public byte[] getRawBytes() {
        return rawBytes;
    }

    @Override
    public String toString() {
        StringBuilder dialogText = new StringBuilder(100);
        dialogText.append("Format: ").append(formatName).append('\n');
        dialogText.append("Contents: ").append(contents).append('\n');
        int rawBytesLength = rawBytes == null ? 0: rawBytes.length;
        dialogText.append("Raw bytes: (").append(rawBytesLength).append(" bytes)\n");
        dialogText.append("Orientation: ").append(orientation).append('\n');
        dialogText.append("EC level: ").append(errorCorrectionLevel).append('\n');
        return dialogText.toString();

    }
}
