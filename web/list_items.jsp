<%-- 
    Document   : addItemForm
    Created on : Nov 2, 2020, 9:57:35 PM
    Author     : HKDUC
--%>
<%@page import="model.Category"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file = "template/header.jsp"%>
<ul class="nav nav-tabs" role="tablist">
    <c:forEach items="${requestScope.categories}" var="ctg">
        <li class="nav-item">
            <a class="nav-link ${ctg.categoryNo eq 1?"active":""}" data-toggle="tab" href="#ctg${ctg.categoryNo}">
                ${ctg.category}
            </a>
        </li>
    </c:forEach>
    <input class="form-control mr-2" id="searchingInput" onkeyup="searchItems()" style="width: 15%;" 
           type="search" placeholder="Search" aria-label="Search">
</ul>

<div class="tab-content">
    <c:forEach items="${requestScope.categories}" var="ctg">
        <div id="ctg${ctg.categoryNo}" class="container tab-pane fade ${ctg.categoryNo eq 1?"active show":""}"><br>
            <div class="row">
                <c:forEach items="${requestScope.items}" var="item">
                    <c:if test ="${item.categoryNo == ctg.categoryNo}">
                        <div class="col-6 col-md-4 col-lg-3 mt-1 item">
                            <c:choose>
                                <c:when test="${(sessionScope.username != null) && (sessionScope.role == 'admin')}">
                                    <a href="goodsreceipt?itemid=${item.itemID}" style="text-decoration: none;">
                                </c:when>
                                <c:otherwise>
                                    <a href="item_detail?itemid=${item.itemID}" style="text-decoration: none;">
                                </c:otherwise>
                            </c:choose>
                                <img src ="${item.thumbnailPath}" width="100%">
                                <p class="truncate itemname pl-3 mb-0" style="color:black;">${item.itemName}</p>
                                <b class="truncate pl-2" style="color:black;">
                                    <span class="addsep">${item.statusList[item.statusList.size()-1].price}</span>đ
                                    -<span class="addsep">${item.statusList[0].price}</span>đ
                                </b>
                            </a>
                        </div>
                    </c:if>
                </c:forEach>
            </div>
        </div>
    </c:forEach>
</div>
<script>
    addThousandSep();
</script>
<%@ include file = "template/footer.jsp"%>