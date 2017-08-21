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
    
//		model.addAttribute("type", "list");
//		model.addAttribute("loglist", logs);


			
		});
    
    </script>    
</head>
<body>
<div id='log' class='logarea'>
<c:if test="${pageType !=null && pageType == 'list' }">
<table width="100%">
<tr>
<td>No</td>
<td>Attacker</td>
<td>Defender</td>
<td>Date</td>
<td>wins</td>
</tr>
<c:forEach var="i" items="${logs}" varStatus="status">
<tr><a href="/combat/loglist/${logs.LogName}" target="_self">
<td>${status.count}</td>
<td>${logs.attackUserID}</td>
<td>${logs.defenseUserID}</td>
<td>${logs.battleDate}</td>
<td>${logs.whoWins}</td>
</a></tr>
</c:forEach>
</table>
</c:if>
 </div>

</body>
</html>