package com.convert.usd.aud.currency.utill;


import static android.content.Context.CLIPBOARD_SERVICE;

import android.app.Activity;
import android.app.DownloadManager;
import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.Display;
import android.webkit.MimeTypeMap;
import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;

import com.convert.usd.aud.currency.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.OnUserEarnedRewardListener;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.google.android.gms.ads.rewarded.RewardItem;
import com.google.android.gms.ads.rewarded.RewardedAd;
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback;

import java.io.File;

public class Fun {

    public static Context context;
    private static int count = 0;
    private static int countfc = 0;
    private static InterstitialAd mInterstitialAd;
    private static RewardedAd mRewardedAd;
    private static int divider = 3;
    private static int fc = 4;
    private static int admobon = 0;
    public static Activity activity;
    private FrameLayout adContainerView;
    static AdView adView;
    public static String sc = "9";
    public static boolean removeAds = false;

    public Fun(Context context, Activity activity) {
        this.context = context;
        this.activity = activity;

    }

    public static void showBannerAds(FrameLayout adContainerView, Activity activity) {
        // Step 1 - Create an AdView and set the ad unit ID on it.
        adView = new AdView(activity);
        adView.setAdUnitId(activity.getString(R.string.admob_banner_id));
        adContainerView.addView(adView);
        if (removeAds) {
        } else {
            if (!sc.equals("0")) {
                loadBanner(activity);
            }
        }

    }

    public static void rateApp() {
        Uri uri = Uri.parse("market://details?id=" + activity.getPackageName());
        Intent myAppLinkToMarket = new Intent(Intent.ACTION_VIEW, uri);
        try {
            activity.startActivity(myAppLinkToMarket);
        } catch (ActivityNotFoundException e) {
            // Toast.makeText(activity, " unable to find market app", Toast.LENGTH_LONG).show();
        }
    }

    private static void loadBanner(Activity activity) {
        // Create an ad request. Check your logcat output for the hashed device ID
        // to get test ads on a physical device, e.g.,
        // "Use AdRequest.Builder.addTestDevice("ABCDE0123") to get test ads on this
        // device."
        AdRequest adRequest =
                new AdRequest.Builder()
                        .build();

        AdSize adSize = getAdSize(activity);
        // Step 4 - Set the adaptive ad size on the ad view.
        adView.setAdSize(adSize);


        // Step 5 - Start loading the ad in the background.
        adView.loadAd(adRequest);
    }

    private static AdSize getAdSize(Activity activity) {
        // Step 2 - Determine the screen width (less decorations) to use for the ad width.
        Display display = activity.getWindowManager().getDefaultDisplay();
        DisplayMetrics outMetrics = new DisplayMetrics();
        display.getMetrics(outMetrics);

        float widthPixels = outMetrics.widthPixels;
        float density = outMetrics.density;

        int adWidth = (int) (widthPixels / density);

        // Step 3 - Get adaptive ad size and return for setting on the ad view.
        return AdSize.getCurrentOrientationAnchoredAdaptiveBannerAdSize(activity, adWidth);
    }


    private static BroadcastReceiver attachmentDownloadCompleteReceive;

    public static boolean checkInternet() {
        ConnectivityManager connectivityManager = (ConnectivityManager) activity.getSystemService(activity.CONNECTIVITY_SERVICE);
        NetworkInfo info = connectivityManager.getActiveNetworkInfo();

        if (info != null && info.isConnected()) {
            return true;
        } else {
            return false;

        }
    }


    private static int ac = 0;

    public static void addShowreward() {

        AdRequest adRequest = new AdRequest.Builder().build();

        RewardedAd.load(activity, activity.getString(R.string.admob_reward_id),
                adRequest, new RewardedAdLoadCallback() {
                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        // Handle the error.
                        mRewardedAd = null;
                    }

                    @Override
                    public void onAdLoaded(@NonNull RewardedAd rewardedAd) {
                        mRewardedAd = rewardedAd;
                    }
                });

        if (mRewardedAd != null) {
            mRewardedAd.show(activity, new OnUserEarnedRewardListener() {
                @Override
                public void onUserEarnedReward(@NonNull RewardItem rewardItem) {
                    // Handle the reward.
                    int rewardAmount = rewardItem.getAmount();
                    String rewardType = rewardItem.getType();
                }
            });
        } else {
            addShowAdmob();
        }

    }

    public static void addShowAdmob() {

        if (removeAds) {
        } else {
            AdRequest adRequest = new AdRequest.Builder().build();
            InterstitialAd.load(activity, activity.getString(R.string.admob_insta_id), adRequest,
                    new InterstitialAdLoadCallback() {
                        @Override
                        public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {

                            // an ad is loaded.
                            mInterstitialAd = interstitialAd;
                            if (mInterstitialAd != null) {
                                mInterstitialAd.show(activity);
                            }
                        }

                        @Override
                        public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {

                            Log.i("MainActivity", loadAdError.getMessage());
                            mInterstitialAd = null;
                        }

                    });


        }
    }

    public static void addShow() {
        count++;
        if (removeAds) {

        } else {
            if (!sc.equals("0")) {

                if (count % divider == 0) {
                    addShowAdmob();
                }

                if (count % fc == 0) {
                    addShowreward();
                }
            }
        }

    }


}