document.getElementById('submitBtn').addEventListener('click', function() {
    // 현재 페이지에서 .awardCount 클래스를 가진 요소들의 개수를 구합니다.
    var awardCounts = document.getElementsByClassName('awardCount').length;

    // awardCount 값을 폼 데이터에 추가합니다.
    document.getElementById('awardCount').value = awardCounts;

    // alert 창에 awardCounts 값을 표시합니다.
    alert(awardCounts);

    // 폼을 서버로 제출합니다.
    document.getElementById('CertifyForm').submit();
});

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
        // document.body.style.overflow = 'hidden';

    })
    // 모달창 닫음
    const closeModal = document.getElementById("close-modal")
    closeModal.addEventListener("click", e => {
        input.value="";
        // document.body.style.overflow = 'auto';
        modalOff(e);
    })


