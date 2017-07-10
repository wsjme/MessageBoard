<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>留言板</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/easyui.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/icon.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/demo.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.11.0.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.cookie.js"></script>
    <script>
        <%
            String referer=request.getHeader("referer");
            if(referer==null||!referer.startsWith("http://localhost:8080")){
                response.sendRedirect("error.jsp");
                return;
            }
        %>
        //定时器
        /*var t1 = window.setInterval(flush,500);*/
        //页面加载时载入表格数据
        $(function () {

            //通过读取cookie的方式载入表格数据
            /*if($.cookie('datalocal')){
             var datalocal = $.cookie('datalocal');
             $('#tab').datagrid({
             data:JSON.parse(datalocal)
             });
             }*/
            $('#tab').datagrid({
                url: '${pageContext.request.contextPath}/fs'
            });
        })

        //弹出对话框，添加留言
        function add() {
            $('#dd').show();
            $('#dd').dialog({
                title: '请添加留言',
                width: 400,
                height: 200,
                closed: false,
                cache: true,
                href: '',
                modal: true
            });
        }
        //点击提交按钮--关闭对话框，通过ajax接受数据给表格
        function submit() {
            var url = "${pageContext.request.contextPath}/as";
            var params = {
                message: $("#message").val(),
                username: '${sessionScope.username}',
                userid:${sessionScope.userid}
            };

            $('#dd').dialog('close');
            $.post(url, params, function (d) {
                $('#tab').datagrid({
                    data: d
                });
                /* var str = JSON.stringify(d);
                 $.cookie('datalocal', str ,{ expires: 1 });*/
            }, "json")
        }

        //点击关闭按钮--关闭对话框
        function clo() {
            $('#dd').dialog('close');
        }

        //删除留言
        function del() {
            var userid1 = ${sessionScope.userid};
            var rows = $('#tab').datagrid("getSelections");//获取你选择的所有行  //循环所选的行
            if(userid1 == "1"){
                for (var i = 0; i < rows.length; i++) {
                    var index = $('#tab').datagrid('getRowIndex', rows[i]);//获取某行的行号
                    $('#tab').datagrid('deleteRow', index);
                    var url = "${pageContext.request.contextPath}/ds";
                    var rowID = {rowID: rows[i].id};
                    $.post(url, rowID, function (d) {})
                }
            }else{
                var n = 0;
                for (var i = 0; i < rows.length; i++) {
                    var index = $('#tab').datagrid('getRowIndex', rows[i]);
                    var userid2 = rows[i].userid;
                    if(userid1==userid2){
                        $('#tab').datagrid('deleteRow', index);
                        var url = "${pageContext.request.contextPath}/ds";
                        var rowID = {rowID: rows[i].id};
                        $.post(url, rowID, function (d) {})
                        n++;
                    }else{
                        $("#tips").html("[他人留言不可删除哦！]");
                        window.setTimeout(function () {
                            $("#tips").html("");
                        }, 2000);
                        return;
                    }
                }
                $("#tips").html("您成功删除了"+n+"条留言");
                window.setTimeout(function () {
                    $("#tips").html("");
                }, 2000);
            }

        }

        function flush() {
            $('#tab').datagrid({
                url: '${pageContext.request.contextPath}/fs'
            });
        }
        function mymessage() {
            $('#tab').datagrid({
                queryParams: {
                    userid:${sessionScope.userid}
                },
                url:'${pageContext.request.contextPath}/ss'
            });
        }
    </script>
</head>
<body background="${pageContext.request.contextPath}/img/1.jpg" style="background-size:100% 100%;background-repeat:no-repeat">
<div style="position:absolute;width:900px;height:400px;left: 500px;top: 220px;">

    <table id="tab" class="easyui-datagrid" style="width:702px;height:370px;" data-options="ctrlSelect:true">

        <div id="dd" style="border-radius:3px" data-options="buttons:'#bb'" hidden="hidden">
            <form id="ff" method="post" hidden="hidden">
                <input id="message" class="easyui-textbox" data-options="fit:true,multiline:true,height:200">
            </form>
        </div>


        <div id="bb" style="display: none">
            <a id="submit" href="#" class="easyui-linkbutton" onclick="submit()">提交</a>
            <a id="close" href="#" class="easyui-linkbutton" onclick="clo()">关闭</a>
        </div>
        <div>
            &nbsp;&nbsp;<label style="font-size:30px;">
            <span style="color: #fff58f">
                欢迎
                <span style="color: #33ff6b">[   ${sessionScope.username}   ]</span>
                的到来！
            </span></label>&nbsp;&nbsp;
            <a href="login.jsp"><span style="color: #ff466b">退出登录</span></a>
            &nbsp;&nbsp;&nbsp;<span id="tips" style="color: #ff1f28"></span>
        </div>
        <div style="position:absolute;left: 365px;top: 10px;">
            <a id="btn_add" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add'"
               onclick="add()">添加留言</a>
        </div>
        <div style="position:absolute;left: 450px;top: 10px;">
            <a id="btn_del" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-remove'"
               onclick="del()">删除留言</a>
        </div>
        <div style="position:absolute;left: 535px;top: 10px;">
            <a id="btn_flush" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-reload'" onclick="flush()">刷新留言</a>
        </div>
        <div style="position:absolute;left: 620px;top: 10px;">
            <a id="btn_my" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-man'"
               onclick="mymessage()">我的留言</a>
        </div>
        <thead>
        <tr>
            <script>
                function formatImg(val,row) {
                        return "<img src=${pageContext.request.contextPath}/"+val+" style=width:25px;height:25px>"
                }
            </script>
            <th data-options="field:'avatar',width:32,formatter:formatImg">头像</th>
            <th data-options="field:'username',width:90">昵称</th>
            <th data-options="field:'message',width:343">留言内容</th>
            <th data-options="field:'time',width:145">留言时间</th>
            <th data-options="field:'ip',width:90">IP地址</th>
        </tr>
        </thead>

    </table>

</div>

</body>

</html>
