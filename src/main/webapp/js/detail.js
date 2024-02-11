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


    let urlParams = new URLSearchParams(window.location.search);
    const productId = urlParams.get('id');
    
    if (productId) {
        $.post("./detailProduct/selectProduct",
            {
                id: productId
            },
            (data) => {
                price.html("" + data.price / 100);
                main_img.attr('src', "upload/" + data['picture01']);
                $(".goods_producer").html(data.producer);
                $(".goods_name").html(data.name);
                $(".img_01").attr("src", "upload/" + data.picture01);
                $(".img_02").attr("src", "upload/" + data.picture02);
                
                $("#detail_text").html(data.detailText);
                $("#producer").html(data.producer);
                $("#comment").html(data.comment);
                
                
                updatePrice();
            },
            'json'
        );
    }
  
    
    

});











