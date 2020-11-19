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
        <a onclick="switchTab(${ctg.categoryNo});" class="nav-link ${ctg.categoryNo eq 1?"active":""}" data-toggle="tab" href="#ctg${ctg.categoryNo}">
            ${ctg.category}
        </a>
    </li>
    </c:forEach>
    <div class="form-inline ml-5 mb-1">
        <input class="form-control mr-2" id="searchingInput" style="width: 150px; display: inline-block;" 
                   type="search" placeholder="Tìm kiếm">
        <button class="btn btn-outline-success" onclick="searchItems();">Tìm kiếm
            <svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-search" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
            <path fill-rule="evenodd" d="M10.442 10.442a1 1 0 0 1 1.415 0l3.85 3.85a1 1 0 0 1-1.414 1.415l-3.85-3.85a1 1 0 0 1 0-1.415z"/>
            <path fill-rule="evenodd" d="M6.5 12a5.5 5.5 0 1 0 0-11 5.5 5.5 0 0 0 0 11zM13 6.5a6.5 6.5 0 1 1-13 0 6.5 6.5 0 0 1 13 0z"/>
            </svg>
        </button>
    </div>
    
</ul>

<div class="tab-content">
    <c:forEach items="${requestScope.categories}" var="ctg">
        <div id="ctg${ctg.categoryNo}" class="container tab-pane fade ${ctg.categoryNo eq 1?"active show":""}"><br>
            <div class="row">
                <c:forEach items="${requestScope.items}" var="item">
                    <c:if test ="${item.categoryNo == ctg.categoryNo}">
                        <div class="col-6  col-lg-3 mt-1 item pagingitem" style="text-align: center;">
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
                                -<span class="d-block d-sm-inline"><span class="addsep">${item.statusList[0].price}</span>đ</span>
                            </b>
                            </a>
                        </div>
                    </c:if>
                </c:forEach>
            </div>
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
    curtab = 1;
    tabname = 'ctg';
    searchItems();
</script>
<%@ include file = "template/footer.jsp"%>