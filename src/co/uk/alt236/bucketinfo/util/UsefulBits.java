package co.uk.alt236.bucketinfo.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.provider.Settings;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;


public class UsefulBits {
	private final String TAG =  this.getClass().getName();
	private Context c;
 
	public UsefulBits(Context cntx) {
		//Log.d(TAG, "^ Object created");
		c = cntx;
	}

	public void showAlert(String title, String text, String button1Text){
		if (button1Text.equals("")){button1Text = c.getString(android.R.string.ok);}

		try{
			AlertDialog.Builder ad = new AlertDialog.Builder(c);
			ad.setTitle( title );
			ad.setMessage(text);

			ad.setPositiveButton( button1Text, null );
			ad.show();
		}catch (Exception e){
			Log.e(TAG, "^ ShowAlert()", e);
		}	
	}

	public boolean isOnline() {
		try{ 
			final ConnectivityManager cm = (ConnectivityManager) c.getSystemService(Context.CONNECTIVITY_SERVICE);

			if (cm != null) {
				final NetworkInfo activeNetwork = cm.getActiveNetworkInfo();

				if(activeNetwork != null && activeNetwork.getState() == NetworkInfo.State.CONNECTED){
					Log.d(TAG, "^ NET: isConnected() = true - type = " + activeNetwork.getTypeName());
					return true;
				}else{
					Log.w(TAG, "^ NET: isConnected() = false");
					return false;
				}
			} else {
				Log.e(TAG, "^ NET: isConnected() = false - cm is null!");
				return false;
			}
		}catch(Exception e){
			Log.e(TAG, "^ NET: isConnected() = false - error!", e);
			return false;
		}
	}

	/**
	 * Gets the software version and version name for this application
	 */
	public enum SOFTWARE_INFO{
		NAME, VERSION, NOTES, CHANGELOG, COPYRIGHT, ACKNOWLEDGEMENTS, PRIVACY
	}

	
	public String getVersion(){
		PackageInfo pi;
		try {
			pi = c.getPackageManager().getPackageInfo(c.getPackageName(), 0);
			return pi.versionName;
		} catch (NameNotFoundException e) {
		}
		return "- Error - ";
	}
	
//	public String getSoftwareInfo(SOFTWARE_INFO info) {
//		try {
//			PackageInfo pi = c.getPackageManager().getPackageInfo(c.getPackageName(), 0);
//			Resources appR = c.getResources();
//			CharSequence txt;
//			// Store the software version code and version name somewhere..
//			switch(info){
//			case VERSION:
//				return pi.versionName;
//			case NAME:
//				txt = appR.getText(appR.getIdentifier("app_name", "string", c.getPackageName())); 
//				break;
//			case NOTES:
//				txt = appR.getText(appR.getIdentifier("app_notes", "string", c.getPackageName())); 
//				break;
//			case CHANGELOG:
//				txt = appR.getText(appR.getIdentifier("app_changelog", "string", c.getPackageName())); 
//				break;
//			case COPYRIGHT:
//				txt = appR.getText(appR.getIdentifier("app_copyright", "string", c.getPackageName())); 
//				break;
//			case ACKNOWLEDGEMENTS:
//				txt = appR.getText(appR.getIdentifier("app_acknowledgements", "string", c.getPackageName())); 
//				break;
//			case PRIVACY:
//				txt = appR.getText(appR.getIdentifier("app_privacy_statement", "string", c.getPackageName())); 
//				break;
//			default:
//				return "";
//			}
//			String res = txt.toString();
//			res = res.replaceAll("\\t", "");
//			res = res.replaceAll("\\n\\n", "\n");
//
//			return res.trim();
//		} catch (Exception e) {
//			Log.e(TAG, "* Error @ getSoftwareInfo(" + info.name() + ") ", e);
//			return "";
//		}
//	} 

	public String formatDateTime(String formatString, Date d){
		Format formatter = new SimpleDateFormat(formatString);
		return formatter.format(d);
	}

	public Calendar convertMillisToDate(long millis){
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(millis);
		return calendar;
	}

