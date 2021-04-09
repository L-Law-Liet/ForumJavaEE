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
<% if(request.getAttribute("user") == null) { %>
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
    <div class="card">
        <div class="card-header">
            Quote
        </div>
        <div class="card-body">
            <blockquote class="blockquote mb-0">
                <p>A well-known quote, contained in a blockquote element.</p>
                <footer class="blockquote-footer">Someone famous in <cite title="Source Title">Source Title</cite></footer>
            </blockquote>
        </div>
        <div class="d-flex justify-content-end">
            <div class="m-2">
                <form class="m-2" action="LikePost" method="post">
                    <input hidden name="id" value="1">
                    <button class="btn btn-danger">likes: 1</button>
                </form>
                <form class="m-2" action="DislikePost" method="post">
                    <input hidden name="id" value="1">
                    <button class="btn btn-warning">dislikes: 1</button>
                </form>
            </div>
        </div>
        <div class="card card-body m-3">
            <strong>Naem</strong>
            <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Adipisci amet, at dolore doloribus error illum inventore mollitia temporibus tenetur veritatis! Ad blanditiis enim, ipsum molestiae nisi officiis perspiciatis repellat sequi.</p>
            <div class="d-flex justify-content-end">
                <div>
                    <form class="m-2" action="LikeComment" method="post">
                        <input hidden name="id" value="1">
                        <button class="btn btn-danger">likes: 1</button>
                    </form>
                    <form class="m-2" action="DislikeComment" method="post">
                        <input hidden name="id" value="1">
                        <button class="btn btn-warning">dislikes: 1</button>
                    </form>
                </div>
            </div>
        </div>
        <div class="card card-body m-3">
            <strong>Naem</strong>
            <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Adipisci amet, at dolore doloribus error illum inventore mollitia temporibus tenetur veritatis! Ad blanditiis enim, ipsum molestiae nisi officiis perspiciatis repellat sequi.</p>
            <div class="d-flex justify-content-end">
                <div>
                    <form class="m-2" action="LikeComment" method="post">
                        <input hidden name="id" value="1">
                        <button class="btn btn-danger">likes: 1</button>
                    </form>
                    <form class="m-2" action="DislikeComment" method="post">
                        <input hidden name="id" value="1">
                        <button class="btn btn-warning">dislikes: 1</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
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
        <div class="d-flex justify-content-end">
            <div class="m-2">
                <form class="m-2" action="LikePost" method="post">
                    <input hidden name="id" value="<%post.getId();%>">
                    <button class="btn btn-danger">likes: 1</button>
                </form>
                <form class="m-2" action="DislikePost" method="post">
                    <input hidden name="id" value="<%post.getId();%>">
                    <button class="btn btn-warning">dislikes: 1</button>
                </form>
            </div>
        </div>
        <%
            for (Comment comment : post.getComments()) {%>
        <div class="card card-body m-3">
            <strong><%comment.getUser().getName();%></strong>
            <p><%comment.getBody();%></p>
            <div class="d-flex justify-content-end">
                <div>
                    <form class="m-2" action="LikeComment" method="post">
                        <input hidden name="id" value="<%comment.getId();%>">
                        <button class="btn btn-danger">likes: 1</button>
                    </form>
                    <form class="m-2" action="DislikeComment" method="post">
                        <input hidden name="id" value="<%comment.getId();%>">
                        <button class="btn btn-warning">dislikes: 1</button>
                    </form>
                </div>
            </div>
        </div>
        <%}%>
    </div>
        <%}%>
</div>
<jsp:include page="layout/footer.jsp" />
