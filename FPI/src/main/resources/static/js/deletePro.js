// 전문가 탈퇴 폼 제출
function deleteForm(){
    var deleteForm =document.getElementById("deleteForm").submit();
    deleteForm.addEventListener("submit", function (){
        var proId = document.querySelector("#deleteForm .proId").value;
        var proName = document.querySelector("#deleteForm .input-box").value;

    });

}

// 탈퇴모달창 관련코드들
    const modal=document.getElementById("modal");
    const input = document.querySelector(".input-box");
    const hidden = document.querySelector(".proId");

    function modalOn(){
        modal.style.display="flex"
    }

    function modalOff() {
        modal.style.display = "none"
    }
    // 모달창 보임
    const btnModal = document.getElementById("btn-modal");
    btnModal.addEventListener("click", e => {
        modalOn(e);
        hidden.style.display="none !important";
        // document.body.style.overflow = 'hidden';
    })

    // 모달창 닫음
    const closeModal = document.getElementById("close-modal");
    closeModal.addEventListener("click", e => {
        input.value="";
        // document.body.style.overflow = 'auto';
        modalOff(e);
    })


