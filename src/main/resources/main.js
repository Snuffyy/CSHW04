

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
      btn.disabled = false;
      break;
    case "1":
      console.log("MD5");
      type = "/md5/";
      btn.innerHTML = "decode";
      btn.disabled = false;
      break;
    case "2":
      console.log("otpE");
      type = "/otp/e/";
      btn.innerHTML = "encrypt";
      btn.disabled = true;
      break;
    case "3":
      console.log("otpD");
      type = "/otp/d/";
      btn.innerHTML = "decrypt";
      btn.disabled = true;
      break;
  }
}

function process() {
  const message = msg.value;

  let url = `${baseUrl}${type}?m=${message}`;

  if (!key.disabled) {
    // append key
    url += `&k=${key.value}`

    if (keyLen != msgLen) {
      console.log("Should be of same len!");
      return;
    }
  }

  console.log(url);

  loader.classList.remove("hide");
  out.classList.add("hide");

  get(url, (resp) => {
    // loader.classList.add("hide");
    // out.classList.remove("hide");
    // console.log(resp);
    // out.innerHTML = resp;
  });
}

function get(url, fun) {

  let req = new XMLHttpRequest();
  req.onreadystatechange = () => {
    loader.classList.add("hide");
    out.classList.remove("hide");
    console.log(resp);

    if (req.readyState == 4 && req.status == 200) {
      fun(req.response)
      out.innerHTML = req.response;
    }
    else{
      out.innerHTML = "";
    }
  };

  req.open("GET", url, true);
  req.send(null);
}

function isOfSameLen(keyLen, msgLen) {
  return keyLen == msgLen;
}

function isDelPressed(event) {
  const key = event.keyCode || event.charCode;
  return key == 8 || key == 46;
}

msg.onkeydown = (event) => {
  if (type == "/sha/" || type == "/md5/") {
    return;
  }

  if (isDelPressed(event)) {
    msgLen--;
  }
  else {
    msgLen++;
  }

  if (isOfSameLen(msgLen, keyLen)) {
    btn.disabled = false;
  }
  else {
    btn.disabled = true;
  }
};

key.onkeydown = (event) => {
  if (type == "sha" || type == "md5") {
    return;
  }

  if (isDelPressed(event)) {
    keyLen--;
  }
  else {
    keyLen++;
  }

  if (isOfSameLen(msgLen, keyLen)) {
    btn.disabled = false;
  }
  else {
    btn.disabled = true;
  }
};

function clear() {
  msg.value = "";
  key.value = "";
  msgLen = 0;
  keyLen = 0;
}