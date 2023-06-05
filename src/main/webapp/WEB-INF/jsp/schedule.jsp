<%@ page contentType="text/html;charset=UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Schedule</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="<c:url value='/css/schedule.css'/>">
</head>
<body>
<t:navbar/>
<div class="schedule-desc"><h3>My schedule</h3></div>

<center>
    <c:if test="${fact != null}">
        <h6 style="color: darkgrey">Statistical fact: The count of your trainings is more than ${fact} other coaches from your category!</h6>
    </c:if>
    <div class="schedule-list">
        <c:forEach var = "training" items="${trainings}">
            <t:schedule day="${training.day.name}" group="${training.currentGroup}" hs="${training.startHour}" ms="${training.startMinute}" he="${training.endHour}" me="${training.endMinute}"/>
        </c:forEach>
    </div>
</center>
<div class="settings" style="text-align: center;"><h3>Schedule Settings</h3></div>
<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">

<div class="schedule-settings">
    <form:form method="post" action="trainings/add" modelAttribute="form">
        <h6 style="color: darkgrey">You can add only one training for group at one day</h6>
        <c:if test="${addGroupError == true}">
            <h5 class='wrong-data' style="color: red">Incorrect group name</h5>
        </c:if>
        <c:if test="${addTimeError == true}">
            <h5 class='wrong-data' style="color: red">Incorrect time of training</h5>
        </c:if>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
        <b>Add new training:</b>
        <br>
        <label for="inputDay">Day of week:</label>
        <form:select path="day" class="form-select" id="inputDay" >
            <option>Monday</option>
            <option>Tuesday</option>
            <option>Wednesday</option>
            <option>Thursday</option>
            <option>Friday</option>
            <option>Saturday</option>
            <option>Sunday</option>
        </form:select>

        <div class="mb-3">
            <label for="inputName">Name of group:</label>
            <div id="helpBlock" class="form-text">
                You can add training only for existing <a href="/groups">groups</a>
            </div>
            <form:input path="group" id="inputName" type="text" class="form-control" pattern="[a-zA-Zа-яА-Я0-9_.\-]+" aria-describedby="helpBlock"/>
        </div>
        <div id="helpBlock1" class="form-text">
            Training cannot last less than 15 minutes
        </div>
        <div class="mb-3">
            <label for="inputHours">Start hour:</label>
            <form:input path="startHour" id="inputHours" type="number" class="form-control" min="5" max="22" aria-describedby="helpBlock1"/>
        </div>
        <div class="mb-3">
            <label for="inputMinutes">Start minute:</label>
            <form:input path="startMinute" id="inputMinutes" type="number" class="form-control" min="0" max="59"/>
        </div>
        <div class="mb-3">
            <label for="inputHours1">End hour:</label>
            <form:input path="endHour" id="inputHours1" type="number" class="form-control" min="5" max="22"/>
        </div>
        <div class="mb-3">
            <label for="inputMinutes1">End minute:</label>
            <form:input path="endMinute" id="inputMinutes1" type="number" class="form-control" min="0" max="59"/>
        </div>
        <br>
        <div class="data-button">
            <input type="submit" value="Add" name="color-button" class="btn btn-dark"/>
        </div>
    </form:form>

    <form:form method="post" action="trainings/delete" modelAttribute="form">
        <c:if test="${deleteError == true}">
            <h5 class='wrong-data' style="color: red">Cannot find training</h5>
        </c:if>
        <c:if test="${deleteGroupError == true}">
            <h5 class='wrong-data' style="color: red">Incorrect group name</h5>
        </c:if>
        <b>Delete training:</b>
        <br>
        <label for="inputDeleteDay">Day of week:</label>
        <form:select path="deleteDay" class="form-select" id="inputDeleteDay" >
            <option>Monday</option>
            <option>Tuesday</option>
            <option>Wednesday</option>
            <option>Thursday</option>
            <option>Friday</option>
            <option>Saturday</option>
            <option>Sunday</option>
        </form:select>
        <div class="mb-3">
            <label for="inputDeleteName">Name of group:</label>
            <div id="helpBlock2" class="form-text">
                You can delete training only for existing <a href="/groups">groups</a>
            </div>
            <form:input path="deleteGroup" id="inputDeleteName" type="text" class="form-control" pattern="[a-zA-Zа-яА-Я0-9_.\-]+" aria-describedby="helpBlock1"/>
        </div>
        <div class="data-button">
            <input type="submit" value="Delete" name="color-button" class="btn btn-dark"/>
        </div>
    </form:form>
</div>

</body>
</html>