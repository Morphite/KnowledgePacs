<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Sets</title>

    <link rel="stylesheet" href="style.css">
    <link rel="stylesheet" href="index.css">
    <link rel="stylesheet" href="grid.css">
    <script type="text/javascript" src="grid.js"></script>

</head>

<body>

<div id="container">

    <section class="dhx_sample-controls">
        <button class="dhx_sample-btn dhx_sample-btn--flat"><a href="/">Back</a></button>
    </section>

    <section class="dhx_sample-controls">
        <div class="dhx_form-group dhx_form-group--inline dhx_form-group--no-message-holder" id="form-select">
            <label for="columns" class="dhx_sample-label">Select column:</label>
            <select name="columns" class="dhx_select dhx_sample-input" id="columns">
                <option value="id_set" selected>Id</option>
                <option value="title">Title</option>
            </select>
        </div>
        <div class="dhx_form-group dhx_form-group--inline dhx_form-group--no-message-holder">
            <label class="dhx_demo-switch__label dhx_label" for="filterInput">Value: </label>
            <input id="filterInput" class="dhx_sample-input" oninput="filter(this.value)" type="text"/>
        </div>
    </section>

    <div id="grid_container"></div>

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
</div>

<script>
    var grid = new dhx.Grid("grid_container", {
        columns: [
            {id: "id_set", header: [{text: "#", align: "center"}], align: "center"},
            {id: "title", header: [{text: "Title", align: "center"}], align: "center"},
            {type: "link", id: "delete", header: [{text: "Delete", align: "center"}], htmlEnable: true, align: "center"}
        ],
        autoWidth: true
    });

    grid.data.parse(${sets});

    grid.events.on("CellDblClick", doOnRowDblClicked);

    function doOnRowDblClicked(row) {
        window.open("/set/" + row.id_set);
        return false;
    }

    function filter(val) {
        if (!val) {
            grid.data.filter();
        } else {
            var column = document.getElementById("columns").value;
            grid.data.filter({
                by: column,
                match: val,
                compare: function (val, match) {
                    return new RegExp(match, "i").test(val)
                }
            });
        }
    }

    function selectChange() {
        document.querySelector("#filterInput").value = "";
        grid.data.filter();
    }

</script>

</body>
</html>