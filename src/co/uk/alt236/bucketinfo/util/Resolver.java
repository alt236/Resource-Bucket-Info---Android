package co.uk.alt236.bucketinfo.util;

import android.content.Context;
import android.content.res.Configuration;
import android.util.DisplayMetrics;
import co.uk.alt236.bucketinfo.R;

public class Resolver {
	
	private final Context mContext;
	
	public Resolver(Context context){
		this.mContext = context;
	}
	
	public String resolveDpi(int dpi){
		if (dpi >= DisplayMetrics.DENSITY_XHIGH){
			return mContext.getString(R.string.density_xhdpi);
		}
		if (dpi >= DisplayMetrics.DENSITY_HIGH){
			return mContext.getString(R.string.density_hdpi);
		}
		if (dpi >= DisplayMetrics.DENSITY_MEDIUM){
			return mContext.getString(R.string.density_mdpi);
		}
		if (dpi >= DisplayMetrics.DENSITY_LOW){
			return mContext.getString(R.string.density_ldpi);
		}

		return "unspecified";
	}

	public String resolveScreenOrientation(int orientation){
		switch(orientation){
		case Configuration.ORIENTATION_LANDSCAPE:
			return mContext.getString(R.string.orientation_landscape);
		case Configuration.ORIENTATION_PORTRAIT:
			return mContext.getString(R.string.orientation_portrait);
		case Configuration.ORIENTATION_SQUARE:
			return mContext.getString(R.string.orientation_square);
		default:
			return "undefined (" + orientation +")";
		}
	}

	public String resolveScreenLayout(int layout){
		switch(layout & Configuration.SCREENLAYOUT_SIZE_MASK){
		case Configuration.SCREENLAYOUT_SIZE_XLARGE:
			return mContext.getString(R.string.layout_xlarge);
		case Configuration.SCREENLAYOUT_SIZE_LARGE:
			return mContext.getString(R.string.layout_large);
		case Configuration.SCREENLAYOUT_SIZE_NORMAL:
			return mContext.getString(R.string.layout_normal);
		case Configuration.SCREENLAYOUT_SIZE_SMALL:
			return mContext.getString(R.string.layout_small);
		default:
			return "undefined (" + layout +")";
		}
	}
}
