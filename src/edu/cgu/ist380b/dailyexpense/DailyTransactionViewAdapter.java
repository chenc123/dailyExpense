package edu.cgu.ist380b.dailyexpense;

import java.util.List;

import edu.cgu.ist380b.dailyexpense.db.CategoryDataSource;
import edu.cgu.ist380b.dailyexpense.db.Transaction;
import edu.cgu.ist380b.dailyexpense.db.TransactionDataSource;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

public class DailyTransactionViewAdapter extends BaseAdapter{
	private ImageButton clickedImageButton;
	private static List<Transaction> transactionList;
	private Context context;
	private String date;
	
	private LayoutInflater mInflater;
	
	public DailyTransactionViewAdapter(Context context, List<Transaction> results,String date) {
		 transactionList = results;
		 
		 mInflater = LayoutInflater.from(context);
		 this.context=context;
		 this.date=date;
	}
	 
	 public int getCount() {
		  return transactionList.size();
		  
	}
	 
	 public Object getItem(int position) {
		  return transactionList.get(position);
	 }

	 public long getItemId(int position) {
		  return position;
	 }
	 
	 public View getView(int position, View convertView, ViewGroup parent) {
		  
		 ViewHolder holder;
		  if (convertView == null) {
		   convertView = mInflater.inflate(R.layout.transaction_item_view, null);
		   holder = new ViewHolder();
		   holder.cCategory = (TextView) convertView.findViewById(R.id.cCategoryTextView);
		   holder.cItem = (TextView) convertView.findViewById(R.id.cItemTextView);
		   holder.cAmount = (TextView) convertView.findViewById(R.id.cAmountView);
		   holder.imgBtnDeleteTransac = (ImageButton) convertView.findViewById(R.id.deleteTransacBtn);

		   convertView.setTag(holder);
		  } else {
		   holder = (ViewHolder) convertView.getTag();
		  }
		 
		  String name;
		  CategoryDataSource ds = new  CategoryDataSource(context);
		  ds.open();
		  name = ds.getCategoryNameById(transactionList.get(position).getCategoryId());
		  ds.close();
		  holder.cCategory.setText(name);
		  // holder.cCategory.setText(Integer.toString(transactionList.get(position).getCategoryId()));
		  holder.cItem.setText(transactionList.get(position).getItem());
		  holder.cAmount.setText(" $ " + transactionList.get(position).getAmount().toString());
		  holder.imgBtnDeleteTransac.setTag(transactionList.get(position).getId());
		  
		 
		  holder.imgBtnDeleteTransac.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				clickedImageButton=(ImageButton)v;
				Transaction transacDelete = new Transaction();
				transacDelete.setId(Integer.parseInt(clickedImageButton.getTag().toString()));
				TransactionDataSource transacDS=new TransactionDataSource(context);
				transacDS.open();
				transacDS.deleteTransac(transacDelete);
				transacDS.close();
				
				
				//Intent intent=new Intent();
				//intent.setClassName(context, "edu.cgu.ist380b.dailyexpense.MainActivity");
				//intent.putExtra("personID", personId);
				//context.startActivity(intent);		
			}
		});

		  return convertView;
		 }
	 
	 static class ViewHolder {
		  TextView cCategory;
		  TextView cItem;
		  TextView cAmount;
		  ImageButton imgBtnDeleteTransac;
		 }
	 
}
