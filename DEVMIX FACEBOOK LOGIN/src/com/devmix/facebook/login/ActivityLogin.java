package com.devmix.facebook.login;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.googlecode.androidannotations.annotations.AfterViews;
import com.googlecode.androidannotations.annotations.EActivity;
import com.googlecode.androidannotations.annotations.Extra;
import com.googlecode.androidannotations.annotations.Fullscreen;
import com.googlecode.androidannotations.annotations.NoTitle;

@Fullscreen
@NoTitle  
@EActivity(R.layout.activity_login) 
public class ActivityLogin extends FragmentActivity {

	@Extra
	public int iconId;
	@Extra
	public String strProfileName;
	@Extra
	public String appName;
	@Extra
	public String applicationId = "207173972801162";
	public SplashFragment splashFragment;
	@AfterViews
	public void afterViews(){
		splashFragment = new SplashFragment_();
		Bundle bundle = new Bundle();
		bundle.putInt("iconId", iconId);
		bundle.putString("strProfileName", strProfileName);
		bundle.putString("appName", appName);
		bundle.putString("applicationId", applicationId);
		splashFragment.setArguments(bundle);
		
		getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, splashFragment).commit();
	}
}
