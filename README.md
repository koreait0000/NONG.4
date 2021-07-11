# NONG.4<br>

<p align="center">
	<img width="70%" height="70%" src="documents/video/nong4.gif"></img>
	</p>

## 목차<br>
1. [개요](#chapter1)
    - 주제 선정 과정 및 기준
    - 서비스 소개
    - 구상 동기
    - 기대 효과
2. [적절성](#chapter2)
    - 사용 API
    - flow chart
    - 벤치마킹
3. [기술성](#chapter3)
    - UI 설계 및 구현
5. [가능성](#chapter4)
    - 발전 가능성
    - 사회적 가치창출
6. [요구분석](#chapter5)
    - 시장조사
    - 실태조사
7. [기타](#chapter6)
    - 개발 툴
    - 팀원 구성및 역할분담

## 개요<a id="chapter1"></a><br>
### 주제 선정 과정 및 기준<br>

- 브레인 스토밍을 통한 주제 선정 과정

<p align="center">
<img width="70%" height="70%" src="/documents/img/브레인스토밍.png"></img>
</p>

- 주제 선정 기준

<p align="center">
<img width="70%" height="70%" src="/documents/img/주제선정기준.png"></img>
</p>

### <서비스 소개><br>
 - 사회적 임업 및 농업 활성화를 위해 초보, 예비 임, 농업인들을 위한 길라잡이 플랫폼
### <구상 동기><br>
 - 조사를 해보니 기존 농업인을 위한 서비스는 잘 마련되어 있었지만, 초보 임업인, 농업인들이 농사를 짓기엔 서비스 및 가이드 라인이 부족하다.
 - 그래서 초보 및 예비 임, 농업인들을 위한 길라잡이 플랫폼을 제작하기 위해 프로젝트를 시작하였다.
### <기대 효과><br>
 - 현재 연령 분포가 중장년층에서 노년층이 가장 많은 산간 및 농업지역에서 부족한 청년층에 인구유입을 기대해 볼 수 있다.
 - 젊은 층에 대한 유입으로 동식물에 대한 아이들의 학습효과도 기대해 볼 수 있다.
 - 그로 인해 나아가서는 농촌 주민에 대한 삶의 질이 개선된다.
## 적절성<a id="chapter2"></a><br>
### <사용 API><br>
 - 산림청_임산물DB백과(임업진흥원)(https://www.data.go.kr/data/3070830/openapi.do)
-> 다양한 임산물 정보를 제공받을 수 있다.
 - 산림청_산림자원통계 서비스(https://www.data.go.kr/data/15080832/openapi.do)
-> 산림기본통계, 국가산림자원조사, 전국산주현황, 임산물생산조사 등에 조사자료를 토대로 한 API를 활용하여 판매 및 유통 서비스 서포터 기능을 제공
 - 농촌진흥청_농작업달력 정보(https://www.data.go.kr/data/15033498/openapi.do)
-> 작목별 농작업 일정 정보를 달력 형태의 디자인으로 제공하여 쉽게 직관적으로 정보를 확인할 수 있는 정보 제공 기능
 - 원클릭 농업기술(http://api.nongsaro.go.kr/sample/ajax/oneClickFarmngTchnlgy/oneClickFarmngTchnlgyList.html)
-> 원클릭으로 쉽게 작목별 정보를 직관적으로 제공하는 기능
 - 주제별 짧은 기술동영상(http://api.nongsaro.go.kr/sample/ajax/curationMvp/curationMvpList.html)
-> 단계별 영상을 활용해 사용자들에게 알맞은 가이드라인 제공
수 있는 정보 제공 기능
### Flow Chart<br>

<p align="center"><img width="70%" height="70%" src="/documents/img/flowchart.png"></img></p>

### <예산군 농업 기술센터(1)><br>
- 장점 : 다양한 건강관련(대처 요령, 예방 및 상세한 설명) 정보 제공
- 단점 : 예산군이라는 지역적으로 한정되어 있어 타 지역에서는 사용이 어려움.
### <귀농 귀촌 종합센터(2)><br>
- 장점 : 귀농 귀촌 정착지원을 위한 교육 프로그램 및 교육 정보의 링크가 다수 존재
- 단점 : 너무 많은 정보를 제공하고 있어 사용자에게 적합한 정보를 찾기에는 어려움
### <귀농산 어촌 종합센터(3)><br>
- 장점 : 다양한 서비스를 제공하여 사용자로 하여금 많은 서비스를 제공받을 수 있다.
- 단점 : 처음 사용하는 사람에게는 어디서부터 시작해야 될지 막막하게 느낄수도 있다.
### <(1),(2),(3)에 결과를 토대로 단점들을 보완하고 강점을 부각하여 설계함><br>
- 귀산촌 귀농 정착 지원을 위한 프로그램 연결 서비스 지원
- 직관적인 UI 설계 및 배치
- 카테고리 별 간략하고 실속 있는 정보 제공
- 자율과제 및 지정과제를 포함한 추가 기능 구현
- 커뮤니티 서비스를 통한 농업, 임업 소셜 네트워크 구축
- 카카오톡과 연동하여 새로운 플랫폼 활용도 기대

## 기술성<a id="chapter3"></a><br>

- 직관적인 UI 구성
- 맞춤별 서비스 지원
- 건의 및 불편사항 접수
- 카카오 채널 연동 챗봇 서비스 제공
- 맞춤별 서비스에 농촌 공공데이터 활용

## 가능성<a id="chapter4"></a>
### 발전가능성<br>
#### 커뮤니티 비즈니스 등에 대한 발전 가능성<br>
- 비대면 상담 서비스, 게시판을 통한 커뮤니티 구축
- 누구나 쉽게 서비스를 확인할 수 있도록 시각적 구성 페이지 구축
- 오픈소스로 이루어진 프로그램으로 플랫폼 구축 비용 절감
- 구체적인 개발문서 정리로 개발 관련 커뮤니티 발전도 가능
- 맞춤별 서비스에 농촌 공공데이터 활용
#### 사회적 가치 창출<br>
 - 예비 임, 농업인들의 삶의 질 향상
 - 산림지역의 인구증가로 인한 지역경제 활성화
 - 과소화·고령화, 공동체 해체 위기에 직면한 대한민국의 농촌공동체가 농업을 통해 지역사회 문제를 스스로 해결할 수 있는 주체로 역량을 강화하며 활성화할수 있도록 국가차원에서 지역 단위의 사회적 농업을 체계적으로 육성할 수 있는 정책적 방안을 제시할 뿐만 아니라 예비,초보 임, 농업인들을 위한 다양한 사업 추진 가능성 증가,
 - 지역공동체와 사회적 농장 간 네트워킹, 농장들의 연대를 증가시켜 사회적 임, 농업을 활성화할 수 있는 제도적 기반 마련

## 요구분석<a id="chapter"></a><br>

## 개발툴<a id="chapter6"></a><br>

<p align="left">
<kbd><img src="/documents/img/hs.jpg"></img></kbd>
<kbd><img src="/documents/img/intelliJ.jpg"></img></kbd>
</p>

## 데이터베이스<br>

<kbd><img src="/documents/img/mysql.png"></img></kbd>

## 사용 기술<br>

<p align="left">
<kbd><img src="/documents/img/ajax.jpg"></img></kbd>
<kbd><img src="/documents/img/bcrypt.png"></img></kbd>
<kbd><img src="/documents/img/htmlcssjs.png"></img></kbd>
<kbd><img src="/documents/img/java.png"></img></kbd>
<kbd><img src="/documents/img/json.png"></img></kbd>
<kbd><img src="/documents/img/jsoup.png"></img></kbd>
<kbd><img src="/documents/img/jstl.jpg"></img></kbd>
<kbd><img src="/documents/img/mybatis.jpg"></img></kbd>
<kbd><img src="/documents/img/security.jpg"></img></kbd>
<kbd><img src="/documents/img/spring boot.png"></img></kbd>
</p>

## 팀 정보<br>

<table width="788" align="center">
<thead>
<tr>
<th width="100" align="center">성명</th>
<th width="150" align="left">담당</th>
<th width="175" align="center">이메일</th>
</tr> 
</thead>
<tbody>
<tr>
<td width="100" align="center">이준형</td>
<td width="150">프론트엔드 개발<br>개발 작업 등</td>
<td width="175" align="center">
	<a href="mailto:dksms78@gmail.com"><img src="https://img.shields.io/static/v1?label=&message=dksms78@gmail.com&color=orange&style=flat-square&logo=gmail"></a>
	</td>
</tr>
<tr>
<td width="100" align="center">김상혁</td>
<td width="150">백엔드 개발<br>영상 작업 등</td>
<td width="175" align="center">
	<a href="mailto:dksms78@gmail.com"><img src="https://img.shields.io/static/v1?label=&message=dksms78@gmail.com&color=orange&style=flat-square&logo=gmail"></a>
	</td>
</tr><tr>
<td width="100" align="center">유병욱</td>
<td width="150">프론트엔드 개발<br>개발문서 작업 등</td>
<td width="175" align="center">
	<a href="mailto:ybo4117@naver.com"><img src="https://img.shields.io/static/v1?label=&message=ybo4117@naver.com&color=orange&style=flat-square&logo=gmail"></a>
	</td>
</tr><tr>
<td width="100" align="center">하승우</td>
<td width="150">백엔드 개발<br>영상 작업 등</td>
<td width="175" align="center">
	<a href="mailto:hahas5@naver.com"><img src="https://img.shields.io/static/v1?label=&message=hahas5@naver.com&color=orange&style=flat-square&logo=gmail"></a>
	</td>
</tr>
</tbody>
</table>
