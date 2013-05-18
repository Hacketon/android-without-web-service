package com.steffanbecker.pedidos;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.steffanbecker.pedidos.database.orm.DaoSession;
import com.steffanbecker.pedidos.database.orm.Itens;
import com.steffanbecker.pedidos.database.orm.helper.DataBaseHelper;

public class ItensAddActivity extends Activity {

	private long mCatId;
	private EditText mNameItem;
	private EditText mValueItem;
	private ImageView mImageItem;
	private long mItemId;
	private String mImagePath;
	
	private static final int IMAGE_SELECT = 1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_itens_add);

		Bundle extras = getIntent().getExtras();
		if (extras != null) {
			mCatId = extras.getLong("id");
			mNameItem = (EditText) findViewById(R.id.etNome);
			mNameItem.setSingleLine();
			mValueItem = (EditText) findViewById(R.id.etValue);
			mImageItem = (ImageView) findViewById(R.id.imageItem);
			mImageItem.setOnClickListener(this.onSelectImage);
			
			
			mItemId = extras.getLong("itemId");

			if (mItemId != 0) {
				mNameItem.setText(extras.getString("name"));
				mValueItem.setText(String.valueOf(extras.getDouble("value")));
				mImagePath = extras.getString("path");
				mImageItem.setImageBitmap(BitmapFactory.decodeFile(mImagePath));
				this.setTitle(R.string.title_activity_itens_edit);
			}

		} else {
			finish();
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_itens_add, menu);
		return true;
	}

	OnClickListener onSelectImage = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
			photoPickerIntent.setType("image/*");
			startActivityForResult(photoPickerIntent, IMAGE_SELECT); 
		}
	};
	
	public void callSaveAction(View v) {
		DaoSession session = DataBaseHelper.getSession(this
				.getApplicationContext());
		if (mItemId != 0) {
			session.getItensDao()
					.update(new Itens(mItemId, mCatId, mNameItem.getText()
							.toString(), Double.parseDouble(mValueItem
							.getText().toString()), mImagePath));
			Toast.makeText(this, "Item editado com sucesso", Toast.LENGTH_LONG)
					.show();
		} else {
			session.getItensDao()
					.insert(new Itens(null, mCatId, mNameItem.getText()
							.toString(), Double.parseDouble(mValueItem
							.getText().toString()), mImagePath));
			Toast.makeText(this, "Item adicionado com sucesso",
					Toast.LENGTH_LONG).show();
		}
		DataBaseHelper.closeDb();
		finish();
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if(resultCode == RESULT_OK){
			if(requestCode == IMAGE_SELECT){
				try{
					Uri selectedImage = data.getData();
		            InputStream imageStream = getContentResolver().openInputStream(selectedImage);
		            Bitmap image = BitmapFactory.decodeStream(imageStream);
		            
		            String filename = Environment.getExternalStorageDirectory()+"/SistemaDePedidos/";
		            File f = new File(filename);
		            if(!f.exists()){
		            	f.mkdirs();
		            }
		            filename = filename+System.currentTimeMillis()+".jpg";
		            FileOutputStream out = new FileOutputStream(filename);
		            image.compress(Bitmap.CompressFormat.JPEG, 90, out);
		            mImageItem.setImageBitmap(image);
		            this.mImagePath = filename;
		            out.flush();
		            out.close();
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		}
	}
}
