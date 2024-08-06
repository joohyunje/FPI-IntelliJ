const loginUserId = $('input[name="loginUserId"]').val();
const communityId = $('input[name="communityId"]').val();
const loginName = $('input[name="loginName"]').val();
const proName=$('input[name="proName"]').val();


// 시작시 실행할 함수
$(document).ready(function () {
    $('#editBtn').click(function () {
        window.location.href = '/community/edit/' + communityId;
    });
    const communityId = window.location.pathname.split('/')[3];
    countViews(communityId)
    getCommentsList(communityId);
})

// 게시글 삭제
document.getElementById('deleteBtn').addEventListener('click', function () {
    // var communityId = document.querySelector('input[name="communityId"]').value;
    // Form을 통해 POST 요청으로 서버에 삭제를 요청하도록 변경
    var form = document.createElement('form');
    form.method = 'post';
    form.action = '/community/delete/' + communityId;
    document.body.appendChild(form);
    form.submit();
})


// 댓글 리스트 가져옴
function getCommentsList(communityId) {
    $.ajax({
        method: 'get',
        url: '/comments/' + communityId,
        success: function (data) {
            countComment(communityId)
            let commentListArea = $('.comment-list')

            // 댓글이 작성될 해당 섹션 비우기.
            commentListArea.empty();

            // 댓글 없을 때 표시할 html
            if (data.length === 0) {
                commentListArea.append(
                    `<div class="comment-box" style="margin-top:10px;text-align: center;border: none !important">
                         작성된 댓글이 없습니다.
                    </div>`
                );
            }

            // 댓글 있을 때 목록을 뿌려줄 반복문.
            data.forEach(function (comment) {
                let commentDate = formatDate(comment.commentRegisterDate);
                let buttons = '';
                let editStr = '';

                // 작성일과 수정일을 비교해서 html 에 다른 모양으로 표시.
                if (comment.commentUpdateDate !== comment.commentRegisterDate) {
                    commentDate = formatDate(comment.commentUpdateDate);
                    editStr = ' (수정)';
                }

                // 현재 로그인된 계정과 댓글 작성자가 동일하다면 만들어줄 버튼

                if (proName === comment.author || loginName === comment.author) {
                    buttons = `
                        <div class="comment-actions" style="margin-top: 10px">
                            <button onclick="updateComment(${comment.commentId})" class="report-button">수정</button>
                            <button onclick="deleteComment(${comment.commentId})" class="send-button">삭제</button>
                        </div>
                    `
                }

                // 종합적으로 뿌려줄 html
                let commentElement = `
                    <li class="comment-box" id="comment-${comment.commentId}" style="display:flex;flex-direction: column;list-style-type: none;margin-bottom: 0.3rem;padding:0.625rem 1.875rem; ">
                        <div class="comment-body commentEdit ">
                            <div class="comment-body">
                                <strong class="comment-title">
                                <img alt="#" src="https://static.cdn.soomgo.com/upload/profile-default/soomgo_65.jpg?h=320&w=320&webp=1">
                                <span class="comment-nickname">${comment.author}</span>
                                <span class="comment-date">${commentDate}${editStr}</span>
                                </strong>
                                ${buttons}
                                <div class="comment-text">
                                    ${comment.commentContent}
                                </div>
                            </div>
                        </div>
                    </li>
                `
                // 해당 섹션에 댓글의 개수 만큼 차례대로 추가
                commentListArea.append(commentElement);
            });

            // 주소창에서 해당 댓글아이디를 찾아서 이동
            const commentId = window.location.pathname.split('/')[4];
            if (commentId) {
                const targetComment = $(`#comment-${commentId}`);
                if (targetComment.length) {
                    // 댓글을 위치로 딱 이동함
                    const offset = targetComment.offset().top;
                    $('html, body').scrollTop(offset);
                    // 강조 스타일 추가
                    targetComment.css({
                        'background-color': '#F0F8FF'
                    });
                }
            }

        },
        error: function (data) {
            console.error(data, "실패");
        }
    })
}
// 엔터키로 댓글 추가하기
function enterkey() {
    if (window.event.keyCode == 13) {
        event.preventDefault(); // 기본 동작(줄 바꿈)을 막음
        addComment();
    }
}

//댓글 추가
function addComment() {
    // let communityId = $('input[name="communityId"]').val();
    let commentContent = $('#commentContent').val().trim(); //앞뒤 공백 제거해 줘야 엔터의 공백값이 등록불가능하게 됨

    //textarea비어있으면 경고
    if (!commentContent) {
        alert('내용을 입력하세요!');
        return
    }
    $.ajax(
        {
            method: 'post',
            url: '/comments',
            contentType: 'application/json', //서버에서 내가 보낸 데이터를 읽을때 json으로 인식하여 읽어들임
            data: JSON.stringify({//json형태를 문자열로 바꿔서 보냄 키:value를 문자열 형태로
                communityId: communityId,
                commentContent: commentContent,
                userId: loginUserId
            }),
            success: function (data) {
                $('#commentContent').val('')
                getCommentsList(communityId);
            },
            error: function (data) { //매개변수 없어도 에러안남
                console.error(data)
            }

        }
    )
}

