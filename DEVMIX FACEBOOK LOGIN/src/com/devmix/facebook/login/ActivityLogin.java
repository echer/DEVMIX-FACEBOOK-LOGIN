package com.devmix.facebook.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.googlecode.androidannotations.annotations.AfterViews;
import com.googlecode.androidannotations.annotations.EActivity;
import com.googlecode.androidannotations.annotations.Extra;
import com.googlecode.androidannotations.annotations.Fullscreen;
import com.googlecode.androidannotations.annotations.NoTitle;
import com.googlecode.androidannotations.annotations.res.StringRes;
import com.sromku.simple.fb.SimpleFacebook.OnLoginListener;

@Fullscreen
@NoTitle  
@EActivity(R.layout.activity_login)
public class ActivityLogin extends FragmentActivity implements OnLoginListener{

	public static final int RESULT_LOGIN_SUCESS = 1;
	public static final int RESULT_LOGIN_FAIL = -1;
	public static final int RESULT_LOGIN_BACK_PRESSED = -2;
	@Extra
	public int iconId;
	@Extra
	public String strProfileName;
	@Extra
	public String appName;
	@Extra
	public String applicationId = "207173972801162";
	@Extra
	public String nameSapce = "devmixsnapshot";
	@StringRes
	public String permissaoNegada;
	
	
	public SplashFragment splashFragment;
	
	@Override
	public void onBackPressed(){
		setResult(RESULT_LOGIN_BACK_PRESSED);
		finish();
	}
	
	@AfterViews
	public void afterViews(){
		
		setResult(RESULT_CANCELED);
		
		splashFragment = new SplashFragment_();
		splashFragment.setOnLoginListener(this);
		
		Bundle bundle = new Bundle();
		bundle.putInt("iconId", iconId);
		bundle.putString("strProfileName", strProfileName);
		bundle.putString("appName", appName);
		bundle.putString("applicationId", applicationId);
		bundle.putString("nameSapce", nameSapce);
		
		splashFragment.setArguments(bundle);
		
		getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, splashFragment).commit();
	}

	@Override
	public void onThinking() {
		Log.i("Chamou", "Chamou");
	}

	@Override
	public void onException(Throwable throwable) {
		Intent i = new Intent();
		i.putExtra("exception", throwable);
		setResult(RESULT_LOGIN_FAIL,i);
		finish();
	}

	@Override
	public void onFail(String reason) {
		Intent i = new Intent();
		i.putExtra("result", reason);
		setResult(RESULT_LOGIN_FAIL,i);
		finish();
	}

	@Override
	public void onLogin() {
		setResult(RESULT_LOGIN_SUCESS);
		finish();
	}

	@Override
	public void onNotAcceptingPermissions() {
		Intent i = new Intent();
		i.putExtra("result", permissaoNegada);
		setResult(RESULT_LOGIN_FAIL,i);
		finish();
	}
}
