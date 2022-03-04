var login = {

    init: function (){

        $("#btn_joinForm").on("click", function () {
            login.moveToJoinPage();
        });

        $("#btn_back").on("click",function (){
            login.moveToHomePage();
        });
        $(document.body).delegate(":input", "keyup", function (e) {
            if (e.which == 13)
                $("#btn_login").trigger("click");
        });

        $("#btn_login").on("click", function() {
            var email = $("input[name='username']").val(), password = $("input[name='password']").val();
            if (email == "") {
                alert("아이디를 입력하세요");
                return false;
            } else if (password == "") {
                alert("비밀번호를 입력하세요");
                return false;
            }
            $(this).closest("form").submit();
        });

        $("#btn_joinForm").on("click", function() {
            location.href = "/join";
        });


    },
    moveToJoinPage(){
        location.href = "/join";
    },
    moveToHomePage() {
        location.href = "/";
    },
    loginAjax() {
        let datas = {
            username: username.value,
            password: password.value
        };
        console.log(datas);
        $.ajax({
            type: 'POST',
            url: '/api/common/ajaxLogin',
            contentType: 'application/json; charset=utf-8',
            data:JSON.stringify(datas)
        }).done(function (loginCheck) {
            console.log(loginCheck);
            if (loginCheck) {
                alert("login ok");
                location.href = "/main";
            } else {
                alert("login fail")
            }




        }).fail(function (error) {
        });
    }
}

login.init();