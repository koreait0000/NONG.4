const comm_pagingElem = document.querySelector('#comm-paging');

function moveToDetail(iboard){
    location.href = 'boardDetail?iboard=' + iboard;
}

function paging(param) {
    const prevBtn = document.createElement('button');
    const nextBtn = document.createElement('button');
    let page = 1;

    prevBtn.innerText = '이전';
    nextBtn.innerText = '다음';


    const init = {
        method: 'POST',
        body: JSON.stringify(param),
        headers: {
            'accept': 'application/json',
            'content-type': 'application/json;charset=UTF-8'
        }
    };

    prevBtn.addEventListener('click',() => {

        fetch('community', init)
            .then(res => res.json())
            .then(myJson => {
                console.log("myjson : "+myJson);

                if(page == 1 || page > 1) {
                    // prevBtn.disabled = true;
                    if(page != 1) {
                        page--;
                    }
                    console.log("page :" + page);
                }
            })
    })
    comm_pagingElem.append(prevBtn,nextBtn);
}

paging();
