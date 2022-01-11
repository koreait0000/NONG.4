const tableRoundDiv = document.querySelector('.tableRound');

function fncViewButtom(kidofcomdtySeCode) {
    const active = document.querySelector('.active');
    if(active != null){
        active.classList.remove('active');
    }
    let code = kidofcomdtySeCode;
    const aTag = document.getElementById(code);
    aTag.classList.add('active');

    fetch('farmWorkingPlan',
        {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(kidofcomdtySeCode)
        })
        .then(res => res.json())
        .then(myJson => {
            tabArea(myJson);
        })
}

function tabArea(myJson) {
    tableRoundDiv.innerHTML = '';
    myJson.workScheduleLst.forEach(function (item){
        const cntDiv = document.createElement('div');
        const textSpan = document.createElement('span');
        const fileSpan = document.createElement('span');
        const iconImg = document.createElement('img');

        cntDiv.className = 'scheduleDiv';

        iconImg.src = 'http://www.nongsaro.go.kr/ps/img/icon/icon_file.gif';
        iconImg.addEventListener('click', () => {
            location.href = item.fileDownUrlInfo;
        })

        textSpan.addEventListener('click', () => {
            location.href = 'farmWorkingInfo?cntntsNo='+item.cntntsNo;
        })
        textSpan.className = 'textSpan pointer';
        fileSpan.className = 'fileSpan pointer';
        textSpan.append(item.sj);
        fileSpan.append(iconImg);

        cntDiv.append(textSpan, fileSpan);
        tableRoundDiv.append(cntDiv);
    })

}

fncViewButtom(210004);