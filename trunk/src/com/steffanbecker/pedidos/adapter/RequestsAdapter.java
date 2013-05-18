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

import com.steffanbecker.pedidos.ItensListActivity;
import com.steffanbecker.pedidos.R;
import com.steffanbecker.pedidos.RequestsListActivity;
import com.steffanbecker.pedidos.database.orm.DaoSession;
import com.steffanbecker.pedidos.database.orm.Requests;
import com.steffanbecker.pedidos.database.orm.helper.DataBaseHelper;

public class RequestsAdapter extends BaseAdapter {

	private List<Requests> mRequests;
	private RequestsListActivity mActivity;
	private DaoSession mSession;

	public RequestsAdapter(RequestsListActivity activity) {

		this.mActivity = activity;
		this.mSession = DataBaseHelper.getSession(activity);
		this.mRequests = this.mSession.getRequestsDao().loadAll();
		DataBaseHelper.closeDb();
	}

	@Override
	public int getCount() {
		return this.mRequests.size();
	}

	@Override
	public Requests getItem(int pos) {
		return mRequests.get(pos);
	}

	@Override
	public long getItemId(int pos) {
		return this.getItem(pos).getRequest_id();
	}

	@Override
	public View getView(int pos, View v, ViewGroup parent) {

		LayoutInflater inflater = (LayoutInflater) mActivity
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		View rowView = inflater.inflate(R.layout.row_categories, parent, false);
		final String catName = String.valueOf(mRequests.get(pos)
				.getRequest_table_number());

		TextView name_category = (TextView) rowView
				.findViewById(R.id.tv_category_name);
		name_category.setText("Mesa "+catName);

		final long idCat = Long.parseLong(mRequests.get(pos).getRequest_id()
				.toString());

		rowView.setId((int) idCat);

		rowView.setOnCreateContextMenuListener(new OnCreateContextMenuListener() {

			@Override
			public void onCreateContextMenu(ContextMenu menu, View v,
					ContextMenuInfo menuInfo) {

				MenuInflater menuInflater = ((Activity) mActivity)
						.getMenuInflater();
				menuInflater.inflate(R.menu.categories_menu_list, menu);

				MenuItem delete = menu.findItem(R.id.removeCategory);

				final long reqId = v.getId();
				
				delete.setOnMenuItemClickListener(new OnMenuItemClickListener() {

					@Override
					public boolean onMenuItemClick(MenuItem item) {
						new AlertDialog.Builder(mActivity)
								.setMessage(
										"Deseja realmente finalizar esse pedido?")
								.setNeutralButton("Cancelar", null)
								.setPositiveButton("Ok",
										new DialogInterface.OnClickListener() {

											@Override
											public void onClick(
													DialogInterface dialog,
													int which) {
												mSession = DataBaseHelper
														.getSession(mActivity);
												mSession.getRequestsDao()
														.delete(new Requests(
																reqId));
												DataBaseHelper.closeDb();
												mActivity.fillRequests();
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
