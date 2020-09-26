<%@ page import="java.io.File" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: azama
  Date: 25.09.2020
  Time: 14:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>DISK</title>
    <%@include file="layouts/links.jsp"%>
</head>
<body>

<%--WORK WITH FOLDERS PATH--%>
<%!
    String upload_path = "";
%>
<%
    out.print("<script>console.log('Current page: "+page.toString()+"')</script>");

    if(request.getParameter("link") == null){
        response.sendRedirect("http://localhost:8080/assignment_war/servlet?link=&show-all=");
    }
    else{
        upload_path = request.getParameter("link").replace("/","\\");
    }
    application.setAttribute("file-upload-path", upload_path);
    application.setAttribute("resend-link", request.getParameter("link"));
%>


    <div class="ui container">
        <%--                Header--%>
        <h1 class="ui header segment 1" style="font-size: 40px"><a href="index.jsp" style="text-decoration: none; color: black">Cloud Storage</a></h1>
        <div class="ui container segment 1">
            <div class="ui container">
                <div class="ui pointing menu">
                    <h1 style="padding: 12px">File manager</h1>
                    <div class="right item">
                        <form action="servlet" method="get" class="ui action input">
                            <input type="text" style="width: 250px !important;" name="search-text" placeholder="File name...">
                            <input type="text" style="display: none" value="<%=upload_path%>" name="search-path">
                            <button class="ui button" style="width: 90px !important;" type="submit">Search</button>
                        </form>
                    </div>
                </div>
                <div class="ui divider"></div>
                <br>

<%--                FILE/FOLDER INFORMATION BLOCK--%>
                <div class="ui container info message" id="edit-field">
                </div>
                <br><br>

<%--                Folder--%>
                <div class="ui grid">
                    <%=request.getAttribute("result")%>
                </div>
                <br><br>
                <div class="ui divider"></div>

<%--                Upload & Create--%>
                <div id="upload">
                    <form action="upload" method="post" enctype="multipart/form-data">
                        <input type="file" name="file" class="ui button"/>
                        <input type="text" name="file-path" style="display: none" value="<%=upload_path%>">
                        <button type="submit" name="upload-file" class="ui button primary">Upload</button>
                    </form>
                </div>
                <div id="folder">
                    <form action="servlet" class="ui form" method="post">
                        <input type="text" name="folder-path" style="display: none" value="<%=upload_path%>">
                        <input type="text" style="width: 300px !important;" name="folder-name" placeholder="Folder Name...">
                        <button class="ui button positive" name="create-folder" type="submit">Create</button>
                    </form>
                </div>
                <div class="ui divider"></div>

                <div class="ui container">
                    <button class="ui positive button" id="create-folder"><i class="plus icon"></i>Create new folder here</button>
                    <button class="ui primary button" id="upload-file"><i class="upload icon"></i>Upload new file here</button>
                </div>
                <br><br><br>
            </div>
        </div>
    </div>
</body>
</html>
