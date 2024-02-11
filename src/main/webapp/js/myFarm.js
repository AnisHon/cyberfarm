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
    const userLevel = ["管理员", "普通用户"]

    const template = $(".cart_template").clone().removeClass("dont_display").removeClass("cart_template");
    const templateFather = $(".tbody")

    let data = new Date();
    let dataString = `${data.getFullYear()}-${data.getMonth()}-${data.getDay()}`;
    let name = "卢本伟牛逼";
    let user = "注册于 " + dataString + " 普通用户" + " 普通权限"
    let sex = 0;
    let phoneNum = "114514"
    let level = 0;
    let totalCarts = 10;


    fillInfo(name, user, sex, phoneNum)
    displayCarts();


    $("#empty").on("click", (e) => {
        templateFather.empty();
        totalCarts = 0;
        displayCarts();
    })

    templateFather.on("click", "button", (event) => {
        let id = $(event.target).attr("data-id")
        $(event.target).parent().parent().remove();
        totalCarts--;
        templateFather.empty();
        displayCarts()
    })


    function fillInfo(name, user, sex, phone) {
        username.html(name);
        info.html(user);
        gender.html(genderList[sex]);

        inputName.val(name);
        inputTel.val(phone);
        inputGender.val(sex)

        displayTel.html(phone);
        displayCreate.html(dataString);
        displayLevel.html(userLevel[level]);
    }

    function displayCarts() {
        if (totalCarts === 0) {
            $(".cart_panel").parent().addClass("dont_display");
            $(".tip").removeClass("dont_display");
        } else {
            $(".cart_panel").parent().removeClass("dont_display");
            $(".tip").addClass("dont_display");

            const cartName = template.children().eq(0)
            const cartNum = template.children().eq(1)
            const cartDel = template.children().eq(2)

            for (let i = 0; i < totalCarts; i++) {
                cartName.html(i + "");
                cartNum.html(i + "");
                cartDel.attr("data-id", "" + i);
                templateFather.append(template.clone());
            }

        }

    }
})



