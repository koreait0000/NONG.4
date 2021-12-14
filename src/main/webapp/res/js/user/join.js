const joinBtnElem = document.querySelector('#joinBtn');
const frmElem = document.querySelector('#joinForm');
const idCheckElem = document.querySelector('#idCheck');
const userNickElem = document.querySelector('#userNick');
const telElem = document.querySelector('#tel');
const nmElem = document.querySelector('#nm');
const EmailElem = document.querySelector('#email');
const msgElem = document.querySelector('#msg');

const mailJ = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i;
const userNickJ = /^[A-Za-z가-힣0-9]{2,8}$/; //A-Z,a~z,가~힣,0~9로 이뤄진 2~8자리
const phoneJ = /^01([0|1|6|7|8|9])-?([0-9]{4})-?([0-9]{4})$/;
const pwJ = /^[A-Za-z0-9]{4,12}$/;  //A-Z,a~z,0~9로 시작하는 4~12자리 비밀번호
const nameJ = /^[가-힣]{2,6}$/; //가~힣,한글로 이뤄진 문자열 이름 2~6자리

let emailChk = 0;
let nickChk = 0;
let telChk = 0;

// 회원가입 체크
if(joinBtnElem) {
    joinBtnElem.addEventListener('click', () => {
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
        var sum = emailChk + nickChk + telChk;
        console.log('회원가입체크1')
        if (sum == 3) {
            const param = {
                email: EmailElem.value,
                pw: pwElem.value,
                nm: nmElem.value,
                userNick: userNickElem.value,
                tel: telElem.value
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
        } else {
            alert('중복확인해주세요.')
            return;
        }
    }
        function proc(myJson) {
            if (myJson.result === 1) {
                alert('회원가입 성공')
                location.href = "/user/login?needEmail=1";
            } else {
                alert('실패');
                return;
                // 데이터전송막기?
            }
        }

        function formCheck() {
            if (!pwJ.test(pwElem.value)) {
                alert('비밀번호는 영문 대,소문자 및 숫자만 가능합니다.');
                pwElem.focus();
                return;
            }
            if (pwElem.value !== pwReElem.value) {
                alert('비밀번호가 일치 하지 않습니다.');
                pwReElem.focus();
                return;
            }


            if (EmailElem.value == '' || pwElem.value == '' || nmElem.value == '' || userNickElem.value == '' || telElem.value == '') {
                if (EmailElem.value == '') {
                    setTimeout(function () {
                        EmailElem.focus();
                    }, 1);
                } else if (pwElem.value == '') {
                    setTimeout(function () {
                        pwElem.focus();
                    }, 1);
                } else if (pwReElem.value == '') {
                    setTimeout(function () {
                        pwReElem.focus();
                    }, 1);
                } else if (nmElem.value == '') {
                    setTimeout(function () {
                        nmElem.focus();
                    }, 1);
                } else if (userNickElem.value == '') {
                    setTimeout(function () {
                        userNickElem.focus();
                    }, 1);
                } else {
                    setTimeout(function () {
                        telElem.focus();
                    }, 1);
                }
                return false;
            }


            ajax();
        }

        joinBtnElem.addEventListener('click', () => {
            console.log('회원가입 버튼');
            formCheck();
        })

        // 이름 확인 체크
        if (!nameJ.test(nmElem.value)) {
            nmElem.addEventListener('keyup', () => {
                msgNmElem.innerText = '이름은 2~6자리 한글만 사용 가능 합니다.';
                if (nameJ.test(nmElem.value)) {
                    msgNmElem.innerText = '';
                }
            })
        }

        function userNickKeyupProc() {
            userNickElem.addEventListener('keyup', () => {
                msgUserNickElem.innerText = '닉네임을 다시 확인 해주세요';
                msgUserNickElem.style.color = '#BE2457';
                userNickElem.focus();
                return;
            })
        }

        // 닉네임 확인 체크
        if (!userNickJ.test(userNickElem.value)) {
            userNickElem.addEventListener('keyup', () => {
                msgUserNickElem.innerText = '닉네임은 2~8자리 특수문자를 제외하고 사용 가능 합니다.';

                if (userNickJ.test(userNickElem.value)) {
                    // Todo, 211202 닉네임 중복체크 추가
                    console.log('userNick : ' + userNickElem.value);
                    userNickCheckProc();
                    msgUserNickElem.innerText = '';
                }
            })
        }

        function userNickCheckProc() {
            const param = {
                userNick: userNickElem.value
            }
            console.log('userNick! : ' + param);
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
                    if (myJson.result === 1) {
                        userNickKeyupProc();
                        msgUserNickElem.innerText = '사용가능한 닉네임 입니다.';
                        nickChk = 1;
                        console.log('result Nick : ' + myJson.result);
                    } else {
                        userNickKeyupProc();
                        msgUserNickElem.innerText = '중복된 닉네임 입니다.';
                        nickChk = 0;
                        msgUserNickElem.focus();
                        return;
                    }
                })
        }

        // 휴대폰 번호 중복 확인 체크
        if (!phoneJ.test(telElem.value)) {
            console.log('tel key chk1')
            telElem.addEventListener('keyup', () => {
                console.log('tel key chk2')
                msgTelElem.innerText = '휴대폰번호는 "-" 제외 숫자만 입력 가능합니다.';
                if (phoneJ.test(telElem.value)) {
                    msgTelElem.innerText = '';
                    phoneCheckProc();
                }
            })
        }

        function phoneCheckProc() {
            const param = {
                tel: telElem.value
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
                    if (myJson.result === 1) {
                        // Todo 211206, 휴대번호가 자릿수 이상하게 해도 submit 됨
                        msgTelElem.innerText = '중복된 휴대번호가 없습니다.';
                        telChk = 1;
                    } else {
                        msgTelElem.innerText = '중복된 휴대번호 입니다.';

                        msgTelElem.innerText = '중복된 휴대번호가 존재합니다.';
                        telChk = 0;

                        msgTelElem.focus();
                        return;
                    }
                })
        }
    }

// 이메일 중복확인 체크
    function formMailCheck() {
        if (!mailJ.test(EmailElem.value)) {
            msgElem.innerText = '이메일형식에 맞게 작성해주세요'
            EmailElem.focus();
            return;
        }
        mailCheckProc();
    }

    function mailCheckProc() {
        const param = {
            // Todo, 닉네임, 휴대전화 추가
            email: EmailElem.value,
            nick: userNickElem.value,
            tel: telElem.value
        }
        fetch('/user/chkOverlap',
            {
                credentials: 'include',
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(param)
            })
            .then(function (res) {
                return res.json();
            })
            .then(function (myJson) {
                if (myJson.result === 1) {
                    msgElem.innerText = '사용 가능한 이메일 입니다.';
                    emailChk = 1;
                    msgElem.style.color = '#14148C';

                } else {
                    msgElem.innerText = '중복된 이메일이 있습니다.';
                    emailChk = 0;
                    msgElem.style.color = '#BE2457';
                }
            })
    }

    EmailElem.addEventListener('keyup', () => {
        formMailCheck();
    })