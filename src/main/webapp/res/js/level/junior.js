const videoListElem = document.querySelector('#videoApi');

function apiTest() {
    fetch('/openapi/apiTest', {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json',
        }
    })
        .then(res => res.json())
        .then(myJson => {
            //console.log(myJson);
            let videoImg = myJson['videoImg'];
            testF(myJson);
        })
}
function testF(data){
    data.forEach(function (item){
        const videoListDiv = document.createElement('div');
        const videoImgSpan = document.createElement('span');
        const videoLetterSpan = document.createElement('span')
        const videoTitleDiv = document.createElement('div');
        const videoOriginDiv = document.createElement('div');
        const img = document.createElement('img');

        videoListDiv.className = 'videoListDiv pointer';
        videoImgSpan.className = 'videoImgSpan';
        videoLetterSpan.className = 'videoLetterSpan';
        videoTitleDiv.className = 'videoTitleDiv';
        videoOriginDiv.className = 'videoOriginDiv';

        img.src = item.videoImg;

        videoImgSpan.append(img);
        videoTitleDiv.append(item.videoTitle);
        videoOriginDiv.append(item.videoOriginInstt);

        videoLetterSpan.append(videoTitleDiv);
        videoLetterSpan.append(videoOriginDiv);

        videoListDiv.append(videoImgSpan);
        videoListDiv.append(videoLetterSpan);


        videoListDiv.addEventListener('click', () => {
            location.href = 'junior?videoLink=' + item.videoLink;
        })

        videoListElem.append(videoListDiv);

    })
}

apiTest();