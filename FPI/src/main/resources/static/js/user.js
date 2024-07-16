
// 회원가입 폼 제출
function OAuthForm(){
    var OAuthform =document.getElementById("OAuthform").submit();
    OAuthform.addEventListener("submit", function (){
        var region = document.getElementById("region").value;
        var city = document.getElementById("city").value;
        var category = document.getElementById("category").value;
    });

}

function CertifyForm(){
    var CertifyForm =document.getElementById("CertifyForm").submit();
    CertifyForm.addEventListener("submit", function (){
        var region = document.getElementById("region").value;
        var city = document.getElementById("city").value;
        var category = document.getElementById("category").value;
    })
}

// 삭제 폼 제출
function deleteForm(){
    var deleteForm =document.getElementById("deleteForm").submit();
    deleteForm.addEventListener("submit", function (){
        var userId = document.querySelector("#deleteForm .userId").value;
        var userName = document.querySelector("#deleteForm .input-box").value;

    });

}

// 모달창 관련코드들
    const modal=document.getElementById("modal")
    const input = document.querySelector(".input-box")
    const hidden = document.querySelector(".userId");
    function modalOn(){
        modal.style.display="flex"


    }

    function modalOff() {

        modal.style.display = "none"
    }
    // 모달창 보임
    const btnModal = document.getElementById("btn-modal")
    btnModal.addEventListener("click", e => {
        modalOn(e);
        hidden.style.display="none !important";
    })
    // 모달창 닫음
    const closeModal = document.getElementById("close-modal")
    closeModal.addEventListener("click", e => {
        input.value="";
        modalOff(e)
    })


