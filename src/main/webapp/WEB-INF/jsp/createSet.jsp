<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Create Set</title>
    <link rel="stylesheet" href="/style.css">
    <link rel="stylesheet" href="/index.css">
    <link rel="stylesheet" href="/grid.css">
</head>
<body>

<div class="wrapper">
    <h2>Create Set</h2>

    <form action="/sets/create" method="post">
        <input class="dhx_sample-input set_input" type="text" name="title" placeholder="Title">

        <select class="pac_select dhx_select dhx_sample-input" multiple name="idPacs">
            <c:forEach items="${pacs}" var="pac">
                <option value="${pac.id_pac}">${pac.title}</option>
            </c:forEach>
        </select>

        <section class="dhx_sample-controls">
            <button class="dhx_sample-btn dhx_sample-btn--flat pac_create_btn"><a href="javascript:history.back()">Back</a></button>
            <input class="dhx_sample-btn dhx_sample-btn--flat pac_create_btn" type="submit" value="Create">
        </section>

    </form>
</div>

</body>
</html>
