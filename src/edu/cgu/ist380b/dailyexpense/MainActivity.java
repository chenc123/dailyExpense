package edu.cgu.ist380b.dailyexpense;


import edu.cgu.ist380b.dailyexpense.db.Category;
import edu.cgu.ist380b.dailyexpense.db.CategoryDataSource;
import edu.cgu.ist380b.dailyexpense.db.Transaction;
import edu.cgu.ist380b.dailyexpense.db.TransactionDataSource;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import android.os.Bundle;
import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;



public class MainActivity extends FragmentActivity implements
ActionBar.TabListener{

	SectionsPagerAdapter mSectionsPagerAdapter;
	ViewPager mViewPager;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		// Set up the action bar.
		final ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		
		// Create the adapter that will return a fragment for each of the three
		// primary sections of the app.
		mSectionsPagerAdapter = new SectionsPagerAdapter(
		getSupportFragmentManager());
		
		// Set up the ViewPager with the sections adapter.
		mViewPager = (ViewPager) findViewById(R.id.pager);
		mViewPager.setAdapter(mSectionsPagerAdapter);
		
		
		// When swiping between different sections, select the corresponding
		// tab. We can also use ActionBar.Tab#select() to do this if we have
		// a reference to the Tab.
		mViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
			@Override
			public void onPageSelected(int position) {
				actionBar.setSelectedNavigationItem(position);
			}
		});

		// For each of the sections in the app, add a tab to the action bar.
		for (int i = 0; i < mSectionsPagerAdapter.getCount(); i++) {
			// Create a tab with text corresponding to the page title defined by
			// the adapter. Also specify this Activity object, which implements
			// the TabListener interface, as the callback (listener) for when
			// this tab is selected.
			actionBar.addTab(actionBar.newTab().setText(mSectionsPagerAdapter.getPageTitle(i))
					.setTabListener(this));
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	  
	
	
	@Override
	public void onTabSelected(ActionBar.Tab tab,
			FragmentTransaction fragmentTransaction) {
		// When the given tab is selected, switch to the corresponding page in
		// the ViewPager.
		mViewPager.setCurrentItem(tab.getPosition());
	}

	@Override
	public void onTabUnselected(ActionBar.Tab tab,
			FragmentTransaction fragmentTransaction) {
	}

	@Override
	public void onTabReselected(ActionBar.Tab tab,
			FragmentTransaction fragmentTransaction) {
	}
	
	/**
	 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
	 * one of the sections/tabs/pages.
	 */
	public class SectionsPagerAdapter extends FragmentPagerAdapter {

		public SectionsPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {
			// getItem is called to instantiate the fragment for the given page.
			// Return a DummySectionFragment (defined as a static inner class
			// below) with the page number as its lone argument.
			Fragment fragment = new TransactionTab();
			if (position == 1)
				fragment = new CategoryFragment();
			//else if (position == 2)
				//fragment = new CalendarFragment();

			Bundle args = new Bundle();
			args.putInt(TransactionTab.ARG_SECTION_NUMBER, position + 1);
			fragment.setArguments(args);
			return fragment;
		}

		@Override
		public int getCount() {
			// Show 2 total pages.
			return 2;
		}

		@Override
		public CharSequence getPageTitle(int position) {
			Locale l = Locale.getDefault();
			switch (position) {
			case 0:
				return getString(R.string.title_section1).toUpperCase(l);
			case 1:
				return getString(R.string.title_section2).toUpperCase(l);
			}
			return null;
		}
	}

	
	
	/**
	 * A dummy fragment representing a section of the app, but that simply
	 * displays dummy text.
	 */
	public static class TransactionTab extends Fragment {
		/**
		 * The fragment argument representing the section number for this
		 * fragment.
		 */
		
		public static final String ARG_SECTION_NUMBER = "section_number";
		public TransactionTab(){};
		final Calendar c = Calendar.getInstance();

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			final View rootView = inflater.inflate(R.layout.transaction_tab,
					container, false);
			// Get database connection 
			
			final TransactionDataSource datasource = new TransactionDataSource(this.getActivity());
			 Log.e(MainActivity.class.getName(), "db is  "+ datasource);
			 datasource.open();
			 //Transaction transac = new Transaction();
			 //transac.setItem("Dinner");
			 //transac.setTs("06/23/2013");
			 //transac.setAmount(5.0f);
			 //transac.setCategoryId(1);
			 
			 //datasource.createTransac(transac);
		/*	 
		   ListView transacView = (ListView) rootView.findViewById(R.id.transacView);
		   List<Transaction> transacList = datasource.getAllTranByDate(new SimpleDateFormat("MM/dd/yyyy").format(c.getTime()));
		   datasource.close();
		  
		   ArrayAdapter<Transaction> adapter = new ArrayAdapter<Transaction>(this.getActivity(),
			           android.R.layout.simple_list_item_1, transacList);
		   transacView.setAdapter(adapter);
		*/
		
			Context context = this.getActivity();
			TransactionDataSource tranDS = new TransactionDataSource(this.getActivity());
			CategoryDataSource cateDS = new CategoryDataSource(this.getActivity());
			List<Transaction> dailyTransactionList;
		    tranDS.open();
		    cateDS.open();
			
		    
		    
			
		   
		   	TextView dateView = (TextView) rootView.findViewById(R.id.dateNow);
		   	
			final SimpleDateFormat sdf = new SimpleDateFormat("MMM d (EEE)");
			dateView.setText(sdf.format(c.getTime()));
		
			
			
		    dailyTransactionList=tranDS.getAllTranByDate(new SimpleDateFormat("MM/dd/yyyy").format(c.getTime()));
					
		    
		    ListView transacView = (ListView) rootView.findViewById(R.id.transacView);
		    transacView.setAdapter(new DailyTransactionViewAdapter(this.getActivity(), 
					dailyTransactionList, new SimpleDateFormat("MM/dd/yyyy").format(c.getTime())));	
			
		    
		    
			ImageButton preBtn = (ImageButton) rootView.findViewById(R.id.previousDayButton);
			ImageButton nextBtn = (ImageButton) rootView.findViewById(R.id.nextDayButton);
			
			preBtn.setOnClickListener(new View.OnClickListener() {
				
				TextView dateView = (TextView) rootView.findViewById(R.id.dateNow);
				ListView transacView = (ListView) rootView.findViewById(R.id.transacView);
				@Override
				public void onClick(View v) {
					datasource.open();
					
					//change the date to previous day
					//SimpleDateFormat  dateFormat  = new SimpleDateFormat("MMM d (EEE)");
					int dayShift = -1; // Positive for next days, negative for previous days
					
					//the date now
					
					if (dayShift  != 0) {
					        c.add(Calendar.DAY_OF_YEAR, dayShift);
					}
					dateView.setText(sdf.format(c.getTime()));
					List<Transaction> transacList = datasource.getAllTranByDate(new SimpleDateFormat("MM/dd/yyyy").format(c.getTime()));
					//ArrayAdapter<Transaction> adapter = new ArrayAdapter<Transaction>(getActivity(),
					  //         android.R.layout.simple_list_item_1, transacList);
					 transacView.setAdapter(new DailyTransactionViewAdapter(getActivity(), 
							 transacList, new SimpleDateFormat("MM/dd/yyyy").format(c.getTime())));	
					datasource.close();
				    //transacView.setAdapter(adapter);
				}
			});
			
			nextBtn.setOnClickListener(new View.OnClickListener() {
				TextView dateView = (TextView) rootView.findViewById(R.id.dateNow);
				ListView transacView = (ListView) rootView.findViewById(R.id.transacView);
				@Override
				public void onClick(View v) {
					//change the date to previous day
					//SimpleDateFormat  dateFormat  = new SimpleDateFormat("MMM d (EEE)");
					
					datasource.open();
					int dayShift = 1; // Positive for next days, negative for previous days
														
					if (dayShift  != 0) {
					        c.add(Calendar.DAY_OF_YEAR, dayShift);
					}
					dateView.setText(sdf.format(c.getTime()));
					List<Transaction> transacList = datasource.getAllTranByDate(new SimpleDateFormat("MM/dd/yyyy").format(c.getTime()));
					//ArrayAdapter<Transaction> adapter = new ArrayAdapter<Transaction>(getActivity(),
					  //         android.R.layout.simple_list_item_1, transacList);
					datasource.close();
					
				    //transacView.setAdapter(adapter);
				    transacView.setAdapter(new DailyTransactionViewAdapter(getActivity(), 
							 transacList, new SimpleDateFormat("MM/dd/yyyy").format(c.getTime())));	
				}
			});
			ImageButton addExpenseBtn =(ImageButton)rootView.findViewById(R.id.addNewExpense);
			addExpenseBtn.setOnClickListener(new OnClickListener() {
				
				
				@Override
				public void onClick(View v) {
					Context context = v.getContext();
					Intent intent = new Intent();
					intent.setClassName(context, "edu.cgu.ist380b.dailyexpense.AddExpenseActivity");
					//intent.putExtra("Date", sdf.format(c.getTime()));
					intent.putExtra("Date", new SimpleDateFormat("MM/dd/yyyy").format(c.getTime()));
				
					context.startActivity(intent);
				}
			});	
			
			return rootView;
			
		}

		
		
	}

	public static class CategoryFragment extends Fragment {
		/**
		 * The fragment argument representing the section number for this
		 * fragment.
		 */
		public static final String ARG_SECTION_NUMBER = "section_number";

		public CategoryFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.category_tab, container,
					false);
			Button addCategoryBtn = (Button) rootView.findViewById(R.id.addCategoryBtn);
			final ListView categoryListView = (ListView) rootView.findViewById(R.id.categoryListView);
			final EditText addCategoryEditText = (EditText) rootView.findViewById(R.id.addCategoryEditText);
			
			
			
			
			final CategoryDataSource cateDS = new CategoryDataSource(this.getActivity());
			cateDS.open(); 
			List<Category> categoryList = cateDS.getAllCategories();
					
			cateDS.close();
			  
			   ArrayAdapter<Category> adapter = new ArrayAdapter<Category>(this.getActivity(),
				           android.R.layout.simple_list_item_1, categoryList);
			   categoryListView.setAdapter(adapter);
			
			   addCategoryBtn.setOnClickListener(new View.OnClickListener(){

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					String newCategory;
					newCategory = addCategoryEditText.getText().toString();
					Category cate = new Category();
					
					if (newCategory.trim().length() > 0 ){
						cate.setCategoryName(newCategory);
						cateDS.open();
						cateDS.createCategory(cate);
						cateDS.close();
						
						cateDS.open(); 
						List<Category> categoryList = cateDS.getAllCategories();
								
						cateDS.close();
						addCategoryEditText.setText("");
						   ArrayAdapter<Category> adapter = new ArrayAdapter<Category>(getActivity(),
							           android.R.layout.simple_list_item_1, categoryList);
						   categoryListView.setAdapter(adapter);
					}else {
	                    Toast.makeText(getActivity(), "Please enter label name",
	                            Toast.LENGTH_SHORT).show();
	                }
					
				}
				   
			   });
			   
			   
			return rootView;
		}
	}

	
	
	

}
