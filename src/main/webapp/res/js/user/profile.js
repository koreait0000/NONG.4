const fileList = [];
const selectImgArrElem    = document.querySelector('#selectImgArr');
const submitUploadElem   = document.querySelector('#submitUpload');
const displayImgListElem = document.querySelector('#displayImgList');
const profileImgElem = document.querySelector('.profileImg');
const titleElem = document.querySelector('#title');
const modalElem = document.querySelector('section .modal');
const profileModElem = document.querySelector('.pointer.profileMod');
const modalImgElem = document.querySelector('.modal-img');
const modalImgCloseElem = document.querySelector('.modal-img #modal-img-close');
const profileItemContElem = document.querySelector('.profileCont');
const initFileInput = () => {
    const dt = new DataTransfer();
    return;
}

const fileInput   = document.createElement('input');
const submitInput = document.createElement('input');

profileModElem.addEventListener('click', () => {
    modalImgElem.classList.remove('hide');
    profileItemContElem.innerHTML = '';

    const fileDiv = document.createElement('div');

    fileInput.type    = 'file';
    fileInput.id      = 'selectImgArr';
    fileInput.accept  = 'image/*';

    submitInput.type  = 'submit';
    submitInput.id    = 'submitUpload';
    submitInput.value = '업로드';

    fileDiv.append(fileInput,submitInput);

    profileItemContElem.append(displayImgListElem);
    profileItemContElem.append(fileDiv);

    fileInput.addEventListener('change', ()=> {
        const files = fileInput.files;
        console.log('files : ' + files.length);
        console.log('fileName : ' + files);
        console.log('벨류 : ' + fileInput.value);
        for(let i=0; i<files.length; i++) {
            fileList.pop(files[i-1]);
            fileList.push(files[i]);
        }
        displaySelectedImgArr();
    });
})

// 모달창 닫기
if(modalImgCloseElem) {
    modalImgCloseElem.addEventListener('click', () => {
        modalImgElem.classList.add('hide');
        let file = document.getElementById('file').files = initFileInput.files;
        console.log()
    });
}

profileImgElem.addEventListener('click', () => {
    modalElem.classList.remove('hide');
})

// 이미지들의 선택되면 fileList에 추가
// selectImgArrElem.addEventListener('change', ()=> {
//     const files = selectImgArrElem.files;
//     console.log('files : ' + files.length);
//     for(let i=0; i<files.length; i++) {
//         fileList.push(files[i]);
//     }
//     displaySelectedImgArr();
// });

// fileList에 추가 된 이미지들을 디스플레이 처리
function displaySelectedImgArr() {
    togglesubmitUpload();
    displayImgListElem.innerHTML = '';

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
                console.log('files :: ' + selectImgArrElem.files.length);
            });
            img.src = reader.result;
            displayImgListElem.append(img);
        };
    }
}

// submit버튼 활성화/비활성화
function togglesubmitUpload() {
    submitInput.disabled = true;
    if(fileList.length > 0) {
        submitInput.disabled = false;
    }
}

// Ajax 파일 업로드
submitInput.addEventListener('click', () => {
    const data = new FormData();

    if(fileList.length > 0) {
        for(let i=0; i<fileList.length; i++) {
            data.append('imgArr', fileList[i]);
        }
    }

    fetch('profile', {
        method: 'POST',
        body: data
    })
        .then(res => res.json())
        .then(myJson => {
            switch(myJson.result) {
                case 0:
                    alert('프로필 이미지 등록에 실패하셨습니다.');
                    break;
                case 1:
                    location.href = '/user/profile';
                    break;
            }
        })
})
togglesubmitUpload();