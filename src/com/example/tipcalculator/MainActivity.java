package com.example.tipcalculator;


import java.math.BigDecimal;
import java.math.RoundingMode;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.text.Editable;
import android.text.TextWatcher;


public class MainActivity extends Activity {
	private EditText etAmount;
	private EditText etTip;
	private TextView tvTip;	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		etAmount = (EditText)findViewById(R.id.etAmount);
		etTip = (EditText)findViewById(R.id.etTip);
		tvTip = (TextView)findViewById(R.id.tvTip);

		etAmount.addTextChangedListener(new TextWatcher() {
		    @Override
		    public void onTextChanged(CharSequence s, int start, int before, int count) {
		        // Fires right as the text is being changed (even supplies the range of text)
		    	BigDecimal tip = calculateTip();		    	 
				tvTip.setText("$" + tip.toString());
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
		    		tvTip.setText("$" + tip.toString());
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
		BigDecimal tip = new BigDecimal(0);
		try{
			Double amount = Double.parseDouble(etAmount.getText().toString());
			tip = BigDecimal.valueOf((double)(amount * percent / 100));
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
