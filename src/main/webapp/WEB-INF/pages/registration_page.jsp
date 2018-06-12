<%@ include file="library.jsp"%>
<div class="container">
    <h1 align="center">Registration</h1>
        <c:if test="${not empty regSuc}">
            <div class="alert alert-success">${regSuc}</div>
        </c:if>
    <div align="center">
    <form:form method="post" id="regform" action="registration" modelAttribute="registration" class="form-horizontal">
    <div class="form-group col-sm-6">
        <form:label path="username">Login:</form:label>
        <form:input class="form-control" placeholder="Enter login" type="text" id="name" path="username"/>
        <form:errors path="username" cssClass="alert-danger"/>
    </div>
    <div class="form-group col-sm-6">
        <form:label path="email">E-Mail:</form:label>
        <form:input class="form-control" placeholder="Enter E-Mail" type="text" id="email" path="email"/>
        <form:errors path="email" cssClass="alert-danger"/>
    </div>
    <div class="form-group col-sm-6">
        <form:label path="phone">Phone number:</form:label>
        <form:input maxlength="13" class="form-control" placeholder="+380XXXXXXXXX" type="phone" id="phone" path="phone"/>
        <form:errors path="phone" cssClass="alert-danger"/>
    </div>
    <div class="form-group col-sm-6">
        <form:label path="password">Password:</form:label>
        <form:input class="form-control" placeholder="Enter password" type="password" id="password" path="password"/>
        <form:errors path="password" cssClass="alert-danger"/>
    </div>
    <div class="col-sm-10 col-md-offset-3">
    <input class="btn btn-success" type="submit" value="Registration">
    <input class="btn btn-success" type="reset" value="Reset">
    </div>
</form:form>
    </div>
    </div>
</div>