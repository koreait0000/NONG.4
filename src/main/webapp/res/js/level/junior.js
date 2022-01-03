const videoSearchElem = document.querySelector('#videoSearch');
const pageMakerElem = document.querySelector('#pageMaker');

let mainCategoryS = '';
let sTypeS = 'sSj';
// let sTextS = sessionStorage.getItem('sText');
let sTextS = '';
let currentPageS = 1;

function apiVideo(sType, sText) {
    fetch('/openapi/apiTest?sType=' + sType + '&sText=' + sText, {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json',
        }
    })
        .then(res => res.json())
        .then(myJson => {
            // myJson.sText = sTextS;
            console.log('myJson.sText : ' + myJson.sText)
            // location.href = 'junior?sType=' + sType + '&sText=' + sText;
            makeVideoList(myJson);
        })
}

function category(mainCategory, sType, sText, currentPage) {
    console.log('currentPage ::::: ' + currentPage)
    const param = {
        mainCategory : mainCategory,
        sType : sType,
        sText : sText,
        pageNo : currentPage
    }
    fetch('/level/category', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(param)
    })
        .then(res => res.json())
        .then(myJson => {
            console.log('myJson  : ' + myJson)
            myJson.sText = sessionStorage.getItem('sText');
        })
}
function makeVideoList(){
    videoSearchElem.innerHTML = '';
    const outerRound = document.createElement('div');

    const innerRoundTop = document.createElement('div');
    const innerInput = document.createElement('div');
    const sType = document.createElement('select');
    const sText = document.createElement('input');

    const innerSubmit = document.createElement('div');
    const submitStrong = document.createElement('strong');
    const submitA = document.createElement('a');

    const innerRoundBottom = document.createElement('div');
    const innerCategory = document.createElement('div');
    const nTitle = document.createElement('strong');
    const mainCategorySelect = document.createElement('select');

    // console.log('categoryCode : ' + categoryCode)

    outerRound.className = 'outerRound';

    innerRoundTop.className = 'innerRound';
    innerRoundTop.type = 'select';

    const optionsSj = document.createElement('option');
    const optionsMvpClipSj = document.createElement('option');
    sType.className = 'sType';
    sType.id = 'sType';

    optionsSj.value = 'sSj';
    optionsSj.innerText = '주제목';
    optionsSj.selected;

    optionsMvpClipSj.value = 'sMvpClipSj';
    optionsMvpClipSj.innerText = '짧은 기술동영상 제목';

    const regExp = /^[가-힣|a-z|A-Z|0-9|\s]*$/; // 한글,영문,숫자만 입력가능
    sText.className = 'sText';
    sText.id = 'sText';
    sText.type = 'text';
    sText.innerText = 'sText';

    innerInput.className = 'innerInput';

    innerSubmit.className = 'innerSubmit';
    submitStrong.className = 'button'
    submitA.type = 'onclick';
    submitA.return = 'false';
    submitA.innerText = '조회';
    submitA.addEventListener('click', () => {
        console.log(sText.value.length)
        console.log('clickPageNo : ' + myJson.pageNo)
        if(sText.value === ' ') {
            alert('첫 글자는 공백을 사용할 수 없습니다.');
            sText.focus();
            sText.value = sText.value.replace(' ',''); // 공백 제거
            return false;
        }
        if(!regExp.test(sText.value) || sText.value !== '' && sText.value.length < 2) {
            alert('한영 및 숫자만 혹은 2글자 이상만 입력이 가능합니다');
            sText.focus();
            return false;
        }
        console.log('sText : ' + sText.value)
        sessionStorage.setItem('sText', sText.value);
        console.log('getItem_after : ' + sessionStorage.getItem('sText'));
        mainCategoryS = mainCategory.value;
        let sTextS = '';
        let sTypeS = '';
        sTextS = sText.value;

        sTypeS = sType.value;

        // locationValid(sTypeS, sTextS);

        //apiVideo(sTypeS, sTextS);

        // category(mainCategoryS, sTypeS, sTextS, currentPageS);
    })

    innerRoundBottom.className = 'innerRound';

    innerCategory.className = 'innerCategory';

    nTitle.className = 'nTitle';
    nTitle.innerText = '품목 분류';

    const optionDefault = document.createElement('option');
    const optionFc = document.createElement('option'); // 식량작물
    const optionIc = document.createElement('option'); // 특용작물
    const optionVc = document.createElement('option'); // 채소
    const optionFt = document.createElement('option'); // 과수
    const optionFl = document.createElement('option'); // 화훼
    const optionLp = document.createElement('option'); // 축산
    const optionIn = document.createElement('option'); // 곤충
    const optionAe = document.createElement('option'); // 농업공학
    const optionRe = document.createElement('option'); // 농촌환경
    const optionEe = document.createElement('option'); // 환경생태
    const optionSf = document.createElement('option'); // 토양비료
    const optionCs = document.createElement('option'); // 농산물안정성
    const optionMi = document.createElement('option'); // 농업경영, 정보
    const optionFr = document.createElement('option'); // 농식품자원원
    const optionAs = document.createElement('option'); // 농업재해예방
    const optionCa = document.createElement('option'); // 도시농업
    const optionBt = document.createElement('option'); // 생명공학
    mainCategorySelect.className = 'mainCategory';
    mainCategorySelect.id = 'mainCategory';
    optionDefault.value = 'DF';
    optionFc.value = 'FC';
    optionIc.value = 'IC';
    optionVc.value = 'VC';
    optionFt.value = 'FT';
    optionFl.value = 'FL';
    optionLp.value = 'LP';
    optionIn.value = 'IN';
    optionAe.value = 'AE';
    optionRe.value = 'RE';
    optionEe.value = 'EE';
    optionSf.value = 'SF';
    optionCs.value = 'CS';
    optionMi.value = 'MI';
    optionFr.value = 'FR';
    optionAs.value = 'AS';
    optionCa.value = 'CA';
    optionBt.value = 'BT';

    optionDefault.text = '선택하세요';
    optionDefault.selected;
    optionFc.text = '식량작물';
    optionIc.text = '특용작물';
    optionVc.text = '채소';
    optionFt.text = '과수';
    optionFl.text = '화훼';
    optionLp.text = '축산';
    optionIn.text = '곤충';
    optionAe.text = '농업공학';
    optionRe.text = '농촌환경';
    optionEe.text = '환경생태';
    optionSf.text = '토양비료';
    optionCs.text = '농산물안정성';
    optionMi.text = '농업경영 · 정보';
    optionFr.text = '농식품자원';
    optionAs.text = '농업재해예방';
    optionCa.text = '도시농업';
    optionBt.text = '생명공학';

    mainCategorySelect.append(optionDefault, optionFc, optionIc, optionVc, optionFt, optionFl, optionLp, optionIn, optionAe, optionRe, optionEe, optionSf, optionCs, optionMi, optionFr, optionAs, optionCa, optionBt);

    sType.append(optionsSj,optionsMvpClipSj);

    innerCategory.append(nTitle,mainCategorySelect);
    innerRoundBottom.append(innerCategory);
    innerInput.append(sType,sText);
    submitStrong.append(submitA);
    innerSubmit.append(submitStrong);
    innerRoundTop.append(innerInput,innerSubmit);
    outerRound.append(innerRoundTop,innerRoundBottom);
    videoSearchElem.append(outerRound);

    // const pageMakerDiv = document.createElement('div');
    // pageMakerDiv.className = 'pageMaker';
    // const pageNum = document.createElement('a');
    // const prevBtn = document.createElement('button');
    // const nextBtn = document.createElement('button');
    //
    // let totalData = myJson.totalCount; // 총 데이터 수
    // let dataPerPage = myJson.numOfRows; // 한 페이지에 나타낼 글 수
    // let pageCount = 10; // 페이징에 나타낼 페이지 수
    // let globalCurrentPage = 1; // 현재 페이지
    //
    // // 글 목록 표시 호출(테이블 생성)
    // // dispalyData(1, dataPerPage);
    //
    // // 페이징 표시 호출
    // pageMaker(totalData, dataPerPage, pageCount, myJson.pageNo);
    //
    // function pageMaker(totalData, dataPerPage, pageCount, currentPage) {
    //     pageMakerDiv.innerHTML = '';
    //     console.log('currentPage! : ' + currentPage)
    //
    //     let totalPage = Math.ceil(totalData / dataPerPage); // 총 페이지 수
    //     console.log('totalPage : ' + totalPage)
    //     if(totalPage < pageCount) {
    //         pageCount = totalPage;
    //     }
    //
    //     let pageGroup = Math.ceil(currentPage / pageCount); // 페이지 그룹
    //     console.log('pageGroup : ' + pageGroup);
    //     let endPage = pageGroup * pageCount; // 화면에 보여질 마지막 페이지 번호
    //
    //     if(endPage > totalPage) {
    //         endPage = totalPage;
    //     }
    //
    //     let startPage = endPage - (pageCount - 1); // 화면에 보여질 첫번째 페이지 번호
    //     console.log('startPage : '+ startPage);
    //     let prev = startPage - 1;
    //     let next = endPage + 1;
    //     prevBtn.innerHTML = '이전';
    //
    //     console.log('next : ' + next);
    //     console.log('endPage : ' + endPage);
    //     let temp;
    //
    //     if(prev > 0) {
    //         prevBtn.style.display = 'block';
    //     } else {
    //         prevBtn.style.display = 'none';
    //     }
    //     if(endPage < next) {
    //         nextBtn.innerHTML = '다음';
    //         nextBtn.style.display = 'block';
    //     }
    //     if(totalPage <= endPage) {
    //         nextBtn.style.display = 'none';
    //     }
    //     console.log('currentPage### : '+currentPage)
    //     console.log('startPage1234 : '+ startPage);
    //
    //     let mainCategoryS = '';
    //     let sTypeS = '';
    //     let sTextS = '';
    //
    //     for(temp = startPage; temp <= endPage; temp++) {
    //         if(currentPage == temp) {
    //             pageNum.innerHTML += `<a href="#" onclick="category(${mainCategoryS}, sTypeS, sTextS, ${temp});" id="${temp}">${temp}</a>`;
    //             currentPage = temp;
    //         } else {
    //             pageNum.innerHTML += `<a href="#" onclick="category(mainCategoryS, sTypeS, sTextS, ${temp});" id="${temp}">${temp}</a>`;
    //             currentPage = temp;
    //         }
    //     }
    //
    //     pageMakerDiv.append(prevBtn,pageNum, nextBtn);
    // }
    // prevBtn.addEventListener('click', () => {
    //     pageNum.innerHTML = '';
    //     myJson.pageNo = parseInt(myJson.pageNo) - 10;
    //     console.log('myJson.pageNo_prevBtn : ' + myJson.pageNo)
    //     pageMaker(totalData, dataPerPage, pageCount, myJson.pageNo);
    // })
    // nextBtn.addEventListener('click', () => {
    //     pageNum.innerHTML = '';
    //     myJson.pageNo = parseInt(myJson.pageNo) + 10;
    //     console.log('myJson.pageNo ::' + myJson.pageNo)
    //     pageMaker(totalData, dataPerPage, pageCount, myJson.pageNo);
    // })

    // videoListElem.append(table, pageMakerDiv);
    // communityBoardElem.after(videoListElem);
}

