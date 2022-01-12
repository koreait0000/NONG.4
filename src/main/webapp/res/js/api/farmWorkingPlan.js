const tableRoundDiv = document.querySelector('.tableRound');
const t = document.getElementById('${workingList.kidofcomdtySeCode}');


//const tbody = document.querySelector('.tbody');

function fncViewButtom(kidofcomdtySeCode) {
    console.log('text : ' + t)
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
        textSpan.className = 'textSpan';
        fileSpan.className = 'fileSpan';
        textSpan.append(item.sj);
        fileSpan.append(iconImg);

        cntDiv.append(textSpan, fileSpan);
        tableRoundDiv.append(cntDiv);
    })

}

fncViewButtom(210004);