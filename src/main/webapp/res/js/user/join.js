const joinBtnElem = document.querySelector('#joinBtn')
const frmElem = document.querySelector('#joinForm');
const idCheckElem = document.querySelector('#idCheck')
const userNickElem = document.querySelector('#userNick');
const telElem = document.querySelector('#tel');
const nmElem = document.querySelector('#nm');
const EmailElem = document.querySelector('#email');
const msgElem = document.querySelector('#msg');


const mailJ = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i;
const userNickJ = /^[A-Za-z가-힣0-9]{2,8}$/; //A-Z,a~z,가~힣,0~9로 이뤄진 2~8자리
const phoneJ = /^01([0|1|6|7|8|9])-?([0-9]{4})-?([0-9]{4})$/;

function emptyCheck() {
     if(EmailElem.value.length > 1) {
         joinBtnElem.removeAttribute('disabled');
         console.log('빈값이 아닙니다');
         console.log(EmailElem.value);
         // joinBtnElem.setAttribute('disabled','disabled');
     }
 }

function disabledRemove() {
    joinBtnElem.removeAttribute('disabled');
    joinBtnElem.classList.add('pointer');
}

function disabledAdd() {
    joinBtnElem.setAttribute('disabled','disabled');
    joinBtnElem.classList.remove('pointer');
}

// 회원가입 체크
if(joinBtnElem) {
    joinBtnElem.addEventListener('click', ()=> {
        const param = {
            EmailElem : EmailElem.value,
            userNick : userNickElem.value,
            tel : telElem.value
        }
        fetch('/user/chkOverlap',
            {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(param)
            })
            .then(res => res.json())
            .then(myJson => {
                if(myJson.result === 1) {
                    alert('회원가입 성공할때');
                    location.reload();
                }
                else {
                    alert('실패');
                    location.reload();
                }
            })
    })
    const pwElem = document.querySelector('#pw');
    const pwReElem = document.querySelector('#pwRe');
    const nmElem = document.querySelector('#nm');

    const msgNmElem = document.querySelector('#msgNm');
    const msgUserNickElem = document.querySelector('#msgUserNick');
    const msgTelElem = document.querySelector('#msgTel');

    const pwJ = /^[A-Za-z0-9]{4,12}$/;  //A-Z,a~z,0~9로 시작하는 4~12자리 비밀번호
    const nameJ = /^[가-힣]{2,6}$/; //가~힣,한글로 이뤄진 문자열 이름 2~6자리

     function ajax() {

         const param = {
             email : EmailElem.value,
             pw : pwElem.value,
             nm : nmElem.value,
             userNick : userNickElem.value,
             tel : telElem.value
         }
         if(EmailElem.value == '' || pwElem.value == '' || nmElem.value == '' || userNickElem.value == '' || telElem.value == '') {
             if(EmailElem.value == '') {
                 setTimeout(function(){EmailElem.focus();}, 1);
             } else if(pwElem.value == '') {
                 setTimeout(function(){pwElem.focus();}, 1);
             } else if(pwReElem.value == '') {
                 setTimeout(function(){pwReElem.focus();}, 1);
             } else if(nmElem.value == '') {
                 setTimeout(function(){nmElem.focus();}, 1);
             } else if(userNickElem.value == '') {
                 setTimeout(function(){userNickElem.focus();}, 1);
             } else {
                 setTimeout(function(){telElem.focus();}, 1);
             }
             return false;
         }
         fetch('/user/join', {
             method: 'POST',
             headers: {
                 'Content-Type': 'application/json',
             },
             body: JSON.stringify(param)
         })
             .then(res => res.json())
             .then(myJson => {
                 proc(myJson);
             })
     }
     function proc (myJson) {
         if(myJson.result === 1) {
             alert('회원가입 성공')
             location.href="/user/login?needEmail=1";
         }
     }
    function formCheck() {
        if(!pwJ.test(pwElem.value)) {
            alert('비밀번호는 영문 대,소문자 및 숫자만 가능합니다.');
            pwElem.focus();
            return;
        }
        if(pwElem.value !== pwReElem.value) {
            alert('비밀번호가 일치 하지 않습니다.');
            pwReElem.focus();
            return;
        }
        ajax();
    }

    joinBtnElem.addEventListener('click', ()=> {
        console.log('회원가입 버튼');
        formCheck();
    })

    // 이름 확인 체크
    if(!nameJ.test(nmElem.value)) {
        nmElem.addEventListener('keyup',()=>{
            msgNmElem.innerText = '이름은 2~6자리 한글만 사용 가능 합니다.';
            if(nameJ.test(nmElem.value)) {
                msgNmElem.innerText = '';
            }
        })
    }

function userNickKeyupProc() {
    userNickElem.addEventListener('keyup', ()=> {
        msgUserNickElem.innerText = '닉네임을 다시 확인 해주세요';
        msgUserNickElem.style.color = '#BE2457';
        userNickElem.focus();
        return;
    })
}

    // 닉네임 확인 체크
    if(!userNickJ.test(userNickElem.value)) {
        userNickElem.addEventListener('keyup',()=>{
            msgUserNickElem.innerText = '닉네임은 2~8자리 특수문자를 제외하고 사용 가능 합니다.';

            if(userNickJ.test(userNickElem.value)) {
                // Todo, 211202 닉네임 중복체크 추가
                console.log('userNick : '+userNickElem.value);
                userNickCheckProc();
                msgUserNickElem.innerText = '';
            }
        })
    }
    function userNickCheckProc() {
        const param = {
            userNick : userNickElem.value
        }
        console.log('userNick! : '+param);
        fetch('/user/chkOverlap',
            {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(param)
        })
            .then(res => res.json())
            .then(myJson => {
                if(myJson.result === 1) {
                    userNickKeyupProc();
                    msgUserNickElem.innerText = '사용가능한 닉네임 입니다.';
                    console.log('result Nick : '+myJson.result);
                }
                else {
                    userNickKeyupProc();
                    msgUserNickElem.innerText = '중복된 닉네임 입니다.';
                    msgUserNickElem.focus();
                    return;
                }
        })
    }

    // 휴대폰 번호 중복 확인 체크
    if(!phoneJ.test(telElem.value)) {
        console.log('tel key chk1')
        telElem.addEventListener('keyup', ()=> {
            console.log('tel key chk2')
            msgTelElem.innerText = '휴대폰번호는 "-" 제외 숫자만 입력 가능합니다.';
            if(phoneJ.test(telElem.value)) {
                msgTelElem.innerText = '';
                phoneCheckProc();
            }
        })
    }
    function phoneCheckProc() {
        const param = {
            tel : telElem.value
        }
        fetch('/user/chkOverlap',
            {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(param)
            })
            .then(res => res.json())
            .then(myJson => {
                if(myJson.result === 1) {
                    // Todo 211206, 휴대번호가 자릿수 이상하게 해도 submit 됨
                    msgTelElem.innerText = '';
                }
                else {
                    msgTelElem.innerText = '휴대번호는 7자리 이상을 입력해주세요!';
                    msgTelElem.focus();
                    return;
                }
            })
    }
}

