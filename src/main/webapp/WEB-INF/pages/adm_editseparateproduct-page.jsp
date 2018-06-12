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
                            <a class="nav-link" href="/admcategoryedit">Category Edit</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link active" href="/admproductedit">Product Edit</a>
                        </li>
                    </ul>
                </div>
                <div class="card-body">
                        <c:if test="${not empty updated}">
                            <div class="alert alert-success">${updated}</div>
                        </c:if>
                    <div class="row justify-content-md-center">
                        <div class="col col-lg-8">
                            <form:form method="post" id="editProduct" action="/edit-product/${productedit.id}" modelAttribute="productedit" enctype="multipart/form-data">
                                <div class="form-group">
                                    <form:input type="text" class="form-control" id="productId" path="id" readonly="true"/>
                                </div>
                                <div class="form-group">
                                    <form:label for="productName" path="name">Product name</form:label>
                                    <form:input type="text" class="form-control" id="productName" path="name"/>
                                    <form:errors path="name" cssClass="alert-danger"/>
                                </div>
                                <div class="form-group">
                                    <form:label for="categorySelect" path="category">Category</form:label>
                                    <form:select class="form-control" id="categorySelect" path="category">
                                        <form:options items="${categoryList}"/>
                                    </form:select>
                                </div>
                                <div class="form-group">
                                    <form:label for="productPrice" path="price">Price</form:label>
                                    <form:input type="text" class="form-control" id="productPrice" path="price"/>
                                    <form:errors path="price" cssClass="alert-danger"/>

                                </div>
                                <div class="form-group">
                                    <form:label for="inStockSelect" path="inStock">In Stock</form:label>
                                    <form:select class="form-control" id="inStockSelect" path="inStock">
                                        <form:options items="${inStockList}"/>
                                    </form:select>
                                </div>
                                <div class="form-group">
                                    <form:label for="productShortDescription" path="shortDescription">ShortDescription</form:label>
                                    <form:textarea maxlength="250" class="form-control" id="productShortDescription" rows="4" path="shortDescription"/>
                                    <div class="invalid-feedback">Add Product short description!</div>
                                </div>
                                <div class="form-group">
                                    <form:label for="productDescription" path="description">Description</form:label>
                                    <form:textarea maxlength="2000" class="form-control" id="productDescription" rows="4" path="description"/>
                                    <div class="invalid-feedback">Add Product description!</div>
                                </div>
                                <button type="submit" class="btn btn-primary">Edit Product</button>
                            </form:form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</div>