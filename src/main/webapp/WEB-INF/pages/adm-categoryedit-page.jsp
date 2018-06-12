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
                            <a class="nav-link active" href="#">Category Edit</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="/admproductedit">Product Edit</a>
                        </li>
                    </ul>
                </div>
                <div class="card-body">
                    <h5 class="card-title">Edit Category</h5>
                    <table class="table">
                        <thead>
                        <tr>
                            <th scope="col">Id</th>
                            <th scope="col">Category</th>
                            <th scope="col"></th>
                            <th scope="col"></th>
                        </tr>
                        </thead>
                        <c:forEach var="category" items="${categoryList}">
                            <tbody>
                            <tr>
                                <td>${category.categoryId}</td>
                                <td>${category.categoryName}</td>
                                <td><a href="/edit-category/${category.categoryId}" type="button" class="btn btn-dark" type="submit">EDIT</a></td>
                                <td style="font-size: 120%"><a onclick="deleteCategory(${category.categoryId})"><span class="fas fa-trash" style="cursor: pointer"></span></a></td>
                            </tr>
                            </tbody>
                        </c:forEach>
                    </table>
                </div>
                <hr>
                <div class="row justify-content-md-center">
                    <div class="col col-lg-8">
                        <c:if test="${not empty categoryAdded}">
                            <h5 class="alert alert-success">${categoryAdded}</h5>
                        </c:if>
                        <c:if test="${empty categoryAdded}">
                            <h5 class="alert alert-dark">Add Category</h5>
                        </c:if>
                        <form:form method="post" id="addCategory" action="admcategoryedit" modelAttribute="category" enctype="multipart/form-data">
                            <div class="form-group">
                                <form:label for="categoryId" path="categoryId">Category Id</form:label>
                                <form:input type="text" class="form-control" id="categoryId" path="categoryId"/>
                            </div>
                            <div class="form-group">
                                <form:label for="productPrice" path="categoryName">Category Name</form:label>
                                <form:input type="text" class="form-control" id="productPrice" path="categoryName"/>
                                <form:errors path="categoryName" cssClass="alert-danger"/>
                            </div>
                            <button type="submit" class="btn btn-primary">Add Category</button>
                        </form:form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>