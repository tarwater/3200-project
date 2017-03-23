$(document).ready(function () {

  var colors = [
                  "#ff8d00",
                  "#00f2f9",
                  "#15f600",
                  "#ff5454",
                  "#f284f9",
                  "#9a5aff",
                  "#0061fc"];

  window.setInterval(function () {

    var color = colors.shift();
    colors.push(color);

    $(".blinkLink").css({
            "color": color
        });

  }, 130);


})
