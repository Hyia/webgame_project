<%@ page session="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div id="after_login" >
<form id='afterloginform' action="/logout" method='post'>
	<p>${UserInfo.getUserNicName()} 님 환영합니다.<input type="submit" value='logout'></p>
	<p>자원: ${UserInfo.getSaveProduction()}</p>
</form>
	<table id='buildtimeTable'>
   <c:forEach items="${constructTimes}" var="mapElement">
      <tr>
           <td>성 ${mapElement.key.name}(위치:${mapElement.key.locationID})</td>
      </tr>
      <tr>
           <c:forEach items="${mapElement.value}" var="listElement" >
              <td>건물방번호: ${listElement.key}</td>
              <td>시간: ${listElement.value}</td>
           </c:forEach>
      </tr>
   </c:forEach>
	<table>	
	<table id='marchtimeTable'>
   <c:forEach items="${marchTimes}" var="mapElement">
      <tr>
           <td colspan="2">성 ${mapElement.key.name}(위치:${mapElement.key.locationID})</td>
      </tr>
           <c:forEach items="${mapElement.value}" var="listElement" >
      <tr>
              <td colspan="2">영웅ID: ${listElement.key}</td>
              </tr><tr>
              <td><c:if test="${listElement.value.isReturning()}"><img src="/images/img_attack.png"/></c:if>
              <c:if test="${!listElement.value.isReturning()}"><img src="/images/img_returning.png"/></c:if></td>
              <td>${listElement.value.getRemainTime()}</td>
      </tr>
           </c:forEach>
   </c:forEach>
	<table>	
</div>

