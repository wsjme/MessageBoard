<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>留言板</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/easyui.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/icon.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/demo.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.11.0.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/json2.js"></script>
    <script>





        function timeout(s) {
            $("#usename_msg").html(s);
            window.setTimeout(function () {
                $("#usename_msg").html("");
            }, 2000);
        }

        function signup() {
            $("#loginValue").form('submit', {
                url: '${pageContext.request.contextPath}/sus',
                onSubmit: function (param) {
                    param.username = $("#username").val();
                    param.password = $("#password").val();
                    param.sex = $('input:radio[name="sex"]:checked').val();
                    /*if($("#boy").val()!=null){
                        param.sex = "boy";
                        alert("男");
                    }else{
                        param.sex = "girl";
                        alert("女");
                    }*/
                },
                success: function (data) {
                    var JsonObj = eval("(" + data + ")");
                    var s = JsonObj['message'];
                    if (s == "请输入密码！" || s == "密码格式不正确！") {
                        $("#password_msg").html(s);
                        window.setTimeout(function () {
                            $("#password_msg").html("");
                        }, 2000);
                    } else {
                        timeout(s);
                    }
                }
            })

        }


        function signin() {
            if($.trim($("#username").val())==""||$.trim($("#password").val())==""){
                return;
            }
            $("#loginValue").form('submit', {
                url: '${pageContext.request.contextPath}/sis',
                onSubmit: function (param) {
                    param.username = $("#username").val();
                    param.password = $("#password").val();
                },
                success: function (data) {
                    var JsonObj = eval("(" + data + ")");
                    var s = JsonObj['message'];

                    if (s == "用户不存在！") {
                        timeout(s);
                    }else if (s == "密码错误！") {
                        $("#password_msg").html(s);
                        window.setTimeout(function () {
                            $("#password_msg").html("");
                        }, 2000);
                    }else{
                        //跳转到jumpservlet 传参数username
                        var username = $("#username").val();
                        var password = $("#password").val();

                        var role = s;
                        location.replace('${pageContext.request.contextPath}/js?username=' + username + '&&role=' + s);
                    }

                }
            })
        }


    </script>
</head>
<body background="img/1.jpg" style="background-size:100% 100%;background-repeat:no-repeat;">
<div style="position:absolute;width:500px;height:400px;left:55%;top:50%;margin-left:-250px;margin-top:-100px;z-index:99;">
    <form id="loginValue">
        <div style="position:absolute;top: -5px;width:500px;">
            <label for="username"><span style="color: #ffffff;font-size: 25px">用户名:</span></label>&nbsp;&nbsp;&nbsp;&nbsp;
            <div style="position:absolute;left: 100px;top: 5px;width:500px;">
                <input id="username" class="easyui-textbox" data-options="iconCls:'icon-man'"
                       style="width:200px;height: 30px">
                <div style="position:absolute;left: 205px;top: 8px;width:500px;">
                    <span id="usename_msg" style="color: #ff241a"></span>
                </div>
            </div>

        </div>

        <div style="position:absolute;top:10%">
            <label for="username"><span style="color: #ffffff;font-size: 25px;">密&nbsp;&nbsp;&nbsp;码:</span></label>&nbsp;&nbsp;&nbsp;&nbsp;
            <div style="position:absolute;left: 100px;top:10%">
                <input id="password" type="password" class="easyui-textbox" data-options="iconCls:'icon-lock'"
                       style="width:200px;height: 30px">
                <div style="position:absolute;left: 205px;top: 10px;width:500px;">
                    <span id="password_msg" style="color: #ff241a"></span>
                </div>
            </div>

        </div>
    </form>
    <div style="position:absolute;left: 50px;top: 125px;">

        <a id="btn_signin" href="#" class="easyui-linkbutton" data-options="size:'large'" onclick="signin()">---登
            录---</a>

    </div>
    <div style="position:absolute;left: 180px;top:125px">
        <a id="btn_signup" href="#" class="easyui-linkbutton" data-options="size:'large'" onclick="signup()">---注
            册---</a>
    </div>



    <div style="position:absolute;left: 70px;top:95px">
        <label><input form="loginValue" id="boy" name="sex" value="boy" checked="checked" type="radio"/><span style="color: #ffffff">男</span></label>
    </div>
    <div style="position:absolute;left: 200px;top:95px">
        <label><input form="loginValue" id="girl" name="sex" value="girl" type="radio"/><span style="color: #ffffff">女</span></label>
    </div>


    </table>

</div>

</body>

</html>
