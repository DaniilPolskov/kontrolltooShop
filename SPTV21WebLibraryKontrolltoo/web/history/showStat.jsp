
       <h3 class="w-100 d-flex justify-content-center mt-5">??????????</h3>
       <div class="w-50 p-3 container">
            <div class="row">
                <div class="m-2 w-100">
                    <form action="calcStat" method="POST">
                        <div class="">
                            <div class="m-3 row w-100">
                                <div class="col">
                                    <select name="selectYear" class="form-select form-select-sm" aria-label=".form-select-sm example">
                                        <option value="" >???????? ???</option>
                                        <c:forEach var="year" begin="${year-3}" end="${year}">
                                            <option value="${year}" <c:if test="${selectYear==year}">selected</c:if>>${year}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class="col">
                                    <select name="selectMonth" class="form-select form-select-sm" aria-label=".form-select-sm example">
                                        <option value=""  >???????? ?????</option>
                                        <c:forEach var="month" begin="1" end="12">
                                            <option value="${month}" <c:if test="${selectMonth==month}">selected</c:if>>${month}</option>
                                        </c:forEach>
                                    </select>
                                </div>    
                                <div class="col">
                                    <select name="selectDay" class="form-select form-select-sm" aria-label=".form-select-sm example">
                                        <option value=""  selected>???????? ????</option>
                                        <c:forEach var="day" begin="1" end="31">
                                            <option value="${day}"<c:if test="${selectDay==day}">selected</c:if>>${day}</option>
                                        </c:forEach>
                                    </select>
                                </div>    
                            </div>
                
    
