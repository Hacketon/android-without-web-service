package com.steffanbecker.pedidos;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;
import android.widget.Toast;

import com.steffanbecker.pedidos.adapter.TableNumbersAdapter;
import com.steffanbecker.pedidos.database.orm.DaoSession;
import com.steffanbecker.pedidos.database.orm.Requests;
import com.steffanbecker.pedidos.database.orm.helper.DataBaseHelper;
import com.steffanbecker.requests.RequestsStates;

public class RequestsAddActivity extends Activity {

	private Spinner tablesNumbers;
	private int mRequestId = 0;
	private DaoSession mSession;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_requests_add);

		tablesNumbers = (Spinner) findViewById(R.id.selectTables);
		tablesNumbers.setAdapter(new TableNumbersAdapter(this));
		tablesNumbers.getSelectedItem();

		Bundle extras = getIntent().getExtras();
		if (extras != null) {

		}
	}

	public void callSaveAction(View v) {
		if (mRequestId == 0) {
			mSession = DataBaseHelper.getSession(this);
			mSession.getRequestsDao().insert(
					new Requests(null, (int) tablesNumbers.getSelectedItemId(),
							RequestsStates.NEW, 0.0));
			Toast.makeText(this, "Pedido adicionado com sucesso",
					Toast.LENGTH_LONG).show();
		} else {

		}
		DataBaseHelper.closeDb();
		finish();
	}

}
