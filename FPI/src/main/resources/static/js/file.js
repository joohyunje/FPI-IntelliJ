document.getElementById('fileInput').addEventListener('change',function (event){
    const file = event.target.files[0]
    const proImg = document.getElementById('proImg');
    const proImgChange = document.getElementById('proImgChange');


    if(file){
        const reader = new FileReader(); //파일을 읽는 객체
        reader.onloadend = function (e) { //파일 읽어들였다면
            //img태그 추가
            proImgChange.src= e.target.result; //img태그에 이미지 주소추가
            proImg.innerHTML='';

        };
        reader.readAsDataURL(file);
    }
    // 파일선택 취소시 초기화
    else{
        proImg.innerHTML='';
    }
})
