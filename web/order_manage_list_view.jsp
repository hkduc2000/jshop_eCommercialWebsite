<%-- 
    Document   : order_manage_view
    Created on : Nov 9, 2020, 10:21:40 PM
    Author     : HKDUC
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file = "template/header.jsp"%>

<ul class="nav nav-tabs" role="tablist">
    <c:forEach var="i" begin="1" end="${steps.size()-1}">
        <li class="nav-item"> 
            <a onclick="switchTab(${steps[i].processStepNo});" class="nav-link ${i eq 1?"active":""}" data-toggle="tab" 
               href="#step${steps[i].processStepNo}">${steps[i].processStep}</a>
        </li>
    </c:forEach>
</ul>

<div class="tab-content">
    <c:forEach var="i" begin="1" end="${steps.size()-1}">
        <div id="step${steps[i].processStepNo}" class="container tab-pane fade ${i eq 1?"active show":""}"><br>
            <table class="table">
                <thead>
                    <tr>
                        <th scope="col">Mã đơn hàng</th>
                        <th scope="col">Trạng thái</th>
                        <th scope="col">Thời gian đặt hàng</th>
                        <th scope="col">Sản phẩm</th>
                        <th scope="col">Tổng giá tiền</th>
                    </tr>
                </thead>
                <tbody>
                <c:forEach items="${orders}" var="order">
                    <c:if test ="${order.processStepNo == steps[i].processStepNo}">
                        <tr class="pagingitem showit">
                            <td class="align-middle" scope="col">
                                <form action="order_manage" method="POST">
                                    <input type="hidden" name="orderID" value="${order.orderID}">
                                    <input type="submit" style="border:none; background-color: transparent; color: #007bff;" value="${order.orderID}">
                                </form>
                            </td>
                            <td class="align-middle">${steps[order.processStepNo-1].processStep}</td>
                            <td scope="col" class="align-middle">${order.created_date}</td>
                            <td scope="col" class="align-middle">
                            <c:if test="${order.items.size()>0}">
                                <c:forEach var="j" begin="0" end="${order.items.size()-1}">
                                <p class="m-0">
                                    ${order.items[j].itemName} - ${variouscdt[order.orderInfo[j].conditionNo-1].condition}
                                    <svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-x" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                                        <path fill-rule="evenodd" d="M4.646 4.646a.5.5 0 0 1 .708 0L8 7.293l2.646-2.647a.5.5 0 0 1 .708.708L8.707 8l2.647 2.646a.5.5 0 0 1-.708.708L8 8.707l-2.646 2.647a.5.5 0 0 1-.708-.708L7.293 8 4.646 5.354a.5.5 0 0 1 0-.708z"/>
                                    </svg>
                                    ${order.orderInfo[j].quantity}
                                </p>
                                </c:forEach>
                            </c:if>
                            </td>
                            <td scope="col" class="align-middle">
                                <span class="addsep">${order.total_price} </span>đ
                            </td>
                        </tr>
                    </c:if>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </c:forEach>
</div>
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
    curtab = 2;
    size = 6;
    tabname = 'step';
    itemDisplay = 'table-row'
    switchTab(2);
</script>
<%@ include file = "template/footer.jsp"%>