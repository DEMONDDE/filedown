<%--
  Created by IntelliJ IDEA.
  User: 胡建德
  Date: 2020/3/20
  Time: 13:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <script src="js/jquery-3.4.1.min.js"></script>
    <script>
        function check() {
            var $file1 = $("input[name='uploadfile']").val();//用户文件内容(文件)
            // 判断文件是否为空
            if ($file1 == "") {
                alert("请选择上传的目标文件! ")
                return false;
            }
            var formData = new FormData();//这里需要实例化一个FormData来进行文件上传
            formData.append("myfile",document.getElementById("file1").files[0]);
            $.ajax({url: "fileUploadServlet",
                type: 'POST',
                cache: false,
                processData: false,
                contentType: false,
                enctype:"multipart/form-data",
                dataType:"text",
                data: formData,
                async:false,
                error: function (request) {
                alert("服务器故障");
                },
                success: function (data) {
                    alert(data);
                }
            })
        }
    </script>
</head>
<body>

    请选择文件:<input id="file1" type="file" name="uploadfile" multiple="multiple">
    <input type="button" value="上传" onclick="check()"><br>

<a href="${pageContext.request.contextPath}/index.html"><button>返回</button></a>

</body>
</html>
