package com.steffanbecker.pedidos;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.steffanbecker.pedidos.database.orm.Categories;
import com.steffanbecker.pedidos.database.orm.DaoSession;
import com.steffanbecker.pedidos.database.orm.helper.DataBaseHelper;

public class CategoriesAddActivity extends Activity {

	private EditText mName;
	private CheckBox mActive;
	private long mCatId = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_categories_add);

		mName = (EditText) findViewById(R.id.etNome);
		mActive = (CheckBox) findViewById(R.id.rbActive);

		Bundle extras = getIntent().getExtras();
		if (extras != null) {
			mCatId = extras.getLong("id");
			mName.setText(extras.getString("name"));
			mActive.setChecked((extras.getInt("active") == 1) ? true : false);
			mName.setSelection(mName.getText().toString().length());
			DataBaseHelper.closeDb();
			this.setTitle("Editar Categoria");
		} else {
			this.setTitle("Adicionar Categoria");
		}

	}

	public void callSaveAction(View v) {

		DaoSession session = DataBaseHelper.getSession(this);

		if (mCatId > 0) {
			int active = (mActive.isChecked()) ? 1 : 0;

			Categories c = session.getCategoriesDao().load(mCatId);

			c.setCategory_active(active);
			c.setCategory_name(mName.getText().toString());
			session.getCategoriesDao().update(c);

			DataBaseHelper.closeDb();

			Toast.makeText(this, "Categoria editada com sucecsso",
					Toast.LENGTH_LONG).show();
		} else {
			int active = (mActive.isChecked()) ? 1 : 0;

			session.getCategoriesDao().insert(
					new Categories(null, mName.getText().toString(), active));

			DataBaseHelper.closeDb();

			Toast.makeText(this, "Categoria adicionada com sucecsso",
					Toast.LENGTH_LONG).show();
		}

		finish();
	}

}
