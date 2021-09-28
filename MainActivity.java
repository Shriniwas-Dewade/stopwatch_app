package com.shriniwas.StopWatch;

import android.app.*;
import android.os.*;
import android.view.*;
import android.widget.*;
import java.util.*;

public class MainActivity extends Activity 
{
	int seconds = 0;
	boolean running;
	boolean wasRunning;
	
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
		
		if(savedInstanceState != null){
			seconds = savedInstanceState.getInt("seconds");
			running = savedInstanceState.getBoolean("running");
			wasRunning = savedInstanceState.getBoolean("wasrunning");
		}
		run();
    }

	@Override
	protected void onSaveInstanceState(Bundle outState)
	{
		// TODO: Implement this method
		super.onSaveInstanceState(outState);
		outState.putInt("seconds", seconds); 

        outState.putBoolean("running", running); 

        outState.putBoolean("wasrunning", wasRunning); 
		
	}

	@Override
	protected void onPause()
	{
		// TODO: Implement this method
		super.onPause();
		wasRunning = running;
		running = false;
	}

	@Override
	protected void onResume()
	{
		// TODO: Implement this method
		super.onResume();
		if(wasRunning){
			running = true;
		}
	}
	
	public void start(View v){
		running = true;
	}
	
	public void stop(View v){
		running = false;
	}
	
	public void reset(View v){
		running = false;
		seconds = 0;
	}
	
	public void run(){
		final TextView tv = (TextView) findViewById(R.id.Time);
		
		final Handler hand = new Handler();
		
		hand.post(new Runnable() {
			@Override
			public void run(){
				int hours = seconds / 3600;
				int min = (seconds % 3600) / 60;
				int sec = seconds % 60;
				
				String time = String.format(Locale.getDefault(),"%d:%02d:%02d",hours,min,sec);
				
				tv.setText(time);
				
				if(running){
					seconds += 1;
				}
				
				hand.postDelayed(this, 1000); 
				
			}
			
			
			
		});
		
	}
	
}
