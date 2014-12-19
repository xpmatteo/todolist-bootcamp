function substituteContentInList(data) {
    var template = $('#toDoListTpl').html();
    var html = Mustache.to_html(template, data);
    $('#containerList').html(html);
}

function onClickAddToDoSuccess(data) {
    substituteContentInList(data);
    $('#toDoText').val("");
}

function onClickAddToDoError(data) {
    substituteContentInList("rotto tutto... ahi ahi ahi");
}

function onClickAddToDo() {
    console.log("click " + $('#formToDo').serialize());

    $.ajax({
        type: "POST",
        url: "//localhost:8080/addToDo",
        data: $('#formToDo').serialize(),
        success: onClickAddToDoSuccess,
        error: onClickAddToDoError,
    });
    return false;
}

function getDataFromServer(callback) {
    $.getJSON('//localhost:8080/list', callback);
}

