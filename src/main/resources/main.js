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
    url += `&k=${key.value}`
  }

  console.log(url);

  get(url, (resp) => {
    out.classList.remove("hide");
    console.log(resp);
    out.innerHTML = resp;
  });
}

function get(url, fun) {
  loader.classList.remove("hide");

  let req = new XMLHttpRequest();
  req.onreadystatechange = () => {
    loader.classList.add("hide");
    if (req.readyState == 4 && req.status == 200) {
      fun(req.response)
    }
  };

  req.open("GET", url, true);
  req.send(null);
}

function clear() {
  msg.value = "";
  key.value = "";
}