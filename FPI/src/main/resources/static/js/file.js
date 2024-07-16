document.getElementById('fileInput').addEventListener('change',function (event){
    const file = event.target.files[0]
    const proImg = document.getElementById('proImg');
    if(file){
        const reader = new FileReader(); //파일을 읽는 객체
        reader.onloadend = function (e) { //파일 읽어들였다면
            const img = document.createElement('img'); //img태그 추가
            img.src= e.target.result; //img태그에 이미지 주소추가
            proImg.innerHTML='';
            proImg.appendChild(img);
        };
        reader.readAsDataURL(file);
    }
    else{
        proImg.innerHTML='';
    }
})
