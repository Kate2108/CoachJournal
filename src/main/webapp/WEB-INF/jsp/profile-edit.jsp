<%@ page contentType="text/html;charset=UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<html>
<head>
  <title>Edit profile</title>
</head>
<body>
  <t:navbar/>
  <form:form method="post" action="edit" modelAttribute="profileForm">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
    <div class="m-5 m-lg-5">
      <div class="mb-3">
        <label for="selector" class="form-label">Select new category</label>
        <form:select path="category" class="form-select" id="selector">
          <option>Second</option>
          <option>First</option>
          <option>Superior</option>
        </form:select>
        <form:errors path="category" cssStyle="color: red" element="div" />
      </div>
      <br>
      <div class="mb-3">
        <label for="InputExp" class="form-label">Select new work experience</label>
        <form:input path="experience" id="InputExp" type="number" class="form-control" min="1" max="60"/>

        <form:errors path="experience" cssStyle="color: red" element="div" />
      </div>
      <div class="mb-3">
        <label for="selector1" class="form-label">Select new sport class</label>
        <form:select path="sportClass" class="form-select" id="selector1">
          <option>Candidate for master of Sports</option>
          <option>Master of Sports</option>
          <option>Master of Sports of international class</option>
        </form:select>
        <form:errors path="sportClass" cssStyle="color: red" element="div" />
      </div>
    </div>
      <div class="m-5 m-lg-5">
      <div class="data-button">
        <input type="submit" value="Save" name="color-button" class="btn btn-dark"/>
      </div>
    </div>
  </form:form>

</body>
</html>