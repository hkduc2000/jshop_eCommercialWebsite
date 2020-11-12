<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file = "template/header.jsp"%>
<h5 class="col-12 col-sm-4">Mã đơn hàng #${order.orderID}</h5> 
<h5 class="col-12 col-sm-7">Trạng thái đơn hàng: ${steps[order.processStepNo-1].processStep}</h5>
<table class="table table-striped">
    <thead>
        <tr>
            <th scope="col">#</th>
            <th scope="col">Sản phẩm</th>
            <th scope="col">Tình trạng</th>
            <th scope="col">Số lượng</th>
            <th scope="col">Giá</th>
        </tr>
    </thead>
    <tbody>
        <c:if test="${order.orderInfo.size()>0}">
            <c:forEach var="i" begin="0" end="${order.orderInfo.size()-1}">
                <tr>
                    <td>${i+1}</td>
                    <td>
                        <a href="item_detail?itemid=${order.items[i].itemID}">
                            <img src="${order.items[i].thumbnailPath}" width="50px" height="50px">
                            <span>${order.items[i].itemName}</span>
                        </a>
                    </td>
                    <td class="align-middle">${variouscdt[order.orderInfo[i].conditionNo-1].condition}</td>
                    <td name="quantity" class="align-middle">${order.orderInfo[i].quantity}</td>
                    <td class="align-middle">
                        <span class="addsep" name="price">
                            ${order.orderInfo[i].price}
                        </span>đ
                    </td>
                </tr>
            </c:forEach>
        </c:if>
        <tr>
            <td></td>
            <td></td>
            <td></td>
            <td>Thành tiền</td>
            <td><span class="addsep" id="totalPrice">${order.total_price}</span>đ</td>
        </tr>
    </tbody>
</table>
       
<table class="table col-12 col-sm-8 col-md-6 col-lg-5" style="float: right; margin-left: 2000px">
    <thead>
        <tr>
            <th colspan="2"  style="text-align: center;">Thông tin giao hàng</th>
        </tr>
    </thead>
    <tbody>
        <tr>
            <td>Người nhận</td>
            <td style="text-align: right;">${order.recipientName}</td>
        </tr>
        <tr>
            <td>Địa chỉ giao hàng</td>
            <td style="text-align: right;">${order.address}</td>
        </tr>
        <tr>
            <td>Số điện thoại người nhận</td>
            <td style="text-align: right;">${order.recipientPhone}</td>
        </tr> 
    </tbody>
</table>

<div class="col-12" style="text-align: right; float: right; margin-left: 2000px">
    <c:if test="${order.processStepNo <=2}">
        <form action="order_process" method="POST">
            <input type="hidden" name="OrderID" value="${order.orderID}">
            <input type="hidden" name="Step" value="5">
            <button type="submit" class="btn btn-danger mr-4 mb-2">Hủy đơn hàng</button>
        </form>   
    </c:if>

    <a href="order_list" class="btn btn-secondary mr-4">Quay lại</a>
</div>
<script>
    addThousandSep();
</script>
<%@ include file = "template/footer.jsp"%>