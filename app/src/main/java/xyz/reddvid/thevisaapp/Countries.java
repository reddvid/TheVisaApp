package xyz.reddvid.thevisaapp;

import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

public class Countries {
    private String simpleText;
    private int countryCode;
    private String visaText;
    private String daysText;

    public Countries(@NonNull final String simpleText, @NonNull final String visaText, @NonNull final int countryCode) {
        setSimpleText(simpleText);
        setVisaText(visaText);
        setFlagPath(countryCode);
        setDaysText(daysText);
    }

    private void setFlagPath(@NonNull final int countryCode) {
        this.countryCode = countryCode;
    }

    public void setSimpleText(@NonNull final String simpleText) {
        this.simpleText = simpleText;
    }

    public void setVisaText(@NonNull String visaText) {
        if (visaText.equals("0"))
            this.visaText = "Visa required";
        else if (visaText.equals("1"))
            this.visaText = "Visa on arrival";
        else if (visaText.equals("2"))
            this.visaText = "e-Visa";
        else if (visaText.equals("3"))
            this.visaText = "Visa-free";
        else
            this.visaText = visaText;

        //this.visaText = visaText;
    }

    public void setDaysText(@NonNull final String daysText) {
        this.daysText = daysText;
    }

    @NonNull
    public int getFlagPath() {
        return countryCode;
    }

    @NonNull
    public String getSimpleText() {
        return simpleText;
    }

    @NonNull
    public String getDaysText() {
        return daysText;
    }

    @NonNull
    public String getVisaText() {
        return visaText;
    }
}
