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

import android.os.Bundle;
import android.support.v4.app.Fragment;
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
import com.sromku.simple.fb.Permissions;
import com.sromku.simple.fb.SimpleFacebook;
import com.sromku.simple.fb.SimpleFacebook.OnLoginListener;
import com.sromku.simple.fb.SimpleFacebookConfiguration;

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
	@FragmentArg
	public String nameSpace;
	@ViewById
	public ImageView splashIcon;
	@ViewById
	public TextView profileName;
	@ViewById
	public TextView splashAppName;
	
	private OnLoginListener onLoginListener;
	
	private SimpleFacebook mSimpleFacebook;
	
	private Permissions[] permissions = new Permissions[]
			{
			    Permissions.USER_PHOTOS,
			    Permissions.EMAIL,
			    Permissions.PUBLISH_ACTION
			};
	
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
    	SimpleFacebookConfiguration configuration = new SimpleFacebookConfiguration.Builder()
	        .setAppId(applicationId)
	        .setNamespace(nameSpace)
	        .setPermissions(permissions)
	        .build();
    	SimpleFacebook.setConfiguration(configuration);
    	
    	setUIState();
    }
    
    @Override
    public void onResume()
    {
        super.onResume();
        mSimpleFacebook = SimpleFacebook.getInstance(getActivity());
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
		getOnLoginListener().onLogin();
	}

	/**
	 * You are logged out
	 */
	private void loggedOutUIState(){
	}
    
    @Click
    public void loginButton(){
    	mSimpleFacebook.login(getOnLoginListener());
    }

	public OnLoginListener getOnLoginListener() {
		return onLoginListener;
	}

	public void setOnLoginListener(OnLoginListener onLoginListener) {
		this.onLoginListener = onLoginListener;
	}

}
