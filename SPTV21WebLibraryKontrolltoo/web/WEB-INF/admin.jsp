
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<h3 class="w-100 d-flex justify-content-center mt-5">Администрирование</h3>
        <div class="w-100 p-3 d-flex justify-content-center">
            <form action="changeRole" method="POST">
                <div class="card border-0 m-2" style="width: 30rem;">
                    <div class="mb-3 row">
                        <label for="inputName" class="col-sm-3 col-form-label">Имя</label>
                        <div class="col-sm-9">
                            <select class="form-select">
                                <c:forEach var="user" items="${users}" varstatus="status">
                                    <option value="${user.id}">
                                        ${user.login}
                                    <c:forEach var="role" items="${user.roles}">
                                        ${role},
                                    </c:forEach>
                                    </option>
                                </c:forEach>
                            </select>
                          <input type="text" class="form-control" id="inputName" name="firstname">
                        </div>
                    </div>
                    <div class="mb-3 row">
                        <label for="inputLastname" class="col-sm-3 col-form-label">Фамилия</label>
                        <div class="col-sm-9">
                            <select name="selectedRole" class="form-select">
                                    <c:forEach var="user" items="${users}" varstatus="status">
                                    <option value="${user.id}">
                                        ${user.login}
                                    <c:forEach var="role" items="${user.roles}">
                                        ${role},
                                    </c:forEach>
                            </select>
                          <input type="text" class="form-control" id="inputLastname" name="lastname">
                        </div>
                    </div>
                    <div class="mb-3 row">
                        <div class="col-sm-12 d-flex justify-content-end">
                            <button name="addRole" class="btn btn-primary w-25" type="submit">Добавить</button>
                            <button name="removeRole" class="btn btn-primary w-25" type="submit">Удалить роль</button>
                        </div>
                    </div>
                </div>
            </form>
        </div>
