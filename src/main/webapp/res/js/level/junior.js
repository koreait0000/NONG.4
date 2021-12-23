const videoListElem = document.querySelector('#videoApi');
const communityBoardElem = document.querySelector('.community-board');

function apiVideo() {
    fetch('/openapi/apiTest', {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json',
        }
    })
        .then(res => res.json())
        .then(myJson => {
            makeVideoList(myJson);
        })
}

function category(mainCategory, sType, sText) {
    const param = {
        mainCategory : mainCategory,
        sType : sType,
        sText : sText
    }
    fetch('/openapi/category', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(param)
    })
        .then(res => res.json())
        .then(myJson => {
            console.log('myJson : ' + myJson);
            makeVideoList(myJson);
        })
}
function makeVideoList(myJson){
    videoListElem.innerHTML = '';
    communityBoardElem.innerHTML = '';
    const table = document.createElement('table');
    const thead = document.createElement('thead');
    const tbody = document.createElement('tbody');

    const headTr = document.createElement('tr');
    const heading1 = document.createElement('th');
    const heading2 = document.createElement('th');
    const heading3 = document.createElement('th');
    const heading4 = document.createElement('th');

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
        category(mainCategory.value, sType.value, sText.value);
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
    communityBoardElem.append(outerRound);

    heading1.append('동영상');
    heading2.append('품목분류');
    heading3.append('주제목');
    heading4.append('짧은 기술동영상 제목');

    headTr.append(heading1, heading2, heading3, heading4);

    myJson.data.forEach(function (item){
        const bodyTr = document.createElement('tr');
        const videoImgCol = document.createElement('th');
        const stdPrdlstCodeNmCol = document.createElement('th');
        const sjCol = document.createElement('th');
        const mvpClipSjCol = document.createElement('th');
        const img = document.createElement('img');

        bodyTr.className = 'bodyTr pointer';

        img.src = item.videoImg;
        videoImgCol.append(img);
        stdPrdlstCodeNmCol.append(item.stdPrdlstCodeNm);
        sjCol.append(item.sj);
        mvpClipSjCol.append(item.mvpClipSj);
        bodyTr.append(videoImgCol, stdPrdlstCodeNmCol, sjCol, mvpClipSjCol);

        bodyTr.addEventListener('click', () => {
            location.href = 'junior?videoLink=' + item.videoLink;
        })
        tbody.append(bodyTr);
    })

    thead.append(headTr);
    table.append(thead, tbody);

    videoListElem.append(table);
    communityBoardElem.after(videoListElem);
}

apiVideo();