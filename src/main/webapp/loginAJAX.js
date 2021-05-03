let stb = document.getElementById("submitButton");

stb.addEventListener('click', ((e) => login(e)));

function login(e) {
    window.location='ViewTicketsUser.html';
}