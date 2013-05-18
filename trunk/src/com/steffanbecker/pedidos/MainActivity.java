package com.steffanbecker.pedidos;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	public void callCategories(View v) {
		startActivity(new Intent(this, CategoriesListActivity.class));
	}

	public void callRequests(View v) {
		startActivity(new Intent(this, RequestsListActivity.class));
	}
}
