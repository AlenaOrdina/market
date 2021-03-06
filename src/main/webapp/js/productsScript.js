function removeAllProducts() {
    $(".products_item").remove()
}

function removeAllPageButtons() {
    $(".page__button").remove()
}

function checkBoxesData() {
    var boxes = $("input[name=brand]:checked");
    var brands = [];


    if(boxes.length > 0) {
        for (var i = 0; i < boxes.length; i++) {
            var box = boxes[i];
            brands.push($(box).val())
        }
    }


    return brands
}

function radioData() {
    return $("input[name=sortedBy]:checked").val();
}

function getProductHtml(product){
    return [
        "<div class=\"products_item\">" +
        "<div style=\"background-image: url(/img/products/" + product.id + ".jpg)\"" +
        "class=\"items_img\"></div>"+
        "<div class=\"products__description\">"+
        "<dl>"+
        "<dt>"+
        "<div class =\"mytitle\">"+product.title+
        "</div>"+
        "</dt>"+
        "<dd>" + product.category + ", " + product.productParameterDTO.weight + "</dd>"+
        "</dl>"+
        "<div class=\"products__description_text\">"+
        "<p>"+
        product.description+
        "</p>"+
        "</div>"+
        "<h4>Price: " + product.price + " $" +
        "<a onclick='addToBucket(" + product.id + ")'" +
        "class=\"knopka01\">"+
        "Add to cart" +
        "</a>"+
        "</h4>" +
        "</div>" +
        "</div>"
    ];
}

function makeData(categories, sortedBy, page) {
    var data ="";

    if(sortedBy)
        data = "sortedBy=" + sortedBy + "&";
    if(categories.length>0)
        data += "categories=" + categories + "&";
    if(page)
        data += "page=" + page;

    console.log(data);
    return data
}

function makeItWork() {
    showProducts(1);
    showPagesCount();
}

function showProducts(page) {

    removeAllProducts();
    $.ajax({
        url: "http://localhost:8081/productsRest",
        type: "get",
        data: makeData(checkBoxesData(), radioData(), page),
        success: function (data) {
            data.forEach(function(product){
                    $("#products").append(getProductHtml(product))
                }
            )
        }
    })
}

function showAllProducts() {

    $.ajax({
        url: "http://localhost:8081/productsRest",
        type: "get",
        data: makeData(checkBoxesData()),
        success: function (data) {
            data.forEach(function(product){
                    $("#products").append(getProductHtml(product))
                }

            )
        }
    })
}

function getPageButtonHtml(num){
    return [
        "<li class=\"page__button\">" +
        "   <a class=\"link link_header\" onclick='showProducts(" + num + ")'>" + num + "</a> " +
        "</li>"
    ];
}

function showPagesCount() {
    $.ajax({
        url: "http://localhost:8081/pagesCount",
        type: "get",
        data: makeData(checkBoxesData()),
        success: function (data) {
                    removeAllPageButtons();
                    for(var n=0; n<data; n++) {
                        $("#pageButtons").append(getPageButtonHtml(n+1))
                    }
                }
        })
}

function showAllPagesCount() {
    $.ajax({
        url: "http://localhost:8081/pagesCount",
        type: "get",
        success: function (data) {
            removeAllPageButtons();
            for(var n=0; n<data; n++) {
                $("#pageButtons").append(getPageButtonHtml(n+1))
            }
        }
    })
}

function addToBucket(prodId) {

    $.ajax({
            url: "http://localhost:8081/bucket/product  ",
        type: "post",
        data: {
          prodId : prodId
        },
        success:function () {
            smoke.alert ("Product added to cart!");
        }
    })

}

function confirmFunction() {
    var popup=document.getElementById("myPopup");
    popup.classList.toggle("show");
}

$(document).ready(function() {

    'use strict';

    $('.input-file').each(function() {
        var $input = $(this),
            $label = $input.next('.js-labelFile'),
            labelVal = $label.html();

        $input.on('change', function(element) {
            var fileName = '';
            if (element.target.value) fileName = element.target.value.split('\\').pop();
            fileName ? $label.addClass('has-file').find('.js-fileName').html(fileName) : $label.removeClass('has-file').html(labelVal);
        });
    });

});