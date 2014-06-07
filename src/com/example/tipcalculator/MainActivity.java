package com.example.tipcalculator;


import java.math.BigDecimal;
import java.math.RoundingMode;

import android.app.Activity;
import android.os.Bundle;
//import android.util.Log;
//import android.view.Menu;
//import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {
	private EditText etAmount;
	private TextView tvTip;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		etAmount = (EditText)findViewById(R.id.etAmount);
		tvTip = (TextView)findViewById(R.id.tvTip);		
	}
	
	protected BigDecimal calculateTip(Integer percent){		 
		Double amount = Double.parseDouble(etAmount.getText().toString());
		BigDecimal tip = BigDecimal.valueOf((double)(amount * percent / 100));
		return tip.setScale(2, RoundingMode.HALF_UP);
	}
	
	public void tip10(View v){
		BigDecimal tip = calculateTip(10);
		tvTip.setText("$" + tip.toString());
	}
	public void tip15(View v){
		BigDecimal tip = calculateTip(15);
		tvTip.setText("$" + tip.toString());
	}
	public void tip20(View v){
		BigDecimal tip = calculateTip(20);
		tvTip.setText("$" + tip.toString());
	}
}
