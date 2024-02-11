
const imgRootDir = "upload"
$(() => {

    
    //Cart
    const total = $(".total");
    const count = $(".count");
    const rootItem = $(".item");
    const itemTemplate = $(".item_template").clone();


    const pageLimit = 12;

    let currentCategory = 0;

    $(".classification").on("click", "a", (event) => {
        let category = $(event.target).attr("data-id");
        if (currentCategory !== category) {
            $("dd").removeClass("active_item");
            $(event.target).parent().addClass("active_item");
            currentCategory = category;
            getColumn(0, pageLimit, category);
        }

    });

    getColumn(0, pageLimit, 0);
    
    // total.html("$");
    // count.html("" + countNum);


    // http://localhost:8080/cyberfarm_war_exploded/

    function getColumn(begPage, pageLimit, categoryIndex) {
        $.post("./productColumn/selectAll?t=" + Date.now(), {
            begin: begPage,
            limit: pageLimit,
            category: categoryIndex
        }, (data) => {
            console.log(data)

            displayGoods(rootItem, itemTemplate, data.columns); 

            // init(begPage, data.total);
        }, 'json')
    }
    function displayGoods(root_item, itemTemplateClone, dataColumns) {
        root_item.empty();
        itemTemplateClone.removeClass("item_template");
        itemTemplateClone.removeAttr("hidden");

        const title = itemTemplateClone.find("h3");
        const price = itemTemplateClone.find(".caption p").eq(0);
        const button = itemTemplateClone.find(".caption p .btn")
        const img = itemTemplateClone.find("img");

        for (const column of dataColumns) {
            title.html(column.name);
            price.html("$" + column.price / 100);
            img.attr("src", imgRootDir + "/" + column.coverUrl);
            button.attr("href", "detail.html" + "?id=" + column.id);
            root_item.append(itemTemplateClone.clone());
        }

    }

//    function init(current, countNumber) {
//        $("#pager").zPager({
//            pageCount: countNumber,
//            pageData: 1,
//            current: current,
//            pageStep: 5,
//            btnShow: true,
//            ajaxSetData: false
//        });
//
//    }




})



