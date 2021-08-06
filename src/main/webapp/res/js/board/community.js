const comm_pagingElem = document.querySelector('#comm-paging');

function moveToDetail(iboard){
    location.href = 'boardDetail?iboard=' + iboard;
}

function communityPaging() {
    const prevBtn = document.createElement('button');
    const nextBtn = document.createElement('button');
    let currentPage = 1;

    prevBtn.innerText = '이전';
    nextBtn.innerText = '다음';
    comm_pagingElem.append(prevBtn,nextBtn);

    if(currentPage == 1){
        prevBtn.disabled = true;
    }

    prevBtn.addEventListener('click', () => {
        currentPage = currentPage - 1;
        if(currentPage == 1){
            prevBtn.disabled = true;
        }
        if(currentPage != 4){
            nextBtn.disabled = false;
        }
        console.log('currentPage : ' + currentPage);
        communityPagingAjax(currentPage);

    });

    nextBtn.addEventListener('click', () => {
        currentPage = currentPage + 1;
        if(currentPage != 1){
            prevBtn.disabled = false;
        }
        if(currentPage == 4) {
            nextBtn.disabled = true;
        }
        console.log('currentPage : ' + currentPage);
        communityPagingAjax(currentPage);

    });
    console.log('paging c : ' + currentPage);

    communityPagingAjax(currentPage);
}

function communityPagingAjax(currentPage){
    var currentPage = currentPage;

    fetch('community/' + currentPage)
        .then(function(res){
            return res.json();
        })
        .then(function(myJson) {
            console.log(myJson);
            makeCommunityPaging(myJson);
        });

}

function makeCommunityPaging(data){
    comm_pagingElem.innerHTML = '';

    data.forEach(function (item){

    })
}
function swiper(){
    const swiper = new Swiper('.swiper-container', {
        // Optional parameters
        slidesPerView: 2, // 동시에 보여줄 슬라이드 갯수
        spaceBetween: -60, // 슬라이드간 간격
        direction: 'horizontal',
        loop: true,
        autoplay: {  // 자동 슬라이드 설정 , 비 활성화 시 false
            delay: 3000,   // 시간 설정
            disableOnInteraction: false,  // false로 설정하면 스와이프 후 자동 재생이 비활성화 되지 않음
        },
        // If we need pagination
        pagination: {
            el: '.swiper-pagination',
        },
        // Navigation arrows
        navigation: {
            nextEl: '.swiper-button-next',
            prevEl: '.swiper-button-prev',
        }
    });
}

swiper();
communityPaging();
