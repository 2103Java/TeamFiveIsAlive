


function login() {
    console.log("login...");
    if (/*authentication*/true) {
        document.getElementById("loginPanel").style.display = "none";
        document.getElementById("createAccountPanel").style.display = "none";
        document.getElementById("userLandingPanel").style.display = "block";
        document.getElementById("adminLandingPanel").style.display = "none";
        document.getElementById("a1").style.display = "contents";
    }
}
function showCreateAccountPanel() {
    document.getElementById("loginPanel").style.display = "none";
    document.getElementById("createAccountPanel").style.display = "block";
    document.getElementById("userLandingPanel").style.display = "none";
    document.getElementById("adminLandingPanel").style.display = "none";
}
function showUserLandingPanel() {
    document.getElementById("loginPanel").style.display = "none";
    document.getElementById("createAccountPanel").style.display = "block";
    document.getElementById("userLandingPanel").style.display = "none";
    document.getElementById("adminLandingPanel").style.display = "none";
}
function showAdminLandingPanel() {
    document.getElementById("loginPanel").style.display = "none";
    document.getElementById("createAccountPanel").style.display = "block";
    document.getElementById("userLandingPanel").style.display = "none";
    document.getElementById("adminLandingPanel").style.display = "none";
}