// 댓글 삭제
function deleteComment(commentId) {

    if (!confirm('정말로 삭제하시겠습니까?')) {
        return;
    }

    $.ajax({
        method: 'delete',
        url: '/comments/' + commentId,
        success: function (data) {
            console.log(data, '삭제 성공')
            getCommentsList($('input[name="communityId"]').val());
        },
        error: function (data) {
            console.error(data, '삭제 실패')
        }
    })
}
// 댓글 수정눌렀을때 수정 폼 생성 함수
function createEditForm(commentId, currentContent){
    return `
<div style="display: flex">
        <div style="width: 100%">
            <label for="commentContent"> 댓글 </label>
            <textarea class="comment-edit-content editTextarea" name="comment-input" id="commentContent"  onkeyup="enterkey()" >${currentContent}</textarea>
        </div>
        <div style="display: flex;">
            <div class="enter-box" style="display:flex;">
                <button class="btn btn-primary" onClick="editComment(${commentId})">수정완료</button>
            </div>
            <div class="enter-box" style="display:flex;">
                <button class="btn btn-secondary" onClick="cancelEdit()">취소</button>
            </div>
        </div>


</div>
`;

}

// 수정버튼누르면
function updateComment(commentId) {
    // 기존 댓글 내용을 가지고 와서, 수정 폼에 넣는다.
    let comment = $(`#comment-${commentId}`);
    let content = comment.find('.comment-text').text()
    comment.find('.commentEdit').html(createEditForm(commentId, content))
}

//수정 완료 버튼 눌렀을때
function editComment(commentId){
    let comment = $(`#comment-${commentId}`);
    //textarea : 속성이 두개 있음.
    // 데이터를 뿌려줄때는 text, 입력한 데이터를 가져올때는 value
    let content = comment.find('.comment-edit-content').val()

    $.ajax({
        method:'put',
        url : '/comments/' + commentId,
        contentType: 'application/json',
        data: JSON.stringify({
            commentContent: content
        }),
        success : function(data) {
            console.log('수정 성공')
            getCommentsList($('input[name="communityId"]').val());
        },
        error: function(data) {
            console.log('수정 실패')
        }
    })

}
//수정취소 버튼 눌렀을 때, 새롭게 로드
function cancelEdit(){
    getCommentsList($('input[name="communityId"]').val());
}

// 댓글카운트 가져오기
function countComment(communityId){
$.ajax({
    url:'/comments/countComment/'+ communityId,
    type:'GET',
    success : function (response){
        var commentCount = response.commentCount;
        $('#commentCount').text('댓글 '+ commentCount);
    },
    error: function() {
        console.log('실패slslsl')
    }
})
}

// 조회수 가져오기
function countViews(communityId){
    $.ajax({
        url:'/countViews/'+ communityId,
        type:'GET',
        success : function (response){
            var countViews = response.countViews;
            $('#countViews').text('조회수 '+ countViews);
        },
        error: function() {
            console.log('실패slslsl')
        }
    })
}

// 날짜 포맷
function formatDate(dateString) {
    const now = new Date();
    const commentDate = new Date(dateString); // 문자열을 Date 객체로 변환

    const nowYear = now.getFullYear();
    const nowMonth = now.getMonth();
    const nowDate = now.getDate();

    const commentYear = commentDate.getFullYear();
    const commentMonth = commentDate.getMonth();
    const commentDateDate = commentDate.getDate();

    let displayText = "";

    // 년, 월, 일이 모두 같은 경우 "오늘"로 표시
    // if (nowYear === commentYear && nowMonth === commentMonth && nowDate === commentDateDate) {
    //     displayText = "오늘";
    // } else {
    // 그 외의 경우, 정해진 포맷으로 표시
    const yy = commentYear.toString().slice(-2); // 마지막 두 자리를 가지고 옴.
    const M = commentMonth + 1; // 월은 0부터 시작하므로 1을 더해줍니다.
    const d = commentDateDate;
    const HH = commentDate.getHours().toString().padStart(2, '0');
    const mm = commentDate.getMinutes().toString().padStart(2, '0'); // 두자리 수 일 때 앞에 0을 붙임.

    displayText = `${yy}년 ${M}월 ${d}일 ${HH}시 ${mm}분`;
    // }
    return displayText;
}



