// 마이페이지에서 활동내역관련
document.addEventListener('DOMContentLoaded', function() {
    // 요소 선택
    const tabs = {
        community: document.getElementsByClassName('tab1')[0],
        review: document.getElementsByClassName('tab2')[0],
        comment: document.getElementsByClassName('tab3')[0]
    };

    const contents = {
        community: document.getElementsByClassName('main-container-list community')[0],
        review: document.getElementsByClassName('main-container-list review')[0],
        comment: document.getElementsByClassName('main-container-list comment')[0]
    };

    // 초기 상태 설정
    tabs.community.classList.add('active');
    contents.community.style.display = 'block';
    contents.review.style.display = 'none';
    contents.comment.style.display = 'none';

    // 탭 클릭 이벤트 리스너
    tabs.community.addEventListener('click', function() {
        tabs.community.classList.add('active');
        tabs.review.classList.remove('active');
        tabs.comment.classList.remove('active');

        contents.community.style.display = 'block';
        contents.review.style.display = 'none';
        contents.comment.style.display = 'none';
    });

    tabs.review.addEventListener('click', function() {
        tabs.review.classList.add('active');
        tabs.community.classList.remove('active');
        tabs.comment.classList.remove('active');

        contents.community.style.display = 'none';
        contents.review.style.display = 'block';
        contents.comment.style.display = 'none';
    });

    tabs.comment.addEventListener('click', function() {
        tabs.comment.classList.add('active');
        tabs.community.classList.remove('active');
        tabs.review.classList.remove('active');

        contents.community.style.display = 'none';
        contents.review.style.display = 'none';
        contents.comment.style.display = 'block';
    });

    //
    //
    // // 요소 선택
    // const tabs = {
    //     community: document.getElementsByClassName('tab1')[0],
    //     review: document.getElementsByClassName('tab2')[0],
    //     comment: document.getElementsByClassName('tab3')[0]
    // };
    //
    // const contents = {
    //     community: document.getElementsByClassName('main-container-list community')[0],
    //     review: document.getElementsByClassName('main-container-list review')[0],
    //     comment: document.getElementsByClassName('main-container-list comment')[0]
    // };
    //
    // // 초기화 함수
    // function initializeTabs() {
    //     const rinks = window.location.pathname.split('/');
    //     const lastRink = rinks[rinks.length - 1];
    //
    //     // 초기 탭 상태 설정
    //     if (lastRink === 'community') {
    //         tabs.community.classList.add('active');
    //         tabs.review.classList.remove('active');
    //         tabs.comment.classList.remove('active');
    //
    //         contents.community.style.display = 'block';
    //         contents.review.style.display = 'none';
    //         contents.comment.style.display = 'none';
    //     } else if (lastRink === 'review') {
    //         tabs.review.classList.add('active');
    //         tabs.community.classList.remove('active');
    //         tabs.comment.classList.remove('active');
    //
    //         contents.community.style.display = 'none';
    //         contents.review.style.display = 'block';
    //         contents.comment.style.display = 'none';
    //     } else if (lastRink === 'comment') {
    //         tabs.comment.classList.add('active');
    //         tabs.community.classList.remove('active');
    //         tabs.review.classList.remove('active');
    //
    //         contents.community.style.display = 'none';
    //         contents.review.style.display = 'none';
    //         contents.comment.style.display = 'block';
    //     }
    // }
    //
    // // 초기화 함수 호출
    // initializeTabs();
    //
    // // 탭 클릭 이벤트 리스너
    // tabs.community.addEventListener('click', function() {
    //     window.history.pushState({}, '', '/user/activeList/community');
    //     initializeTabs(); // 탭 상태 재설정
    // });
    //
    // tabs.review.addEventListener('click', function() {
    //     window.history.pushState({}, '', '/user/activeList/review');
    //     initializeTabs(); // 탭 상태 재설정
    // });
    //
    // tabs.comment.addEventListener('click', function() {
    //     window.history.pushState({}, '', '/user/activeList/comment');
    //     initializeTabs(); // 탭 상태 재설정
    // });
});