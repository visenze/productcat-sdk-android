package com.visenze.productcat.android.ui;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.visenze.productcat.R;

public class PrivacyPolicy {

    private static final String IS_PRIVACY_SHOWN="is_privacy_shown";
    private static final String IS_TERMS_ACCEPTED="is_terms_accepted";
    private static final String IS_ADS_ACCEPTED="is_ads_accepted";

    private AlertDialog consentDialog;

    private SharedPreferences pref;

    private boolean isPrivacyShown;

    private boolean isTermsAccepted;

    private boolean isAdsAccepted;

    public PrivacyPolicy(Context context) {

        String pref_file = context.getString(R.string.preference_file);
        pref = context.getSharedPreferences(pref_file, Context.MODE_PRIVATE);

        isPrivacyShown = pref.getBoolean(IS_PRIVACY_SHOWN, false);
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
        View view = inflater.inflate(R.layout.dialog_privacy_policy, null);

        final TextView termsAndPolicy = view.findViewById(R.id.terms_and_policy);
        final CheckBox terms = view.findViewById(R.id.agree_service);
        terms.setChecked(true);

        final CheckBox ads = view.findViewById(R.id.agree_ad);
        ads.setChecked(false);
        final Button acceptBtn = view.findViewById(R.id.btn_accept);

        terms.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(isChecked) {
                    ads.setEnabled(true);
                    acceptBtn.setEnabled(true);
                } else {
                    ads.setEnabled(false);
                    acceptBtn.setEnabled(false);
                }
            }
        });


        builder.setView(view);
        final AlertDialog dialog = builder.create();
        dialog.setCanceledOnTouchOutside(false);

        final String policyUrl = context.getString(R.string.weblink_policy);
        termsAndPolicy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(policyUrl));
                context.startActivity(intent);
            }
        });

        acceptBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isPrivacyShown = true;
                isTermsAccepted = terms.isChecked();
                isAdsAccepted = ads.isChecked();

                setPrefValues(isTermsAccepted, isAdsAccepted);
                dialog.dismiss();
            }
        });
        TextView denyBtn = view.findViewById(R.id.btn_deny);
        denyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isPrivacyShown = true;
                isTermsAccepted = false;
                isAdsAccepted = false;

                setPrefValues(isTermsAccepted, isAdsAccepted);
                dialog.dismiss();
            }
        });
        return dialog;
    }


    private void setPrefValues(boolean termsAccepted, boolean adAccepted) {
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean(IS_PRIVACY_SHOWN, true);
        editor.putBoolean(IS_TERMS_ACCEPTED, termsAccepted);
        editor.putBoolean(IS_ADS_ACCEPTED, adAccepted);
        editor.apply();
    }

    public boolean isPrivacyShown() {
        return isPrivacyShown;
    }

    public boolean isTermsAccepted() {
        return isTermsAccepted;
    }

    public boolean isAdsAccepted() {
        return isAdsAccepted;
    }

}
