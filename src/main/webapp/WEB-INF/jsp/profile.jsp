<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>My profile</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="<c:url value='/css/profile.css'/>">
</head>
<body>
<t:navbar/>
<div class="container">

    <div class="data-form">
        <h3 style="text-align: center">My profile</h3>
        <br>
        <div class="field">
            Username: <p><c:out value="${profile.account.username}"/></p>
        </div>
        <div class="field">
            Email: <p><c:out value="${profile.account.email}"/></p>
        </div>

        <div class="field">
            Country: <p><c:out value="${profile.account.country}"/></p>
        </div>

        <div class="field">
            Gender: <p><c:out value="${profile.account.gender}"/></p>
        </div>

        <br>
        <a><button type="submit" id="logOut" class="btn btn-dark" >Log out</button></a>
    </div>

    <div class="data-form">
        <h3 style="text-align: center">My stats</h3>
        <br>
        <div class="field">
            Category: <p><c:out value="${profile.category}"/></p>
        </div>
        <div class="field">
            Work Experience: <p><c:out value="${profile.experience}"/></p>
        </div>
        <div class="field">
            Class: <p><c:out value="${profile.sportClass}"/></p>
        </div>
        <br>
        <div class="data-button">
            <a href="profile/edit" class="btn btn-dark">Edit</a>
        </div>
    </div>
</div>
<script src="<c:url value='/js/js.js'/>"></script>
</body>
</html>