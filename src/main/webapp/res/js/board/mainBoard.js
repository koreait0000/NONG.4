const recordElem = document.querySelector('.record');

console.log(recordElem.dataset.hitCount);

function moveToDetail(iboard){
    location.href = 'boardDetail?iboard=' + iboard;
}

function hitAjax(hitCount) {

}