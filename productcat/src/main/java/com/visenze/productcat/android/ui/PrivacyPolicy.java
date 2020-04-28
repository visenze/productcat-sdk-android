package com.visenze.productcat.android.ui;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.visenze.productcat.R;
import com.visenze.productcat.android.util.ProductCatUIDManager;

public class PrivacyPolicy {

    private static final String IS_PRIVACY_SHOWN="is_privacy_shown";
    private static final String IS_TERMS_ACCEPTED="is_terms_accepted";
    private static final String IS_ADS_ACCEPTED="is_ads_accepted";

    private AlertDialog consentDialog;

    private AlertDialog productRecommendDialog;

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
        productRecommendDialog = createProductRecommendDialog(context);

    }

    public void showConsentDialog() {
        consentDialog.show();
    }

    public void showProductRecommendDialog() {
        productRecommendDialog.show();
    }


    private AlertDialog createProductRecommendDialog(final Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.dialog_product_recomendation, null);

        builder.setView(view);
        final AlertDialog dialog = builder.create();
        dialog.setCanceledOnTouchOutside(false);

        final TextView agree = view.findViewById(R.id.btn_accept);
        final TextView deny = view.findViewById(R.id.btn_deny);

        agree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isAdsAccepted = true;
                setPrefValues(isTermsAccepted, isAdsAccepted);
                dialog.dismiss();
            }
        });

        deny.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isAdsAccepted = false;
                setPrefValues(isTermsAccepted, isAdsAccepted);
                dialog.dismiss();
            }
        });
        return dialog;
    }




    private AlertDialog createConsentDialog(final Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.dialog_privacy_policy, null);

        builder.setView(view);
        final AlertDialog dialog = builder.create();
        dialog.setCanceledOnTouchOutside(false);

        final TextView agree = view.findViewById(R.id.btn_agree);
        final TextView decline = view.findViewById(R.id.btn_decline);
        final TextView privacyPolicy = view.findViewById(R.id.privacy_policy);
        final TextView termsOfUse = view.findViewById(R.id.terms_of_use);

        decline.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                isPrivacyShown = true;
                isTermsAccepted = false;

                setPrefValues(isTermsAccepted, isAdsAccepted);
                dialog.dismiss();
            }
        });

        agree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isPrivacyShown = true;
                isTermsAccepted = true;

                if(!isAdsAccepted) {
                    showProductRecommendDialog();
                }
                dialog.dismiss();
            }
        });

        String uid = ProductCatUIDManager.getUid();
        final String policyUrl = context.getString(R.string.weblink_policy) + "?uid=" + uid;

        privacyPolicy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(policyUrl));
                context.startActivity(intent);
            }
        });

        final String termsOfUseUrl = context.getString(R.string.weblink_terms_of_use) +"?uid=" + uid;

        termsOfUse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(termsOfUseUrl));
                context.startActivity(intent);
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

    public void setAdsAccepted(boolean isAdsAccepted) {
        this.isAdsAccepted = isAdsAccepted;
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean(IS_ADS_ACCEPTED, isAdsAccepted);
        editor.apply();
    }

}
