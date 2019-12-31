/*点击评论*/
function post() {
    var questionId = $("#question_id").val();
    var content = $("#comment_content").val();
    addComment(questionId, 1, content);
}

function addComment(targetId, type, content) {
    $.ajax({
        type: "POST",
        url: "/comment",
        contentType: 'application/json',
        data: JSON.stringify({
            "parentId": targetId,
            "content": content,
            "type": type
        }),
        success: function (result) {
            if (result.code == 222) {
                //成功
                window.location.reload();
                $("#comment_section").hide();
            } else {
                if (result.code == 2003) {
                    //un login
                    var login = confirm(result.message);
                    if (login) {
                        //如果要登陆，则跳转页面  open方法打开一个新地址
                        window.open("https://github.com/login/oauth/authorize?client_id=c04ddabe01d852b742d1&redirect_uri=http://localhost:8887/callback&scope=user&state=1");
                        //来记录关不关闭窗口
                        window.localStorage.setItem("closabe", true);
                    }
                } else {
                    alert(result.code + result.message);
                }
            }
        },
        dataType: "json"
    });
}

function postsecond(e) {
    var commentId = e.getAttribute("data-id");
    var content = $("#comment-second-" + commentId).val();
    addComment(commentId, 2, content);
}

/* 二级评论-- 展开*/
function secondComment(e) {
    var id = e.getAttribute("data-id");
    var commentfunc = $("#comment-" + id);
    if (commentfunc.hasClass("in")) {
        commentfunc.removeClass("in");
        e.classList.remove("active");
        commentfunc.empty();
    } else {
        //展开二级评论区
        $.getJSON('/comment/' + id, function (data) {
            console.log(data);
            $.each(data.data.reverse(), function (index, result) {
                commentfunc.prepend("<div class='col-lg-12 col-md-12 col-sm-12 col-xs-12 comment-area' >" +
                    "  <div class='media'><div class='media-left'><img class='media-object img-rounded' src=" + result.user.avatarUrl + "></div>" +
                    "  <div class='media-body comment-body'><h4 class='media-heading comment-head'>" +
                    "  <span>" + result.user.name + "</span><span>" + moment(result.gmtCreate).format('YYYY-MM-DD') + "</span></h4>" +
                    "<div>" + result.content + "</div></div></div></div>");
            });
            commentfunc.addClass("in");
            e.classList.add("active");
        });

    }
    console.log(id);
}

/*展示:焦点事件
$(document).ready(function(){
    $("#tag").focus(function(){
        $("#label-tag").show();
    });
    $("#tag").blur(function(){
    });
    $("#label-tag").focus(function(){
        $("#label-tag").show();
    });
    $("#label-tag").blur(function () {
        $("#label-tag").hide();
    })
});*/

function showSelectTag() {
    $("#select-tag").show();
}

function selectTag(e) {
    var value = e.getAttribute("data-tag");
    var pre = $('#tag').val();
//pre.split(",").indexOf(value)
    if (pre.indexOf(value) == -1) {
        //说明value不存在
        if (pre) {
            $('#tag').val(pre + ',' + value);
        } else {
            $('#tag').val(value);
        }
    }
}
