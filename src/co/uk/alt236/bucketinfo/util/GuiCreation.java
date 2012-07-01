package co.uk.alt236.bucketinfo.util;

import co.uk.alt236.bucketinfo.R;
import co.uk.alt236.bucketinfo.R.color;
import co.uk.alt236.bucketinfo.R.id;
import co.uk.alt236.bucketinfo.R.layout;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TableRow;
import android.widget.TextView;

public class GuiCreation {
	private final String TAG =  this.getClass().getName();
	private View.OnClickListener textViewCopyClickListener; 
	private int tableIdOffset = 1;
	private final Context c;
	private final UsefulBits uB;
	private final LayoutInflater inflater;

	public GuiCreation(final Context c) {
		super();

		this.c = c;
		inflater = (LayoutInflater)c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		this.uB = new UsefulBits(c);

	}

	public TableRow createRow(String column1, String column2, String column3) {

		TableRow tr = (TableRow) inflater.inflate(R.layout.table_row_data, null);

		TextView title = (TextView) tr.findViewById(R.id.title);
		TextView data1 = (TextView) tr.findViewById(R.id.data1);
		TextView data2 = (TextView) tr.findViewById(R.id.data2);
		int color = Color.TRANSPARENT;

		if (tableIdOffset % 2 == 1) {color = c.getResources().getColor(R.color.alt_row_color);}

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



	public void resetOffset(){
		tableIdOffset = 1;
	}
}
