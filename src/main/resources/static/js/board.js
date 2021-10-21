const boardTitle=document.querySelector("#title")
const boardContent=document.querySelector("#content")
const boardWriteBtn=document.querySelector("#btn-board-save")

let indexBoard={
    getSummerNote:getSummerNote(),
    writePost:writePost()

}

function getSummerNote(){
    $('.summernote').summernote({
    tabsize: 2,
    height: 300
});
}

function handleBoardWriteBtnClick(){
    let data={
        title: boardTitle.value,
        content: boardContent.value
    }
    let token = document.querySelector("#csrfToken")
    let tokenName=token.name
    let tokenVal=token.value

    $.ajax({
        type:"POST",
        url:"/board/api/writePost",
        data:JSON.stringify(data),
        contentType: "application/json;charset=utf-8",
        dataType: "json",
        beforeSend:function(xhr){
            xhr.setRequestHeader(tokenName,tokenVal)
        }
    }).done(function (res){
        console.log(res)
        location.href="/"
    }).fail(function(e){
        console.log(e)
        }
    )
}

function writePost(){
    if(boardWriteBtn){
        boardWriteBtn.addEventListener("click",handleBoardWriteBtnClick)

    }
}
indexBoard.getSummerNote
indexBoard.writePost