package edu.cgu.ist380b.dailyexpense.db;

import java.util.ArrayList;
import java.util.List;



import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class TransactionDataSource {
	// Database fields
	  private SQLiteDatabase database;
	  private MySQLiteHelper dbHelper;
	  private String[] allColumns = { 
			  MySQLiteHelper.TRANSACTION_COLUMN_ID,
			  MySQLiteHelper.TRANSACTION_COLUMN_ITEM, 
			  MySQLiteHelper.TRANSACTION_COLUMN_TS,
			  MySQLiteHelper.TRANSACTION_COLUMN_AMOUNT,  
			  MySQLiteHelper.TRANSACTION_COLUMN_AMOUNT,  
			  MySQLiteHelper.TRANSACTION_COLUMN_CATEGORY_ID
	  };
	 
	  
	  public TransactionDataSource(Context context) {
		  try{
	    dbHelper = new MySQLiteHelper(context);
		  }
		  catch (Exception e)
		  {
			    Log.e(TransactionDataSource.class.getName(), "Error opening the db "+ e.getMessage());
		  }
	  }
	  
	  public void open() throws SQLException {
		    database = dbHelper.getWritableDatabase();
		    // Enable foreign key constraints
	        if (!database.isReadOnly()) {
	        	database.execSQL("PRAGMA foreign_keys = ON;");
		  }
	  }
	  public void close() {
		    dbHelper.close();
		  }

	 

	  public Transaction createTransac(Transaction transac) {
	    ContentValues values = new ContentValues();
	    values.put(MySQLiteHelper.TRANSACTION_COLUMN_ITEM, transac.getItem());
	    values.put(MySQLiteHelper.TRANSACTION_COLUMN_TS, transac.getTs());
	    values.put(MySQLiteHelper.TRANSACTION_COLUMN_AMOUNT, transac.getAmount());
	    values.put(MySQLiteHelper.TRANSACTION_COLUMN_CATEGORY_ID, transac.getCategoryId());
	    long insertId = database.insert(MySQLiteHelper.TABLE_TRANSACTION, null, values);
	    transac.setId((int)insertId);
	    Log.i(TransactionDataSource.class.getName(), "Record : Transaction with id:" + transac.getId() +" was inserted to the db.");
	    
	    
	    return transac;
	  }

	  public void deleteTransac(Transaction transac) {
	    long id = transac.getId();
	    //System.out.println("Transaction deleted with id: " + id);
	    database.delete(MySQLiteHelper.TABLE_TRANSACTION, MySQLiteHelper.TRANSACTION_COLUMN_ID
	        + " = " + id, null);
	    Log.i(TransactionDataSource.class.getName(), "Record : Transaction with id:" + transac.getId() +" was deleted from the db.");
	  }
	  
	 public List<String> getAllCategoryName(){
		 List<String> categoryList = new ArrayList<String>();
		 String selectQuery = "SELECT  A.categoryName " +
		 		"FROM " + MySQLiteHelper.TABLE_CATEGORY + " AS A, " + MySQLiteHelper.TABLE_TRANSACTION + " AS B"
		 		+ "WHERE A._id = B.categoryId GROUP BY A.id";
		 
		 Cursor cursor = database.rawQuery(selectQuery, null);
		 // looping through all rows and adding to list
		 cursor.moveToFirst();
			while (!cursor.isAfterLast()) {
		    	
		    	categoryList.add(cursor.getString(0));
		      cursor.moveToNext();
		    }
			cursor.close();
		    return categoryList;
		 
	 }
	  
	  public List<Transaction> getAllTranByDate(String date) {
		  List<Transaction> transacsList = new ArrayList<Transaction>();
	 
			Cursor cursor = database.query(MySQLiteHelper.TABLE_TRANSACTION,
		        allColumns, MySQLiteHelper.TRANSACTION_COLUMN_TS +"='"+date+"'", null, null, null, null); 
			cursor.moveToFirst();
			while (!cursor.isAfterLast()) {
		    	Transaction transac = cursorToTransac(cursor);
		    	transacsList.add(transac);
		      cursor.moveToNext();
		    }
			cursor.close();
		    return transacsList;
		  }
	 	  
	  public List<Transaction> getAllTransacs() {
	    List<Transaction> transacsList = new ArrayList<Transaction>();

	    Cursor cursor = database.query(MySQLiteHelper.TABLE_TRANSACTION,
	        allColumns, null, null, null, null, null);

	    cursor.moveToFirst();
	    while (!cursor.isAfterLast()) {
	    	Transaction transac = cursorToTransac(cursor);
	    	transacsList.add(transac);
	      cursor.moveToNext();
	    }
	    // Make sure to close the cursor
	    cursor.close();
	    return transacsList;
	  }
	  
	  private Transaction cursorToTransac(Cursor cursor) {
		Transaction transac = new Transaction();
		 long id =  cursor.getLong(cursor.getColumnIndexOrThrow(MySQLiteHelper.TRANSACTION_COLUMN_ID));
		 String item =cursor.getString(cursor.getColumnIndexOrThrow(MySQLiteHelper.TRANSACTION_COLUMN_ITEM));
		 String ts = cursor.getString(cursor.getColumnIndexOrThrow(MySQLiteHelper.TRANSACTION_COLUMN_TS));
		 Float amount = cursor.getFloat(cursor.getColumnIndexOrThrow(MySQLiteHelper.TRANSACTION_COLUMN_AMOUNT));
		 long categoryId = cursor.getLong(cursor.getColumnIndexOrThrow(MySQLiteHelper.TRANSACTION_COLUMN_CATEGORY_ID));
		 
		transac.setId((int)id);
	    transac.setItem(item);
	    transac.setTs(ts);
	    transac.setAmount(amount);
	    transac.setCategoryId((int)categoryId);
	    return transac;
	  }
}
