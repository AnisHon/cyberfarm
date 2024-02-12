$(() => {
    const not_login = $("#not_login");
    const login = $("#login");
    const usernameTag = $("#username");

    $.post("./user/selectUsername", (data) => {

        if (Boolean(data.isLogin)) {
            not_login.addClass("dont_display");
            login.removeClass("dont_display");
            usernameTag.html(data.username);
        }
    }, "json" );

})