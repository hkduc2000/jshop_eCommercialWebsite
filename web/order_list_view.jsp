<%-- 
    Document   : order_list_view
    Created on : Nov 9, 2020, 1:22:17 PM
    Author     : HKDUC
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file = "template/header.jsp"%>
<table class="table table-striped">
    <thead>
        <tr>
            <th scope="col">#</th>
            <th scope="col">Mã đơn hàng</th>
            <th scope="col">Trạng thái</th>
            <th scope="col">Thời gian đặt hàng</th>
            <th scope="col">Sản phẩm</th>
            <th scope="col">Tổng giá tiền</th>
        </tr>
    </thead>
    <tbody>
        <c:if test="${orders.size()>0}">
            <c:forEach var="i" begin="0" end="${orders.size()-1}">
                <tr class="pagingitem showit">
                    <td scope="col" class="align-middle">${i+1}</td>
                    <td class="align-middle" scope="col">
                        <form action="order_list" method="POST">
                            <input type="hidden" name="orderID" value="${orders[i].orderID}">
                            <input type="submit" style="border:none; background-color: transparent; color: #007bff;" value="${orders[i].orderID}">
                        </form>
                    </td>
                    <td class="align-middle">${steps[orders[i].processStepNo-1].processStep}</td>
                    <td scope="col" class="align-middle">${orders[i].created_date}</td>
                    <td scope="col" class="align-middle">
                        <c:if test="${order.items.size()>0}">
                            <c:forEach var="j" begin="0" end="${orders[i].items.size()-1}">
                                <p class="m-0">
                                    ${orders[i].items[j].itemName} - ${variouscdt[orders[i].orderInfo[j].conditionNo-1].condition}
                                    <svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-x" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                                        <path fill-rule="evenodd" d="M4.646 4.646a.5.5 0 0 1 .708 0L8 7.293l2.646-2.647a.5.5 0 0 1 .708.708L8.707 8l2.647 2.646a.5.5 0 0 1-.708.708L8 8.707l-2.646 2.647a.5.5 0 0 1-.708-.708L7.293 8 4.646 5.354a.5.5 0 0 1 0-.708z"/>
                                    </svg>
                                    ${orders[i].orderInfo[j].quantity}
                                </p>
                            </c:forEach>
                        </c:if>
                    </td>
                    <td scope="col" class="align-middle">
                        <span class="addsep">${orders[i].total_price} </span>đ
                    </td>
                </tr>
            </c:forEach>      
        </c:if>
    </tbody>
</table>
<nav class="mt-4" id="paginationControl">
    <p style="text-align: center; margin-bottom: 0px">Trang 
        <span id="cur">1</span>/<span id="total">10</span>
    </p>
    <ul class="pagination justify-content-center">
        <li class="page-item" style="cursor:pointer; user-select:none;" id="prev">
            <a class="page-link" onclick="switchPage(-1)">Prev</a>
        </li>
        <li class="page-item" style="cursor:pointer; user-select:none;" id="next">
            <a class="page-link" onclick="switchPage(1);">Next</a>
        </li>
    </ul>
</nav>
<script>
    addThousandSep();
    controller = document.getElementById('paginationControl');
    prev = document.getElementById('prev');
    next = document.getElementById('next');
    lblcur = document.getElementById('cur');
    lbltotal = document.getElementById('total');
    curtab = 1;
    size=6;
    itemDisplay = "table-row";
    items = document.getElementsByClassName('pagingitem showit');
    if (items.length <= size) {
        controller.style.display = "none";
    } else {
        controller.style.display = "block";
        lbltotal.innerHTML = Math.ceil(items.length / size);
    }
    cur = 1;
    for (var j = 0; (j < items.length) && (j < size); j++) {
        items[j].style.display = "table-row";
    }
    for (var j = size; j < items.length; j++) {
        items[j].style.display = "none";
    }
</script>
<%@ include file = "template/footer.jsp"%>