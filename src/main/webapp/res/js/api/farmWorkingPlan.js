const tbody = document.querySelector('.tbody');

function fncViewButtom(kidofcomdtySeCode) {
    console.log('kidofcomdtySeCode : ' + kidofcomdtySeCode)

    fetch('farmWorkingPlan',
        {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(kidofcomdtySeCode)
        })
        .then(res => res.json())
        .then(myJson => {
            console.log('?? : ' + myJson.workScheduleLst.length)
            tabArea(myJson);
        })
}

function tabArea(myJson) {
    myJson.workScheduleLst.forEach(function (item){
        const scheduleTr = document.createElement('tr');
        const scheduleTd = document.createElement('td');
        const scheduleA  = document.createElement('a');

        scheduleA.innerText = item.sj;

        scheduleTd.append(scheduleA);
        scheduleTr.append(scheduleTd);
        tbody.append(scheduleTr);
    })
}