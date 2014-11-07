package com.android.settings.yun;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceChangeListener;
import android.preference.Preference.OnPreferenceClickListener;
import android.preference.PreferenceActivity;
import android.widget.Toast;

public class MainActivity extends PreferenceActivity {

	Context mContext = null;
	public static final String STATUSBAR_TRAFFIC="YUN";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.yunsetting_preference);
		mContext = this;
		change_statusbar_traffic();
		CheckBoxPreference systemui_roundr=(CheckBoxPreference)findPreference("systemui_roundr");
		
	}
	private void change_statusbar_traffic() {
		CheckBoxPreference mstatusbar_traffic=(CheckBoxPreference)findPreference("statusbar_traffic");
		mstatusbar_traffic.setOnPreferenceClickListener(new OnPreferenceClickListener() {
			
			@Override
			public boolean onPreferenceClick(Preference arg0) {
				return true;
			}
		});
		mstatusbar_traffic.setOnPreferenceChangeListener(new OnPreferenceChangeListener() {
			
			@Override
			public boolean onPreferenceChange(Preference arg0, Object arg1) {
				Intent it = new Intent("android.intent.action.MAIN");
			   	it.setClassName("com.android.systemui.yun.traffic","com.android.systemui.yun.traffic.HandlerService");
				if((Boolean)arg1==true){
					Toast.makeText(mContext, "checkBox_0改变的值为123" + (Boolean)arg1, Toast.LENGTH_LONG).show();
					
				   	startService(it);
				}
				else{
					Toast.makeText(mContext, "checkBox_0改变的值为" + (Boolean)arg1, Toast.LENGTH_LONG).show();
					stopService(it);
				}
				return true;
			}
		});
	}
	

}
