$(function() {

    getDataFromServer(function(data) {
        substituteContentInList(data);
        $('.customCheck').on('click', checkboxStatus );

    });

    $("#addToDo").click(onClickAddToDo);
    $("#formToDo").submit(onClickAddToDo);

});