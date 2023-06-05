<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Sign in</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
</head>
<body class="reg_body">
<br>
<h2 style="text-align: center">Sign in to use all functions of the website</h2>
<br>
<a style="margin-left: 25px" href="/signUp">Sign Up</a>
<br>
<form:form method="post" modelAttribute="signInForm">
    <div class="m-5 m-lg-5">
        <label for="InputEmail" class="form-label">Email</label>
        <form:input path="email" id="InputEmail" type="email" class="form-control" placeholder="example@gmail.com"/>
        <form:errors path="email" cssStyle="color: red" element="div" />
    </div>

    <div class="m-5 m-lg-5">
        <label for="inputPassword" class="form-label">Password</label>
        <form:input path="password" id="inputPassword" type="password" class="form-control"/>
        <form:errors path="password" cssStyle="color: red" element="div" />
    </div>
    <div class="data-button" style="text-align: center">
        <input type="submit" value="Submit" name="color-button" class="btn btn-dark"/>
    </div>
</form:form>
<div class="m-5 m-lg-5">
    <c:if test="${error != false}">
        <h5 class = 'wrong-data' style="color: red">Incorrect email or password. Try again</h5>
    </c:if>
</div>

</body>
</html>