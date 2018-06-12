<%@ include file="library.jsp"%>
<div class="container">
    <nav aria-label="breadcrumb">
        <ol class="breadcrumb">
            <li class="breadcrumb-item"><a href="/">Home</a></li>
            <li class="breadcrumb-item"><a href="/category/${product.category}">${categoryName}</a></li>
            <li class="breadcrumb-item active" aria-current="page">${product.name}</li>
        </ol>
    </nav>
    <div class="card">
        <div class="row">
            <aside class="col-sm-5 border-right">
                    <div id="carouselExampleControls" class="carousel slide" data-ride="carousel">
                        <div class="carousel-inner">
                            <c:if test="${empty product.image}">
                                <div class="carousel-item active">
                                    <img class="d-block w-100" src="${pageContext.request.contextPath}/resources/images/products/product-no-image.jpg" alt="No image for product">
                                </div>
                            </c:if>
                            <c:if test="${not empty product.image}">
                            <div class="carousel-item active">
                                <img class="d-block w-100" src="${pageContext.request.contextPath}/resources/images/products/${product.imagesList.get(0)}" alt="...">
                            </div>
                            <c:forEach items="${product.imagesList.listIterator(1)}" var="image">
                            <div class="carousel-item">
                                <img class="d-block w-100" src="${pageContext.request.contextPath}/resources/images/products/${image}" alt="...">
                            </div>
                            </c:forEach>
                            </c:if>
                        </div>
                        <a class="carousel-control-prev" href="#carouselExampleControls" role="button" data-slide="prev">
                            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                            <span class="sr-only">Previous</span>
                        </a>
                        <a class="carousel-control-next" href="#carouselExampleControls" role="button" data-slide="next">
                            <span class="carousel-control-next-icon" aria-hidden="true"></span>
                            <span class="sr-only">Next</span>
                        </a>
                    </div>
            </aside>
            <aside class="col-sm-7">
                <article class="card-body p-5">
                    <h3 class="title mb-3">${product.name}</h3>

                    <p class="price-detail-wrap">
                    <span class="price h3 text-warning">
                        <span class="currency">UAH </span><span class="num">${product.price}</span>
                    </span>
                    </p>
                    <dl class="param param-feature">
                        <dt></dt>
                        <dd>
                            <c:choose>
                                <c:when test="${product.inStock==1}">
                                    <span class="btn btn-success">Available</span>
                                </c:when>
                                <c:otherwise>
                                    <span class="btn btn-danger">Not available</span>
                                </c:otherwise>
                            </c:choose>
                        </dd>
                        <hr>
                    </dl>
                    <dl class="item-property">
                        <dt>Short Description</dt>
                        <dd><p>${product.shortDescription}</p></dd>
                    </dl>
                    <dl class="param param-feature">
                        <dt>Category</dt>
                        <dd><a href="${pageContext.request.contextPath}/category/${product.category}">${categoryName}</a></dd>
                    </dl>
                    <hr>
                    <c:if test="${product.inStock==1}">
                        <a type="button" id = "product_${product.id}" class="btn btn-lg btn-primary text-uppercase" onclick="addToCart('${product.id}')">Buy Now</a>
                    </c:if>
                    <c:if test="${product.inStock==0}">
                        <a type="button" id = "product_${product.id}" class="btn btn-lg btn-primary text-uppercase disabled" onclick="addToCart('${product.id}')">Not available</a>
                    </c:if>
                </article>
            </aside>
        </div>
    </div>
    <div class="card text-center">
        <div class="card-header">
            <ul class="nav nav-tabs card-header-tabs">
                <li class="nav-item">
                    <a class="nav-link active">DESCRIPION</a>
                </li>
            </ul>
        </div>
        <div class="card-body">
            <h5 class="card-title">${product.name}</h5>
            <p class="card-text" style="text-align: left">${product.description}</p>
        </div>
    </div>
</div>
