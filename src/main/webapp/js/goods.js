
const imgRootDir = "upload"
$(() => {

    
    //Cart
    const total = $(".total");
    const count = $(".count");
    const rootItem = $(".item");
    const itemTemplate = $(".item_template").clone();
    
    const navRoot = $(".page_navigation .pagination");
    const navItemTemplate = $(".page_navigation .pagination .page_template").clone()
    navItemTemplate.removeClass("page_template").removeClass("dont_display")
    
    const pageLimit = 6;

    let currentCategory = 0;
    
    initBar(currentCategory);

    $.post("./cart/selectCount", (data) => {
        count.html(data);
    })

    count.html()

    navRoot.on("click", "li", (e) => {
        if ( !$(e.target).parent().hasClass("active")) {
            navRoot.children().removeClass("active")
            $(e.target).parent().addClass("active");
            let currentPage = $(e.target).parent().attr("data-page");
            changePage(currentPage, currentCategory);
        }
    });
    
    $(".classification").on("click", "a", (event) => {
        let category = $(event.target).attr("data-id");
        if (currentCategory !== category) {
            $("dd").removeClass("active_item");
            $(event.target).parent().addClass("active_item");
            currentCategory = category;
            // getColumn(currentCategory, pageLimit, category);
            initBar(currentCategory);
        }

    });

    function initBar(categoryId) {
        $.post("./detailProduct/selectProductCount?t=" + Date.now(),
            {
                category: categoryId
            },
            (data) => {
            
                if (data === '404') {
                    return;
                }
                //not 404 begin init
                navRoot.empty();
                let totalPage = Math.ceil(Number(data) / pageLimit);
                for (let i = 1; i <= totalPage; i++) {
                    navItemTemplate.children("a").html(i);
                    navItemTemplate.attr("data-page", i);
                    navRoot.append(navItemTemplate.clone());
                }
                $(".nav_item").eq(0).addClass("active");
                changePage(1, currentCategory);
                
               
            }
            )
    }
    
    function changePage(pageNum, categoryNum) {
        getColumn(pageNum, pageLimit, categoryNum)
    }
    
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



