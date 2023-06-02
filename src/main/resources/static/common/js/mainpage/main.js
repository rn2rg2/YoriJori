$(document).ready(function() {
  function animateCounter(element) {
    element.prop("Counter", 0).animate({
      Counter: element.text()
    }, {
      duration: 2000,
      easing: "swing",
      step: function(now) {
        element.text(Math.ceil(now));
      }
    });
  }

  // IntersectionObserver 사용
  const observer = new IntersectionObserver((entries) => {
    entries.forEach(entry => {
      if (entry.isIntersecting) {
        animateCounter($(entry.target));
        observer.unobserve(entry.target);
      }
    });
  });

  $(".count-num").each(function() {
    observer.observe(this);
  });
});
