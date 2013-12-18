/**
 * Copyright 2010-present Facebook.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.devmix.facebook.login;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.googlecode.androidannotations.annotations.AfterViews;
import com.googlecode.androidannotations.annotations.Click;
import com.googlecode.androidannotations.annotations.EFragment;
import com.googlecode.androidannotations.annotations.FragmentArg;
import com.googlecode.androidannotations.annotations.ViewById;
import com.sromku.simple.fb.SimpleFacebook;
import com.sromku.simple.fb.SimpleFacebook.OnLoginListener;

@EFragment
public class SplashFragment extends Fragment {
	
	@FragmentArg
	public int iconId;
	@FragmentArg
	public String strProfileName;
	@FragmentArg
	public String appName;
	@FragmentArg
	public String applicationId;
	@ViewById
	public ImageView splashIcon;
	@ViewById
	public TextView profileName;
	@ViewById
	public TextView splashAppName;
	private OnLoginListener onLoginListener;
	private SimpleFacebook mSimpleFacebook;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.splash, container, false);
        return view;
    }
    
    @AfterViews
    public void afterViews(){
    	splashIcon.setImageResource(iconId);
    	profileName.setText(strProfileName);
    	splashAppName.setText(appName);
    	
    	mSimpleFacebook = SimpleFacebook.getInstance(getActivity());
    	setUIState();
    }
    
    /**
	 * Set buttons and all ui controls to be enabled or disabled according to facebook login status
	 */
	private void setUIState(){
		if (mSimpleFacebook.isLogin()){
			loggedInUIState();
		}else{
			loggedOutUIState();
		}
	}
	
	/**
	 * You are logged in
	 */
	private void loggedInUIState(){
		onLoginListener.onLogin();
	}

	/**
	 * You are logged out
	 */
	private void loggedOutUIState(){
	}
    
    @Click
    public void loginButton(){
    	mSimpleFacebook.login(new OnLoginListener() {
			@Override
			public void onFail(String reason) {
				onLoginListener.onFail(reason);
			}
			@Override
			public void onException(Throwable throwable) {
				onLoginListener.onException(throwable);
			}
			@Override
			public void onThinking() {
				onLoginListener.onThinking();
			}
			@Override
			public void onNotAcceptingPermissions() {
				onLoginListener.onNotAcceptingPermissions();
			}
			@Override
			public void onLogin() {
				onLoginListener.onLogin();
			}
		});
    }
}
