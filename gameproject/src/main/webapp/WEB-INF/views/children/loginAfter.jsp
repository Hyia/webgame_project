<%@ page session="true" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div id="after_login" >
	<p>${UserInfo.getUserNicName()}님 환영합니다.</p>
	<p>자원 : ${UserInfo.getSaveProduction()}</p>
</div>
