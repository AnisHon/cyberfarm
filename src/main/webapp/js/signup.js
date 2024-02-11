$(() => {

    const password = $("#password");
    const username = $("#username");
    const tel = $("#tel");
    const gender = $("#selectGender");
    const error = $(".error_text");


    let passwordValidate = false;
    let usernameValidate = false;
    let telValidate = false;
    let genderValidate = false;

    password.on("blur", () => {
        passwordValidate = passwordChecker();
    })

    tel.on("blur", () => {
        telValidate = telChecker();
    })

    username.on("blur", () => {
        usernameValidate = usernameChecker();
    })

    gender.on("blur", () => {
        genderValidate = genderChecker();
    })


    $("#register").on("click", (e) => {

        // console.log(usernameValidate + " " + passwordValidate + " " + telValidate + " " + genderValidate);

        if (!passwordValidate || !usernameValidate || !telValidate || !genderValidate) {
            error.html("请检查表单是否填写正确")
            e.preventDefault();
            return false;
        }

    })

    function passwordChecker() {
        const uPattern = /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,}$/

        if (!uPattern.test(password.val())) {
            error.html("密码长度不应少于8，且至少包含一个字母和数字")
            return false;
        }
        error.html("")
        return true;
    }

    function telChecker() {
        const uPattern = /^\d+/
        if (!uPattern.test(tel.val())) {
            error.html("电话号码不能包含非数字")
            return false;
        }
        error.html("")
        return true;
    }

    function usernameChecker() {
        if (username.val().length <= 0) {
            error.html("用户名不能为空")
            return false;
        }
        error.html("")
        return true;
    }

    function genderChecker() {
        if (gender.val() === null) {
            error.html("请选择性别")
            return false;
        }
        error.html("")
        return true;
    }

})