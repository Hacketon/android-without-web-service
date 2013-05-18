package com.steffanbecker.pedidos.adapter;

import java.text.NumberFormat;
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

import com.steffanbecker.pedidos.ItensAddActivity;
import com.steffanbecker.pedidos.ItensListActivity;
import com.steffanbecker.pedidos.R;
import com.steffanbecker.pedidos.database.orm.DaoSession;
import com.steffanbecker.pedidos.database.orm.Itens;
import com.steffanbecker.pedidos.database.orm.ItensDao.Properties;
import com.steffanbecker.pedidos.database.orm.helper.DataBaseHelper;

public class ItensAdapter extends BaseAdapter {

	private List<Itens> mItens;
	private ItensListActivity mActivity;
	private DaoSession mSession;
	private long mCatId;
	private boolean mActionView;

	public ItensAdapter(ItensListActivity activity, long catId,
			boolean actionView) {
		this.mActivity = activity;
		this.mSession = DataBaseHelper.getSession(activity);
		this.mItens = this.mSession.getItensDao().queryBuilder()
				.where(Properties.Item_category_id.eq(catId)).list();
		this.mCatId = catId;
		this.mActionView = actionView;
		DataBaseHelper.closeDb();
	}

	@Override
	public int getCount() {
		return this.mItens.size();
	}

	@Override
	public Itens getItem(int pos) {
		return this.mItens.get(pos);
	}

	@Override
	public long getItemId(int pos) {
		return this.getItem(pos).getItem_id();
	}

	@Override
	public View getView(int pos, View v, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) mActivity
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		View rowView = inflater.inflate(R.layout.row_itens, parent, false);

		final String catName = mItens.get(pos).getItem_name();

		TextView name = (TextView) rowView.findViewById(R.id.tv_item_name);
		name.setText(catName);

		TextView value = (TextView) rowView.findViewById(R.id.tv_item_value);

		NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance();
		value.setText(String.valueOf(currencyFormatter.format(mItens.get(pos)
				.getItem_value())));

		final long idItem = Long.parseLong(mItens.get(pos).getItem_id()
				.toString());

		rowView.setId((int) idItem);

		if (mActionView) {
			rowView.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					mActivity.startActivity(new Intent(mActivity
							.getApplicationContext(), ItensListActivity.class)
							.putExtra("id", idItem).putExtra("name", catName));
				}
			});
		} else {
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
							Itens itens = mSession.getItensDao().load(idItem);
							DataBaseHelper.closeDb();
							Intent i = new Intent(mActivity,
									ItensAddActivity.class);
							i.putExtra("id", mCatId);
							i.putExtra("itemId", itens.getItem_id());
							i.putExtra("name", itens.getItem_name());
							i.putExtra("value", itens.getItem_value());
							i.putExtra("path", itens.getItem_image());
							mActivity.startActivity(i);
							return false;
						}
					});

					delete.setOnMenuItemClickListener(new OnMenuItemClickListener() {

						@Override
						public boolean onMenuItemClick(MenuItem item) {
							new AlertDialog.Builder(mActivity)
									.setMessage(
											"Deseja realmente deletar esse item?")
									.setNeutralButton("Cancelar", null)
									.setPositiveButton(
											"Ok",
											new DialogInterface.OnClickListener() {

												@Override
												public void onClick(
														DialogInterface dialog,
														int which) {
													mSession = DataBaseHelper
															.getSession(mActivity);
													mSession.getItensDao()
															.delete(new Itens(
																	idItem));
													DataBaseHelper.closeDb();
													mActivity.fillItens();
												}
											}).show();
							return true;
						}
					});

				}
			});
		}

		return rowView;
	}

}
