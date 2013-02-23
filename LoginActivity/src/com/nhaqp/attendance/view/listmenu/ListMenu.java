package com.nhaqp.attendance.view.listmenu;

import java.util.ArrayList;

import android.content.Context;
import android.widget.ListView;

public class ListMenu extends ListView {
	
	private ArrayList<ListMenuButton> buttons;

	
	public ListMenu(Context context, ArrayList<ListMenuButton> buttons) {
		super(context);
		this.buttons = buttons;
		ListMenuAdapter adapter = new ListMenuAdapter(context, this.buttons);
		setAdapter(adapter);
	}
	
	/**
	 * 
	 * @param buttons
	 */
	public void setButtons(ArrayList<ListMenuButton> buttons) {
		this.buttons = buttons;
	}
	/**
	 * 
	 * @return
	 */
	public ArrayList<ListMenuButton> getButtons() {
		return buttons;
	}
}