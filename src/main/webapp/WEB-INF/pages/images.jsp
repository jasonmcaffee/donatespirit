
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Images</title>
    <link rel="stylesheet" type="text/css" href="resources/css/style.css">
</head>
<body>
    <%@ include file="/WEB-INF/pages/shared/topbanner.jsp" %>

    <div>${errorMessage}</div>
    <form action="images/upload" method="post" enctype="multipart/form-data">
        <label>image</label><input name="image" type="file" required/>
        <label>description</label><input name="description" type="text" required/>
        <input type="submit"/>
    </form>
</body>
</html>