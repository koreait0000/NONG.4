const cmtFrmElem   = document.querySelector('#cmtFrm');
const cmtListElem  = document.querySelector('#cmtList');
const boardModElem = document.querySelector('#boardMod');
const updParentTElem = document.querySelector('#updParentT');
const updParentCElem = document.querySelector('#updParentC');
const originTitleElem = document.querySelector('#title');
const originCtntElem = document.querySelector('#ctnt');

//const setTitle = boardModElem.dataset.title;
//const setCtnt  = boardModElem.dataset.ctnt;

function boardUpd() {
    const updSpan    = document.createElement('span');
    const delSpan    = document.createElement('span');
    const updBtn     = document.createElement('button');
    const delBtn     = document.createElement('button');
    const titleDiv   = document.createElement('div');
    const ctntDiv    = document.createElement('div');
    const titleInput = document.createElement('input');
    const ctntInput  = document.createElement('textarea');

    const realBtnU = document.createElement('button');

    updBtn.innerText       = '수정';
    delBtn.innerText       = '삭제';
    realBtnU.innerText     = '진짜수정';
    realBtnU.style.display = 'none';

    ctntInput.style.width = 300;
    ctntInput.style.height = 200;

    updBtn.addEventListener('click',() => {
        cmtFrmElem.style.display      = 'none';
        originTitleElem.style.display = 'none';
        originCtntElem.style.display  = 'none';
        cmtListElem.style.display     = 'none';
        updBtn.style.display   = 'none';
        realBtnU.style.display = 'block'

        titleInput.type  = 'text';

        titleInput.value = setTitle;
        ctntInput.value  = setCtnt;

        titleDiv.append(titleInput);
        ctntDiv .append(ctntInput);

        updParentTElem.append(titleDiv);
        updParentCElem.append(ctntDiv);
        console.log('originT  ! : '+ originTitleElem.innerHTML);
        console.log('titleInput !! : ' + titleInput.value);

        realBtnU.addEventListener('click',()=> {
            const param = {
                iboard:   boardModElem.dataset.iboard,
                provider: boardModElem.dataset.provider,
                title:    titleInput.value,
                ctnt:     ctntInput.value
            };
            console.log(param);

            const init = {
                method: 'PUT',
                body: JSON.stringify(param),
                headers: {
                    'accept' : 'application/json',
                    'content-type' : 'application/json;charset=UTF-8'
                }
            };

            fetch('boardUpdate',init)
                .then(res => res.json())
                .then(myJson => {
                    console.log('json : '+myJson.data)
                    switch (myJson.data) {
                        case 0:
                            alert('오류입니다.');
                            break;
                        case 1:
                            console.log('titleInput real : ' + titleInput.value);
                            location.reload();
                            break;

                    }
                })
        })

    })
    delBtn.addEventListener('click',() => {
        const param = {
            iboard:   boardModElem.dataset.iboard,
            provider: boardModElem.dataset.provider,
            title:    titleInput.value,
            ctnt:     ctntInput.value
        };
        console.log(param);

        const init = {
            method: 'DELETE',
            body: JSON.stringify(param),
            headers: {
                'accept' : 'application/json',
                'content-type' : 'application/json;charset=UTF-8'
            }
        };
        fetch('boardDelete',init)
            .then(res => res.json())
            .then(myJson => {
                if (confirm("정말 삭제하시겠습니까?") == true) {
                    location.href = `/board/mainBoard?provider=${param.provider}`;
                    myJson.submit();
                } else {   //취소
                    return false;
                }
            })
    })

    updSpan.append(updBtn);
    updSpan.append(realBtnU);
    delSpan.append(delBtn);

    //boardModElem.append(updSpan,delSpan);
}

/**
function enterInsCmt(){
    if(window.event.keyCode == 13){
        insCmt();
    }
}
**/

function insCmt() {
    const cmtVal = cmtFrmElem.cmt.value;
    const param = {
        iboard: cmtListElem.dataset.iboard,
        cmt: cmtVal
    };
    insCmtAjax(param);
}

function insCmtAjax(param) {
    const init = {
        method: 'POST',
        body: JSON.stringify(param),
        headers:{
            'accept' : 'application/json',
            'content-type' : 'application/json;charset=UTF-8'
        }
    };
    fetch('insCmt', init)
        .then(function(res){
            return res.json();
        })
        .then(function(myJson){
            console.log(myJson);
            switch(myJson.result){
                case 0:
                    alert('댓글 등록 실패!');
                    break;
                case 1:
                    cmtFrmElem.cmt.value='';
                    alert('댓글 등록 완료!');
                    cmtListAjax();
                    break;
            }
        })
}

function cmtListAjax() {
    const iboard = cmtListElem.dataset.iboard;

    fetch('cmt/' + iboard)
        .then(function(res){
            return res.json();
        })
        .then(function(myJson) {
            console.log(myJson);
            makeCmtElemList(myJson);
        });
}

function makeCmtElemList(data) {
    cmtListElem.innerHTML = '';

    data.forEach(function (item){
        const cmtListDiv = document.createElement('div');
        const userNickDiv = document.createElement('div');
        const cmtDiv = document.createElement('div');
        const regdtDiv = document.createElement('div');

        cmtListDiv.className = 'cmtListDiv';
        userNickDiv.className = 'cmtUserNick'
        cmtDiv.className = 'cmt'
        regdtDiv.className = 'cmtRegdt'

        userNickDiv.append(item.userNick);
        cmtDiv.append(item.cmt);
        regdtDiv.append(item.regdt);
        if(item.iuser == cmtListElem.dataset.iuser){
            const cmtModSpan = document.createElement('span');
            const updCmtBtn = document.createElement('button');
            const delCmtBtn = document.createElement('button');

            updCmtBtn.innerText = '수정';
            delCmtBtn.innerText = '삭제';
            cmtModSpan.append(updCmtBtn);
            cmtModSpan.append(delCmtBtn);
            cmtDiv.append(cmtModSpan);

            updCmtBtn.addEventListener('click', () => {
                cmtDiv.innerHTML = '';
                const cmtInput = document.createElement('textarea');
                cmtInput.value = item.cmt;
                cmtDiv.append(cmtInput);
            });
        }
        cmtListDiv.append(userNickDiv);
        cmtListDiv.append(cmtDiv);
        cmtListDiv.append(regdtDiv);

        cmtListElem.append(cmtListDiv);
    })
}


boardUpd();
cmtListAjax();


