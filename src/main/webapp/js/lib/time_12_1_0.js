function CurrentTime(containerNode, startTimeStamp) {
    var self = this,
        timer,
        weekDays = ["Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"],
        months = ["January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"],
        now = new Date(),
        prevTickTimeStamp,
        currTickTimeStamp;


    function addLeadingZero(v) {
        var s = v < 0 ? "-" : "";
        v  = Math.abs(v);

        return s + (v < 10 ? "0" + v : v);
    }

    function generateTimeString() {
        var hours = now.getUTCHours(),
            dayPart = "AM";
        if (hours > 11) {
            hours -= 12;
            dayPart = "PM";
        }
        if (hours == 0) {
            hours = 12;
        }

        return hours + ":" + addLeadingZero(now.getUTCMinutes())
             + " " + dayPart + " - " + weekDays[now.getUTCDay()] + ", " + months[now.getUTCMonth()] + " " + now.getUTCDate() + ", "
             + now.getUTCFullYear();
    }

    function secondTick() {
        currTickTimeStamp = new Date().getTime();
        now.setTime((currTickTimeStamp - prevTickTimeStamp) + startTimeStamp);

        containerNode.nodeValue = generateTimeString();
    }

    this.start = function () {
        clearInterval(timer);
        prevTickTimeStamp = (new Date()).getTime();
        timer = setInterval(secondTick, 1000);
    }
}