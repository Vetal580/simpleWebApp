<%@ include file="library.jsp"%>

<div class="container">
    <nav class="navbar navbar-expand-lg navbar-light bg-light" style="margin-bottom: 10px">
        <div class="collapse navbar-collapse justify-content-center" id="navbarNavAltMarkup">
            <div class="navbar-nav">
                <c:forEach items="${categories}" var="category">
                    <a style="font-size: 110%" class="nav-item nav-link active"
                       href="/category/${category.categoryId}">${category.categoryName}<span
                            class="sr-only">(current)</span></a>
                </c:forEach>
            </div>
        </div>
    </nav>
    <div class="row">
        <div class="card-group">
            <c:forEach var="product" items="${product}">
                <div class=".col-xl-3 col-lg-3">
                    <div class="card" style="height: 460px; width: 250px; margin-bottom: 30px">
                        <c:if test="${product.inStock==1}">
                            <h5 class="text-center"><span class="product-category-instock">In Stock</span></h5>
                        </c:if>
                        <c:if test="${product.inStock==0}">
                            <h5 class="text-center"><span class="product-category-notavailable">Not available</span>
                            </h5>
                        </c:if>
                        <div id="carouselExampleControls" class="carousel categoryslide" data-ride="carousel">
                            <div class="carousel-inner">
                                <c:if test="${empty product.image}">
                                    <div class="carousel-item active">
                                        <a href="${pageContext.request.contextPath}/product/${product.id}"><img
                                                class="d-block w-100"
                                                src="${pageContext.request.contextPath}/resources/images/products/product-no-image.jpg"
                                                alt="No image for product"></a>
                                    </div>
                                </c:if>
                                <c:if test="${not empty product.image}">
                                    <div class="carousel-item active">
                                        <a href="${pageContext.request.contextPath}/product/${product.id}"><img
                                                class="d-block w-100"
                                                src="${pageContext.request.contextPath}/resources/images/products/${product.imagesList.get(0)}"
                                                alt="..."></a>
                                    </div>
                                </c:if>
                            </div>
                        </div>
                        <div class="card-body">
                            <a href="${pageContext.request.contextPath}/product/${product.id}"><h5
                                    class="card-title">${product.name}</h5></a>
                            <div class="footer-card">
                                <p class="card-text" style="color: red; font-size: 18px;"><strong>Price:
                                    UAH ${product.price}</strong></p>
                                <a href="${pageContext.request.contextPath}/product/${product.id}"
                                   class="btn btn-primary">Buy</a>
                            </div>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</div>