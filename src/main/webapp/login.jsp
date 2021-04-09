<%@ page import="java.util.List" %>
<jsp:include page="layout/header.jsp"/>
<div class="btn btn-dark w-100 mb-3">
    <h1>Login</h1>
</div>
<%
    if(request.getAttribute("errors") != null) {
%>
<div class="alert-danger">
    <ul class="m-3 p-3">
        <%
            for (String error: (List<String>)request.getAttribute("errors")){
        %>
        <li>
            <%=error%>
        </li>
        <%
            }
        %>
    </ul>
</div>
<%
    }
%>
<form action="Login" method="post">
    <div class="mb-3">
        <label class="form-label">Email</label>
        <input name="email" type="email" class="form-control" value="<%= request.getParameter("email") != null ? request.getParameter("email") : "" %>">
    </div>
    <div class="mb-3">
        <label class="form-label">Password</label>
        <input name="password" type="password" class="form-control">
    </div>
    <div class="d-flex mb-3">
        <a class="w-100 text-center" href="Register">Don't have account?</a>
    </div>
    <a href="Hello"
       class="btn btn-secondary">Back</a>
    <button type="submit" class="btn btn-primary">Submit</button>
</form>
<jsp:include page="layout/footer.jsp"/>
