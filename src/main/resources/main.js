// Define DOM elements
const sha = document.getElementById("sha");
const md5 = document.getElementById("md5");
const otpE = document.getElementById("otpE");
const otpD = document.getElementById("otpD");

const msg = document.getElementById("msg");
const key = document.getElementById("key");

const btn = document.getElementById("btn");
const out = document.getElementById("out");
const loader = document.getElementById("loader");

let type;

// variables for checking the length of the input fields
let msgLen = 0;
let keyLen = 0;

const baseUrl = "http://dixionary.eu";

update(sha);

/**
 * Trigger on a radio button change.
 *
 * @param radio - button
 */
function update(radio) {
  if (sha.checked || md5.checked) {
    key.disabled = true;
  }
  else {
    key.disabled = false;
  }

  clear();

  switch (radio.value) {
    case "0":
      console.log("SHA");
      type = "/sha/";
      btn.innerHTML = "encode";
      break;
    case "1":
      console.log("MD5");
      type = "/md5/";
      btn.innerHTML = "decode";
      break;
    case "2":
      console.log("otpE");
      type = "/otp/e/";
      btn.innerHTML = "encrypt";
      break;
    case "3":
      console.log("otpD");
      type = "/otp/d/";
      btn.innerHTML = "decrypt";
      break;
  }
}

/**
 * Trigger on submit button click.
 */
function process() {
  const message = msg.value;

  // form a URL to make a GET request to
  let url = `${baseUrl}${type}?m=${message}`;

  // if operations requires a key, append a parameter to the URL
  if (!key.disabled) {
    // append key
    url += `&k=${key.value}`;
  }

  console.log(url);

  // start a progress loader
  loader.classList.remove("hide");
  out.classList.add("hide");

  get(url, (resp) => {
    loader.classList.add("hide");
    out.classList.remove("hide");
    console.log(resp);
    out.innerHTML = resp;
  });
}

/**
 * Perform a async GET request.
 *
 * @param url to send request to.
 * @param fun a callback function.
 */
function get(url, fun) {

  let req = new XMLHttpRequest();

  req.onreadystatechange = () => {

    if (req.readyState == 4 && req.status == 200) {
      // response from a server is successful
      fun(req.response);
    }
    else if (req.readyState == 4 && status != 200) {
      // response from a server is not acceptable
      loader.classList.add("hide");
      out.classList.remove("hide");
      out.innerHTML = "";

      // output area disappears in 3 seconds
      setTimeout(function () {
        out.classList.add("hide");
      }, 3000);
    }
  };

  req.open("GET", url, true);
  req.send(null);
}

/**
 * Return true if message and key are of the same length.
 *
 * @returns {boolean}
 */
function isOfSameLen() {
  return keyLen == msgLen;
}

/**
 * Trigger when 'message' input state changes.
 */
function onMsg() {
  msgLen = msg.value.length;

  if (type == "/md5/") {
    if (msgLen != 32) {
      btn.disabled = true;
    }
    else {
      btn.disabled = false;
    }
  }
  else if (type == "/sha/") {
    if (msgLen > 0) {
      btn.disabled = false;
    }
    else {
      btn.disabled = true;
    }
  }
  else {
    if (isOfSameLen() && msgLen > 0) {
      btn.disabled = false;
    }
    else {
      btn.disabled = true;
    }
  }
}

/**
 * Trigger when 'key' input state changes.
 */
function onKey() {
  keyLen = key.value.length;

  if (isOfSameLen() && msgLen > 0) {
    btn.disabled = false;
  }
  else {
    btn.disabled = true;
  }
}

/**
 * Clear up input fields and reset variables.
 */
function clear() {
  msg.value = "";
  key.value = "";
  msgLen = 0;
  keyLen = 0;
  btn.disabled = true;
}