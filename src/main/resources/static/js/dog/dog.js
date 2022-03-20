let pagination = 0;
let checkLoading = false;


var dog = {

    init() {
        dog.loadDogBoard();
        YesScroll();

    },
    loadDogBoard() {
        $.ajax({
            type: 'GET',
            url: '/api/dog/loadDogBoard/'+pagination,
            contentType: 'application/json; charset=utf-8'
        }).done(function (dataList) {
            dataList.forEach(item => {
                var html = "";
                html += '<div class="post">';
                html +=     '<div class="info">';
                html +=         '<div class="user">';
                html +=             '<div class="profile-pic"><img src="/api/dog/getImage/'+item.images[0].imageId+'" alt=""></div>';
                html +=             '<p class="username">'+item.userNickName+'</p>';
                html +=         '</div>';
                html +=      '</div>';
                html +=      '<img src="/api/dog/getImage/'+item.images[0].imageId+'" class="post-image" alt="">';
                html +=      '<div class="post-content">';
                html +=          '<div class="reaction-wrapper">';
                html +=              '<img src="image/like.png" class="icon" alt="">';
                html +=              '<img src="image/comment.png" class="icon" alt="">';
                html +=              '<img src="image/messenger.png" class="icon" alt="">';
                html +=          '</div>';
                html +=          '<p class="likes">좋아요 1,999개 </p>';
                html +=          '<p class="description"><span>'+item.userNickName+'</span>'+item.dogContents+'</p>';
                html +=          '<p class="post-time">'+item.modDt+'</p>';
                html +=          '<div class="comment">';
                html +=          '<p>댓글 500개 <a href="/dog/detail"><button class="view-btn">모두보기</button></a></p>';
                html +=         '</div>'
                html +=      '</div>';
                html +=      '<div class="comment-wrapper">';
                html +=          '<img src="image/smile.jpg" class="icon" alt="">';
                html +=          '<input type="text" class="comment-box" placeholder="발자국 남기기🐾">';
                html +=          '<button class="comment-btn">🐾</button>';
                html +=      '</div>';
                html +=  '</div>';

                checkLoading = false;

                $(".left-col").append(html);

            });



            /*
            * <div class="post">
                    <div class="info">
                        <div class="user">
                            <div class="profile-pic"><img src="img/myprofile.png" alt=""></div>
                            <p class="username">babyyunji</p>
                        </div>
                        <img src="img/option.png" class="options" alt="">
                    </div>
                    <img src="img/myprofile.png" class="post-image" alt="">

                    <div class="post-content">
                        <div class="reaction-wrapper">
                            <img src="img/like.png" class="icon" alt="">
                            <img src="img/comment.png" class="icon" alt="">
                            <img src="img/messenger.png" class="icon" alt="">
                            <img src="img/save.png" class="save icon" alt="">
                        </div>
                        <p class="likes">좋아요 1,999개 </p>
                        <p class="description"><span>username</span> Cute baby puppy</p>
                        <p class="post-time">2분 전</p>
                    </div>
                    <div class="comment-wrapper">
                        <img src="img/smile.jpg" class="icon" alt="">
                        <input type="text" class="comment-box" placeholder="발자국 남기기🐾">
                        <button class="comment-btn">🐾</button>
                    </div>
                </div>
            *
            * */
        }).fail(function (error) {
        });
    }
}

function YesScroll() {
    const fullContent = document.querySelector('.left-col');
    const screenHeight = screen.height;

    document.addEventListener('scroll', OnScroll, {passive: true})
    function OnScroll() {

        const fullHeight = fullContent.clientHeight;
        const scrollPosition = pageYOffset;
        console.log(fullHeight + "fullHeight");
        console.log(screenHeight/2 + "screenHeight/2");
        console.log(scrollPosition + "scrollPosition");
        if (((fullHeight - screenHeight) <= scrollPosition) && !checkLoading) {
            checkLoading = true;
            pagination++;
            dog.loadDogBoard();
        }
    }
}

dog.init();