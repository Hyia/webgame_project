<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
    <meta charset="utf-8">
    <title>Castle PopUp</title>
</head>
<script src="https://code.jquery.com/jquery-1.8.2.min.js"></script>
<script src="https://cdn.rawgit.com/vast-engineering/jquery-popup-overlay/1.7.13/jquery.popupoverlay.js"></script>

<script>
    $(document).ready(function() {

        // Initialize the plugin
        $('#my_popup').popup();

    });
</script>

<body>

<!-- Add an optional button to open the popup -->
<button class="my_popup_open">Open popup</button>

<!-- Add content to the popup -->
<div id="my_popup">

    얏호

    <!-- Add an optional button to close the popup -->
    <button class="my_popup_close">Close</button>

</div>



</body>
</html>
