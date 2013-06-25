package edu.cgu.ist380b.dailyexpense.db;




import java.util.ArrayList;
import java.util.List;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;


public class CategoryDataSource {
	private SQLiteDatabase database;
	private MySQLiteHelper dbHelper;
	private String[] allColumns= {
			MySQLiteHelper.CATEGORY_COLUMN_ID,
			MySQLiteHelper.CATEGORY_COLUMN_CATEGORY_NAME
	};
	
	public CategoryDataSource(Context context){
		try{
			dbHelper = new MySQLiteHelper(context);
		}
		catch (Exception e)
		{
			Log.e(CategoryDataSource.class.getName(), "Error opening the DB "+ e.getMessage());
		}
	}
	
	public void open() throws SQLException{
		database = dbHelper.getWritableDatabase();
        // Enable foreign key constraints
        if (!database.isReadOnly()) {
        	database.execSQL("PRAGMA foreign_keys = ON;");
        }
		
	}
	
	public void close()
	{
		dbHelper.close();
	}
	
	public Category createCategory(Category category)
	{
		ContentValues values = new ContentValues();
		
		values.put(MySQLiteHelper.CATEGORY_COLUMN_CATEGORY_NAME, category.getCategoryName());
				
		long insertedId= database.insert(MySQLiteHelper.TABLE_CATEGORY, null, values);
		category.setId((int) insertedId);
		Log.i(CategoryDataSource.class.getName(), "Record: Condition with id: "+category.getId()+" was inserted to the DB.");
		return category;
	}
	
	public void deleteCategory(Category category){
		long id=category.getId();
	    database.delete(MySQLiteHelper.TABLE_CATEGORY, MySQLiteHelper.CATEGORY_COLUMN_ID
		        + " = " + id, null);
		    Log.i(CategoryDataSource.class.getName(), "Record : Condition with id:" + category.getId() +" was deleted from the DB.");		
	}
	
	
	public List<Category> getAllCategories() {
	    List<Category> categoryList = new ArrayList<Category>();
 
    Cursor cursor = database.query(MySQLiteHelper.TABLE_CATEGORY,
	        allColumns, null, null, null, null, null);
 
	cursor.moveToFirst();
	    while (!cursor.isAfterLast()) {
	    Category category = cursorToCategory(cursor);
	      categoryList.add(category);
	      cursor.moveToNext();
	    }
	    // Make sure to close the cursor
	    cursor.close();
	    return categoryList;
	  }
	public int getIdByCategoryName(String categoryName){
		int categoryId;
		 
		Cursor cursor = database.query(MySQLiteHelper.TABLE_CATEGORY,
				allColumns, MySQLiteHelper.CATEGORY_COLUMN_CATEGORY_NAME+"='"+categoryName+"'", null, null, null, null); 
		
		cursor.moveToFirst();
		Category category = cursorToCategory(cursor);
		categoryId = category.getId();
	    cursor.close();
	    return categoryId; 
	}
	
	public String getCategoryNameById(int id){
		String categoryName;
		 
		Cursor cursor = database.query(MySQLiteHelper.TABLE_CATEGORY,
				allColumns, MySQLiteHelper.CATEGORY_COLUMN_ID+"='"+id+"'", null, null, null, null); 
		cursor.moveToFirst();
		Category category = cursorToCategory(cursor);
		
		categoryName = category.getCategoryName();
	    cursor.close();
	    return categoryName; 
	}
	public List<String> getAllLabels(){
	        List<String> labels = new ArrayList<String>();
	         
	         
	        Cursor cursor = database.query(MySQLiteHelper.TABLE_CATEGORY,
	    	        allColumns, null, null, null, null, null);
	        // looping through all rows and adding to list
	        cursor.moveToFirst();
		    while (!cursor.isAfterLast()) {
		    Category category = cursorToCategory(cursor);
		      labels.add(category.getCategoryName());
		      cursor.moveToNext();
		    }
	         
	        // closing connection
	        cursor.close();
	               
	        // returning labels
	        return labels;
	    }	
	
	
	private Category cursorToCategory(Cursor cursor) {
		Category category = new Category();
	    // get the values from the cursor 
	    long id =  cursor.getLong(cursor.getColumnIndexOrThrow(MySQLiteHelper.CATEGORY_COLUMN_ID));
	    String categoryName = cursor.getString(cursor.getColumnIndexOrThrow(MySQLiteHelper.CATEGORY_COLUMN_CATEGORY_NAME));
	    category.setId((int) id);
	    category.setCategoryName(categoryName);
	     return category;
	  }
	
}
