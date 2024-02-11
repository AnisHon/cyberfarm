$(() => {

    const total = $(".total");
    const count = $(".count");
    const rootItem = $(".item");
    const itemTemplate = $(".item_template").clone();


    $(".classification").on("click", "a", (event) => {

        $("dd").removeClass("active_item");
        $(event.target).parent().addClass("active_item");
        displayGoods(rootItem, itemTemplate, 9)
    });


    let countNum = 12;
    let price = 100;
    displayGoods(rootItem, itemTemplate, 12);
    total.html("$" + price);
    count.html("" + countNum);


})

function displayGoods(root_item, itemTemplate, num, objects) {

    root_item.empty();

    itemTemplate.removeClass("item_template");
    itemTemplate.removeAttr("hidden");

    const title = itemTemplate.find("h3");
    const price = itemTemplate.find(".caption p").eq(0);
    const button = itemTemplate.find(".caption p .btn")
    const img = itemTemplate.find("img");
    // button.attr("data-id", "");
    img.html()

    console.log(itemTemplate)
    for (let i = 0; i < num; i++) {
        title.html(title.html() + i);
        price.html(price.html() + i);

        // img
        button.attr("href", button.attr("href") + "?id=" + i);
        root_item.append(itemTemplate.clone());
    }
}

