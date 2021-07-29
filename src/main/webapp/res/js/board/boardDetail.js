var cmtFrmElem = document.querySelector('#cmtFrm');
var cmtListElem = document.querySelector('#cmtList');

function enterInsCmt(){
    if(window.event.keyCode == 13){
        insCmt();
    }
}

function insCmt() {
    var cmtVal = cmtFrmElem.cmt.value;
    var param = {
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
    var iboard = cmtListElem.dataset.iboard;

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
        var cmtListDiv = document.createElement('div');
        var userNickDiv = document.createElement('div');
        var cmtDiv = document.createElement('div');
        var regdtDiv = document.createElement('div');

        cmtListDiv.className = 'cmtlistDiv';
        userNickDiv.className = 'cmtUserNick'
        cmtDiv.className = 'cmt'
        regdtDiv.className = 'cmtRegdt'

        userNickDiv.append(item.userNick);
        cmtDiv.append(item.cmt);
        regdtDiv.append(item.regdt);

        cmtListDiv.append(userNickDiv);
        cmtListDiv.append(cmtDiv);
        cmtListDiv.append(regdtDiv);

        cmtListElem.append(cmtListDiv);
    })
}

cmtListAjax();