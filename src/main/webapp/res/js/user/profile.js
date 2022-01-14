const fileList = [];
const displayImgListElem   = document.querySelector('#displayImgList');
const displayImgElem       = document.querySelector('#displayImg');
const btnContElem          = document.querySelector('#btnCont');
const profileModElem       = document.querySelector('.pointer.profileMod');
const modalImgElem         = document.querySelector('.modal-img');
const pwModalImgElem       = document.querySelector('.pwModal-img');
const profileItemContElem  = document.querySelector('.profileCont');
const pwChangeItemContElem = document.querySelector('.pwChangeCont');
const pwChangeElem         = document.querySelector('.pwChange');
const userProvider = displayImgListElem.dataset.provider;

const nickDiv       = document.createElement('div');
const nickValidMsg  = document.createElement('div');
const fileDiv       = document.createElement('div');
const btnDiv        = document.createElement('div'); // 확인, 취소 버튼
const btnDivPw      = document.createElement('div');
const fileInput     = document.createElement('input');
const submitInput   = document.createElement('input'); // 확인
const submitPwInput = document.createElement('input');
const cancelInput   = document.createElement('input'); // 취소
const nickInput     = document.createElement('input');

const pwBoxDiv          = document.createElement('div');

const currentDiv        = document.createElement('div'); // 현재 비밀번호 확인
const currentContDiv    = document.createElement('div');
const msgCurrentContDiv = document.createElement('div'); // msg,input 태그 합친 div
const currentTh         = document.createElement('th');
const currentInput      = document.createElement('input');
const msgCurrentPwDiv   = document.createElement('div'); // msg 단독 div 태그

const changePwDiv        = document.createElement('div'); // 변경 될 비밀번호
const changePwContDiv    = document.createElement('div');
const msgChangePwContDiv = document.createElement('div');
const changePwTh         = document.createElement('th');
const changePwInput      = document.createElement('input');
const msgChangePwDiv     = document.createElement('div');

const changePwReDiv        = document.createElement('div'); // 변경 될 비밀번호 확인
const changePwReContDiv    = document.createElement('div');
const msgChangePwReContDiv = document.createElement('div');
const changePwReTh         = document.createElement('th')
const changePwReInput      = document.createElement('input');
const msgChangePwReDiv     = document.createElement('div');

const msgErrorColor = '#BE2457';

const iuserData   = displayImgListElem.dataset.iuser;
const profileData = displayImgListElem.dataset.profile;
const img = document.createElement('img');

