window.org_vaadin_example_clock_Clock_ClockExtension = function() {

    "use strict";

    this.parentElement = this.getElement(this.getParentId());
    this.timerId = null;
    this.alerttime = 0;
    this.overtime = 0;


    this.reset = function(a, o) {
    	// Cancel previous timer function
    	if (this.timerId) {
    		clearInterval(this.timerId);
    	}

      var c = new Date().getTime();
      this.alerttime = a > 0 ? c+a*1000 : 0;
      this.overtime = o > 0 ? c+o*1000 : 0;

	  var display = this.parentElement;
	  var self = this;

      function timer() {

            // Gets the current time
            var now = new Date();

            // Get the hours, minutes and seconds from the current time
            var hours = now.getHours();
            var minutes = now.getMinutes();
            var seconds = now.getSeconds();

            // Format hours, minutes and seconds
            if (hours < 10) {
                hours = "0" + hours;
            }
            if (minutes < 10) {
                minutes = "0" + minutes;
            }
            if (seconds < 10) {
                seconds = "0" + seconds;
            }

            // Update display
            display.textContent = hours + ':' + minutes + ':' + seconds;

            // Overtime
            var overtime = self.overtime > 0 && now.getTime() > self.overtime;
            var alert = self.alerttime > 0 && now.getTime() > self.alerttime;

            if (overtime) {
                // Add "overtime" style if needed
                if (!display.classList.contains("overtime")) {
                    display.classList.add("overtime");
                    display.classList.remove("alert");
                }
            } else if (alert) {
                // Add "alert" style if needed
                if (!display.classList.contains("alert")) {
                    display.classList.remove("overtime");
                    display.classList.add("alert");
                }
            } else {
                // No extra styles needed
                display.classList.remove("alert");
                display.classList.remove("overtime");
            }

        };

        // Call once immediately
        timer();

        // Set the timer on 1 sec intervals
        this.timerId = setInterval(timer, 500);
    };
}

