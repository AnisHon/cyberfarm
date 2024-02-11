$(() => {
    const goods_detail = $(".goods_detail");
    const price = $("#single_price")
    const total_price = $("#price");
    const count = $("#count");
    const detail_text = goods_detail.children(".detail_text");
    const p = detail_text.children("p");
    const main_img = $(".goods_img .main");


    $("#add").click((event) => {
        if (Number(count.val()) + 1 <= 10) {
            count.val(Number(count.val()) + 1 + '');
        }
        updatePrice();
    })
    $("#minus").click((event) => {
        if (Number(count.val()) - 1 > 0) {
            count.val(Number(count.val()) - 1 + '');
        }
        updatePrice();
    })

    //change img
    $(".goods_img .small").on("click", "img", (event) => {

        if ($(event.target).is(".goods_img .little")) {
            main_img.attr("src", $(event.target).attr("src"))
        }
    });

//change text
    detail_text.children("ul").on("click", "li", (event) => {
        p.html(getDetailText($(event.target).attr("data-col")));
        $(event.target).addClass("active_item").siblings().removeClass("active_item")
    })


    function getDetailText(num) {
        return num + num + "****************"
    }

    function updatePrice() {
        total_price.html(Number(price.html()) * Number(count.val()) + "");
    }


    p.html(getDetailText(1));
    updatePrice();

});











