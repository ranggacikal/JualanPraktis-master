package www.starcom.com.jualanpraktis;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.os.Handler;
import android.util.Base64;
import android.util.Log;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import www.starcom.com.jualanpraktis.Login.SharedPrefManager;

public class SplashActivity extends AppCompatActivity {


    private static int SPLASH_TIME_OUT = 4000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

//        Context pContext;
//        pContext = this;
//
//        try {
//            PackageInfo info = pContext.getPackageManager().getPackageInfo(pContext.getPackageName(), PackageManager.GET_SIGNATURES);
//            for (Signature signature : info.signatures) {
//                MessageDigest md = MessageDigest.getInstance("SHA");
//                md.update(signature.toByteArray());
//                String hashKey = new String(Base64.encode(md.digest(), 0));
//                Log.d("HashKey", "key: "+hashKey);
//            }
//        } catch (NoSuchAlgorithmException e) {
//            Log.e("SplashActivity", "printHashKey()", e);
//        } catch (Exception e) {
//            Log.e("SplashActivity", "printHashKey()", e);
//        }


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (SharedPrefManager.getInstance(SplashActivity.this).isLoggedIn()){
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    intent.putExtra("tab",0);
                    startActivity(intent);
                    finish();
                }else{
                    Intent intent = new Intent(getApplicationContext(), WelcomePageActivity.class);
                    intent.putExtra("tab",0);
                    startActivity(intent);
                    finish();
                }
            }
        }, SPLASH_TIME_OUT);
    }
}
