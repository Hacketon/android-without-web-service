package com.steffanbecker.pedidos.adapter;

import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.View;
import android.view.View.OnCreateContextMenuListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.steffanbecker.pedidos.CategoriesAddActivity;
import com.steffanbecker.pedidos.CategoriesListActivity;
import com.steffanbecker.pedidos.ItensListActivity;
import com.steffanbecker.pedidos.R;
import com.steffanbecker.pedidos.database.orm.Categories;
import com.steffanbecker.pedidos.database.orm.DaoSession;
import com.steffanbecker.pedidos.database.orm.helper.DataBaseHelper;

public class CategoriesAdapter extends BaseAdapter {

	private List<Categories> mCategories;
	private CategoriesListActivity mActivity;
	private DaoSession mSession;

	public CategoriesAdapter(CategoriesListActivity activity) {

		this.mActivity = activity;
		this.mSession = DataBaseHelper.getSession(activity);
		this.mCategories = this.mSession.getCategoriesDao().loadAll();
		DataBaseHelper.closeDb();
	}

	@Override
	public int getCount() {
		return this.mCategories.size();
	}

	@Override
	public Categories getItem(int pos) {
		return this.mCategories.get(pos);
	}

	@Override
	public long getItemId(int pos) {
		return this.mCategories.get(pos).getCategory_id();
	}

	@Override
	public View getView(int pos, View v, ViewGroup parent) {

		LayoutInflater inflater = (LayoutInflater) mActivity
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		View rowView = inflater.inflate(R.layout.row_categories, parent, false);
		final String catName = mCategories.get(pos).getCategory_name();

		TextView name_category = (TextView) rowView
				.findViewById(R.id.tv_category_name);
		name_category.setText(catName);

		final long idCat = Long.parseLong(mCategories.get(pos).getCategory_id()
				.toString());

		rowView.setId((int) idCat);

		rowView.setOnCreateContextMenuListener(new OnCreateContextMenuListener() {

			@Override
			public void onCreateContextMenu(ContextMenu menu, View v,
					ContextMenuInfo menuInfo) {

				MenuInflater menuInflater = ((Activity) mActivity)
						.getMenuInflater();
				menuInflater.inflate(R.menu.categories_menu_list, menu);

				MenuItem edit = menu.findItem(R.id.editCategory);
				MenuItem delete = menu.findItem(R.id.removeCategory);

				edit.setOnMenuItemClickListener(new OnMenuItemClickListener() {

					@Override
					public boolean onMenuItemClick(MenuItem item) {
						mSession = DataBaseHelper.getSession(mActivity);
						Categories cats = mSession.getCategoriesDao().load(
								idCat);
						DataBaseHelper.closeDb();
						Intent i = new Intent(mActivity,
								CategoriesAddActivity.class);
						i.putExtra("id", cats.getCategory_id());
						i.putExtra("name", cats.getCategory_name());
						i.putExtra("active", cats.getCategory_active());
						mActivity.startActivity(i);
						return false;
					}
				});

				delete.setOnMenuItemClickListener(new OnMenuItemClickListener() {

					@Override
					public boolean onMenuItemClick(MenuItem item) {
						new AlertDialog.Builder(mActivity)
								.setMessage(
										"Deseja realmente deletar essa categoria?")
								.setNeutralButton("Cancelar", null)
								.setPositiveButton("Ok",
										new DialogInterface.OnClickListener() {

											@Override
											public void onClick(
													DialogInterface dialog,
													int which) {
												mSession = DataBaseHelper
														.getSession(mActivity);
												mSession.getCategoriesDao()
														.delete(new Categories(
																idCat));
												DataBaseHelper.closeDb();
												mActivity.fillCategories();
											}
										}).show();
						return true;
					}
				});

			}
		});

		rowView.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				mActivity.startActivity(new Intent(mActivity
						.getApplicationContext(), ItensListActivity.class)
						.putExtra("id", idCat).putExtra("name", catName));
			}
		});

		return rowView;
	}

}
