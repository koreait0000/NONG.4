const apiVideoElem = document.querySelector('.apiVideo-class');
const monthFarmTechElem = document.querySelector('.monthFarmTech-class');
const farmWorkingPlanElem = document.querySelector('.farmWorkingPlan-class');

apiVideoElem.addEventListener('click', () => {
    location.href="/api/apiVideo";
});
monthFarmTechElem.addEventListener('click', () => {
    location.href="/api/monthFarmTech";
});
farmWorkingPlanElem.addEventListener('click', () => {
    location.href="/api/farmWorkingPlan";
});

function moveToDetail(iboard){
    location.href = '/board/boardDetail?iboard=' + iboard;
}
