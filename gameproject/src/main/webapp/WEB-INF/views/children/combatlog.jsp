<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="true" %>
<html lang="kr">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <style>
        .logarea { padding:20px 0 10px 0; text-align: center; border: solid; background-color: #0ab4ff}
        .logtable { padding:20px 0 10px 0; text-align: center; border: solid; color: white;}
        .logtitle { color: red}
    </style>
<script src='http://code.jquery.com/jquery-3.1.0.js'></script>
    <script>
    //머리
      $(document).ready( function(e){
    
			$('tr[id]').click(function(e) {
				$(this).parents('#maincontent').load('/combat/loglist/'+$(this).attr('id'));
			});
		});
    
    </script>    
</head>
<body>
<div id='log' class='logarea'>
<c:if test="${pageType !=null && pageType == 'list' }">
<table id='logtable' width="100%">
<tr>
<td>No</td>
<td>Attacker</td>
<td>Defender</td>
<td>Date</td>
<td>wins</td>
</tr>
<c:forEach var="i" items="${loglist}" varStatus="status">
<tr id='${i.logName}'>
<td>${status.count}</td>
<td>${i.attackUserID}</td>
<td>${i.defenseUserID}</td>
<td>${i.battleDate}</td>
<td>${i.whoWins}</td>
</tr>
</c:forEach>
</table>
</c:if>
 </div>

</body>
</html>