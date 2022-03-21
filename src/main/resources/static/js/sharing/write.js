window.addEventListener('load', function() {
    const frame = document.querySelector(".body");
    const image = document.querySelector(".image");
    const upload_file = document.querySelector("#upload-file");
    const upload_name = document.querySelector(".upload-name");

    upload_file.addEventListener('input', ()=> {
        if(isImage(upload_file.files[0])){
            changeVal(upload_name, (upload_file.files[0].name));
            const reader = new FileReader();
            reader.addEventListener('load', ()=> {
                image.src = reader.result;
                if(frame.classList.length>1){
                    frame.classList.remove('visible');
                }
            });
            // fileURL 읽어와서 저장
            reader.readAsDataURL(upload_file.files[0]);
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
});

var sharingWrite = {

    init() {
        $("#btn_write").on("click", function () {
            sharingWrite.upload();
        });
    },
    upload() {
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

        let inputData = {
            content: sharingContent.value,
        }

        $.ajax({
            type: 'POST',
            url: '/api/sharing/save/sharing',
            contentType: 'application/json; charset=utf-8',
            data:JSON.stringify(inputData)
        }).done(function (returnData) {
            console.log(returnData);
            $.ajax({
                type:"POST",
                url: "/api/sharing/save/image/"+returnData,
                processData: false,
                contentType: false,
                data: formData,
                dataType:'json',
            }).done(function (test) {
                console.log(test);
                alert("업로드 성공");
                location.href = "/sharing";
            }).fail(function (error) {
            });


        }).fail(function (error) {
        });



    }
}

sharingWrite.init();

