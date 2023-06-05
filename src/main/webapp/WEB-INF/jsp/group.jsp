<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
      integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<link rel="stylesheet" type="text/css" href="<c:url value='/css/group.css'/>">
<html>
<head>
    <title>My groups</title>
</head>
<body>
<t:navbar/>
<div class="group-description"><h3>My groups</h3></div>
<center>
    <c:if test="${fact != null}">
        <h6 style="color: darkgrey">Statistical fact: The number of your athletes is more than ${fact} other coaches from
            your category!</h6>
    </c:if>
        <div class="group-list">
        <c:forEach var="group" items="${groups}" varStatus="counter">
            <t:group num="${counter.count}" name="${group.name}" count="${group.countOfMembers}"/>
        </c:forEach>
    </div>
</center>

<div class="settings"><h3>Group's Settings</h3></div>

<div class="group-settings">
    <form:form method="post" action="groups/add" modelAttribute="form">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
        <c:if test="${addError == true}">
            <h5 class='wrong-data' style="color: red">name cannot be empty</h5>
        </c:if>
        <b>Add new group:</b>
        <div class="mb-3" style="width: 170px;">
            <label for="inputName">Name of group:</label>
            <form:input path="name" id="inputName" type="text" class="form-control" pattern="[a-zA-Zа-яА-Я0-9_.\-]+"/>
        </div>
        <div class="mb-3" style="width: 150px;">
            <label for="inputCount">Count of members:</label>
            <form:input path="countOfMembers" id="inputCount" type="number" class="form-control" min="1"/>

            <div class="data-button">
                <input type="submit" value="Add" name="color-button" class="btn btn-dark"/>
            </div>
        </form:form>
    </div>
    <div class="group-settings">
        <form:form method="post" action="groups/delete" modelAttribute="form">
        <c:if test="${deleteError == true}">
            <h5 class='wrong-data' style="color: red">enter name of existed group</h5>
        </c:if>
        <b>Delete group:</b>
        <div class="mb-3" style="width: 170px;">
            <label for="inputDeleteName">Name of group:</label>
            <form:input path="deleteName" id="inputDeleteName" type="text" class="form-control"
                        pattern="[a-zA-Zа-яА-Я0-9_.\-]+"/>
        </div>
        <div class="mb-3" style="width: 150px;">
            <div class="data-button">
                <input type="submit" value="Delete" name="color-button" class="btn btn-dark"/>
            </div>
            </form:form>
        </div>
    </div>
    <div class="group-settings">
        <form:form method="post" action="groups/update" modelAttribute="form">
        <c:if test="${updateError == true}">
            <h5 class='wrong-data' style="color: red">enter name of existed group</h5>
        </c:if>
        <b>Update group:</b>
        <div class="mb-3" style="width: 170px;">
            <label for="inputUpdateName">Name of group:</label>
            <form:input path="updateName" id="inputUpdateName" type="text" class="form-control"
                        pattern="[a-zA-Zа-яА-Я0-9_.\-]+"/>
        </div>
        <div class="mb-3" style="width: 150px;">
            <label for="inputNewCount">Count of members:</label>
            <form:input path="newCountOfMembers" id="inputNewCount" type="number" class="form-control" min="1"/>

            <div class="data-button">
                <input type="submit" value="Update" name="color-button" class="btn btn-dark"/>
            </div>
            </form:form>
        </div>
    </div>
</div>
</body>
</html>