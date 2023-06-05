const logoutBtn = document.getElementById("logOut");

logoutBtn.addEventListener("click", function(event) {
    event.preventDefault();
    if (confirm("Are you sure you want to log out?")) {
        const xhr = new XMLHttpRequest();
        xhr.open("GET", "/logout");
        xhr.onload = function() {
            window.location.href = "/signIn";
        };
        xhr.send();
    }
});
