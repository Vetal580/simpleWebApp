<%@ include file="library.jsp"%>

<nav class="navbar navbar-expand-lg navbar-dark bg-dark" style="margin-bottom: 5px">
    <a class="navbar-brand" href="/">SHOP</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNavDropdown">
        <ul class="navbar-nav">
            <li class="nav-item active">
                <security:authorize access="isAnonymous()">
            <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/cart"><span class = "glyphicon glyphicon-shopping-cart"></span> Cart</a></li>
            <li class="nav-item"><a class="nav-link" href="/registration"><span class="glyphicon glyphicon-user"></span> Sign Up</a></li>
            <li class="nav-item"><a class="nav-link" href="/login"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>
            </security:authorize>
            <security:authorize access="hasRole('ROLE_USER')">
                <li class="nav-item"><a class="nav-link" href="/cart"><span class="glyphicon glyphicon-user"></span>Cart</a></li>
                <li class="nav-item"><a class="nav-link" href="/logout"><span class="glyphicon glyphicon-log-in"></span>Logout</a></li>
            </security:authorize>

            <security:authorize access="hasRole('ROLE_ADMIN')">
                <li class="nav-item"><a class="nav-link" href="/admin"><span class="glyphicon glyphicon-user"></span>Admin</a></li>
                <li class="nav-item"><a class="nav-link" href="/cart"><span class="glyphicon glyphicon-user"></span>Cart</a></li>
                <li class="nav-item"><a class="nav-link" href="/logout"><span class="glyphicon glyphicon-log-in"></span>Logout</a></li>
            </security:authorize>
            </li>
        </ul>
    </div>
</nav>
</div>