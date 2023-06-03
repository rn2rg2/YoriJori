/**
 * 
 */

function animateNumbers() {
  $(".count-num").each(function() {
    $(this).prop("Counter", 0).animate({
      Counter: $(this).text()
    }, {
      duration: 2000,
      easing: "swing",
      step: function(now) {
        $(this).text(Math.ceil(now));
      }
    });
  });
}

let numbersAnimated = false; 

function isElementVisible($element) {
  let elementTop = $element.offset().top;
  let elementBottom = elementTop + $element.outerHeight();
  let viewportTop = $(window).scrollTop();
  let viewportBottom = viewportTop + $(window).height();

  return elementTop < viewportBottom && elementBottom > viewportTop;
}

$(window).on("scroll", function() {
  if (!numbersAnimated && isElementVisible($("#stats-counter"))) {
    numbersAnimated = true; 
    setTimeout(animateNumbers, 0); 
  }
});

if (!numbersAnimated && isElementVisible($("#stats-counter"))) {
  numbersAnimated = true; 
  setTimeout(animateNumbers, 0); y
}