const fileList = [];
const displayImgListElem  = document.querySelector('#displayImgList');
const displayImgElem      = document.querySelector('#displayImg');
const btnContElem         = document.querySelector('#btnCont');
const profileModElem      = document.querySelector('.pointer.profileMod');
const modalImgElem        = document.querySelector('.modal-img');
const profileItemContElem = document.querySelector('.profileCont');
const userNickData  = displayImgListElem.dataset.usernick;

const nickDiv     = document.createElement('div');
const fileDiv     = document.createElement('div');
const btnDiv      = document.createElement('div'); // 확인, 취소 버튼
const fileInput   = document.createElement('input');
const submitInput = document.createElement('input'); // 확인
const cancelInput = document.createElement('input'); // 취소
const nickInput   = document.createElement('input');

// 프로필수정 클릭 시 모달창 open
profileModElem.addEventListener('click', () => {
    modalImgElem.classList.remove('hide');
    profileItemContElem.innerHTML = '';

    fileInput.type    = 'file';
    fileInput.id      = 'selectImgArr';
    fileInput.accept  = 'image/*';

    nickInput.type        = 'text';
    nickInput.id          = 'profile-nickname';
    nickInput.placeholder = userNickData;

    submitInput.type  = 'submit';
    submitInput.id    = 'submitUpload';
    submitInput.value = '확인';

    cancelInput.type  = 'button';
    cancelInput.id    = 'cancelInput';
    cancelInput.value = '취소';

    // nickDiv.append();
    fileDiv.append(displayImgElem,fileInput,nickInput);
    btnDiv.append(cancelInput,submitInput);
    displayImgListElem.append(fileDiv,nickDiv);
    btnContElem.append(btnDiv);

    //className()
    btnDiv.className = 'btnDiv';
    fileDiv.className = 'profile-box';
    displayImgElem.className = 'profileImg';

    // profileItemContElem.append(fileDiv);
    profileItemContElem.append(displayImgListElem);
    profileItemContElem.append(btnContElem);

    // 서버에 저장된 썸네일이 변경 될 시
    fileInput.addEventListener('change', ()=> {
        const files = fileInput.files;
        for(let i=0; i<files.length; i++) {
            fileList.pop(files[i-1]);
            fileList.push(files[i]);
        }
        displaySelectedImgArr();
    });
})

// 모달창 닫기
if(btnDiv) {
    btnDiv.addEventListener('click', () => {
        modalImgElem.classList.add('hide');
        location.reload(true); // 서버에서 현재 페이지를 강제로 reload
    });
}

// fileList에 추가 된 이미지들을 디스플레이 처리
function displaySelectedImgArr() {
    // togglesubmitUpload();
    displayImgElem.innerHTML = '';

    for(let i=0; i<fileList.length; i++) {
        const item   = fileList[i];
        const reader = new FileReader();
        reader.readAsDataURL(item);
        // 파일을 로드 한 후
        reader.onload = () => {
            const img = document.createElement('img');
            img.addEventListener('click', () => {
                fileList.splice(i,1);
                displaySelectedImgArr();
                fileInput.value = '';
            });
            img.src = reader.result;
            displayImgElem.innerHTML = '';
            displayImgElem.append(img);
            displayImgListElem.append(displayImgElem);
        };
    }
}

// submit버튼 활성화/비활성화
// function togglesubmitUpload() {
//     submitInput.disabled = true;
//     if(fileList.length > 0 ) {
//         submitInput.disabled = false;
//     }
// }

// Ajax 파일 업로드
submitInput.addEventListener('click', () => {
    const data = new FormData();
    console.log('nickLeng : ' + nickInput.value.length)
    if(fileList.length > 0 || nickInput.value.length > 2) {
        data.append('nick',nickInput.value);

        for(let i=0; i<fileList.length; i++) {
            data.append('imgArr', fileList[i]);
            console.log('NickValue' + nickInput.value)
        }
    }
    // data.append('nick',nickInput.value);

    fetch('profile', {
        method: 'PUT',
        body: data
    })
        .then(res => res.json())
        .then(myJson => {
            switch(myJson.result) {
                case 0:
                    alert('프로필 이미지 등록에 실패하셨습니다.');
                    break;
                case 1:
                    console.log('myjson.result' + myJson);
                    alert('프로필 이미지 등록에  성공하셨습니다.');
                    location.href = '/user/profile';
                    break;
            }
        })
})
// togglesubmitUpload();