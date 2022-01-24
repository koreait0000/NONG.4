const comm_pagingElem     = document.querySelector('#news-list');
const comm_paging_btnElem = document.querySelector('#news-paging-btn');
const commListDiv         = document.createElement('div');

const prevBtn = document.createElement('button');
const nextBtn = document.createElement('button');

function communityPaging() {
    let currentPage = 1;

    prevBtn.innerText = '이전';
    nextBtn.innerText = '다음';

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
        communityPagingAjax(currentPage);
    });

    communityPagingAjax(currentPage);

    comm_paging_btnElem.append(nextBtn);
    comm_paging_btnElem.append(prevBtn);
}

function communityPagingAjax(currentPage){

    var currentPage = currentPage;

    fetch('community/' + currentPage)
        .then(function(res){
            return res.json();
        })
        .then(function(myJson) {
            makeCommunityPaging(myJson);
        });
}

function makeCommunityPaging(data){

    commListDiv.innerHTML = '';

    if(data.length < 5){
        nextBtn.disabled = true;
    }

    data.forEach(function (item){
        const commListElemDiv  = document.createElement('div');
        const commFirstLine    = document.createElement('div');
        const commProviderSpan = document.createElement('span');
        const commTitleSpan    = document.createElement('span');
        const commWiterSpan    = document.createElement('span');
        const commRegdtSpan    = document.createElement('span');
        const commHitCountSpan = document.createElement('span');

        commListElemDiv.className  = 'news-list-detail pointer';
        commProviderSpan.className = 'news-provider-span';
        commTitleSpan.className    = 'news-title-span';
        commRegdtSpan.className    = 'news-regdt-span';
        commHitCountSpan.className = 'news-count-span';

        if(item.provider == 'freedom'){
            commProviderSpan.append('자유게시판');
        } else if (item.provider == 'question') {
            commProviderSpan.append('질문게시판');
        } else if (item.provider == 'strategy') {
            commProviderSpan.append('공략게시판');
        } else {
            commProviderSpan.append('친구게시판');
        }
        commTitleSpan.append(item.title);
        commWiterSpan.append(item.userNick);
        commRegdtSpan.append(item.regdt);
        commHitCountSpan.append(item.hitCount);

        commFirstLine.append(commProviderSpan);
        commFirstLine.append(commTitleSpan);

        commListElemDiv.append(commFirstLine);
        commListElemDiv.append(commWiterSpan);
        commListElemDiv.append(commRegdtSpan);
        commListElemDiv.append(commHitCountSpan);

        commListElemDiv.addEventListener('click', () => {
            moveToDetail(item.iboard);
        });

        commListDiv.append(commListElemDiv);

    })

    comm_pagingElem.append(commListDiv);

}

function apiSwiper(){
    const swiper = new Swiper('.apiSwip', {
        // Optional parameters
        slidesPerView: 2, // 동시에 보여줄 슬라이드 갯수
        spaceBetween: -60, // 슬라이드간 간격
        direction: 'horizontal',
        loop: true,
        autoplay: {  // 자동 슬라이드 설정 , 비 활성화 시 false
            delay: 3000,   // 시간 설정
            disableOnInteraction: false,  // false로 설정하면 스와이프 후 자동 재생이 비활성화 되지 않음
        },
        // Navigation arrows
        navigation: {
            nextEl: '.swiper-button-next',
            prevEl: '.swiper-button-prev',
        }
    });
}

function hotItemSwiper(){
    const swiper = new Swiper('.hotItemSwip', {
        // Optional parameters
        slidesPerView: 2, // 동시에 보여줄 슬라이드 갯수
        spaceBetween: -60, // 슬라이드간 간격
        direction: 'horizontal',
        loop: true,
        autoplay: {  // 자동 슬라이드 설정 , 비 활성화 시 false
            delay: 3000,   // 시간 설정
            disableOnInteraction: false,  // false로 설정하면 스와이프 후 자동 재생이 비활성화 되지 않음
        }
    });
}

apiSwiper();
hotItemSwiper();
communityPaging();
