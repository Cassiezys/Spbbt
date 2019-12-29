function post(){
    var questionId = $("#question_id").val();
    var content = $("#comment_content").val();
    $.ajax({
       type:"POST",
       url:"/comment",
        contentType:'application/json',
        data:JSON.stringify({
           "parentId":questionId,
            "content":content,
            "type":1
        }),
        success:function (result) {
            if(result.code == 200){
                //成功
                $("#comment_section").hide();
            }else{
                if(result.code == 2003){
                    //un login
                    var login=confirm(result.message);
                    if(login){
                        //如果要登陆，则跳转页面  open方法打开一个新地址
                        window.open("https://github.com/login/oauth/authorize?client_id=c04ddabe01d852b742d1&redirect_uri=http://localhost:8887/callback&scope=user&state=1");
                        //来记录关不关闭窗口
                        window.localStorage.setItem("closabe",true);
                    }
                }
            }
        },
        dataType:"json"
    });
    console.log(questionId);
    console.log(content);
}
