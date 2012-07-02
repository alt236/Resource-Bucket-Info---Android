package co.uk.alt236.bucketinfo.util;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TableRow;
import android.widget.TextView;
import co.uk.alt236.bucketinfo.R;

public class GuiCreation {
	private final String TAG =  this.getClass().getName();
	private View.OnClickListener textViewCopyClickListener; 
	private int tableIdOffset = 1;
	private final Context c;
	private final LayoutInflater inflater;
	private final UsefulBits mUsefullBits;
	
	public GuiCreation(final Context c) {
		super();
		this.c = c;
		inflater = (LayoutInflater)c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		this.mUsefullBits = new UsefulBits(this.c); 
	}

	public TableRow createTitleRow(String column1) {
		TableRow tr = (TableRow) inflater.inflate(R.layout.table_row_header, null);

		TextView title = (TextView) tr.findViewById(R.id.title);
		title.setText(column1);

		return tr;
	}
	
	public TableRow createRow(String column1, String column2, String column3) {

		TableRow tr = (TableRow) inflater.inflate(R.layout.table_row_data, null);

		TextView title = (TextView) tr.findViewById(R.id.title);
		TextView data1 = (TextView) tr.findViewById(R.id.data1);
		TextView data2 = (TextView) tr.findViewById(R.id.data2);
		int color = c.getResources().getColor(R.color.table_row_color);

		if (tableIdOffset % 2 == 1) {color = c.getResources().getColor(R.color.table_row_alt_color);}

		title.setText(column1);
		data1.setTag(column2);
		data1.setClickable(true);
		data1.setOnClickListener(textViewCopyClickListener);
		data1.setText(column2);

		data2.setTag(column3);
		data2.setClickable(true);
		data2.setOnClickListener(textViewCopyClickListener);
		data2.setText(column3);
		
		tr.setBackgroundColor(color);
		tableIdOffset += 1;
		return tr;
	}


	public TableRow createDrawableRow(String column1, int drawableId, String column3) {

		TableRow tr = (TableRow) inflater.inflate(R.layout.table_row_image, null);

		TextView title = (TextView) tr.findViewById(R.id.title);
		ImageView data1 = (ImageView) tr.findViewById(R.id.data1);
		TextView data2 = (TextView) tr.findViewById(R.id.data2);
		
		int color = c.getResources().getColor(R.color.table_row_color);

		if (tableIdOffset % 2 == 1) {color = c.getResources().getColor(R.color.table_row_alt_color);}

		title.setText(column1);

		try{
			Drawable d = c.getResources().getDrawable(drawableId);
			data1.setImageDrawable(d);
		} catch (Exception e){
			Log.w(TAG, "^ createDrawableRow() Error: " + e);
			e.printStackTrace();
			mUsefullBits.showToast("Error getting " + column1 + " drawable: " + e.getMessage());
		}
		
		data2.setTag(column3);
		data2.setClickable(true);
		data2.setOnClickListener(textViewCopyClickListener);
		data2.setText(column3);
		
		tr.setBackgroundColor(color);
		tableIdOffset += 1;
		return tr;
	}

	public void resetOffset(){
		tableIdOffset = 1;
	}
}
