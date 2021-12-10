// 이메일찾기 & 비밀번호 찾기 화면전환
function search_check(num) {
    if (num == '1') {
        document.getElementById('searchPw').style.display = 'none';
        document.getElementById('searchId').style.display = '';
    } else {
        document.getElementById('searchId').style.display = 'none';
        document.getElementById('searchPw').style.display = '';
    }
}

// 이메일찾기
function EmailFind_click() {
    const nmElem = document.querySelector('#nm');
    const telElem = document.querySelector('#tel');
    const msgElem = document.querySelector('#msg');

    if(nmElem.value === '') {
        setTimeout(function(){nmElem.focus();}, 1);
        return false;
    } else if(telElem.value.length < 8) {
        setTimeout(function(){telElem.focus();}, 1);
        return false;
    }

    const param = {
        nm : nmElem.value,
        tel : telElem.value
    }

    const init = {
        method : 'POST',
        headers : {
            'accept' : 'application/json',
            'Content-Type' : 'application/json'
        },
        body : JSON.stringify(param)
    }

    fetch('/user/findEmail',init)
        .then(res => res.json())
        .then(myJson => {
            if(myJson.result === null) {
                msgElem.innerText = '정보를 잘못 입력하셨습니다.'
            } else {
                msgElem.innerText = '회원님의 이메일은 : ' + myJson.result.email + ' 입니다.';
            }
        })
}

// 비밀번호 찾기
function pwSearch_click() {
    const emailElem = document.querySelector('#email');

    if(emailElem.value === '') {
        setTimeout(function(){emailElem.focus();}, 1);
        return false;
    }

    const param = {
        email : emailElem.value
    }

    const init = {
        method : 'POST',
        headers : {
            'accept' : 'application/json',
            'Content-Type' : 'application/json'
        },
        body : JSON.stringify(param)
    }

    fetch('/user/findPw',init)
        .then(res => res.json())
        .then(myJson => {
            if(myJson.result === 0) {
                msgElem.innerText = '이메일을 잘못 입력하셨습니다.';
            } else {
                alert('회원님의 이메일로 임시비밀번호를 발송 하였습니다.');
                return false;
                location.href = '/user/login';
            }
    })
}

function clipboard(val) {
    console.log('val : '+val);
}

clipboard();