package com.emitrom.easygwt.wf.client.utils;

import java.util.ArrayList;
import java.util.List;

import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.Field;
import com.google.gwt.user.client.Timer;

/**
 * Utility class similar to @see FormButtonBinding
 * 
 * This is a more generic class where the user can bind a button
 * or even multiple buttons to do auto-enable disable of the buttons based on
 * the field/s supplied.
 * 
 * At any given time the user has the ability to add more buttons and fields.
 * 
 * @author Alfredo Quiroga-Villamil
 *
 */
public class FieldButtonBinding {
	
	private List<Button> buttons;
	private List<Field<?>> fields;
	private Timer timer;
	private int interval = 500;
	
	/**
	 * 
	 * @param fields
	 */
	public FieldButtonBinding(List<Field<?>> fields) {
		buttons = new ArrayList<Button>();
		this.fields = fields;
		init();
	}
	
	/**
	 * Used if the client invokes stopMonitoring
	 * to re-start the monitoring of all the fields.
	 */
	public void startMonitoring() {
		timer.run();
		timer.scheduleRepeating(interval);
	}
	
	/**
	 * Used to cancel monitoring
	 */
	public void stopMonitoring() {
		timer.cancel();
	}

	/**
	 * @return the buttons
	 */
	public List<Button> getButtons() {
		return buttons;
	}

	/**
	 * 
	 * @param button
	 */
	public void addButton(Button button) {
		buttons.add(button);
	}
	
	/**
	 * @param buttons the buttons to set
	 */
	public void setButtons(List<Button> buttons) {
		this.buttons = buttons;
	}

	/**
	 * @return the fields
	 */
	public List<Field<?>> getFields() {
		return fields;
	}

	/**
	 * @param fields the fields to set
	 */
	public void setFields(List<Field<?>> fields) {
		this.fields = fields;
	}
	
	/**
	 * 
	 * @param field
	 */
	public void addField(Field<?> field) {
		fields.add(field);
	}

	/**
	 * @return the interval
	 */
	public int getInterval() {
		return interval;
	}

	/**
	 * @param interval the interval to set
	 */
	public void setInterval(int interval) {
		this.interval = interval;
	}
	
	private void init() {
		
		timer = new Timer() {

			@Override
			public void run() {
				
				for (Button button : buttons) {
					for (Field<?> field: fields) {
						if (field.isValid(true)) {
							button.setEnabled(true);
						} else {
							button.setEnabled(false);
						}
					}
				}
				
			}

		};

		startMonitoring();
		
	}

}