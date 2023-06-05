<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
    <title>Registration</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
</head>
<body class="reg_body">
    <div class="m-5 m-lg-5">
        <c:if test="${errors != null}">
            <c:forEach var="error" items="${errors}">
                <h5 class = 'wrong-data' style="color: red">${error}</h5>
            </c:forEach>
        </c:if>
    </div>
    <form:form method="post" action="signUp" modelAttribute="signUpForm">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
        <div class="m-5 m-lg-5">
            <div class="mb-3">
                <label for="InputUsername" class="form-label">Username</label>
                <form:input path="username" id="InputUsername" type="text" class="form-control"/>
                <form:errors path="username" cssStyle="color: red" element="div" />
            </div>

            <div class="mb-3">
                <label for="InputEmail" class="form-label">Email address</label>
                <form:input path="email" id="InputEmail" type="email" class="form-control" placeholder="example@gmail.com"/>
                <form:errors path="email" cssStyle="color: red" element="div" />
            </div>

            <div class="mb-3">
                <label for="inputPassword" class="form-label">Password</label>
                <form:input path="password" id="inputPassword" type="password" class="form-control"/>
                <form:errors path="password" cssStyle="color: red" element="div" />
            </div>

            <div class="mb-3">
                <label for="inputRepeatPassword" class="form-label">Repeat Password</label>
                <form:input path="repeatPassword" id="inputRepeatPassword" type="password" class="form-control"/>
                <form:errors path="repeatPassword" cssStyle="color: red" element="div" />
            </div>

            <div class="form-check">
                <label class="form-check-label" for="male">Male</label>
                <form:radiobutton path="gender" class="form-check-input" value="male" id="male"/>
            </div>
            <div class="form-check">
                <label class="form-check-label" for="female">Female</label>
                <form:radiobutton path="gender" class="form-check-input" value="female" id="female"/>
                <form:errors path="gender" cssStyle="color: red" element="div" />
            </div>
            <br>
            <label for="selector" class="form-label">Country</label>
            <form:select path="country" class="form-select" id="selector">
                <option>Armenia</option>
                <option>Belarus</option>
                <option>China</option>
                <option>France</option>
                <option>Kazakhstan</option>
                <option>Germany</option>
                <option>Russia</option>
                <option>Spain</option>
            </form:select>
            <form:errors path="country" cssStyle="color: red" element="div" />
            <br>
            <div class="data-button">
                <input type="submit" value="Submit" name="color-button" class="btn btn-dark"/>
            </div>
        </div>
    </form:form>
</body>
</html>