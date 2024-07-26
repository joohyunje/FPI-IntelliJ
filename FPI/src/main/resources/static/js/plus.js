// 폼에서 +누르면 자격증 추가,경력 추가 input,textarea생기는거 구현

document.addEventListener('DOMContentLoaded', function() {
    const plus = document.querySelector('.plus');

    const awardPlus = document.querySelector('.awardPlus');

    // var certiCount = document.getElementsByClassName('certiCount').length;
    // var certiCounts = document.getElementById('certiCount');
    // certiCounts.value= certiCount;



    plus.addEventListener('click', function() {
        // HTML 문자열을 생성하여 새로운 자격증 입력 필드를 추가
        const CertiLabel = `
          <div class="certi-label certiCount">
            <input type="text" id="certiOrgan" name="certiOrgan" class="form-control" style="margin-right: 10px;" placeholder="자격증 발급기관을 입력하세요">
            <input type="text" id="certiNum" name="certiNum" class="form-control" placeholder="자격증 번호를 입력하세요">
            <div class="minus" >-</div>
          </div>
        `;
        // 바로 아래에 새로운 자격증 입력 필드 추가
        const parentDiv = document.querySelector('.plusPoint');
        parentDiv.insertAdjacentHTML('beforeend', CertiLabel);

        const minus = parentDiv.querySelector('.certi-label:last-child .minus');
        if(minus){
            minus.addEventListener('click', function(e) {
                if (e.target.classList.contains('minus')) {

                    //closest :탐색하여 가장 가까운 지정된 CSS 선택자와 일치하는 요소를 반환
                    const certiLabel = e.target.closest('.certi-label');
                    if (certiLabel) {
                        // certi-label 요소 삭제
                        certiLabel.remove();
                    }
                }
            });

        }

    });


    awardPlus.addEventListener('click', function() {
        // HTML 문자열을 생성하여 새로운 자격증 입력 필드를 추가,숨겨진 삭제버튼 보임
        const awards = `
            <div class = "award" style="display: flex">
            <textarea id="award" name="award" maxlength="255" class="textarea awardCount"></textarea>
            <div class="awardMinus" >-</div>
            </div>
        `;
        const parentDiv = document.querySelector('.awardPlusPoint');
        parentDiv.insertAdjacentHTML('beforeend', awards);
        // 바로 아래에 새로운 자격증 입력 필드 추가

        const awardMinus = parentDiv.querySelector('.award:last-child .awardMinus');
        if(awardMinus) {
            awardMinus.addEventListener('click', function (e) {
                if (e.target.classList.contains('awardMinus')) {

                    const awardArea = e.target.closest('.award');
                    if (awardArea) {
                        // certi-label 요소 삭제
                        awardArea.remove();
                    }
                }
            });
        }

    });

});
