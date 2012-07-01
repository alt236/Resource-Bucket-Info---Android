package co.uk.alt236.bucketinfo.activities;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;
import android.widget.TableLayout;
import co.uk.alt236.bucketinfo.R;
import co.uk.alt236.bucketinfo.util.GuiCreation;

public class MainActivity extends Activity {
	TableLayout mTable;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		mTable = (TableLayout) findViewById(R.id.table);

		getInfo();
	}

	private void getInfo() {
		GuiCreation gc = new GuiCreation(this);
		DisplayMetrics metrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(metrics);
		Display display = ((WindowManager)getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();

		mTable.addView(gc.createRow("Manufacturer:", String.valueOf(android.os.Build.MANUFACTURER), ""));
		mTable.addView(gc.createRow("Model:", String.valueOf(android.os.Build.MODEL), ""));
		mTable.addView(gc.createRow("Product:", String.valueOf(android.os.Build.MODEL), ""));
		mTable.addView(gc.createRow("Brand:", String.valueOf(android.os.Build.BRAND), ""));
		mTable.addView(gc.createRow("API Level:", String.valueOf(android.os.Build.VERSION.SDK_INT), ""));
		mTable.addView(gc.createRow("OS Version:", String.valueOf(android.os.Build.VERSION.RELEASE), ""));
		mTable.addView(gc.createRow("Screen width:", String.valueOf(metrics.heightPixels), ""));
		mTable.addView(gc.createRow("Screen height:", String.valueOf(metrics.widthPixels), ""));
		mTable.addView(gc.createRow("Screen density:", String.valueOf(metrics.density), ""));
		mTable.addView(gc.createRow("Screen DPI:", String.valueOf(metrics.densityDpi), "(" + resolveDpi(metrics.densityDpi) + ")"));
		mTable.addView(gc.createRow("Screen Layout:", resolveScreenLayout(getResources().getConfiguration().screenLayout), ""));
		mTable.addView(gc.createRow("Screen Orientation:", resolveScreenOrientation(getResources().getConfiguration().orientation), ""));
		if(android.os.Build.VERSION.SDK_INT >= 8){
			mTable.addView(gc.createRow("Display Rotation:", String.valueOf(display.getRotation()), "(API 8+)"));
		}
		mTable.addView(gc.createRow("Display width :", String.valueOf(display.getWidth()), "(Depracated)"));
		mTable.addView(gc.createRow("Display height:", String.valueOf(display.getHeight()), "(Depracated)"));
	}


	private String resolveDpi(int dpi){
		switch(dpi){
		case DisplayMetrics.DENSITY_LOW:
			return "ldpi";
		case DisplayMetrics.DENSITY_MEDIUM:
			return "mdpi";
		case DisplayMetrics.DENSITY_HIGH:
			return "hdpi";
		case DisplayMetrics.DENSITY_TV:
			return "tvdpi";
		case DisplayMetrics.DENSITY_XHIGH:
			return "xhdpi";
		default:
			return "non-standard";
		}
	}

	private String resolveScreenOrientation(int orientation){
		switch(orientation){
		case Configuration.ORIENTATION_LANDSCAPE:
			return "landscape";
		case Configuration.ORIENTATION_PORTRAIT:
			return "portrait";
		case Configuration.ORIENTATION_SQUARE:
			return "square";
		default:
			return "undefined (" + orientation +")";
		}
	}

	private String resolveScreenLayout(int layout){
		switch(layout & Configuration.SCREENLAYOUT_SIZE_MASK){
		case Configuration.SCREENLAYOUT_SIZE_XLARGE:
			return "xlarge";
		case Configuration.SCREENLAYOUT_SIZE_LARGE:
			return "large";
		case Configuration.SCREENLAYOUT_SIZE_NORMAL:
			return "normal";
		case Configuration.SCREENLAYOUT_SIZE_SMALL:
			return "small";
		default:
			return "undefined (" + layout +")";
		}
	}
}