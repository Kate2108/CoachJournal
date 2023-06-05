<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Confirm email</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
</head>
<body>
<div class="m-5 m-lg-5">
    <h5 style="color: darkgrey;"> You receive message with code. Check your email</h5>
    <div class="mb-3">
        <c:if test="${error != null}">
            Incorrect code
        </c:if>
    <form:form method="post" action="confirmation" modelAttribute="form">
        <label for="InputCode" class="form-label">Code</label>
        <form:input path="code" id="InputCode" type="number" class="form-control"/>
        <br>
        <div class="data-button">
            <input type="submit" value="Submit" name="color-button" class="btn btn-dark"/>
        </div>
    </form:form>
    </div>
</div>
</body>
</html>
