const videoListElem = document.querySelector('#videoApi');

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
function makeVideoList(myJson){
    const table = document.createElement('table');
    const thead = document.createElement('thead');
    const tbody = document.createElement('tbody');

    const headTr = document.createElement('tr');
    const heading1 = document.createElement('th');
    const heading2 = document.createElement('th');
    const heading3 = document.createElement('th');
    const heading4 = document.createElement('th');

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
        console.log('link : ' + item.videoLink);
        bodyTr.append(videoImgCol, stdPrdlstCodeNmCol, sjCol, mvpClipSjCol);

        bodyTr.addEventListener('click', () => {
            location.href = 'junior?videoLink=' + item.videoLink;
        })
        tbody.append(bodyTr);
    })

    thead.append(headTr);
    table.append(thead, tbody);

    console.log(myJson.totalCount);

    videoListElem.append(table);
}

apiVideo();