const serviceModalElem = document.querySelector('.service_modal');
const serviceBeforeElem = document.querySelector('.service_before');
const serviceAfterElem = document.querySelector('.service_after');
const serviceBtnElem = document.querySelector('.serviceBtn');
const sendBtnElem = document.querySelector('.sendBtn');
const moreServiceBtnElem = document.querySelector('.moreServiceBtn');
const modalCloseElem = document.querySelector('.modal_close');
const serviceTitleElem = document.querySelector('.serviceTitle');
const serviceCtntElem = document.querySelector('.serviceCtnt');

serviceBtnElem.addEventListener('click', () => {
    serviceBeforeElem.classList.add('hide');
    serviceModalElem.classList.remove('hide');
})

sendBtnElem.addEventListener('click', () => {
    serviceTitleElem.value = '';
    serviceCtntElem.value = '';
    serviceModalElem.classList.add('hide');
    serviceAfterElem.classList.remove('hide');
})

moreServiceBtnElem.addEventListener('click', () => {
    serviceTitleElem.value = '';
    serviceCtntElem.value = '';
    serviceAfterElem.classList.add('hide');
    serviceModalElem.classList.remove('hide');
})

modalCloseElem.addEventListener('click', () => {
    serviceTitleElem.value = '';
    serviceCtntElem.value = '';
    serviceModalElem.classList.add('hide');
    serviceBeforeElem.classList.remove('hide');
})