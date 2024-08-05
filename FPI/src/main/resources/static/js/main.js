//메인에서 헤더값에 따라 css효과 다르게 적용하려고 사용
// 전문가 색상, 회원색상 구분하여 색상진행중이기 때문에

function getCurrentURL(){
    return window.location.pathname; //현재 경로 반환함
}
    let url = getCurrentURL();
    // var inputBox =document.querySelector('.input-box')
    var categorys = document.querySelectorAll('.category')
    if(url.includes('/main/pro')){
        // inputBox.addEventListener('mouseenter', function(){
        //     this.style.borderColor ='#F8CACC';
        // });
        // inputBox.addEventListener('mouseleave', function() {
        //     this.style.borderColor = ''; // 호버 해제 시 기본 배경색으로 복구
        // });
        // inputBox.addEventListener('focus', function(){
        //     this.style.borderColor ='#F8CACC';
        // });
        // inputBox.addEventListener('blur', function() {
        //     this.style.borderColor = '';
        // });// 포커스 해제 시

        categorys.forEach(function (category){
            category.addEventListener('mouseenter', function(){
                this.style.backgroundColor ='#F8CACC';
            });
            category.addEventListener('mouseleave', function() {
                this.style.backgroundColor = ''; // 호버 해제 시 기본 배경색으로 복구
            });


            category.addEventListener('focus', function(){
                this.style.backgroundColor ='#F8CACC';
            });
            category.addEventListener('blur', function() {
                this.style.backgroundColor = '';
            });

        });


    }
