/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/* global parseInt */

function removeAccents(str) {
    var AccentsMap = [
        "aàảãáạăằẳẵắặâầẩẫấậ",
        "AÀẢÃÁẠĂẰẲẴẮẶÂẦẨẪẤẬ",
        "dđ", "DĐ",
        "eèẻẽéẹêềểễếệ",
        "EÈẺẼÉẸÊỀỂỄẾỆ",
        "iìỉĩíị",
        "IÌỈĨÍỊ",
        "oòỏõóọôồổỗốộơờởỡớợ",
        "OÒỎÕÓỌÔỒỔỖỐỘƠỜỞỠỚỢ",
        "uùủũúụưừửữứự",
        "UÙỦŨÚỤƯỪỬỮỨỰ",
        "yỳỷỹýỵ",
        "YỲỶỸÝỴ"
    ];
    for (var i = 0; i < AccentsMap.length; i++) {
        var re = new RegExp('[' + AccentsMap[i].substr(1) + ']', 'g');
        var char = AccentsMap[i][0];
        str = str.replace(re, char);
    }
    return str;
}

//function searchItems() {
//    var input, filter, searchingArea,a,txtValue;
//    input = document.getElementById("searchingInput");
//    filter = input.value.toUpperCase();
//    filter = removeAccents(filter);
//    searchingArea = (document.querySelector("div.active")).getElementsByClassName("row")[0];
//    items = searchingArea.getElementsByClassName("item");
//    for (i = 0; i < items.length; i++) {
//        a = items[i].getElementsByTagName("p")[0];
//        txtValue = a.textContent || a.innerText;
//        txtValue = removeAccents(txtValue);
//        if (txtValue.toUpperCase().indexOf(filter) > -1) {
//            items[i].style.display = "";
//        } else {
//            items[i].style.display = "none";
//        }
//    }
//}

var cur;
var items = [];
var size = 8;
var controller;
var prev ;
var next;
var lblcur;
var lbltotal;
var curtab;
var tabname = 'ctg';
var itemDisplay = "block";

function switchPage(state) {
    lblcur.innerHTML = parseInt(lblcur.innerHTML) + state;
    for (var j = (cur - 1) * size; (j < items.length) && (j < cur * size); j++) {
        items[j].style.display = "none";
    }
    cur = cur + state;
    for (var j = (cur - 1) * size; (j < items.length) && (j < cur * size); j++) {
        items[j].style.display = itemDisplay;
    }
    if (cur === Math.ceil(items.length / size)) {
        next.classList.add('disabled');
    } else {
        next.classList.remove('disabled');
    }
    if (cur == 1) {
        prev.classList.add('disabled');
    } else {
        prev.classList.remove('disabled');
    }
}

function switchTab(i) {
    lblcur.innerHTML = 1;
    curtab = i;
    for (var j = (cur - 1) * size; (j < items.length) && (j < cur * size); j++) {
        items[j].style.display = "none";
    }
    var tab = document.getElementById(tabname + i);
    items = tab.getElementsByClassName('pagingitem showit');
    prev.classList.add('disabled');
    next.classList.add('disabled');
    if (items.length <= size) {
        controller.style.display = "none";
    } else {
        controller.style.display = "block";
        lbltotal.innerHTML = Math.ceil(items.length / size);
    }
    if (items.length === 0) {
        return;
    }
    cur = 1;
    for (var j = 0; (j < items.length) && (j < size); j++) {
        items[j].style.display = itemDisplay;
    }
    for (var j = size; j < items.length; j++) {
        items[j].style.display = "none";
    }
    next.classList.remove('disabled');
}


function searchItems() {
    var input, filter, searchingArea, a, txtValue;
    input = document.getElementById("searchingInput");
    filter = input.value.toUpperCase();
    filter = removeAccents(filter);
    searchingArea = (document.querySelector("div.active")).getElementsByClassName("row")[0];
    items = searchingArea.getElementsByClassName("item");
    for (i = 0; i < items.length; i++) {
        a = items[i].getElementsByTagName("p")[0];
        txtValue = a.textContent || a.innerText;
        txtValue = removeAccents(txtValue);
        if (txtValue.toUpperCase().indexOf(filter) > -1) {
            items[i].classList.add('showit');
        } else {
            items[i].classList.remove('showit');
        }
    }
    switchTab(curtab);
}

function addThousandSep() {
    var sp = document.getElementsByClassName('addsep');
    for (var i = 0; i < sp.length; i++) {
        sp[i].innerText = parseInt(sp[i].innerText).toLocaleString('en');
    }
}
