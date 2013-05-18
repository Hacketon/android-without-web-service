package com.steffanbecker.pedidos.database.orm.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.steffanbecker.pedidos.database.orm.DaoMaster;
import com.steffanbecker.pedidos.database.orm.DaoMaster.DevOpenHelper;
import com.steffanbecker.pedidos.database.orm.DaoSession;

public class DataBaseHelper {
	private static SQLiteDatabase sDb;
	private static DaoMaster sDaoMaster;

	public static DaoSession getSession(Context context) {
		DevOpenHelper helper = new DaoMaster.DevOpenHelper(context, "requests-db",
				null);
		sDb = helper.getWritableDatabase();
		sDaoMaster = new DaoMaster(sDb);
		return sDaoMaster.newSession();
	}

	public static void closeDb() {
		sDb.close();
	}
}