// 이메일 중복확인 체크

idCheckElem.addEventListener('keyup',()=> {
    msgElem.innerText = '이메일을 확인 해주세요.';
    // joinBtnElem.setAttribute('disabled','disabled');
})

function emailKeyupProc() {
    EmailElem.addEventListener('keyup',()=> {
        msgElem.innerText = '이메일 확인을 다시 해주세요!';
        msgElem.style.color = '#BE2457';
        EmailElem.focus();
        return;
    })
}

if(idCheckElem) {
    function formMailCheck() {
        if (!mailJ.test(EmailElem.value) ) {
            alert('이메일을 ~~~~형식에 맞게 바꿔주세요')
            EmailElem.focus();
            return;
        }
        mailCheckProc();
    }

    function mailCheckProc(){
        const mailElem = EmailElem.value;
        const param = {
            // Todo, 닉네임, 휴대전화 추가
            email: EmailElem.value,
            nick : userNickElem.value,
            tel : telElem.value
        }

        console.log('email : '+EmailElem.value);

        fetch('/user/chkOverlap',
            {
                credentials: 'include',
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(param)
            })
            .then(function(res){
                return res.json();
            })
            .then(function(myJson) {
                if(myJson.result === 1) {
                    emailKeyupProc();
                    msgElem.innerText = '사용 가능한 이메일 입니다.';
                    msgElem.style.color = '#14148C';
                }
                else {
                    emailKeyupProc();
                    msgElem.innerText = '중복된 이메일이 있습니다.';
                    msgElem.style.color = '#BE2457';
                }
            })
    }
}
idCheckElem.addEventListener('click',()=> {
    formMailCheck();
})


// userNickElem.addEventListener('keyup',()=> {
//     mailCheckProc();
// })
// telElem.addEventListener('keyup',()=> {
//     mailCheckProc();
// })

// userNickElem.addEventListener('keyUp', ()=> {
//     userNickCheckProc();
// })

emptyCheck();