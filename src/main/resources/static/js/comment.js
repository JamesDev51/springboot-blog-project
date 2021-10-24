const commentBoardId=document.querySelector("#boardId")
const commentUserId=document.querySelector("#userId")
const commentContent=document.querySelector("#comment-content")
const commentSaveBtn=document.querySelector("#btn-comment-save")

let indexComment={
    writeComment:writeComment()
}
//댓글 작성 메소드 ajax
function handleCommentSaveBtnClick(){
    let token = document.querySelector("#csrfToken")
    let tokenName=token.name
    let tokenVal=token.value
    if (commentContent.value===""){
        alert("댓글 내용을 입력하세요")
        return false
    }
    let data={
        userId:commentUserId.value,
        boardId:commentBoardId.value,
        content:commentContent.value
    }
    $.ajax({
        type:"POST",
        url:`/board/api/writeComment/${data.boardId}`,
        data:JSON.stringify(data),
        contentType:"application/json;charset=utf-8",
        dataType:"json",
        beforeSend:function (xhr){
            xhr.setRequestHeader(tokenName,tokenVal)
        }
    }).done(function (res){
        location.href=`/post/${data.boardId}`
    }).fail(function (e){
        console.log(e)
    })
}

function writeComment(){
    if(commentSaveBtn){
        commentSaveBtn.addEventListener("click",handleCommentSaveBtnClick)
    }
}

function commentDelete(boardId,commentId){
    let token = document.querySelector("#csrfToken")
    let tokenName=token.name
    let tokenVal=token.value
    $.ajax({
        type:"DELETE",
        url:`/board/api/deleteComment/${commentId}`,
        dataType:"json",
        beforeSend:function (xhr){
            xhr.setRequestHeader(tokenName,tokenVal)
        }
    }).done(function (){
        alert("댓글 삭제 완료")
        location.href=`/post/${boardId}`
    }).fail(function (e){
        console.log(e)
    })

}
indexComment.writeComment