$(document).ready(function (){
    $('#summernote').summernote({
        placeholder : 'content',
        minHeight : 370,
        maxHeight : null,
        focus : true,
        lang : 'ko-KR'
    });
});

var encyclopedia = {
    init() {

    }
}

$(()=>{
    $("#subBtn").click( ()=>{

        console.log($("#contents").val());
        console.log( summernote.getData() );

        $.ajax({
            method: "POST",
            url: "/encyclopedia/save",
            data: {
                contents:summernote.getData()
            }
        })
            .done(function( data ) {
                if( data.result=="success")
                {
                    alert("글이 등록되었습니다");
                    location.href="/encyclopedia"
                }
            })
            .fail(function( msg ) {
                alert( "fail " + msg );
            });
    })
});