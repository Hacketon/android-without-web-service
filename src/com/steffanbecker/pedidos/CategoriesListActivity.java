package com.steffanbecker.pedidos;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.steffanbecker.pedidos.adapter.CategoriesAdapter;

public class CategoriesListActivity extends Activity {

	private ListView mCategoriesList;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_categories_list);
		fillCategories();
	}
	
	public void fillCategories(){
		mCategoriesList = (ListView) findViewById(R.id.listCategories);
		mCategoriesList.setAdapter(new CategoriesAdapter(this));
	}
	
	@Override
	protected void onResume() {
		fillCategories();
		super.onResume();
	}
	
	public void callAddCategories(View v){
		startActivity(new Intent(this, CategoriesAddActivity.class));
	}
	
	public void callMain(View v){
		finish();
	}

}
