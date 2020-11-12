<%-- 
    Document   : cart_view
    Created on : Nov 8, 2020, 9:53:55 PM
    Author     : HKDUC
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file = "template/header.jsp"%>
<table class="table table-striped">
    <thead>
        <tr>
            <th scope="col">#</th>
            <th scope="col">Sản phẩm</th>
            <th scope="col">Tình trạng</th>
            <th scope="col">Số lượng</th>
            <th scope="col">Kho</th>
            <th scope="col">Giá</th>
            <th scope="col"></th>
        </tr>
    </thead>
    <tbody>
        <c:if test="${cart.orderInfo.size()>0}">
            <c:forEach var="i" begin="0" end="${cart.orderInfo.size()-1}">
                <tr>
                    <td>${i+1}</td>
                    <td>
                        <a href="item_detail?itemid=${cart.items[i].itemID}">
                            <img src="${cart.items[i].thumbnailPath}" width="50px" height="50px">
                            <span>${cart.items[i].itemName}</span>
                        </a>
                    </td>
                    <td class="align-middle">${variouscdt[cart.orderInfo[i].conditionNo-1].condition}</td>
                    <td name="quantity" class="align-middle">${cart.orderInfo[i].quantity}</td>
                    <c:choose>
                        <c:when test="${cart.items[i].statusList[cart.orderInfo[i].conditionNo-1].quantity >= cart.orderInfo[i].quantity}">
                            <td class="align-middle">Còn hàng</td>
                        </c:when>
                        <c:otherwise>
                            <td>Hết hàng</td>
                        </c:otherwise>
                    </c:choose>
                    <td class="align-middle">
                        <span class="addsep" name="price">
                            ${cart.items[i].statusList[cart.orderInfo[i].conditionNo-1].price}
                        </span>đ
                    </td>
                    <td class="align-middle">
                        <form action="delete_items_in_cart" method="POST">
                            <input type="hidden" name="OrderID" value="${cart.orderInfo[i].orderID}">
                            <input type="hidden" name="ItemID" value="${cart.orderInfo[i].itemID}">
                            <input type="hidden" name="ConditionNo" value="${cart.orderInfo[i].conditionNo}">
                            <button type="submit" class="rounded" style="border: none; background-color: #9c2828;">
                                <svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-cart-x-fill" fill="#ffffff" xmlns="http://www.w3.org/2000/svg">
                                <path fill-rule="evenodd" d="M.5 1a.5.5 0 0 0 0 1h1.11l.401 1.607 1.498 7.985A.5.5 0 0 0 4 12h1a2 2 0 1 0 0 4 2 2 0 0 0 0-4h7a2 2 0 1 0 0 4 2 2 0 0 0 0-4h1a.5.5 0 0 0 .491-.408l1.5-8A.5.5 0 0 0 14.5 3H2.89l-.405-1.621A.5.5 0 0 0 2 1H.5zM4 14a1 1 0 1 1 2 0 1 1 0 0 1-2 0zm7 0a1 1 0 1 1 2 0 1 1 0 0 1-2 0zM7.354 5.646a.5.5 0 1 0-.708.708L7.793 7.5 6.646 8.646a.5.5 0 1 0 .708.708L8.5 8.207l1.146 1.147a.5.5 0 0 0 .708-.708L9.207 7.5l1.147-1.146a.5.5 0 0 0-.708-.708L8.5 6.793 7.354 5.646z"/>
                                </svg>
                            </button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </c:if>
        <tr>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td>Thành tiền</td>
            <td><span class="addsep" id="totalPrice"></span>đ</td>
        </tr>
    </tbody>
</table>
<div class="col-12" style="text-align: right;">
    <button class="btn btn-success mr-4" id="checkOutBtn">
        <a href="checkout" style="text-decoration: none; color: white;">
            Thanh toán
        </a>
    </button>
</div>
<script>
    var p = document.getElementsByName('price');
    var q = document.getElementsByName('quantity');
    var sum = 0;
    for (var i = 0; i < p.length; i++) {
        var price = parseInt(p[i].innerText.replaceAll(',', ''));
        var quantity = parseInt(q[i].innerText);
        sum += (price * quantity);
    }
    document.getElementById('totalPrice').innerHTML = sum;
    if (sum==0){
        var btn = document.getElementById("checkOutBtn");
        btn.disabled = true;
        btn.getElementsByTagName('a')[0].removeAttribute('href')
    } 
    addThousandSep();
</script>
<%@ include file = "template/footer.jsp"%>