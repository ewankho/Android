package com.martinmervin.flashlight;

import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {

	//flag to detect flash is on or off
	private boolean isLighOn = true;
 
	private Camera camera;
 
	private Button button;
 
	@Override
	protected void onStop() {
		super.onStop();
 
		if (camera != null) {
			camera.release();
		}
	}
 
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
 
		button = (Button) findViewById(R.id.buttonFlashlight);
 
		Context context = this;
		PackageManager pm = context.getPackageManager();
 
		// if device support camera?
		/*if (!pm.hasSystemFeature(PackageManager.FEATURE_CAMERA)) {
			Log.e("err", "Device has no camera!");
			return;
		}*/
 
		camera = Camera.open();
		final Parameters p = camera.getParameters();
 
		button.setOnClickListener(new OnClickListener() {
 
			public void onClick(View arg0) {
 
				if (isLighOn) {
 
					// Log.i("info", "torch is turn off!");
 
					p.setFlashMode(Parameters.FLASH_MODE_OFF);
					camera.setParameters(p);
					camera.stopPreview();
					isLighOn = false;
 
				} else {
 
					// Log.i("info", "torch is turn on!");
 
					p.setFlashMode(Parameters.FLASH_MODE_TORCH);
 
					camera.setParameters(p);
					camera.startPreview();
					isLighOn = true;
 
				}
 
			}
		});
		
		// turn on light on launch
		p.setFlashMode(Parameters.FLASH_MODE_TORCH);		 
		camera.setParameters(p);
		camera.startPreview();
		isLighOn = true;
 
	}
}
