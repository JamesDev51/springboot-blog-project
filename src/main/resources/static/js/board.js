const boardTitle=document.querySelector("#title")
const boardContent=document.querySelector("#content")
const boardWriteBtn=document.querySelector("#btn-board-save")
const boardEditBtn=document.querySelector("#btn-board-edit")
const boardId=document.querySelector("#id")
const boardDeleteBtn=document.querySelector("#btn-delete")


let indexBoard={
    getSummerNote:getSummerNote(),
    writePost:writePost(),
    editPost:editPost(),
    deletePost:deletePost()

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
function handleBoardEditBtnClick(){
    let data={
        title: boardTitle.value,
        content: boardContent.value
    }
    let id=boardId.value;
    let token = document.querySelector("#csrfToken")
    let tokenName=token.name
    let tokenVal=token.value
    $.ajax({
        type:"PUT",
        url:`/board/api/editPost/${id}`,
        data:JSON.stringify(data),
        contentType: "application/json;charset=utf-8",
        dataType: "json",
        beforeSend:function(xhr){
            xhr.setRequestHeader(tokenName,tokenVal)
        }
    }).done(function (res){
        location.href=`/post/${id}`
    }).fail(function(e){
            console.log(e)
        }
    )
}

function editPost(){
    if(boardEditBtn){
        boardEditBtn.addEventListener("click",handleBoardEditBtnClick)
    }
}

function handleBoardDeleteBtnClick(){
    let id=boardId.value
    let token = document.querySelector("#csrfToken")
    let tokenName=token.name
    let tokenVal=token.value
    $.ajax({
        type:"DELETE",
        url:`/board/api/deletePost/${id}`,
        dataType:"json",
        beforeSend:function(xhr){
            xhr.setRequestHeader(tokenName,tokenVal)
        }
    }).done(function (res){
        location.href='/'
    }).fail(function(e){
            console.log(e)
        }
    )

}
function deletePost(){
    if(boardDeleteBtn){
        boardDeleteBtn.addEventListener("click",handleBoardDeleteBtnClick)
    }

}

indexBoard.getSummerNote
indexBoard.writePost
indexBoard.editPost
indexBoard.deletePost