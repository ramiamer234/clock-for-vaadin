package org.vaadin.example.clock;

import com.vaadin.annotations.JavaScript;
import com.vaadin.server.AbstractJavaScriptExtension;
import com.vaadin.ui.Label;


@JavaScript("clock.js")
public class Clock extends Label {
	private static final long serialVersionUID = 1L;

	private static final String STYLE_NAME = "clock";

	private long seconds;
	private ClockExtension clock;
	private long alertSeconds = 0;
	private long overtimeSeconds = 0;

	public Clock() {
		super();
		clock = new ClockExtension(this);
		this.alertSeconds = 0;
		this.overtimeSeconds = 0;
		super.setStyleName(STYLE_NAME);
		this.reset();
	}
	
	public Clock(long alertSec, long overtimeSec) {
		this();
		this.alertSeconds = alertSec;
		this.overtimeSeconds = overtimeSec;
		this.reset();
	}
	
    /*
     * Sets the component's style. Don't add a JavaDoc comment here, we use the
     * default documentation from implemented interface.
     */
    @Override
    public void setStyleName(String style) {
    	super.setStyleName(style);
    	super.addStyleName(STYLE_NAME);
    }

	public void reset(long alertSec, long overtimeSec) {
		this.alertSeconds = alertSec;
		this.overtimeSeconds = overtimeSec;
		clock.reset(alertSeconds, overtimeSeconds);
	}

	public void reset() {
		clock.reset(alertSeconds, overtimeSeconds);
	}

	public class ClockExtension extends AbstractJavaScriptExtension {
		private static final long serialVersionUID = 1L;

		private ClockExtension(Label label) {
			super(label);
		}		
	
		public void reset(long alertSeconds, long overtimeSeconds) {
			callFunction("reset", alertSeconds, overtimeSeconds);
		}

	}
	
}