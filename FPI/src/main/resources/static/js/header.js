let movePro = document.getElementById("movePro")
let moveUser = document.getElementById("moveUser")

movePro.addEventListener("click", function (e) {
    e.preventDefault();
    window.location.href = "http://localhost:8070/main/pro"

});

// 유저전환
moveUser.addEventListener("click", function (e) {
    e.preventDefault();
    window.location.href = "http://localhost:8070/main/user"
});
