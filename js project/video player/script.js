const videoPlayer = document.querySelector(".video-section .video-player");
const video = videoPlayer.querySelector("#video");

/* select video to play */

const popup = document.querySelector(".pop-up-container");
const textInput = document.querySelector(
  ".input-container .link-input #video-link"
); // input to set text on it
const browse = document.querySelector(".input-container .link-input .btn");

// open popup to select video
browse.addEventListener("click", () => {
  popup.classList.add("show");
});

// handle close inside popup
popup.addEventListener("click", () => {
  popup.classList.remove("show");
});

popup
  .querySelector("dialog")
  .addEventListener("click", (e) => e.stopPropagation());

// add listener foreach video
let selectedVideo = null;
popup.querySelectorAll("ul>li").forEach((element) => {
  element.addEventListener("click", () => {
    if (selectedVideo) selectedVideo.classList.remove("selected");
    selectedVideo = element;
    element.classList.add("selected");
  });
});

// handle select button click
popup.querySelector(".actions .btn").addEventListener("click", () => {
  if (selectedVideo) {
    if (!video.paused) togglePlay();

    textInput.value = selectedVideo.textContent.trim();
    video.src = `videos/${selectedVideo.dataset.name}`;
    popup.classList.remove("show");
  }
});

/* controle video */

// updating time progress
function setTime() {
  const time = document.querySelector(".time");
  const progress_watched = document.querySelector(".progress-watched");
  const progress_buffred = document.querySelector(".progress-buffred");
  if (!video.duration || !video.currentTime) {
    time.textContent = "00:00 / 00:00";
    progress_watched.style.transform = `scaleX(0)`;
    progress_buffred.style.transform = `scaleX(0)`;
    return;
  }
  const currentMinutes = Math.floor(video.currentTime / 60)
    .toString()
    .padStart(2, "0");
  const currentSeconds = Math.floor(video.currentTime % 60)
    .toString()
    .padStart(2, "0");

  const totlaMinutes = Math.floor(video.duration / 60)
    .toString()
    .padStart(2, "0");
  const totalSeconds = Math.floor(video.duration % 60)
    .toString()
    .padStart(2, "0");

  time.textContent = `${currentMinutes}:${currentSeconds} / ${totlaMinutes}:${totalSeconds}`;

  progress_watched.style.transform = `scaleX(${
    video.currentTime / video.duration
  })`;
  progress_buffred.style.transform = `scaleX(${
    video.buffered.end(0) / video.duration
  })`;
}

video.addEventListener("timeupdate", setTime);

const play = document.querySelector(".play");
const startButton = document.querySelector(".input-container #start");
const controls = document.querySelector(".controls");

// show play and pause animation
function showPlayPauseAnimation() {
  const show_pause = videoPlayer.querySelector(".show-pause");
  const show_play = videoPlayer.querySelector(".show-play");
  if (video.paused) {
    show_pause.classList.add("animate");
    show_play.classList.remove("animate");
  } else {
    show_pause.classList.remove("animate");
    show_play.classList.add("animate");
  }
}

// switch between play and pause
function togglePlay() {
  if (selectedVideo) {
    if (video.paused) {
      video.play();
      cleaner = hideControlsInTime();
      startButton.textContent = "pause";
    } else {
      video.pause();
      controls.classList.remove("hide");
      startButton.textContent = "play";
    }
    play.classList.toggle("paused");
    play.classList.toggle("playing");
    showPlayPauseAnimation();
  }
}

play.addEventListener("click", togglePlay);
startButton.addEventListener("click", togglePlay);
videoPlayer.addEventListener("click", togglePlay);
videoPlayer.addEventListener("dblclick", toogleFullScreen);

// stop propagation when click inside controls
// so video not toggle

controls.addEventListener("click", (e) => e.stopPropagation());

// toggle full screen

const fullScreen = document.querySelector(".full-screen");
let isFullScreen = false;

function toogleFullScreen() {
  if (isFullScreen) {
    document.exitFullscreen();
  } else {
    videoPlayer.requestFullscreen();
  }
}

fullScreen.addEventListener("click", toogleFullScreen);

