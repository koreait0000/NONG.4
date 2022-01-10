<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="apiContArea">
    <h3>
        <p class="fl">
            <strong>농작업일정</strong>
        (${workScheduleDt.workScheduleDtList[0].kidofcomdtySeCodeNm} - ${workScheduleDt.workScheduleDtList[0].cntntsSj})
        </p>
        <p class="bgRp fl">
            <strong class="miniF">상세</strong>
        </p>
    </h3>
</div>
<div id="nongScheduleTit">
    ${workingDomain.workingInfoList[0].htmlCn}
</div>

<div id="tableRound">
    ${workScheduleDt.workScheduleDtList[0].cn}
</div>
