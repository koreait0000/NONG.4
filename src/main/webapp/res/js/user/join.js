const joinBtnElem = document.querySelector('#joinBtn')
const frmElem = document.querySelector('#joinForm');
const idCheckElem = document.querySelector('#idCheck')


// 회원가입 체크
if(joinBtnElem) {
    joinBtnElem.addEventListener('click', ()=> {
    })
    const pwElem = document.querySelector('#pw');
    const pwReElem = document.querySelector('#pwRe');
    const nmElem = document.querySelector('#nm');
    const userNickElem = document.querySelector('#userNick');

    const msgNmElem = document.querySelector('#msgNm');

    const pwJ = /^[A-Za-z0-9]{4,12}$/;  //A-Z,a~z,0~9로 시작하는 4~12자리 비밀번호
    const nameJ = /^[가-힣]{2,6}$/; //가~힣,한글로 이뤄진 문자열 이름 2~6자리

    function ajax() {

        const param = {
            pw : pwElem.value
        }
        fetch('/user/join', {
            method: 'post',
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
            location.href="/user/login";
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
}


// 이메일 중복확인 체크
const EmailElem = document.querySelector('#email');
const msgElem = document.querySelector('#msg');
idCheckElem.addEventListener('keyup',()=> {
    msgElem.innerText = '이메일을 확인 해주세요.';
    joinBtnElem.setAttribute('disabled','disabled');
})

if(idCheckElem) {
    const mailElem = frmElem.email;
    const mailJ = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i;

    function formMailCheck() {
        if (!mailJ.test(EmailElem.value)) {
            alert('이메일을 ~~~~형식에 맞게 바꿔주세요')
            EmailElem.focus();
            return;
        }
        mailCheckProc();
    }

    function mailCheckProc(){
        const mailElem = EmailElem.value;
        const param = {
            email: EmailElem.value
        }

        console.log(param);
        fetch('/user/chkEmail',
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
                    msgElem.innerText = '사용 가능한 이메일 입니다.';
                    msgElem.style.color = '#14148C';
                    joinBtnElem.removeAttribute('disabled');
                } else {
                    msgElem.innerText = '중복된 이메일이 있습니다.';
                    msgElem.style.color = '#BE2457';
                }
            })
    }
}

idCheckElem.addEventListener('click',()=> {
    formMailCheck();
})

