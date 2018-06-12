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
                            <a class="nav-link" href="/admuseredit">User Edit</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link active" href="/admcategoryedit">Category Edit</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="/admproductedit">Product Edit</a>
                        </li>
                    </ul>
                </div>
                <div class="card-body">
                    <div class="row justify-content-md-center">
                        <div class="col col-lg-8">
                            <c:if test="${not empty updated}">
                                <div class="alert alert-success">${updated}</div>
                            </c:if>
                            <form:form method="post" id="editCategory" action="/edit-category/${category.categoryId}" modelAttribute="category" enctype="multipart/form-data">
                                <div class="form-group">
                                    <form:input type="text" class="form-control" id="categoryId" path="categoryId" readonly="true"/>
                                </div>
                                <div class="form-group">
                                    <form:label for="categoryName" path="categoryName">Category Name</form:label>
                                    <form:input maxlength="50" type="text" class="form-control" id="categoryName" path="categoryName"/>
                                    <form:errors path="categoryName" cssClass="alert-danger"/>
                                </div>
                                <button type="submit" class="btn btn-primary">Edit Category</button>
                            </form:form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</div>