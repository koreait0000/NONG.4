const juniorElem = document.querySelector('.junior-class');
const intermediateElem = document.querySelector('.intermediate-class');
const advancedElem = document.querySelector('.advanced-class');
const masterElem = document.querySelector('.master-class');


juniorElem.addEventListener('click', () => {
    console.log('dd');
    location.href = '/level/junior';
});
intermediateElem.addEventListener('click', () => {
    console.log('dd');
    location.href = '/level/intermediate';
});
advancedElem.addEventListener('click', () => {
    console.log('dd');
    location.href = '/level/advanced';
});
masterElem.addEventListener('click', () => {
    console.log('dd');
    location.href = '/level/master';
});