// 비밀번호 변경 클릭 시 모달창 open
pwChangeElem.addEventListener('click', () => {
    const pwJ   = /^[A-Za-z0-9]{4,12}$/;  //A-Z,a~z,0~9로 시작하는 4~12자리 비밀번호

    pwModalImgElem.classList.remove('hide');

    // 현재 비밀번호
    currentContDiv.className = 'class-current-cont';

    currentInput.type = 'password';
    currentInput.id   = 'pw';
    // currentInput.name = 'pw';
    currentInput.className = 'class-pw';
    currentInput.required  = true;
    currentInput.autofocus = true;
    currentDiv.className   = 'class-th';

    currentTh.type = 'th';
    currentTh.innerText = '현재 비밀번호';

    msgCurrentContDiv.className = 'class-msg-pw';

    msgCurrentPwDiv.innerText = '비밀번호를 입력해주세요.';
    msgCurrentPwDiv.className = 'class-msg';

    // 기존 비밀번호의 focus가 풀렸을 때(Tap이동, 다른곳 클릭)
    currentInput.addEventListener('blur',() => {
        currentAjax();

        // 기존 비밀번호의 값이 공백인 채로 다른 탭으로 넘어가려 할 때 강제 focus
        if(!pwJ.test(currentInput.value) || currentInput.value == '') {
            changePwInput.blur();
            currentInput.focus();
        }
    })

    function currentAjax() {
        const param = {
            currentInput : currentInput.value
        }
        fetch('profile',
            {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(param)
            })
            .then(res => res.json())
            .then(myJson => {
                currentValid(myJson);
            })
    }

    // 기존 비밀번호 Validation check
    function currentValid(myJson) {
        switch(myJson.result) {
            case 0:
                if(!pwJ.test(currentInput.value)) {
                    msgCurrentPwDiv.innerText   = '비밀번호는 영문 대,소문자 및 숫자만 가능합니다.';
                    msgCurrentPwDiv.style.color = msgErrorColor;
                } else if(pwJ.test(currentInput.value)) {
                    msgCurrentPwDiv.innerText   = '비밀번호를 확인해주세요.';
                    msgCurrentPwDiv.style.color = msgErrorColor;
                }
                currentInput.focus();
                break;
            case 1:
                msgCurrentPwDiv.innerText = '';
                currentInput.disabled     = true; // 기존 비밀번호의 값이 맞다면 기존 비밀번호 input 비활성화
                break;
        }
    }

    currentDiv.append(currentTh);
    msgCurrentContDiv.append(currentInput,msgCurrentPwDiv);
    currentContDiv.append(currentDiv,msgCurrentContDiv);

    // 변경할 비밀번호
    changePwContDiv.className = 'class-current-cont';

    changePwInput.type      = 'password';
    changePwInput.className = 'class-pw';
    changePwDiv.className   = 'class-th';

    changePwTh.type      = 'th';
    changePwTh.innerText = '새 비밀번호';

    msgChangePwContDiv.className = 'class-msg-pw';

    msgChangePwDiv.innerText = '변경할 비밀번호를 입력해주세요.';
    msgChangePwDiv.className = 'class-msg';

    // 새 비밀번호의 focus가 풀렸을 때(Tap이동, 다른곳 클릭)
    changePwInput.addEventListener('blur', () => {
        changePwValid();
    })

    // 새 비밀번호 Validation check
    function changePwValid() {
        if(!pwJ.test(changePwInput.value)) {
            msgChangePwDiv.innerText   = '비밀번호는 영문 대,소문자 및 숫자만 가능합니다.';
            msgChangePwDiv.style.color = msgErrorColor;
        } else if(currentInput.value == changePwInput.value) {
            msgChangePwDiv.innerText   = '현재 비밀번호와 동일합니다.';
            msgChangePwDiv.style.color = msgErrorColor;
        } else if(pwJ.test(changePwInput.value)) {
            msgChangePwDiv.innerText = '';
        }
    }

    changePwDiv.append(changePwTh);
    msgChangePwContDiv.append(changePwInput,msgChangePwDiv);
    changePwContDiv.append(changePwDiv,msgChangePwContDiv);

    // 변경할 비밀번호 확인
    changePwReContDiv.className = 'class-current-cont';

    changePwReInput.type      = 'password';
    changePwReInput.className = 'class-pw';
    changePwReDiv.className   = 'class-th';

    changePwReTh.type      = 'th';
    changePwReTh.innerText = '새 비밀번호 확인';

    msgChangePwReContDiv.className = 'class-msg-pw';

    msgChangePwReDiv.innerText = '변경할 비밀번호를 확인해주세요.';
    msgChangePwReDiv.className = 'class-msg';

    // 새 비밀번호 확인의 focus가 풀렸을 때(Tap이동, 다른곳 클릭)
    changePwReInput.addEventListener('blur', () => {
        changePwReValid();
    })

    // 새 비밀번호 확인 Validation check
    function changePwReValid() {
        if(changePwInput.value != changePwReInput.value) {
            submitPwInput.disabled = true;
            msgChangePwReDiv.innerText = '새 비밀번호와 다릅니다.';
            msgChangePwReDiv.style.color = msgErrorColor;
            return false;
        } else if(!pwJ.test(changePwReInput.value)) {
            submitPwInput.disabled = true;
        }
        else {
            submitPwInput.disabled = false;
            msgChangePwReDiv.innerText = '';
            // 최종적으로 새 비밀번호 확인 입력 후 submit 버튼을 누르지 않고
            // 다시 새 비밀번호를 변경하자마자 submit 버튼을 누르는 경우를 막기 위한 validation
            changePwInput.addEventListener('change', () => {
                submitPwInput.disabled = true;
            })
        }
    }

    changePwReDiv.append(changePwReTh);
    msgChangePwReContDiv.append(changePwReInput,msgChangePwReDiv);
    changePwReContDiv.append(changePwReDiv,msgChangePwReContDiv);

    submitPwInput.type  = 'submit';
    submitPwInput.id    = 'submitUpload';
    submitPwInput.value = '확인';
    submitPwInput.disabled = true;

    cancelInput.type  = 'button';
    cancelInput.id    = 'cancelInput';
    cancelInput.value = '취소';

    // 확인 버튼을 눌렀을 때
    submitPwInput.addEventListener('click', () => {
        formCheck();
    })

    // Controller로 보내기 전 formValidation check
    function formCheck() {
        if(currentInput.value == changePwInput.value) {
            alert('기존 비밀번호와 새 비밀번호가 같습니다.');
            // return false;
            return;
            changePwInput.focus();
        } else if(changePwInput.value != changePwReInput.value) {
            alert('새 비밀번호를 다시 확인해주세요.')
            changePwInput.focus();
            return;
        } else {
            submitAjax();
        }
    }

    function submitAjax() {
        fetch('changePw',
            {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(changePwInput.value)
            })
            .then(res => res.json())
            .then(myJson => {
                submitFormValid(myJson);
            })
    }

    // Controller에서 받은 result값으로 validation check
    function submitFormValid(myJson) {
        switch (myJson.result) {
            case 0:
                if (!pwJ.test(changePwReInput.value)) {
                    msgChangePwReDiv.innerText = '비밀번호는 영문 대,소문자 및 숫자만 가능합니다.';
                } else if (pwJ.test(changePwReInput.value) && changePwInput.value != changePwReInput.value) {
                    msgChangePwReDiv.innerText = '비밀번호가 다릅니다.';
                }
                changePwReInput.focus();
                break;
            case 1:
                alert('다시 로그인 해주세요!!!');
                location.href = '/user/logout';
                if (pwJ.test(changePwReInput.value) && changePwInput.value == changePwReInput.value) {
                    msgChangePwReDiv.innerText = '';
                }
                break;
        }
    }

    btnDivPw.append(cancelInput,submitPwInput);
    btnContElem.append(btnDivPw);

    btnDivPw.className   = 'btnDiv';
    pwBoxDiv.className = 'pw-box';

    pwBoxDiv.append(currentContDiv,changePwContDiv,changePwReContDiv);
    pwChangeItemContElem.append(pwBoxDiv);
    pwChangeItemContElem.append(btnContElem);
})

