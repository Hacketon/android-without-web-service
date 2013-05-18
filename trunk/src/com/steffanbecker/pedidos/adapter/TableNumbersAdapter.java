package com.steffanbecker.pedidos.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.steffanbecker.pedidos.RequestsAddActivity;

public class TableNumbersAdapter extends BaseAdapter {

	private RequestsAddActivity mActivity;
	private int[] tablesNumbers;

	public TableNumbersAdapter(RequestsAddActivity activity){
		mActivity = activity;
		int nOfTables = 15;
		tablesNumbers = new int[15]; 
		for(int i = 1;i<=nOfTables;i++){
			tablesNumbers[i-1] = i; 
		}
	}
	
	@Override
	public int getCount() {
		return tablesNumbers.length;
	}

	@Override
	public Integer getItem(int position) {
		return tablesNumbers[position];
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		RelativeLayout rl = new RelativeLayout(mActivity);
		LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		rl.setLayoutParams(lp);
		
		TextView tv = new TextView(mActivity);
		tv.setPadding(5, 10, 0, 10);
		tv.setTextSize(15);
		tv.setLayoutParams(lp);
		tv.setText("Mesa "+String.valueOf(tablesNumbers[position]));
		rl.addView(tv);
		
		return rl;
	}

}
