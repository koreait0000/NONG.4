const fileList = [];
const selectImgArrElem    = document.querySelector('#selectImgArr');
const submitUploadElem   = document.querySelector('#submitUpload');
const displayImgListElem = document.querySelector('#displayImgList');
const profileImgElem = document.querySelector('.profileImg');
const titleElem = document.querySelector('#title');
const modalElem = document.querySelector('section .modal');

profileImgElem.addEventListener('click', () => {
    modalElem.classList.remove('hide');
})

// 이미지들의 선택되면 fileList에 추가
selectImgArrElem.addEventListener('change', ()=> {
    const files = selectImgArrElem.files;
    console.log('files : ' + files.length);
    for(let i=0; i<files.length; i++) {
        fileList.push(files[i]);
    }
    displaySelectedImgArr();
});

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
                console.log('files : ' + selectImgArrElem.files.length);
            });
            img.src = reader.result;
            displayImgListElem.append(img);
        };
    }
}

// submit버튼 활성화/비활성화
function togglesubmitUpload() {
    submitUploadElem.disabled = true;
    if(fileList.length > 0) {
        submitUploadElem.disabled = false;
    }
}

// Ajax 파일 업로드
submitUploadElem.addEventListener('click', () => {
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