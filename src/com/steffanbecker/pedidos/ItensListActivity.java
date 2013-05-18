package com.steffanbecker.pedidos;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.steffanbecker.pedidos.adapter.ItensAdapter;

public class ItensListActivity extends Activity {

	private ListView mListItens;
	private long mCatId;
	private TextView mCategory;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_itens_list);
		mListItens = (ListView) findViewById(R.id.listItens);
		mCategory = (TextView) findViewById(R.id.categoryName);
		if (mListItens.getChildCount() > 0) {
			mListItens.removeViews(0, mListItens.getChildCount() - 1);
		}

		Bundle extras = getIntent().getExtras();
		if (extras != null) {
			mCatId = extras.getLong("id");
			mCategory.setText(extras.getString("name"));
			fillItens();
		} else {
			finish();
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_itens_list, menu);
		return true;
	}

	@Override
	protected void onResume() {
		fillItens();
		super.onResume();
	}
	
	public void callMain(View v) {
		finish();
	}

	public void callAddItem(View v) {
		startActivity(new Intent(this, ItensAddActivity.class).putExtra("id",
				mCatId));
	}

	public void fillItens() {
		mListItens.setAdapter(new ItensAdapter(this, mCatId, false));
	}
}
