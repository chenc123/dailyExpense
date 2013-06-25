package edu.cgu.ist380b.dailyexpense;





import java.util.List;


import edu.cgu.ist380b.dailyexpense.db.CategoryDataSource;
import edu.cgu.ist380b.dailyexpense.db.Transaction;
import edu.cgu.ist380b.dailyexpense.db.TransactionDataSource;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class AddExpenseActivity extends Activity implements OnItemSelectedListener{
	
	private EditText editTextItem;
	private EditText editTextAmount;
	private EditText editTextTs;
	private Spinner spinnerCategory;
	
	private Button btnCancel;
	private Button btnAdd;
	private Intent intent;
	private Context context;
	private String item;
	private Float amount;
	private String ts;
	private String category;
	private int categoryToId;
	private Transaction transac;
	private CategoryDataSource categoryDS;
	private Bundle bundle;
	
	
    
    
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_expense);
		
		
		spinnerCategory = (Spinner) findViewById(R.id.spinnerCategory);
		btnCancel = (Button)findViewById(R.id.cancelExpenseBtn);
		btnAdd = (Button)findViewById(R.id.addExpenseBtn);
		editTextTs=(EditText)findViewById(R.id.tsEditText);
		context = this;
		Intent i = getIntent();
		bundle = i.getExtras();
		editTextTs.setText(bundle.getString("Date"));
		
		
		spinnerCategory.setOnItemSelectedListener(this);
		loadSpinnerData(); 
		
		
		btnCancel.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub	
				intent = new Intent();
				intent.setClassName(context, "edu.cgu.ist380b.dailyexpense.MainActivity");
				context.startActivity(intent);
			}
		});
		
		btnAdd.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				editTextItem =(EditText)findViewById(R.id.itemEditText);
				editTextAmount=(EditText)findViewById(R.id.amountEditText);
				
				spinnerCategory=(Spinner)findViewById(R.id.spinnerCategory);
				
				
				item = editTextItem.getText().toString();
				amount = Float.parseFloat(editTextAmount.getText().toString());
				ts = editTextTs.getText().toString();
				category = spinnerCategory.getSelectedItem().toString();				
							
				/*if(item.length()==0 || firstName.length()==0 || lastName.length()==0)
				{
					incompleteInfoDialog=new IncompleteInfoDialog();
					incompleteInfoDialog.show(getFragmentManager(), "");
				}
				else
				{*/
				categoryDS = new CategoryDataSource(context);
				categoryDS.open();
				categoryToId = categoryDS.getIdByCategoryName(category);
				categoryDS.close();
				
				
					transac = new Transaction();
					transac.setItem(item);
					transac.setAmount(amount);
					transac.setTs(ts);
					transac.setCategoryId(categoryToId);
					
					TransactionDataSource transacDS=new TransactionDataSource(context);
					transacDS.open();
					transacDS.createTransac(transac);
					transacDS.close();
					//default category
					
					
					
					intent=new Intent();
					intent.setClassName(context, "edu.cgu.ist380b.dailyexpense.MainActivity");
					context.startActivity(intent);
				//}
			}
		});
	}
	 /**
     * Function to load the spinner data from SQLite database
     * */
	private void loadSpinnerData() {
        // database handler
    	categoryDS = new CategoryDataSource(this);
    	 categoryDS.open();
		        // Spinner Drop down elements
    
		 List<String> labels = categoryDS.getAllLabels();
		 categoryDS.close();
     // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, labels);
 
        // Drop down layout style - list view with radio button
        dataAdapter
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
 
        // attaching data adapter to spinner
        spinnerCategory.setAdapter(dataAdapter);
    }
    
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position,
            long id) {
        // On selecting a spinner item
        String label =  parent.getItemAtPosition(position).toString();
 
        // Showing selected spinner item
        Toast.makeText(parent.getContext(), "You selected: " + label,
                Toast.LENGTH_LONG).show();
 
    }
 
    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
 
    }
}
	
	

