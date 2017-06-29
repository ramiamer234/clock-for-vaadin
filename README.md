Clock component for Vaadin
==============

This is a special Clock component that displays time in hours, minutes and seconds in 24h format.
Note, synchronizes the time from web browser, so shows the current time in users timezone.

		// Create a Clock
		Clock clock = new Clock();
		content.addComponent(clock);

	    // Create a Pomodoro clock
        Clock clock = new Clock(25*60, 30*60);
        content.addComponent(clock);

		
Special style class names are applied for alert and overtime, that you can apply in your theme:

		/* Default styles for the Clock */
		.clock {
		}

		/* Alert time styles for the Clock */
		.clock.alert {
			color: orange; 
		}
  
		/* Overtime styles for the Clock */
		.clock.overtime {
			color: red;
		}
		


Online demo
=======

Demo is available at [sami.app.fi/clock](http://sami.app.fi/clock-for-vaadin)


Running the demo locally
=======

To run the demo application, run "mvn jetty:run" and open http://localhost:8080/ .

		git clone https://github.com/samie/clock-for-vaadin.git
		cd clock-for-vaadin
		mvn package jetty:run

