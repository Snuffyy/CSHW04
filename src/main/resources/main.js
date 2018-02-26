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

let msgLen = 0;
let keyLen = 0;

const baseUrl = "http://dixionary.eu";

update(sha);

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

function process() {
  const message = msg.value;

  let url = `${baseUrl}${type}?m=${message}`;

  if (!key.disabled) {
    // append key
    url += `&k=${key.value}`;
  }

  console.log(url);

  loader.classList.remove("hide");
  out.classList.add("hide");

  get(url, (resp) => {
    loader.classList.add("hide");
    out.classList.remove("hide");
    console.log(resp);
    out.innerHTML = resp;
  });
}

function get(url, fun) {

  let req = new XMLHttpRequest();
  req.onreadystatechange = () => {

    if (req.readyState == 4 && req.status == 200) {
      fun(req.response);
    }
    else if (req.readyState == 4 && status != 200) {
      loader.classList.add("hide");
      out.classList.remove("hide");
      out.innerHTML = "";

      setTimeout(function () {
        out.classList.add("hide");
      }, 3000);
    }
  };

  req.open("GET", url, true);
  req.send(null);
}

function isOfSameLen(keyLen, msgLen) {
  return keyLen == msgLen;
}

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
    if (isOfSameLen(msgLen, keyLen) && msgLen > 0) {
      btn.disabled = false;
    }
    else {
      btn.disabled = true;
    }
  }
}

function onKey() {

  keyLen = key.value.length;

  if (isOfSameLen(msgLen, keyLen) && msgLen > 0) {
    btn.disabled = false;
  }
  else {
    btn.disabled = true;
  }
}

function clear() {
  msg.value = "";
  key.value = "";
  msgLen = 0;
  keyLen = 0;
  btn.disabled = true;
}