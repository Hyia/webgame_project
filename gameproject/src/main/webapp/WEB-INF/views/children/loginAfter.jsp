<%@ page session="true" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div id="after_login" >
	<p>${UserInfo.getUserNicName()} 님 환영합니다.</p>
	<p>자원: ${UserInfo.getSaveProduction()}</p>
	<table id='buildtimeTable'>
   <c:forEach items="${constructTimes}" var="mapElement">
      <tr>
           <td>성 ${mapElement.key.name}(lo:${mapElement.key.locationID})</td>
      </tr>
      <tr>
           <c:forEach items="${mapElement.value}" var="listElement" >
              <td>건물방번호: ${listElement.key}</td>
              <td>시간: ${listElement.value}</td>
           </c:forEach>
      </tr>
   </c:forEach>
	<table>	
</div>

