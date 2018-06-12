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
                            <a class="nav-link active" href="#">Product Edit</a>
                        </li>
                    </ul>
                </div>
                <div class="card-body">
                    <h5 class="card-title">Edit Users</h5>
                    <table class="table">
                        <thead>
                        <tr>
                            <th scope="col">Name</th>
                            <th scope="col">Price</th>
                            <th scope="col">Category</th>
                            <th scope="col">InStock</th>
                            <th scope="col"></th>
                        </tr>
                        </thead>
                        <c:forEach var="product" items="${allProducts}">
                            <tbody>
                            <tr>
                                <td>${product.name}</td>
                                <td>${product.price}</td>
                                <td>${categoryList.get(product.category)}</td>
                                <td>${product.inStock}</td>
                                <td><a href="/edit-product/${product.id}" type="button" class="btn btn-dark" type="submit">EDIT</a></td>
                                <td style="font-size: 120%"><a onclick="deleteProduct(${product.id})"><span class="fas fa-trash" style="cursor: pointer"></span></a></td>
                            </tr>
                            </tbody>
                        </c:forEach>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>