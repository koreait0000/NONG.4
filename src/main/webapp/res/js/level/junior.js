const videoBtnElem = document.querySelector('.videoBtn');
const levelVideoElem = document.querySelector('.levelVideo');

videoBtnElem.addEventListener('click', () => {
    levelVideoElem.classList.remove('hidden');
})