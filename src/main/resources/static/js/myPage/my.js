var my = {
    init() {
        const frame = document.querySelector(".body");
        const image = document.querySelector("#imgProfile");
        const upload_file = document.querySelector("#upload-file");
        const upload_name = document.querySelector(".upload-name");

        upload_file.addEventListener('input', ()=> {
            if(isImage(upload_file.files[0])){
                my.uploadProfileImage();
            }
            else {
                alert('이미지만 업로드 가능합니다.');
                return;
            }
        });
        function isImage(file){
            return file.type.indexOf('image') >= 0;
        }
        function changeVal(class_name, change_name){
            class_name.value = change_name;
        }

        $("#imgThumb").on("click", function () {
            $("#upload-file").trigger("click");
        });
        // $('#upload-file').on('change',my.uploadThumb);

        my.getUserProfileImg();
    },
    uploadThumb(e) {
        var sel_files = [];

        sel_files = [];
        $('#imgThumb').empty();

        var files = e.target.files;
        var fileArr = Array.prototype.slice.call(files);

        fileArr.forEach(function(f){
            if(!f.type.match("image/.*")){
                alert("이미지 확장자만 업로드 가능합니다.");
                return;
            };
            if(files.length < 11){
                sel_files.push(f);
                var reader = new FileReader();
                reader.onload = function(e){
                    var html = `<img class="thumb" id="imgProfile" ${e.target.result} data-file=${f.name}/>`;
                    $('#imgThumb').append(html);
                };
                reader.readAsDataURL(f);
            }
        })
        if(files.length > 11){
            alert("최대 10장까지 업로드 할 수 있습니다.");
        }
    },
    uploadProfileImage() {
        const imageInput = $("#upload-file")[0];
        // 파일을 여러개 선택할 수 있으므로 files 라는 객체에 담긴다.
        console.log("imageInput: ", imageInput.files)

        if(imageInput.files.length === 0){
            alert("파일을 선택해주세요");
            return;
        }

        const formData = new FormData();

        let fileInput = $("input[name='uploadFile']");
        let fileList = fileInput[0].files;
        let fileObj = fileList[0]

        formData.append("uploadFile", fileObj);
        console.log(formData);
        $.ajax({
            type:"POST",
            url: "/api/my/save/image",
            processData: false,
            contentType: false,
            data: formData,
            dataType:'json',
        }).done(function (test) {
            console.log(test);
            my.getUserProfileImg();
        }).fail(function (error) {
        });
    },
    getUserProfileImg() {
        $.ajax({
            type: 'GET',
            url: '/api/my/getUserProfileImg',
            contentType: 'application/json; charset=utf-8'
        }).done(function (retData) {
                $("#imgThumb").html("");
                var html = "";
                html += '<img class="thumb" id="imgProfile" src="/api/sharing/getImage/'+retData+'">';
                $("#imgThumb").append(html);

            });
    }
}

my.init();