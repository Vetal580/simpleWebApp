<%@ include file="library.jsp"%>

<div class="container">
    <hr>
    <div class="card">
        <c:if test="${empty productList }">
            <h3 class="alert alert-danger">You didn`t put any item to cart</h3>
        </c:if>
        <c:if test="${not empty productList }">
            <table class="table table-hover shopping-cart-wrap">
                <thead class="text-muted">
                <tr>
                    <th scope="col">Product</th>
                    <th scope="col" width="120">Price, UAH</th>
                    <th scope="col" width="200" class="text-right"></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${productList}" var="product">
                    <tr>
                        <td>
                            <figure class="media">
                                <c:if test="${not empty product.imagesList.get(0)}">
                                    <div class="img-wrap"><img
                                            src="${pageContext.request.contextPath}/resources/images/products/${product.imagesList.get(0)}"
                                            alt="..." style="width:30%"></div>
                                </c:if>
                                <c:if test="${empty product.imagesList.get(0)}">
                                    <div class="img-wrap"><img
                                            src="${pageContext.request.contextPath}/resources/images/products/product-no-image.jpg"
                                            alt="..." style="width:30%"></div>
                                </c:if>
                                <figcaption class="media-body">
                                    <h6 class="title text-truncate"><a href="/product/${product.id}">${product.name}</a>
                                    </h6>
                                </figcaption>
                            </figure>
                        </td>
                        <td>
                            <div class="price-wrap">
                                <var class="price">${product.price}</var>
                            </div>
                        </td>
                        <td class="text-right">
                            <a onclick="removeFromCart('${product.id}')" class="btn btn-danger">Remove</a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <a id="checkout" class="btn btn-success btn-default  buy_btn" href="${pageContext.request.contextPath}#">Checkout</a>
        </c:if>
    </div>
</div>