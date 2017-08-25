<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="true" %>
<html lang="kr">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
<script src='http://code.jquery.com/jquery-3.1.0.js'></script>
    <script>
      $(document).ready( function(e){
			$('#heroinfo').load('/hero/${heros.get(0).heroID}');
			
			$('table').click(function(e) {
				$('#heroinfo').load('/hero/'+$(this).attr('id'));
			})
			
		});
    
    </script>    
</head>
<body>
<div id="heroview">
<table>
<tr>
<td rowspan='${heros.size()}'>
<div id="herolist">
<c:forEach var="i" items="${heros}">
<table id='${i.heroID}' border="1">
    <tr>
        <td rowspan='2'>이미지(예정)</td>
        <td>무명의 영웅(${i.heroID}) LV.${i.heroLevel}</td>
    </tr><tr>
        <td>${i.locationID}</td>
    </tr>
</table>
</c:forEach>
</div>
</td>
<td>
<div id='heroinfo'></div>
</td>
</tr>
</table>
</div>

</body>
</html>