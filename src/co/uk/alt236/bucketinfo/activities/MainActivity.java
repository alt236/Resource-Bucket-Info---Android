package co.uk.alt236.bucketinfo.activities;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;
import android.widget.TableLayout;
import co.uk.alt236.bucketinfo.R;
import co.uk.alt236.bucketinfo.util.GuiCreation;
import co.uk.alt236.bucketinfo.util.Resolver;

public class MainActivity extends Activity {
	Resolver mResolver;
	
	TableLayout mTableInfo;
	TableLayout mTableDrawables;
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		mTableInfo = (TableLayout) findViewById(R.id.tableInfo);
		mTableDrawables = (TableLayout) findViewById(R.id.tableDrawables);
		mResolver = new Resolver(this);
		
		populateUI();
	}

	@TargetApi(8)
	private String getDisplayRotation(Display display){
		if(android.os.Build.VERSION.SDK_INT >= 8){
			return String.valueOf(display.getRotation());
		} else {
			return "Unavailable";
		}
	}

	private void populateUI() {
		GuiCreation gc = new GuiCreation(this);
		DisplayMetrics metrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(metrics);
		Display display = ((WindowManager)getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();

		// General Information
		mTableInfo.addView(gc.createTitleRow("Device Information"));
		mTableInfo.addView(gc.createRow("Manufacturer:", String.valueOf(android.os.Build.MANUFACTURER), ""));
		mTableInfo.addView(gc.createRow("Model:", String.valueOf(android.os.Build.MODEL), ""));
		mTableInfo.addView(gc.createRow("Product:", String.valueOf(android.os.Build.MODEL), ""));
		mTableInfo.addView(gc.createRow("Brand:", String.valueOf(android.os.Build.BRAND), ""));
		mTableInfo.addView(gc.createRow("API Level:", String.valueOf(android.os.Build.VERSION.SDK_INT), ""));
		mTableInfo.addView(gc.createRow("OS Version:", String.valueOf(android.os.Build.VERSION.RELEASE), ""));
		mTableInfo.addView(gc.createTitleRow("Screen Information"));
		mTableInfo.addView(gc.createRow("Screen width:", String.valueOf(metrics.heightPixels), ""));
		mTableInfo.addView(gc.createRow("Screen height:", String.valueOf(metrics.widthPixels), ""));
		mTableInfo.addView(gc.createRow("Screen density:", String.valueOf(metrics.density), ""));
		mTableInfo.addView(gc.createRow("Screen DPI:", String.valueOf(metrics.densityDpi), "(" + mResolver.resolveDpi(metrics.densityDpi) + ")"));
		mTableInfo.addView(gc.createRow("Screen Layout:", mResolver.resolveScreenLayout(getResources().getConfiguration().screenLayout), ""));
		mTableInfo.addView(gc.createRow("Screen Orientation:", mResolver.resolveScreenOrientation(getResources().getConfiguration().orientation), ""));
		mTableInfo.addView(gc.createRow("Display Rotation:",getDisplayRotation(display), "(API 8+)"));
		mTableInfo.addView(gc.createRow("Display width :", String.valueOf(display.getWidth()), "(Depracated)"));
		mTableInfo.addView(gc.createRow("Display height:", String.valueOf(display.getHeight()), "(Depracated)"));
		
		// Drawables
		mTableDrawables.addView(gc.createTitleRow("Bucket Test"));
		mTableDrawables.addView(gc.createDrawableRow("Default Bucket:", R.drawable.indicator_default, ""));
		
		mTableDrawables.addView(gc.createDrawableRow("DPI Bucket:", R.drawable.indicator_dpi_no_modifiers, ""));
		mTableDrawables.addView(gc.createDrawableRow("Layout Bucket:", R.drawable.indicator_layout_dpi, ""));
	}



}