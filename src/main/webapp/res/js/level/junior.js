const videoBtnElem = document.querySelector('.videoBtn');
const levelVideoElem = document.querySelector('.levelVideo');
const apiDiv = document.createElement('div');
const apiInput = document.createElement('input');

videoBtnElem.addEventListener('click', () => {
    levelVideoElem.classList.remove('hidden');
})


function apiTest() {
    fetch('/openapi/apiTest', {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json',
        },
        url:
            'http://api.nongsaro.go.kr/service/cropEbook/videoList?'
        +'apiKey=20210713ZU1XHCDLCGWITY5LN99HBW'
        +'&type=json'
        +'&numOfRows=1000'
    })
        .then(res => res.json)
        .then(myJson => {
            console.log(myJson);
            let videoImg = myJson['videoImg'];
            console.log(videoImg)
        })


}

apiTest();