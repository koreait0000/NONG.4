const freeBoardElem     = document.querySelector('#freeBoard');
const freeBoardListElem = document.querySelector('#freeBoardList');

function handleSubmit(e) {
    e.stopPropagation();
}

function insFree(iuser) {
    const titleDiv   = document.createElement('div');
    const ctntDiv    = document.createElement('div');
    const triggerDiv = document.createElement('div');
    const resetDIv   = document.createElement('div');
    const titleVal   = document.createElement('input');
    const ctntVal    = document.createElement('textarea');
    const triggerVal = document.createElement('input');

    titleVal.type = 'text';
    titleVal.name = 'title';
    titleVal.placeholder = '제목';
    titleDiv.append(titleVal);

    ctntVal.name = 'ctnt';
    ctntVal.placeholder = '내용';
    ctntDiv.append(ctntVal);

    triggerVal.type = 'submit';
    triggerVal.value = '글쓰기';
    triggerDiv.append(triggerVal);

    resetDIv.innerHTML = `
    <input type="reset" value="초기화">
    `;
    freeBoardElem.append(triggerDiv,resetDIv,titleDiv,ctntDiv);
    triggerDiv.addEventListener('click',() => {

        const title = titleVal.value;
        const ctnt  = ctntVal.value;
        const iuser = freeBoardElem.dataset.iuser;
        console.log('title : ' +title);
        if(title.length === 0 && ctnt.length === 0) {
            console.log(iuser);
            alert('제목과 내용을 입력하세요.');
            return;
        }
        const param = {
            title,ctnt,iuser
        }
        console.log('param : '+param.valueOf());
            fetch('freeBoard', {
                method: 'POST',
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(param)
            })
                .then(res => res.json())
                .then(myJson => {
                    console.log('리절트 : '+myJson.result);
                    switch (myJson.result) {
                        case 0:
                            alert('등록에 실패하였씁니다.');
                            break;
                        case 1:
                            alert('ㅎㄱㄷ');
                            titleVal.value = '';
                            ctntVal.value  = '';
                            selFree();
                            break;
                    }
                });
    });
    // freeBoardElem.append(formDiv);
}

function selFree() {
    // let iboard = freeBoardListElem.dataset.iboard;
    // let iuser  = freeBoardListElem.dataset.iuser;
    // console.log('iboard : '+iboard);
    // console.log('iuser : '+iuser);
    const tableElem   = document.createElement('table');
    const trElem      = document.createElement('tr');
    const thElemNick  = document.createElement('th');
    const thELemTitle = document.createElement('th');
    const thElemCtnt  = document.createElement('th');
    const thElemRegdt = document.createElement('th');

    thElemNick.innerText  = '작성자';
    thELemTitle.innerText = '제목';
    thElemCtnt.innerText  = '내용';
    thElemRegdt.innerText = '작성일';

    trElem.append(thElemNick);
    trElem.append(thELemTitle);
    trElem.append(thElemCtnt);
    trElem.append(thElemRegdt);

    tableElem.append(trElem);

    freeBoardListElem.append(tableElem);

}
insFree();
selFree();