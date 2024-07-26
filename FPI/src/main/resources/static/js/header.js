// 전문가 전환,회원전환시 메인페이지로 이동하게 되어있음
// status바꿔줌, 컨트롤러에서 status에 따라 세션에 저장되는 값 바뀌게 설정되어있음
//header html에서 그 세션값에 따라 보여지는게 다르게 구현

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
