package com.steffanbecker.pedidos;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ListView;

import com.steffanbecker.pedidos.adapter.RequestsAdapter;

public class RequestsListActivity extends Activity {

	private ListView mRequestsList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_requests_list);

		mRequestsList = (ListView) findViewById(R.id.listRequests);
		fillRequests();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_requests_list, menu);
		return true;
	}

	public void callAddRequest(View v) {
		startActivity(new Intent(this, RequestsAddActivity.class));
	}

	public void callMain(View v) {
		finish();
	}

	public void fillRequests() {
		mRequestsList.setAdapter(new RequestsAdapter(this));
	}

}
