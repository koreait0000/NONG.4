const listBoardElem  = document.querySelector('.list-board');
const boardListBottomElem = document.querySelector('.boardlist-bottom');

const pageMaker = document.createElement('div');

let prev;
let next;
let startPage;
let endPage;
let currentPage = boardListBottomElem.dataset.pagenum;
const keyword   = boardListBottomElem.dataset.keyword;

let prevView = document.createElement('a');
let nextView = document.createElement('a');

let currentPageView;
const currentPageDiv = document.createElement('div');

function searchPaging() {

searchPagingAjax(currentPage);

    function searchPagingAjax(currentPage) {
        currentPage = currentPage;

        fetch('totalSearch/' + currentPage +'/'+ keyword)
            .then(res => res.json())
            .then(myJson => {
                pagingBottom(myJson.pageMaker);
                searchList(myJson.total);
            })
    }

    function pagingBottom(data) {
        currentPageDiv.innerText = '';
        endPage   = data.endPage;
        startPage = data.startPage;
        prev      = data.prev;
        next      = data.next;

        if(prev) {
            prevView.innerText = '이전';

            prevView.addEventListener('click', () => {
                currentPage = startPage - 5;
                currentPageDiv.innerText = '';
                if(event.stopImmediatePropagation()) event.stopImmediatePropagation(); // 동일 DOM의 이벤트 전파 막기
                searchPagingAjax(currentPage);
            })
        } else {
            prevView.innerText = '';
        }

        for(let i= startPage; i<= endPage; i++) {
            currentPageView = document.createElement('a');

            currentPageView.innerText += i;

            currentPageView.addEventListener('click', () => {
                currentPageDiv.innerText = '';
                currentPage = i;
                searchPagingAjax(currentPage);
            })

            currentPageDiv.append(currentPageView);
        }

        if(next && endPage > 0) {
            nextView.innerText = '다음';

            nextView.addEventListener('click', () => {
                currentPage = startPage + 5;

                currentPageDiv.innerText = '';
                if(event.stopImmediatePropagation()) event.stopImmediatePropagation(); // 동일 DOM의 이벤트 전파 막기
                searchPagingAjax(currentPage);
            })
        } else {
            nextView.innerText = '';
        }
        pageMaker.append(currentPageDiv);
        pageMaker.append(prevView, nextView);
    }
}

function searchList(data) {

    listBoardElem.innerHTML = '';

    data.forEach(function (item) {
        const sectionDiv   = document.createElement('div');
        const listTitleDiv = document.createElement('div');
        const listCtntDiv  = document.createElement('div');
        const listNickDiv  = document.createElement('div');
        const titleStrong  = document.createElement('strong');
        const ctntStrong   = document.createElement('strong');
        const nickStrong   = document.createElement('strong');
        const regdtSpan    = document.createElement('span');

        sectionDiv.className   = 'section';
        listTitleDiv.className = 'boardlist-top';
        titleStrong.className  = 'strong-title';
        ctntStrong.className   = 'strong-ctnt';

        titleStrong.append(item.title);
        ctntStrong.append(item.ctnt);
        nickStrong.append(item.userNick);
        regdtSpan.append(item.regdt);

        titleStrong.addEventListener('click', () => {
            moveToDetail(item.iboard);
        })

        listTitleDiv.append(titleStrong);
        listCtntDiv.append(ctntStrong);
        listNickDiv.append(nickStrong,regdtSpan);

        sectionDiv.append(listTitleDiv, listCtntDiv, listNickDiv);
        listBoardElem.append(sectionDiv,pageMaker);
    })
}
pageMaker.className = 'pageMaker'

boardListBottomElem.append(pageMaker);

// ##################################### boardList #####################################

const listVideoElem = document.querySelector('.list-video');
const videoPageMaker = document.createElement('div');

let videoPrevView = document.createElement('a');
let videoNextView = document.createElement('a');

let currentVideoPageView;
const currentVideoPageDiv = document.createElement('div');
function searchVideoPaging() {

    searchVideoPagingAjax(currentPage);

    function searchVideoPagingAjax(currentPage) {
        currentPage = currentPage;

        fetch('totalSearchVideo/' + currentPage +'/'+ keyword)
            .then(res => res.json())
            .then(myJson => {
                videoPagingBottom(myJson.pageMaker);
                searchVideoList(myJson.apiVideoDomain.data);
            })
    }
    function videoPagingBottom(data) {
        endPage   = data.endPage;
        startPage = data.startPage;
        prev      = data.prev;
        next      = data.next;

        if(prev) {
            videoPrevView.innerText = '이전';

            videoPrevView.addEventListener('click', () => {
                currentPage = startPage - 5;
                currentVideoPageDiv.innerText = '';
                if(event.stopImmediatePropagation()) event.stopImmediatePropagation(); // 동일 DOM의 이벤트 전파 막기
                searchVideoPagingAjax(currentPage);
            })
        } else {
            videoPrevView.innerText = '';
        }

        for(let i= startPage; i<= endPage; i++) {
            currentVideoPageView = document.createElement('a');

            currentVideoPageView.innerText += i;

            currentVideoPageView.addEventListener('click', () => {
                currentVideoPageDiv.innerText = '';
                currentPage = i;
                searchVideoPagingAjax(currentPage);
            })

            currentVideoPageDiv.append(currentVideoPageView);
        }

        if(next && endPage > 0) {
            videoNextView.innerText = '다음';

            videoNextView.addEventListener('click', () => {
                currentPage = startPage + 5;

                currentVideoPageDiv.innerText = '';
                if(event.stopImmediatePropagation()) event.stopImmediatePropagation(); // 동일 DOM의 이벤트 전파 막기
                searchVideoPagingAjax(currentPage);
            })
        } else {
            videoNextView.innerText = '';
        }
        videoPageMaker.append(currentVideoPageDiv);
        videoPageMaker.append(videoPrevView, videoNextView);
    }
}
function searchVideoList(data) {
    listVideoElem.innerHTML = '';

    data.forEach(function (item) {
        const videoSectionDiv   = document.createElement('div');
        const videoInnerDiv     = document.createElement('div');
        const videoLinkStrong   = document.createElement('strong');
        const videoSjStrong     = document.createElement('strong')
        const videoClipSjStrong = document.createElement('strong');
        const videoImg = document.createElement('img');

        videoSectionDiv.className   = 'section';
        videoSjStrong.className     = 'strong-sj';
        videoClipSjStrong.className = 'strong-mvpclipsj';

        videoImg.className = 'pointer';
        videoImg.src       = item.videoImg;

        videoLinkStrong.append(videoImg);
        videoSjStrong.append(item.sj);
        videoClipSjStrong.append(item.mvpClipSj);

        videoInnerDiv.append(videoLinkStrong, videoSjStrong, videoClipSjStrong);
        videoSectionDiv.append(videoInnerDiv);

        listVideoElem.append(videoSectionDiv, videoPageMaker);
    })
}

videoPageMaker.className = 'pageMaker'

listVideoElem.append(videoPageMaker);

searchPaging();
searchVideoPaging();