function toogleFullScreenButton() {
  fullScreen.classList.toggle("exited");
  fullScreen.classList.toggle("entered");
  isFullScreen = !isFullScreen;
}

document.addEventListener("fullscreenchange", toogleFullScreenButton);

// hide controls when blur

// debounce help to run callack
// only after amount of time
function _debounce(callback, time) {
  let timeoutCleaner;

  return (...args) => {
    clear();
    timeoutCleaner = setTimeout(() => callback(...args), time);

    function clear() {
      if (timeoutCleaner) clearTimeout(timeoutCleaner);
    }

    return { clear };
  };
}

const hideControlsInTime = _debounce(hideControls, 2000);
let cleaner;

function hideControls() {
  if (!video.paused) {
    controls.classList.add("hide");
    videoPlayer.style.cursor = "none";
  }
}

videoPlayer.addEventListener("mousemove", () => {
  controls.classList.remove("hide");
  videoPlayer.style.cursor = "initial";
  cleaner = hideControlsInTime();
});

videoPlayer.addEventListener("mouseleave", () => {
  hideControls();
  if (cleaner) cleaner.clear();
});

// sound managment
// change between mute and normal

const sound = document.querySelector(".sound");
const slider = sound.querySelector(".slider input");

function toggleMute() {
  if (video.volume > 0) {
    if (video.muted) {
      video.muted = false;
      changeVolume();
    } else {
      video.muted = true;
      changeVolume();
    }
  }
}

sound.addEventListener("click", toggleMute);
slider.addEventListener("click", (e) => e.stopPropagation());

// volume change
function changeVolume() {
  if (slider.value == 0 || video.muted) {
    sound.classList.add("muted");
    sound.classList.remove("loud");
    sound.classList.remove("low");
  } else if (slider.value < 30) {
    sound.classList.remove("muted");
    sound.classList.remove("loud");
    sound.classList.add("low");
  } else {
    sound.classList.remove("muted");
    sound.classList.add("loud");
    sound.classList.remove("low");
  }
  video.volume = slider.value / 100;
}

slider.addEventListener("input", changeVolume);
slider.addEventListener("change", changeVolume);

// change video position
const progress = document.querySelector(".progress-list");

function changeVideoPosition(e) {
  if (selectedVideo) {
    const x = e.clientX;
    const clientLeft = Math.floor(progress.getBoundingClientRect().left);
    const clientRight = Math.floor(progress.getBoundingClientRect().right);
    const clientLength = clientRight - clientLeft;
    const length = x - clientLeft;

    const progressPercent = length / clientLength;
    video.currentTime = video.duration * progressPercent;
  }
}

document
  .querySelector(".progress-list")
  .addEventListener("click", changeVideoPosition);

// handle keypressed events
document.addEventListener("keypress", (e) => {
  switch (e.code) {
    case "KeyS":
      togglePlay();
      break;
    case "KeyF":
      toogleFullScreen();
      break;
  }
});

// handle keydown events
function _throttle(callback, time) {
  let previous = new Date();

  return (...args) => {
    const current = new Date();
    if (current - previous >= time) {
      previous = current;

      callback(...args);
    }
  };
}

function _changeCurrentTime(time) {
  if (selectedVideo) {
    const newValue = video.currentTime + time;
    video.currentTime = Math.min(Math.max(0, newValue), video.duration);
  }
}

function _changeCurrentVolume(volume) {
  const newValue = Number(slider.value) + volume;
  slider.value = Math.min(Math.max(0, newValue), 100);
  changeVolume();
}

const changeCurrentTime = _throttle(_changeCurrentTime, 100);
const changeCurrentVolume = _throttle(_changeCurrentVolume, 100);

document.addEventListener("keydown", (e) => {
  const TIME_CHANGE = 3;
  const volume_CHANGE = 10;
  switch (e.code) {
    case "ArrowRight":
      changeCurrentTime(TIME_CHANGE);
      break;
    case "ArrowLeft":
      changeCurrentTime(-TIME_CHANGE);
      break;
    case "ArrowUp":
      changeCurrentVolume(volume_CHANGE);
      break;
    case "ArrowDown":
      changeCurrentVolume(-volume_CHANGE);
      break;
  }
});
