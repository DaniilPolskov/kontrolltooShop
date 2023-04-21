<%-- 
    Document   : addShoe
    Created on : 21.04.2023, 9:28:22
    Author     : User
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
    <h3 class="w-100 d-flex justify-content-center mt-5">Добавить обувь</h3>
        <div class="w-100 p-3 d-flex justify-content-center">
            <form action="createShoe" method="POST">
                <div class="mb-3 row">   
                    <label for="inputName" class="col-sm-4 col-form-label">Название обуви</label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" id="inputName" name="shoeName" value="${shoeName}">
                        </div>
                    <div class="mb-3 row">
                        <label for="inputPrice" class="col-sm-4 col-form-label">Стоимость</label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control w-50" id="inputPrice" name="price" value="${price}">
                        </div>
                    </div>
                    <div class="mb-3 row">
                        <label for="inputQuantity" class="col-sm-4 col-form-label">Количество экземпляров</label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control w-25" id="inputQuantity" name="quantity" value="${quantity}">
                        </div>
                    <div class="row">
                        <div class="col-sm-12 d-flex justify-content-end">
                            <button class="btn btn-primary w-25" type="submit">Добавить</button>
                        </div>    
                    </div>
                    </div>
                </div>
            </form>    
        </div>
