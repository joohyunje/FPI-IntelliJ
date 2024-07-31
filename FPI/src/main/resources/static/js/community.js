const loginUserId = $('input[name="loginUserId"]').val();
const communityId = $('input[name="communityId"]').val();
const loginName = $('input[name="loginName"]').val();
const proName=$('input[name="proName"]').val();


// 시작시 실행할 함수
$(document).ready(function () {
    $('#editBtn').click(function () {
        // var communityId = document.querySelector('input[name="communityId"]').value;
        window.location.href = '/community/edit/' + communityId;
    });
    // const communityId = $('input[name="communityId"]').val(); //j쿼리 문법
    getCommentsList(communityId); //
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

// 게시글 좋아요
document.getElementById('like').addEventListener('click', function () {
    // var communityId = document.querySelector('input[name="communityId"]').value;
    // var loginUserId = document.querySelector('input[name="loginUserId"]').value;

    var iconColor = document.querySelector(".like-icon");
    if (iconColor.style.color === 'black') {
        iconColor.style.color = 'rgb(106, 118, 133)';
    } else {
        iconColor.style.color = 'black';
    }

    var likeForm = document.createElement('form');
    likeForm.method = 'post';
    likeForm.action = '/community/like/' + communityId;

    // input hidden으로 로그인유저아이디를 컨트롤러로 넘겨줌
    var Input = document.createElement('input');
    Input.type = 'hidden';
    Input.name = 'loginUserId';
    Input.value = loginUserId;
    likeForm.appendChild(Input);
    document.body.appendChild(likeForm);
    likeForm.submit();

})

// 댓글 리스트 가져옴
function getCommentsList(communityId) {
    $.ajax({
        method: 'get',
        url: '/comments/' + communityId,
        success: function (data) {
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
                    <li class="comment-box " id="comment-${comment.commentId}" style="display:flex;flex-direction: column;list-style-type: none;margin-bottom: 0.3rem;padding:0.625rem 1.875rem; ">
                        <strong class="comment-title">
                            <img alt="#" src="https://static.cdn.soomgo.com/upload/profile-default/soomgo_65.jpg?h=320&w=320&webp=1">
                            <span class="comment-nickname">${comment.author}</span>
                            <span class="comment-date">${commentDate}${editStr}</span>
                        </strong>
<!--                        <img class="delete-icon" alt="#" width="10px" height="10px" src="https://w7.pngwing.com/pngs/447/77/png-transparent-computer-icons-x-mark-symbol-check-mark-symbol-miscellaneous-cross-sign.png">-->
                        ${buttons}
                        <div class="comment-text">
                            ${comment.commentContent}
                        </div>
                    </li>
                `
                // 해당 섹션에 댓글의 개수 만큼 차례대로 추가
                commentListArea.append(commentElement);
            })
        },
        error: function (data) {
            console.error(data, "실패");
        }
    })
}

// 엔터키로 댓글 추가하기
function enterkey() {
    if (window.event.keyCode == 13) {
        updateComment();
    }
}

//댓글 추가
function updateComment() {
    // let communityId = $('input[name="communityId"]').val();
    let commentContent = $('#commentContent').val();

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
    // 매개 변수로 pk 잘 넘어왔는지 확인.
    // alert(commentId)
    // console.log(commentId)

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



