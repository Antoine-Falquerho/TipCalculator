package com.example.tipcalculator;


import java.math.BigDecimal;
import java.math.RoundingMode;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.text.Editable;
import android.text.TextWatcher;


public class MainActivity extends Activity {
	private EditText etAmount;
	private EditText etTip;
	private TextView tvTip;	
	private EditText etSplit;
	private TextView tvToPay;
	private TextView tvTotal;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		etAmount = (EditText)findViewById(R.id.etAmount);
		etTip = (EditText)findViewById(R.id.etTip);
		tvTip = (TextView)findViewById(R.id.tvTip);
		etSplit = (EditText)findViewById(R.id.etSplit);
		tvToPay = (TextView)findViewById(R.id.tvToPay);
		tvTotal = (TextView)findViewById(R.id.tvTotal);
		
		final SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
		etTip.setText(pref.getString("percent", "18")); 

		etAmount.addTextChangedListener(new TextWatcher() {
		    @Override
		    public void onTextChanged(CharSequence s, int start, int before, int count) {
		        // Fires right as the text is being changed (even supplies the range of text)
		    	BigDecimal tip = calculateTip();
		    }

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub
				
			}
		});
		
		etTip.addTextChangedListener(new TextWatcher() {
		    @Override
		    public void onTextChanged(CharSequence s, int start, int before, int count) {
		        // Fires right as the text is being changed (even supplies the range of text)
		    	try{
		    		BigDecimal tip = calculateTip();		    		
		    		
		    		Editor edit = pref.edit();
		    		edit.putString("percent", etTip.getText().toString());
		    		edit.commit(); 
		    	}catch (Exception e){
		    		// catch 
		    	}
		    }

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub
				
			}
		});
		
		etSplit.addTextChangedListener(new TextWatcher() {
		    @Override
		    public void onTextChanged(CharSequence s, int start, int before, int count) {
		        // Fires right as the text is being changed (even supplies the range of text)
		    	try{
		    		BigDecimal tip = calculateTip();
		    	}catch (Exception e){
		    		// catch 
		    	}
		    }

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	
	protected BigDecimal calculateTip(){
		Integer percent = Integer.parseInt(etTip.getText().toString());
		Integer split = Integer.parseInt(etSplit.getText().toString());
		BigDecimal tip = new BigDecimal(0);
		BigDecimal total = new BigDecimal(0);
		try{
			Double amount = Double.parseDouble(etAmount.getText().toString());			
			tip = BigDecimal.valueOf((double)((amount * percent / 100) / split  )).setScale(2, RoundingMode.HALF_UP);
			tvToPay.setText("$" + BigDecimal.valueOf(amount / split ).setScale(2, RoundingMode.HALF_UP));
			tvTip.setText("$" + tip.toString());
			Double total_with_tip = (double)((amount / split) + Double.parseDouble(tip.toString()));
			total = BigDecimal.valueOf((double) total_with_tip ).setScale(2, RoundingMode.HALF_UP);
			tvTotal.setText("$" + total.toString());
		}catch (Exception e){
			// do something
		}
		return tip.setScale(2, RoundingMode.HALF_UP);
	}
	
	public void tip10(View v){
		etTip.setText("10");
	}
	public void tip15(View v){
		etTip.setText("15");
	}
	public void tip20(View v){
		etTip.setText("20");
	}
	
	
}
