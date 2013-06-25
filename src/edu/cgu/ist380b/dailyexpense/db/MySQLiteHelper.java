package edu.cgu.ist380b.dailyexpense.db;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MySQLiteHelper extends SQLiteOpenHelper {
	
	private static final String DATABASE_NAME = "dailyexpense.db";
	private static final int DATABASE_VERSION = 5;
	
	public static final String TABLE_TRANSACTION = "transac";
	public static final String TRANSACTION_COLUMN_ID = "_id";
	public static final String TRANSACTION_COLUMN_ITEM = "item";
	public static final String TRANSACTION_COLUMN_TS = "timestamp";
	public static final String TRANSACTION_COLUMN_AMOUNT = "amount";
	public static final String TRANSACTION_COLUMN_CATEGORY_ID = "categoryId";
	
	public static final String TABLE_CATEGORY = "itemCategory";
	public static final String CATEGORY_COLUMN_ID = "_id";
	public static final String CATEGORY_COLUMN_CATEGORY_NAME = "categoryName";
	
	
	private static final String DATABASE_CREATE_TABLE_TRANSACTION = "create table " + TABLE_TRANSACTION
			+ "("
			+ TRANSACTION_COLUMN_ID + " integer primary key autoincrement, "
			+ TRANSACTION_COLUMN_ITEM + " text null,"
			+ TRANSACTION_COLUMN_TS + " text not null,"
			+ TRANSACTION_COLUMN_AMOUNT + " real not null,"
			+ TRANSACTION_COLUMN_CATEGORY_ID + " integer not null,"
			+ "FOREIGN KEY(" + TRANSACTION_COLUMN_CATEGORY_ID + ") REFERENCES " 
				+ TABLE_CATEGORY + "(" + CATEGORY_COLUMN_ID + ") ON DELETE CASCADE"
			+ ")";
	
	private static final String DATABASE_CREATE_TABLE_CATEGORY = "create table " + TABLE_CATEGORY
			+ "("
			+ CATEGORY_COLUMN_ID + " integer primary key autoincrement, "
			+ CATEGORY_COLUMN_CATEGORY_NAME + " text not null"
			+ ")";
	
	
	public MySQLiteHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}	
	
	@Override
	public void onCreate(SQLiteDatabase arg0) {
		arg0.execSQL(DATABASE_CREATE_TABLE_TRANSACTION);
		arg0.execSQL(DATABASE_CREATE_TABLE_CATEGORY);
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		arg0.execSQL("DROP TABLE IF EXISTS " + TABLE_TRANSACTION);
		arg0.execSQL("DROP TABLE IF EXISTS " + TABLE_CATEGORY);
	    onCreate(arg0);
	    Log.w(MySQLiteHelper.class.getName(),
			        "Upgrading database from version " + arg1 + " to "
			            + arg2 + ", which will destroy all old data");   	
	}
}
