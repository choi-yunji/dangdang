var dogDetail = {

    init() {
        dogDetail.loadReply();
        dogDetail.countReply();
    },
    enterReply(e) {
            console.log(replyContent.value)
            console.log(e.key)
            console.log(e)
        if (e.key == 'Enter') {
            if (replyContent.value) {
                dogDetail.saveReply();
            }else {
                alert("댓글을 입력하세요");
            }
        }
    },
    saveReply() {

        let inputData = {
            reply  : replyContent.value ,
            boardId : boardId.value,
            boardType : "D",
        }
        $.ajax({
            type: 'POST',
            url: '/api/reply/save',
            contentType: 'application/json; charset=utf-8',
            data:JSON.stringify(inputData)
        }).done(function (returnData) {
            console.log(returnData);
            dogDetail.loadReply();
            $("#replyContent").val("");
        }).fail(function (error) {
        });
    },
    loadReply() {
        $.ajax({
            type: 'GET',
            url: '/api/reply/findAllReply/D/'+boardId.value,
            contentType: 'application/json; charset=utf-8',
            dataType: 'json'
        }).done(function (returnData) {
            $(".old-comment").html("");
            returnData.forEach(function (item) {
                var html = "";
                html += '<div class="comment">';
                html +=     '<img src="/api/dog/getImage/'+item.PROFILE_IMG_ID+'">';
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
            url: '/api/reply/countReply/D/'+boardId.value,
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

dogDetail.init();
