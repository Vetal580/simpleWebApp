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
                            <a class="nav-link active" href="#">User Edit</a>
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
                    <h5 class="card-title">Edit Users</h5>
                    <table class="table">
                        <thead>
                        <tr>
                            <th scope="col">Name</th>
                            <th scope="col">Email</th>
                            <th scope="col">Phone</th>
                            <th scope="col">Role</th>
                            <th scope="col"></th>
                            <th scope="col"></th>
                        </tr>
                        </thead>
                        <c:forEach var="user" items="${usersList}">
                        <tbody>
                        <tr>
                            <td>${user.username}</td>
                            <td>${user.email}</td>
                            <td>${user.phone}</td>
                            <td>${user.role}</td>
                            <td><a href="/user/${user.id}" type="button" id="id_${user.username}" class="btn btn-dark">EDIT</a></td>
                            <td style="font-size: 120%"><a onclick="deleteUser(${user.id})"><span class="fas fa-trash" style="cursor: pointer"></span></a></td>
                        </tr>
                        </tbody>
                        </c:forEach>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>