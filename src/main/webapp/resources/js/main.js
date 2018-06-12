$('textarea[maxlength]').on('keyup blur', function() {
    // Store the maxlength and value of the field.
    var maxlength = $(this).attr('maxlength');
    var val = $(this).val();

    // Trim the field if it has content over the maxlength.
    if (val.length > maxlength) {
        $(this).val(val.slice(0, maxlength));
    }
});

function addToCart (productid) {
    $.ajax({
        type: "GET",
        dataType: "json",
        url:  "http://" + $(location).attr('host') + "/add-to-cart/" +productid,
    });
    enableSearchButton(productid);
}

function removeFromCart(productId) {
    $.ajax({
        type: "GET",
        dataType: "json",
        url: "http://" + $(location).attr('host') + "/remove-product/" +productId
    });

    $(document).ajaxStop(function(){
        window.location.reload();
    });
}

function deleteUser(userId) {
    $.ajax({
       type: "GET",
       dataType: "json",
       url:  "http://" + $(location).attr('host') + "/deleteuser/" +userId
    });

    $(document).ajaxStop(function(){
        window.location.reload();
    });
}

function deleteProduct(productId) {
    $.ajax({
        type: "GET",
        dataType: "json",
        url:  "http://" + $(location).attr('host') + "/deleteproduct/" +productId
    });

    $(document).ajaxStop(function(){
        window.location.reload();
    });
}

function deleteCategory(categoryId) {
    $.ajax({
        type: "GET",
        dataType: "json",
        url:  "http://" + $(location).attr('host') + "/deletecategory/" +categoryId
    });

    $(document).ajaxStop(function(){
        window.location.reload();
    });
}

function enableSearchButton(id) {
    $("#product_"+id)
        .addClass("disabled")
        .text("In cart");

}
