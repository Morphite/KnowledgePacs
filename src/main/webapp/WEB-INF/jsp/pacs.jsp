<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Pacs</title>

    <link rel="stylesheet" href="style.css">
    <link rel="stylesheet" href="index.css">
    <link rel="stylesheet" href="grid.css">
    <script type="text/javascript" src="grid.js"></script>
</head>
<body>

<div id="container">

    <section class="dhx_sample-controls">
        <button class="dhx_sample-btn dhx_sample-btn--flat pac_create_btn"><a href="javascript:history.back()">Back</a>
        </button>
    </section>

    <section class="dhx_sample-controls">
        <div class="dhx_form-group dhx_form-group--inline dhx_form-group--no-message-holder" id="form-select">
            <label for="columns" class="dhx_sample-label">Select column:</label>
            <select name="columns" class="dhx_select dhx_sample-input" id="columns">
                <option value="id_pac" selected>Id</option>
                <option value="title">Title</option>
                <option value="desc">Description</option>
                <option value="date">Date</option>
            </select>
        </div>
        <div class="dhx_form-group dhx_form-group--inline dhx_form-group--no-message-holder">
            <label class="dhx_demo-switch__label dhx_label" for="filterInput">Value: </label>
            <input id="filterInput" class="dhx_sample-input" oninput="filter(this.value)" type="text"/>
        </div>
    </section>

    <div id="grid_container"></div>


    <div class="wrapper">

        <h2>Create KPac</h2>

        <form action="/kpacs/create" method="post">
            <input class="dhx_sample-input" type="text" name="title" placeholder="Title">
            <input class="dhx_sample-input" type="text" name="description" placeholder="Description">
            <input class="dhx_sample-input creation_date" type="date" name="creation_date" min="01-01-2000"
                   max="${default_date}" value="${default_date}" placeholder="DD/MM/YYYY">

            <section class="dhx_sample-controls">
                <input class="dhx_sample-btn dhx_sample-btn--flat pac_create" type="submit" value="Create">
            </section>

        </form>
    </div>

</div>
</body>

<script>
    var grid = new dhx.Grid("grid_container", {
        columns: [
            {id: "id_pac", header: [{text: "#", align: "center"}], align: "center"},
            {id: "title", header: [{text: "Title", align: "center"}], align: "center"},
            {id: "desc", header: [{text: "Desc.", align: "center"}], align: "center"},
            {id: "date", header: [{text: "Date", align: "center"}], align: "center"},
            {type: "link", id: "delete", header: [{text: "Delete", align: "center"}], htmlEnable: true, align: "center"}
        ],
        autoWidth: true
    });

    grid.data.parse(${pacs});

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
</html>