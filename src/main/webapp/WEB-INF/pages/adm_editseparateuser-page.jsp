<%@ include file="library.jsp"%>

<div class="container">
    <div class="row justify-content-center">
        <div class="col">
            <div class="card text-center">
                <div class="card-header">
                    <ul class="nav nav-tabs card-header-tabs">
                        <li class="nav-item">
                            <a class="nav-link" href="/admin">Add product</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link active" href="/admuseredit">User Edit</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="/admcategoryedit">Category Edit</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="/admproductedit">Product Edit</a>
                        </li>
                    </ul>
                </div>
                <div class="card-body">
                    <div align="center">
                        <c:if test="${not empty updated}">
                            <div class="alert alert-success">${updated}</div>
                        </c:if>
                        <form:form method="post" id="userEdit" action="/user/${useredit.id}" modelAttribute="useredit"
                                   class="form-horizontal">
                            <div class="form-group col-sm-6">
                                <form:input class="form-control" type="text" id="id" path="id" readonly="true"/>
                            </div>
                            <div class="form-group col-sm-6">
                                <form:label path="username">Login:</form:label>
                                <form:input class="form-control" type="text" id="name" path="username"/>
                                <form:errors path="username" cssClass="alert-danger"/>
                            </div>
                            <div class="form-group col-sm-6">
                                <form:label path="email">E-Mail:</form:label>
                                <form:input class="form-control" type="text" id="email" path="email"/>
                                <form:errors path="email" cssClass="alert-danger"/>
                            </div>
                            <div class="form-group col-sm-6">
                                <form:label path="phone">Phone:</form:label>
                                <form:input class="form-control" type="phone" id="phone" path="phone"/>
                                <form:errors path="phone" cssClass="alert-danger"/>
                            </div>
                            <div class="form-group col-sm-6">
                                <form:label path="role">Role:</form:label>
                                <form:input class="form-control" type="text" id="role" path="role"/>
                            </div>
                            <div class="col-sm-10 col-md-offset-3">
                                <button type="submit" class="btn btn-primary">Edit User</button>
                            </div>
                        </form:form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>