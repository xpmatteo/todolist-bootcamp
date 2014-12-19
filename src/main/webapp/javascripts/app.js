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
    if ($('#formToDo').val() != "") {
        $.ajax({
            type: "POST",
            url: "//localhost:8080/todolists/1",
            data: $('#formToDo').serialize(),
            success: onClickAddToDoSuccess,
            error: onClickAddToDoError
        });
        return false;
    }else{
        alert("Inserire il toDo");
    }
}

function getDataFromServer(callback) {
    $.getJSON('//localhost:8080/todolists/1', callback);
}

function checkboxStatus() {
    if($(this).prop("checked") == false){
        $(this).next().css("text-decoration","");
    }else{
        $(this).next().css("text-decoration","line-through");
    }
}