function loginForm() {
    const loginElem = document.getElementById('u-login');
    if (loginElem.style.visibility === "hidden") {
        loginElem.style.visibility = "visible";
    } else {
        loginElem.style.visibility = "hidden";
    }
}