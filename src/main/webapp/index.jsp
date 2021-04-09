<%@ page import="kz.sitedev.Forum.models.User" %>
<%@ page import="java.util.List" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%@ page import="kz.sitedev.Forum.models.Post" %>
<%@ page import="kz.sitedev.Forum.models.Comment" %>
<%--Including--%>
<jsp:include page="layout/header.jsp" />
<%! User user = null; %>
<%--Implicit Object Session--%>
<% user = (User) session.getAttribute("user"); %>
<div class="btn btn-dark w-100 mb-3">
    <h1>Welcome to Forum</h1>
</div>
<% if(request.getAttribute("user") != null) { %>
<div class="my-5">
    <form action="CreatePost" method="post" class="card card-body">
        <div class="form-group">
            <label>Title</label>
            <input  name="title" type="text" class="form-control">
        </div>
        <div class="form-group">
            <label>Body</label>
            <textarea name="body" class="form-control" rows="3"></textarea>
        </div>
        <div class="form-group">
            <button class="btn btn-primary" type="submit">Save</button>
        </div>
    </form>
</div>
<%}%>
<div class="my-5">
    <%
        for (Post post: (List<Post>) request.getAttribute("posts")){%>
    <div class="card">
        <div class="card-header">
            <%post.getTitle();%>
        </div>
        <div class="card-body">
            <blockquote class="blockquote mb-0">
                <p>
                    <%post.getBody();%>
                </p>
                <footer class="blockquote-footer">
                    <%post.getUser().getName();%>
                </footer>
            </blockquote>
        </div>
        <div class="d-flex justify-content-between">
            <div class="m-2">
                <%if (post.getUser().getId() == user.getId()){%>
                <form action="UpdatePost">
                    <input hidden name="id" value="<%post.getId();%>">
                    <button class="btn btn-primary">Update</button>
                </form>
                <%}%>
            </div>
            <div class="m-2">
                <form class="m-2" action="LikePost" method="post">
                    <input hidden name="id" value="<%post.getId();%>">
                    <button class="btn btn-danger">likes: <%post.getLikes();%></button>
                </form>
                <form class="m-2" action="DislikePost" method="post">
                    <input hidden name="id" value="<%post.getId();%>">
                    <button class="btn btn-warning">dislikes: <%post.getDislikes();%></button>
                </form>
            </div>
        </div>

        <% if(request.getAttribute("user") != null) { %>
        <div class="m-3">
            <form action="CreateComment" method="post" class="card card-body">
                <div class="form-group">
                    <label>Body</label>
                    <textarea name="body" class="form-control" rows="3"></textarea>
                </div>
                <input hidden name="post_id" value="<%post.getId();%>">
                <div class="form-group">
                    <button class="btn btn-primary" type="submit">Save</button>
                </div>
            </form>
        </div>
        <%}%>

        <%
            for (Comment comment : post.getComments()) {%>
        <div class="card card-body m-3">
            <strong><%comment.getUser().getName();%></strong>
            <p><%comment.getBody();%></p>
            <div class="d-flex justify-content-between">
                <div class="m-2">
                    <%if (comment.getUser().getId() == user.getId()){%>
                    <form action="UpdateComment">
                        <input hidden name="id" value="<%comment.getId();%>">
                        <button class="btn btn-primary">Update</button>
                    </form>
                    <%}%>
                </div>
                <div>
                    <form class="m-2" action="LikeComment" method="post">
                        <input hidden name="id" value="<%comment.getId();%>">
                        <button class="btn btn-danger">likes: <%comment.getLikes();%></button>
                    </form>
                    <form class="m-2" action="DislikeComment" method="post">
                        <input hidden name="id" value="<%comment.getId();%>">
                        <button class="btn btn-warning">dislikes: <%comment.getDislikes();%></button>
                    </form>
                </div>
            </div>
        </div>
        <%}%>
    </div>
        <%}%>
</div>
<jsp:include page="layout/footer.jsp" />
