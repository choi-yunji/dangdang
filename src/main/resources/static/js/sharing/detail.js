var sharingDetail = {

    init() {
        sharingDetail.loadReply();
        sharingDetail.countReply();
    },
    enterReply() {
        if (window.event.keyCode == 13) {
            // 엔터키가 눌렸을 때 실행할 내용
            if (replyContent.value) {
                sharingDetail.saveReply();
            }else{
                alert("댓글을 입력하세요");
            }
        }
    },
    saveReply() {

        let inputData = {
            reply  : replyContent.value ,
            boardId : boardId.value,
            boardType : "S",
        }
        $.ajax({
            type: 'POST',
            url: '/api/reply/save',
            contentType: 'application/json; charset=utf-8',
            data:JSON.stringify(inputData)
        }).done(function (returnData) {
            console.log(returnData);
            sharingDetail.loadReply();
            $("#replyContent").val("");
        }).fail(function (error) {
        });
    },
    loadReply() {
        $.ajax({
            type: 'GET',
            url: '/api/reply/findAllReply/S/'+boardId.value,
            contentType: 'application/json; charset=utf-8',
            dataType: 'json'
        }).done(function (returnData) {
            $(".old-comment").html("");
            returnData.forEach(function (item) {
                var html = "";
                html += '<div class="comment">';
                html +=     '<img src="/api/sharing/getImage/'+item.PROFILE_IMG_ID+'">';
                html +=     '<div>';

                if (item.DATE_DIFF > 0) {
                    html +=     '<h3>'+item.USER_NICKNAME+'<span>'+item.DATE_DIFF +'일 전</span></h3>';
                }else{
                    html +=     '<h3>'+item.USER_NICKNAME+'<span>'+item.TIME_DIFF +'분 전</span></h3>';
                }
                html +=         ' <p>'+item.REPLY+'</p>';
                html +=         ' <input type="hidden" value="'+item.REPLY_ID+'">';
                html +=         ' <div class="acomment-action">';
                html +=             ' <img src="/image/like.png" alt="">';
                html +=             ' <span>0개</span>';
                html +=             ' <span>답글</span>';
                html +=             ' <a href="">모든 답글 보기</a>';
                html +=          ' </div>';
                html += '  </div>';
                html += '</div>';
                $(".old-comment").append(html);
            });

                console.log(returnData);


        }).fail(function (error) {
        });
    },
    countReply() {
        $.ajax({
            type: 'GET',
            url: '/api/reply/countReply/S/'+boardId.value,
            contentType: 'application/json; charset=utf-8',
            dataType: 'json'
        }).done(function (returnData) {
            var countElement = document.getElementById("replyCount");
            countElement.innerHTML=returnData + "개";
            console.log(returnData);
        }).fail(function (error) {
        });
    }
}

sharingDetail.init();