// 모달창 닫기
if(btnDivPw) {
    btnDivPw.addEventListener('click', () => {
        pwModalImgElem.classList.add('hide');
    });
}
cancelInput.addEventListener('click', () => {
    location.reload();
})


//---------------------------------------프로필변경-------------------------------------------------

// 프로필수정 클릭 시 모달창 open
let  chkImg = '';
const nickJ = /^[가-힣A-Za-z0-9]{3,8}$/;  //가-힣,A-Z,a~z,0~9로 시작하는 3~8자리 비밀번호
profileModElem.addEventListener('click', () => {
    modalImgElem.classList.remove('hide');
    profileItemContElem.innerHTML = '';

    submitInput.disabled = true;

    nickInput.addEventListener('change', () => {
        nickAjax();
    })

    function nickAjax() {
        const param = {
            nickValid : nickInput.value
        }
        fetch('profile',
            {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(param)
            })
            .then(res => res.json())
            .then(myJson => {
                nickValid(myJson);
            })
    }

    function nickValid(myJson) {
        switch(myJson.result) {
            case 0:
                if(nickJ.test(nickInput.value)) {
                    submitInput.disabled   = false;
                    nickValidMsg.innerText = '사용 가능합니다.';
                } else if(!nickJ.test(nickInput.value)) {
                    submitInput.disabled   = true;
                    nickValidMsg.innerText = '한영 및 숫자만 가능합니다.';
                }
                nickInput.addEventListener('blur', () => {
                    submitInput.disabled = true;
                })
                break;
            case 1:
                if(nickJ.test(nickInput.value)) {
                    submitInput.disabled   = true;
                    nickValidMsg.innerText = '이미 사용중인 닉네임입니다.';
                }
                break;
        }
    }

    nickValidMsg.id        = 'nick-msg';

    displayImgElem.addEventListener('click', () => {
        if(chkImg == 'BASIC') {
            chkImg  = 'PROFILE';
            img.src = '/pic/profileImg/' + iuserData + '/' + profileData;
            displayImgElem.append(img);
        } else {
            chkImg  = 'BASIC';
            img.src = '/res/img/BasicProfile.png';
            displayImgElem.append(img);
            fileList.pop();
            fileList.push('basicProfile');
            submitInput.disabled = false;
        }
    })

    fileInput.type    = 'file';
    fileInput.id      = 'selectImgArr';
    fileInput.accept  = 'image/*';

    nickInput.type        = 'text';
    nickInput.id          = 'profile-nickname';
    nickInput.placeholder = '세글자 이상 입력해 주세요.';

    submitInput.type  = 'submit';
    submitInput.id    = 'submitUpload';
    submitInput.value = '확인';

    cancelInput.type  = 'button';
    cancelInput.id    = 'cancelInput';
    cancelInput.value = '취소';

    // nickDiv.append();
    fileDiv.append(displayImgElem,fileInput,nickInput,nickValidMsg);
    btnDiv.append(cancelInput,submitInput);
    displayImgListElem.append(fileDiv,nickDiv);
    btnContElem.append(btnDiv);

    //className()
    btnDiv.className  = 'btnDiv';
    fileDiv.className = 'profile-box';
    displayImgElem.className = 'profileImg';

    // profileItemContElem.append(fileDiv);
    profileItemContElem.append(displayImgListElem);
    profileItemContElem.append(btnContElem);

    // 프로필이미지가 미등록인 사용자라면, 기본 이미지 적용
    if(profileData == '') { // null아님 '' 이거임
        chkImg  = 'BASIC';
        img.src = '/res/img/BasicProfile.png';
        displayImgElem.append(img);
    }else{
        chkImg  = 'PROFILE';
        img.src = '/pic/profileImg/' + iuserData + '/' + profileData;
        displayImgElem.append(img);
    }

    displayImgElem.addEventListener('click', () => {
        if(chkImg == 'BASIC') {
            chkImg  = 'PROFILE';
            img.src = '/pic/profileImg/' + iuserData + '/' + profileData;
        } else {
            chkImg  = 'BASIC';
            img.src = '/res/img/BasicProfile.png';
            fileList.push(img);
        }
        console.log('IMG : ' + img.src)
        console.log('LENGTH : ' + fileInput.files.length)
        console.log('fileLIST_CHK : ' + fileList[0]);
        submitInput.disabled = false;
        displayImgElem.append(img);
    })
    console.log('fileLIST_1 : ' + fileList)
    // 서버에 저장된 썸네일이 변경 될 시
    fileInput.addEventListener('change', ()=> {
        const files = fileInput.files;

        for(let i=0; i<files.length; i++) {
            fileList.pop();
            fileList.push(files[i]);
            console.log('fileLIST_2 : ' + fileList[0])
        }
        submitInput.disabled = false;
        displaySelectedImgArr();
    });
})

