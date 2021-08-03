<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link rel="stylesheet" href="/res/css/chat.css">
<div id="container" class="container">
  <h1>채팅</h1>
  <div id="chating" class="chating">
  </div>

  <div id="yourName">
    <table class="inputTable">
      <tr>
        <th>사용자명</th>
        <th><input type="text" name="userName" id="userName"></th>
        <th><button onclick="chatName()" id="startBtn">이름등록</button></th>
      </tr>
    </table>
  </div>
  <div id="yourMsg">
    <table class="inputTable">
      <tr>
        <th>메시지</th>
        <th><input id="chatting" placeholder="메시지 입력.."></th>
        <th><button onclick="send()" id="sendBtn">보내기</button></th>
      </tr>
    </table>
  </div>
</div>

<script src="/res/js/chat.js"></script>