$(() => {
    const userInfo = $(".user_info");
    const username = $(".username");
    const info = userInfo.children("p").eq(0);
    const gender = $(".gender");

    const inputName = $("#inputUsername");
    const inputTel = $("#inputTel");
    const password = $("#inputPassword3");
    const inputGender = $("#selectGender")

    const displayTel = $("#displayTel");
    const displayCreate = $("#displayData");
    const displayLevel = $("#displayLevel");

    const genderList = ["男", "女", "阿帕奇武装直升机", "沃尔玛购物袋"]
    const userLevel = ["管理员 管理员权限", "普通用户 普通权限"]

    const template = $(".cart_template").clone().removeClass("dont_display").removeClass("cart_template");
    const templateFather = $(".tbody")

    // let data = new Date();
    // let dataString = `${data.getFullYear()}-${data.getMonth()}-${data.getDay()}`;
    // let name = "卢本伟牛逼";
    // let user = "注册于 " + dataString + " 普通用户" + " 普通权限"
    // let sex = 0;
    // let phoneNum = "114514"
    // let level = 0;


    // fillInfo(name, user, sex, phoneNum)

    $.post("./user/selectUser", (data) => {
        let user = `注册于 ${data.createDate} ${userLevel[Number(data.level)]}`;
        fillInfo(data.username, user, Number(data.gender), data.tel, data.createDate, data.level);
    }, "json")



    $.post("./cart/selectCarts",
        (data) => {
            displayCarts(data);
        }, 'json'
    )



    $("#empty").on("click", (e) => {
        templateFather.empty();
        $.post("./cart/clearCart")
        displayCarts();
    })

    templateFather.on("click", "button", (event) => {
        let id = $(event.target).attr("data-id")
        $(event.target).parent().parent().remove();
        $.post("./cart/deleteCart",{
            id: id
        })
        displayCarts()
    })


    function fillInfo(name, user, sex, phone, date, level) {
        username.html(name);
        info.html(user);
        gender.html(genderList[sex]);

        inputName.val(name);
        inputTel.val(phone);
        inputGender.val(sex)

        displayTel.html(phone);
        displayCreate.html(date);
        displayLevel.html(userLevel[level]);
    }

    function displayCarts(carts) {
        $.post("./cart/selectCount", (data) => {
            if (Number(data) === 0) {
                $(".cart_panel").parent().addClass("dont_display");
                $(".tip").removeClass("dont_display");
            } else {
                $(".cart_panel").parent().removeClass("dont_display");
                $(".tip").addClass("dont_display");

                const cartName = template.children().eq(0);
                const cartNum = template.children().eq(1);
                const cartDel = template.children().eq(2).children("button");

                for (let cart of carts) {
                    cartName.html(cart.name);
                    cartDel.attr("data-id", cart.id);
                    cartNum.html(cart.count);
                    templateFather.append(template.clone());
                }

            }
        })


    }
})



