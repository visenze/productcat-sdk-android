package com.visenze.productcat.android.ui;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.visenze.productcat.R;
import com.visenze.productcat.android.util.ProductCatUIDManager;

public class PrivacyPolicy {

    private static final String IS_PRIVACY_SHOWED ="is_privacy_showed";
    private static final String IS_TERMS_ACCEPTED="is_terms_accepted";
    private static final String IS_ADS_ACCEPTED="is_ads_accepted";

    private AlertDialog consentDialog;


    private SharedPreferences pref;

    private boolean isPrivacyShowed;

    private boolean isTermsAccepted;

    private boolean isAdsAccepted;

    public PrivacyPolicy(Context context) {

        String pref_file = context.getString(R.string.productcat_sdk_preference_file);
        pref = context.getSharedPreferences(pref_file, Context.MODE_PRIVATE);

        isPrivacyShowed = pref.getBoolean(IS_PRIVACY_SHOWED, false);
        isTermsAccepted = pref.getBoolean(IS_TERMS_ACCEPTED, false);
        isAdsAccepted = pref.getBoolean(IS_ADS_ACCEPTED, false);

        consentDialog = createConsentDialog(context);

    }

    public void showConsentDialog() {
        consentDialog.show();
    }




    private AlertDialog createConsentDialog(final Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.productcat_sdk_dialog_privacy_policy, null);

        builder.setView(view);
        final AlertDialog dialog = builder.create();
        dialog.setCanceledOnTouchOutside(false);
        final TextView contentView = view.findViewById(R.id.privacy_content);
        contentView.setMovementMethod((LinkMovementMethod.getInstance()));

        final CheckBox box = view.findViewById(R.id.recommend_box);
        final TextView agree = view.findViewById(R.id.btn_agree);
        final TextView decline = view.findViewById(R.id.btn_decline);

        box.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                isAdsAccepted = b;
            }
        });

        decline.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                isPrivacyShowed = true;
                isTermsAccepted = false;

                setPrefValues(isTermsAccepted, isAdsAccepted);
                dialog.dismiss();
            }
        });


        agree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isPrivacyShowed = true;
                isTermsAccepted = true;

                setPrefValues(isTermsAccepted, isAdsAccepted);
                dialog.dismiss();
            }
        });

        return dialog;
    }


    private void setPrefValues(boolean termsAccepted, boolean adAccepted) {
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean(IS_PRIVACY_SHOWED, true);
        editor.putBoolean(IS_TERMS_ACCEPTED, termsAccepted);
        editor.putBoolean(IS_ADS_ACCEPTED, adAccepted);
        editor.apply();
    }

    public boolean isPrivacyShowed() {
        return isPrivacyShowed;
    }

    public void setPrivacyShowed(boolean showed) {
        isPrivacyShowed = showed;
    }

    public boolean isTermsAccepted() {
        return isTermsAccepted;
    }

    public boolean isAdsAccepted() {
        return isAdsAccepted;
    }

    public void setAdsAccepted(boolean isAdsAccepted) {
        if(this.isAdsAccepted != isAdsAccepted) {
            this.isAdsAccepted = isAdsAccepted;
            SharedPreferences.Editor editor = pref.edit();
            editor.putBoolean(IS_ADS_ACCEPTED, isAdsAccepted);
            editor.apply();
        }
    }

}
