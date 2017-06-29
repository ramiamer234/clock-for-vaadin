package org.vaadin.example.clock;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Push;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

/**
 * This UI is the application entry point. A UI may either represent a browser
 * window (or tab) or some part of a html page where a Vaadin application is
 * embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is
 * intended to be overridden to add component to the user interface and
 * initialize non-component functionality.
 */
@Push
@Theme("sampletheme")
public class ClockSampleUI extends UI {
	
	private static final long serialVersionUID = 1L;

	@Override
	protected void init(VaadinRequest vaadinRequest) {
		
		VerticalLayout content = new VerticalLayout();
		content.setSizeFull();
		content.setMargin(true);
		content.setSpacing(true);
		setContent(content);
		
		// Create a Clock from 0s to 10s with 5s alert
		Clock clock = new Clock();
		clock.setStyleName(ValoTheme.LABEL_H1);
		content.addComponent(clock);

		
		// Create a 25 min Pomodoro clock
		Clock clock2 = new Clock(25*60, 30*60);
		clock2.setStyleName(ValoTheme.LABEL_H1);
		content.addComponent(clock2);

		// Create a Clock with 10s alert and 20 sec overtime
		Clock clock3 = new Clock(10, 20);
		clock3.setStyleName(ValoTheme.LABEL_H1);
		content.addComponent(clock3);
		
		HorizontalLayout buttons = new HorizontalLayout();
		buttons.setSpacing(true);		
		content.addComponent(buttons);
		
		buttons.addComponent(new Button("Reset", e ->{ clock3.reset();}));
		buttons.addComponent(new Button("Sync to 15s warning and 30s overtime in 2sec", e -> {
			 Executors.newSingleThreadScheduledExecutor()
			 .schedule(() -> { syncSeconds(clock3, 15,30); }, 2, TimeUnit.SECONDS);
		}));
		buttons.addComponent(new Button("Sync to 5s warning and 10 overtime in 2sec", e -> {
			 Executors.newSingleThreadScheduledExecutor()
					 .schedule(() -> { syncSeconds(clock3, 5,10); }, 2, TimeUnit.SECONDS);
		}));
		buttons.addComponent(new Button("Sync to 10s warning and 20 overtime in 2sec", e -> {
			Executors.newSingleThreadScheduledExecutor()
					.schedule(() -> { syncSeconds(clock3, 10,20); }, 2, TimeUnit.SECONDS);
		}));

	}
	
	/// We need lock the UI using access(...) when modifying it from background thread
	private void syncSeconds(Clock clock, long alert, long overtime) {
		this.access(() -> {
			clock.reset(alert,overtime);});
	}

	@WebServlet(urlPatterns = "/*", name = "ClockSampleUI", asyncSupported = true)
	@VaadinServletConfiguration(ui = ClockSampleUI.class, productionMode = false)
	public static class MyUIServlet extends VaadinServlet {
	}

}
	