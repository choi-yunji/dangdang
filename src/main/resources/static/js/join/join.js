var join = {
    init() {
        $("#userId").on("propertychange change keyup paste input", function () {
            if(userId.value == "" || userId.value == undefined){
                $("#idValidaton").html("아이디를 입력하세요")
                $('#btn_join').attr('disabled', true);

            }else{
                join.checkIdIsExist(userId.value)
            }

        });

        $("#btn_join").on("click",function (){
            join.join();
        })

    },
    checkIdIsExist(id) {
        $.ajax({
            type: 'GET',
            url: '/api/join/checkIsIdExists/' + id,
            contentType: 'application/json; charset=utf-8'
        }).done(function (data) {
            if (data) {
                $("#idValidaton").html("중복된 아이디입니다.")
                $('#btn_join').attr('disabled', true);
            } else {
                $("#idValidaton").html("사용가능한 아이디입니다.")
                $('#btn_join').attr('disabled', false);
            }

        }).fail(function (error) {
        });

    },
    join() {
        if (password.value == "") {
            alert("비밀번호를 입력하세요 ");
        }else{
            let datas = {
                userId: userid.value,
                userPw: password.value,
                userName: username.value,
                userNickName: usernickname.value
            };
            $.ajax({
                type: 'POST',
                url: '/api/join/join',
                contentType: 'application/json; charset=utf-8',
                data:JSON.stringify(datas)
            }).done(function (data) {
                alert("회원가입이 완료되었습니다.");
                location.href = "/";



            }).fail(function (error) {
            });
        }


    }
}

join.init();