var login = {

    init: function (){

        $("#join-btn").on("click", function () {
            login.moveToJoinPage();
        });

        $("#btn_back").on("click",function (){
            login.moveToHomePage();
        });
        $(document.body).delegate(":input", "keyup", function (e) {
            if (e.which == 13)
                $("#btn_login").trigger("click");
        });

        $("#login-btn").on("click", function() {
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

}

login.init();