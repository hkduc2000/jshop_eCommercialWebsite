/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
  for (var i=0; i<AccentsMap.length; i++) {
    var re = new RegExp('[' + AccentsMap[i].substr(1) + ']', 'g');
    var char = AccentsMap[i][0];
    str = str.replace(re, char);
  }
  return str;
}

function searchItems() {
    var input, filter, searchingArea,a,txtValue;
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
            items[i].style.display = "";
        } else {
            items[i].style.display = "none";
        }
    }
}

function addThousandSep(){
    var sp = document.getElementsByClassName('addsep');
    for (var i=0;i<sp.length;i++){
        sp[i].innerText = parseInt(sp[i].innerText).toLocaleString('en');
    }
}