// 모달창 닫기
if(btnDiv) {
    cancelInput.addEventListener('click', () => {
        modalImgElem.classList.add('hide');
    });
}

// fileList에 추가 된 이미지들을 디스플레이 처리
function displaySelectedImgArr() {
    displayImgElem.innerHTML = '';

    for(let i=0; i<fileList.length; i++) {
        const item   = fileList[i];
        const reader = new FileReader();
        reader.readAsDataURL(item);
        // 파일을 로드 한 후
        reader.onload = () => {
            img.addEventListener('click', () => {
                fileList.splice(i,1);
                displaySelectedImgArr();
                fileInput.value = '';
                img.src = '/pic/profileImg/' + iuserData + '/' + profileData;
                displayImgElem.append(img);
            });
            img.src = reader.result;
            displayImgElem.innerHTML = '';
            displayImgElem.append(img);
            displayImgListElem.append(displayImgElem,fileDiv,nickDiv);
        };
    }
}

// Ajax 파일 업로드
submitInput.addEventListener('click', () => {
    const data = new FormData();
    if(fileList.length > 0 || nickInput.value.length > 2) {
        data.append('nick',nickInput.value);
        if(fileList[0] == 'basicProfile'){
            data.append('basicProfile', 'basicProfile');
        } else {
            for(let i=0; i<fileList.length; i++) {
                data.append('imgArr', fileList[i]);
            }
        }
    }
    submitProfile(data);
})

function submitProfile(data) {
    fetch('profile', {
        method: 'PUT',
        body: data,
        timeout : 30000
    })
        .then(res => res.json())
        .then(myJson => {
            switch(myJson.result) {
                case 0:
                    alert('프로필 이미지 등록에 실패하셨습니다.');
                    break;
                case 1:
                    alert('프로필 이미지 등록에  성공하셨습니다.');
                    location.reload();
                    break;
            }
        })
}
if(userProvider != 'nong4'){
    pwChangeElem.innerHTML = '여기서 변경할 수 없습니다.';
    pwChangeElem.setAttribute('disabled','disabled');
    pwChangeElem.classList.remove('pointer');
}