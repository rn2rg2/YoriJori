


	/*
	 * mo js 좋아요 클릭 
	 */

//
	class Heart extends mojs.CustomShape {
	    getShape() {
	      return '<path d="M92.5939814,7.35914503 C82.6692916,-2.45304834 66.6322927,-2.45304834 56.7076029,7.35914503 L52.3452392,11.6965095 C51.0327802,12.9714696 48.9328458,12.9839693 47.6203869,11.6715103 L47.6203869,11.6715103 L43.2705228,7.35914503 C33.3833318,-2.45304834 17.3213337,-2.45304834 7.43414268,7.35914503 C-2.47804756,17.1963376 -2.47804756,33.12084 7.43414268,42.9205337 L29.7959439,65.11984 C29.7959439,65.1323396 29.8084435,65.1323396 29.8084435,65.1448392 L43.2580232,78.4819224 C46.9704072,82.1818068 52.9952189,82.1818068 56.7076029,78.4819224 L70.1696822,65.1448392 C70.1696822,65.1448392 70.1696822,65.1323396 70.1821818,65.1323396 L92.5939814,42.9205337 C102.468673,33.12084 102.468673,17.1963376 92.5939814,7.35914503 L92.5939814,7.35914503 Z"></path>';
	    }
	    getLength() {
	      return 292.110107421875;
	    }
	  }
		const COLORS = {
			    purple: "#8A5CE6",
			    blue: "#42AFE3",
			    yellow: "#FADD4A",
			    red: "#FF6680"
			  };
	  mojs.addShape("heart", Heart);


	  const duration = 350,
	    delayStep = duration / 3,
	    yShift = 10,
	    endAngle = 30;

	  let delay = 0;

	  const OPTS = {
	    shape: "heart",
	    scale: { 0: 1 },
	    angle: { [-90]: endAngle, easing: "cubic.out" },
 y: { 0: -yShift }, 
	    easing: "sin.out",
	    radius: 43,
	    duration
	  };

	  const THEN_OPTS = {
 y: 50, 
	    angle: { to: -45, easing: "cubic.in" },
	    scale: { to: 0, easing: "quad.in" },
	    easing: "sin.in"
	  };

	  const LOGO_THEN_OPTS = {
	    scale: 0,
	    delay: 1000
	  };

const purpleHeart = new mojs.Shape({
  ...OPTS,
  fill: COLORS.purple,
  origin: "100% 100%",
  parent: document.getElementById('heart-container')
}).then({
  ...THEN_OPTS
});

const redHeart = new mojs.Shape({
  ...OPTS,
  shape: "heart",
  fill: COLORS.red,
  origin: "0% 100%",
  delay: (delay += delayStep),
  x: 10, y: { [-55]: -55 - yShift },
  angle: { 40: -endAngle },
  parent: document.getElementById('heart-container')
}).then({
  ...THEN_OPTS,
  angle: { to: 90, easing: "expo.in" }
});

const yellowHeart = new mojs.Shape({
  ...OPTS,
  fill: COLORS.yellow,
  origin: "100% 100%",
  delay: (delay += delayStep),
  y: { [-85]: -85 - yShift }, x: 15,
  angle: { [-30]: endAngle },
  parent: document.getElementById('heart-container')
}).then({
  ...THEN_OPTS,
  duration: 300,
  y: 15
});

const blueCircle = new mojs.Shape({
  shape: "circle",
  fill: COLORS.blue,
  radius: 10,
  scale: { 0: 1 },
  x: { [-25]: -55 }, y: { 0: -70 },
  delay: duration / 2 + 50,
  duration: 1 * duration,
  easing: "quad.out",
  parent: document.getElementById('heart-container')
}).then({
  x: -20, y: 0,
  delay: 50,
  scale: { to: 0.65, easing: "quad.in" },
  duration: duration / 1.75,
  easing: "cubic.in",
  isShowEnd: false
});

const finalHeart = new mojs.Shape({
  shape: "heart",
  stroke: COLORS.red,
  fill: "none",
  strokeWidth: 10,
  x: 1, y: 2,
  strokeLinecap: "round",
  strokeDasharray: "100%",
  strokeDashoffset: { "100%": 0 },
  delay: 2.5 * duration - 50,
  duration: 2.5 * duration,
  easing: "quad.out",
  parent: document.getElementById('heart-container')
}).then({
  scale: 0,
  duration: 1.35 * duration,
  delay: 450,
  angle: 360,
  easing: "cubic.in"
});

  const timeline = new mojs.Timeline({ delay: 0 });
  function clickHeart(callback) {
	    timeline
	      .add(purpleHeart, redHeart, yellowHeart, blueCircle, finalHeart)
	      .play();
	    return callback();
	  }
