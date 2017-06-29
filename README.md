Clock component for Vaadin
==============

This is a digital 24h Clock component that displays time in hours, minutes and seconds in standard 24h format.
Includes API for setting "alert" and "overtime" timeouts that change the CSS for the clock.
Note, synchronizes the time from web browser, ands shows the current time in users timezone.


    // Create a Clock
    Clock clock = new Clock();
    content.addComponent(clock);

    // Create a 25/30 minute Pomodoro clock
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

Demo is available at [sami.app.fi/clock-for-vaadin](http://sami.app.fi/clock-for-vaadin)


Running the demo locally
=======

To run the demo application, run "mvn jetty:run" and open http://localhost:8080/ .

    git clone https://github.com/samie/clock-for-vaadin.git
    cd clock-for-vaadin
    mvn package jetty:run