	public void saveToFile(String fileName, File directory, String contents){

		if (android.os.Environment.getExternalStorageState().equals(
				android.os.Environment.MEDIA_MOUNTED)){
			try {

				if (directory.canWrite()){
					File gpxfile = new File(directory, fileName);
					FileWriter gpxwriter = new FileWriter(gpxfile);
					BufferedWriter out = new BufferedWriter(gpxwriter);
					out.write(contents);
					out.close();
					showToast("Saved to SD as '" + directory.getAbsolutePath() + "/" + fileName + "'", 
							Toast.LENGTH_SHORT, Gravity.TOP,0,0);
				}

			} catch (Exception e) {
				showToast("Could not write file:\n+ e.getMessage()", 
						Toast.LENGTH_SHORT, Gravity.TOP,0,0);
				Log.e(TAG, "^ Could not write file " + e.getMessage());
			}

		}else{
			showToast("No SD card is mounted...", Toast.LENGTH_SHORT, Gravity.TOP,0,0);
			Log.e(TAG, "^ No SD card is mounted.");		
		}
	}

	public void showToast(String message){
		showToast(message, Toast.LENGTH_SHORT, Gravity.TOP,0,0);
	}
	
	public void showToast(String message, int duration, int location, int x_offset, int y_offset){
		Toast toast = Toast.makeText(c.getApplicationContext(), message, duration);
		toast.setGravity(location,x_offset,y_offset);
		toast.show();
	}


	public void showAboutDialogue(){
//		String text = "";
//		String title = "";
//
//		text += c.getString(R.string.app_changelog);
//		text += "\n\n";
//		text += c.getString(R.string.app_notes);
//		text += "\n\n";
//		text += c.getString(R.string.app_acknowledgements);
//		text += "\n\n";
//		text += c.getString(R.string.app_privacy_statement);
//		text += "\n\n";		
//		text += c.getString(R.string.app_copyright);
//		title = c.getString(R.string.app_name) + " v" + getVersion();
//
//		if (!(c==null)){
//			//MyAlertBox.create(c, text, title, c.getString(android.R.string.ok)).show();
//			MyAboutDialogue.create(c, text, title, c.getString(android.R.string.ok)).show();
//			
//		} else {
//			Log.d(TAG, "^ context is null...");
//		}
	}
	
	public boolean isIntentAvailable(String packageName, String className) {
	    final PackageManager packageManager = c.getPackageManager();
	    final Intent intent = new Intent();
	    intent.setClassName(packageName, className);
	    
	    List<ResolveInfo> list =
	            packageManager.queryIntentActivities(intent,
	                    PackageManager.MATCH_DEFAULT_ONLY);
	    
	    if (list.size() > 0) {
	    	Log.d(TAG, "^ Activity exists:" + className);
	    }
	    
	    return list.size() > 0;
	}
	
	public boolean isAirplaneModeOn(){
		return Settings.System.getInt(c.getContentResolver(), Settings.System.AIRPLANE_MODE_ON, 0) == 1; 		
	}
	
	public String tableToString(TableLayout t) {
		StringBuilder sb = new StringBuilder();

		if(t==null){return "Table is null!";}

		for (int i=0; i <= t.getChildCount()-1; i++){
			TableRow row = (TableRow) t.getChildAt(i);

			for (int j=0; j <= row.getChildCount()-1; j++){
				View v = row.getChildAt(j);

				try {
					if(v.getClass() == Class.forName("android.widget.TextView")){
						TextView tmp = (TextView) v;
						sb.append(tmp.getText());
						if(j==0){sb.append(" ");}
					} else if(v.getClass() == Class.forName("android.widget.EditText")){
						EditText tmp = (EditText) v;
						sb.append(tmp.getText().toString());
					} else {
						//do nothing
					}
				} catch (Exception e) {
					sb = new StringBuilder();
					sb.append(e.toString());
					Log.e(TAG, "^ tableToString: " + e.toString());
				}
			}
			sb.append("\n");
		}
		return sb.toString();
	}

	public boolean isDonateVersion() {
		String packageName =  c.getApplicationContext().getPackageName();
		if(packageName == null){
			return true;
		}
		
		if((packageName.endsWith("_D") || packageName.endsWith("_d"))){
			return true;
		}
		
		return false;
	}
}
