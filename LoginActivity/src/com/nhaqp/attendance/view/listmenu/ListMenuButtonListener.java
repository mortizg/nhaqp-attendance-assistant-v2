package com.nhaqp.attendance.view.listmenu;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;

public class ListMenuButtonListener implements OnClickListener {

	private Context context;
	private ListMenuButton boton;
	
	public ListMenuButtonListener(Context context, ListMenuButton boton) {
		this.context = context;
		this.boton = boton;
	}

	public void onClick(View v) {
		Intent intent = null;
		if (boton.getIntent() != null){
			intent = boton.getIntent();
		}
		else if (boton.getClase() != null) {
			intent = new Intent(context, boton.getClase());
		}
		context.startActivity(intent);
	}


}