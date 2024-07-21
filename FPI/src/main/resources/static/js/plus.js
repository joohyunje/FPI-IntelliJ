document.addEventListener('DOMContentLoaded', function() {
    const plus = document.querySelector('.plus');
    const minus = document.querySelector('.minus');

    plus.addEventListener('click', function() {
        // HTML 문자열을 생성하여 새로운 자격증 입력 필드를 추가,숨겨진 삭제버튼 보임
        minus.style.display = 'flex';
        const CertiLabel = `
          <div class="certi-label">
            <input type="text" id="certiOrgan" name="certiOrgan" class="form-control" style="margin-right: 10px;" placeholder="자격증 발급기관을 입력하세요">
            <input type="text" id="certiNum" name="certiNum" class="form-control" placeholder="자격증 번호를 입력하세요">
          </div>
        `;
        // 바로 아래에 새로운 자격증 입력 필드 추가
        const parentDiv = document.querySelector('.plusPoint');
        parentDiv.insertAdjacentHTML('beforeend', CertiLabel);

    });

    minus.addEventListener('click', function() {
        const certiLabels = document.querySelectorAll('.certi-label');
        if (certiLabels.length > 2) { // 이름과, 입력필드로 인해 2개이상이여야함
            const lastCertiLabel = certiLabels[certiLabels.length - 1];
            lastCertiLabel.remove();
            if (certiLabels.length === 3) { //조회시 3개라면 1개가 추가된것, 그것이 삭제되면 -없어져야함
                minus.style.display = 'none';
            }
        }
    })
});