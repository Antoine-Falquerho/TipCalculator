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
import android.widget.SeekBar;
import android.widget.TextView;
import android.text.Editable;
import android.text.TextWatcher;


public class MainActivity extends Activity {
	private EditText etAmount;	
	private TextView tvTip;	
	private EditText etSplit;
	private TextView tvToPay;
	private TextView tvTotal;
	private SeekBar sbTip;
	private TextView tvPercent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		etAmount = (EditText)findViewById(R.id.etAmount);		
		tvTip = (TextView)findViewById(R.id.tvTip);
		etSplit = (EditText)findViewById(R.id.etSplit);
		tvToPay = (TextView)findViewById(R.id.tvToPay);
		tvTotal = (TextView)findViewById(R.id.tvTotal);
		sbTip = (SeekBar)findViewById(R.id.sbTip);
		tvPercent = (TextView)findViewById(R.id.tvPercent);
		
		
		final SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
		sbTip.setProgress(Integer.parseInt(pref.getString("percent", "18")));
		tvPercent.setText(pref.getString("percent", "18") + "%");
		

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
		
		sbTip.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){ 				
			   @Override 
			   public void onProgressChanged(SeekBar seekBar, int progress, 
			     boolean fromUser) { 
			    // TODO Auto-generated method stub				   
				   BigDecimal tip = calculateTip();
				   tvPercent.setText(progress + "%");
//			    seekBarValue.setText(String.valueOf(progress)); 
			   } 

			   @Override 
			   public void onStartTrackingTouch(SeekBar seekBar) { 
			    // TODO Auto-generated method stub 				   
			   } 

			   @Override 
			   public void onStopTrackingTouch(SeekBar seekBar) { 
			    // TODO Auto-generated method stub 				   
			   } 
		}); 
	}
	
	protected BigDecimal calculateTip(){
		Integer percent = sbTip.getProgress();
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
			
			final SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
			Editor edit = pref.edit();
			edit.putString("percent", percent + "");
			edit.commit(); 
			
		}catch (Exception e){
			// do something
		}
		return tip.setScale(2, RoundingMode.HALF_UP);
	}
	
	public void tip10(View v){
		sbTip.setProgress(10);
		tvPercent.setText("10%");
	}
	public void tip15(View v){
		sbTip.setProgress(15);
		tvPercent.setText("15%");
	}
	public void tip20(View v){
		sbTip.setProgress(20);
		tvPercent.setText("20%");
	}
	
	
}