function pageMaker1(){
    pageMakerElem.innerHTML = '';
    const pageMakerDiv = document.createElement('div');
    pageMakerDiv.className = 'pageMaker';
    const pageNum = document.createElement('a');
    const prevBtn = document.createElement('button');
    const nextBtn = document.createElement('button');

    let totalData = pageMakerElem.dataset.totalcount; // 총 데이터 수
    // let dataPerPage = myJson.numOfRows; // 한 페이지에 나타낼 글 수
    let pageCount = 10; // 페이징에 나타낼 페이지 수
    let globalCurrentPage = pageMakerElem.dataset.pageno; // 현재 페이지

    console.log('pageNoDataSet : ' + pageMakerElem.dataset.pageno);

    // 글 목록 표시 호출(테이블 생성)
    // dispalyData(1, dataPerPage);

    pageMaker(totalData, pageCount, globalCurrentPage);
    // 페이징 표시 호출
    function pageMaker(totalData, pageCount, currentPage) {
        pageMakerDiv.innerHTML = '';
        console.log('currentPage! : ' + currentPage)

        let totalPage = Math.ceil(totalData / 10); // 총 페이지 수
        console.log('totalPage : ' + totalPage)
        if(totalPage < pageCount) {
            pageCount = totalPage;
        }

        let pageGroup = Math.ceil(currentPage / pageCount); // 페이지 그룹
        console.log('pageGroup : ' + pageGroup);
        let endPage = pageGroup * pageCount; // 화면에 보여질 마지막 페이지 번호

        if(endPage > totalPage) {
            endPage = totalPage;
        }

        let startPage = endPage - (pageCount - 1); // 화면에 보여질 첫번째 페이지 번호
        console.log('startPage : '+ startPage);
        let prev = startPage - 1;
        let next = endPage + 1;
        prevBtn.innerHTML = '이전';

        console.log('next : ' + next);
        console.log('endPage : ' + endPage);
        let temp;

        if(prev > 0) {
            prevBtn.style.display = 'block';
        } else {
            prevBtn.style.display = 'none';
        }
        if(endPage < next) {
            nextBtn.innerHTML = '다음';
            nextBtn.style.display = 'block';
        }
        if(totalPage <= endPage) {
            nextBtn.style.display = 'none';
        }
        console.log('currentPage### : '+currentPage)
        console.log('startPage1234 : '+ startPage);

        let mainCategoryS = '';

        for(temp = startPage; temp <= endPage; temp++) {
            if(currentPage == temp) {
                pageNum.innerHTML += `<a href="#" onclick="category(mainCategoryS, sTypeS, sTextS, ${temp});" id="${temp}">${temp}</a>`;
                currentPage = temp;
            } else {
                pageNum.innerHTML += `<a href="#" onclick="category(mainCategoryS, sTypeS, sTextS, ${temp});" id="${temp}">${temp}</a>`;
                currentPage = temp;
            }
        }

        pageMakerDiv.append(prevBtn,pageNum, nextBtn);
    }
    prevBtn.addEventListener('click', () => {
        pageNum.innerHTML = '';
        globalCurrentPage = parseInt(globalCurrentPage) - 10;
        pageMaker(totalData, pageCount, globalCurrentPage)
    })
    nextBtn.addEventListener('click', () => {
        pageNum.innerHTML = '';
        globalCurrentPage = parseInt(globalCurrentPage) + 10;
        pageMaker(totalData, pageCount, globalCurrentPage)
    })
    pageMakerElem.append(pageMakerDiv);
}
//apiVideo(sTypeS, sTextS);
//category(mainCategoryS, sTypeS, sText, currentPageS);
// makeVideoList();
//pageMaker1();