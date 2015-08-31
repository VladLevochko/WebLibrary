/**
 * Created by vlad on 30.08.15.
 */
$(document).ready(function(){
    $("#tabs").tabs();
});

$.fn.serializeObject = function () {
    var o = {};
    var a = this.serializeArray();
    $.each(a, function () {
        if (o[this.name] !== undefined) {
            if (!o[this.name].push) {
                o[this.name] = [o[this.name]];
            }
            o[this.name].push(this.value || '');
        } else {
            o[this.name] = this.value || '';
        }
    });
    return o;
};

function processData(form, action){
    $("button").prop('disabled', true);
    var data = $(form).serializeObject();
    $("input").val("");

    $.ajax({
        type: "POST",
        url: action,
        data: {'input data': JSON.stringify(data)},
        dataType: "json",
        success: function(data){
            var into = form + "Form .viewBlock";
            $("button").prop("disabled", false);
            switch (form){
                case "#add":
                    showData(into, data);
                    break;
                case "#find":
                    showData(into, data);
                    break;
                case "#update":
                    showChangeableData(into, data);
                    $(into + " button").remove();
                    break;
                case "#delete":
                    showData(into, data);
                    $(into + " button").remove();
                    $(into).append("<button class = submitButton onclick = 'deleteBook()'>delete</button>");
                    break;
            }
        },
        error: function(xhr, status){
            $("button").prop("disabled", false);
            alert(status);
        }
    })
}

function showData(into, data){
    $(into + " ul").remove();
    $(into + " p").remove();
    $(into).append('<ul></ul>');

    var div = $(into + " ul");
    div.append("<li>"+ data.title +"</li>");
    div.append("<li>"+ data.author +"</li>");
    div.append("<li>"+ data.year +"</li>");
    $.each(data.genres, function(i, item){
        div.append("<li>" + item.genre + "</li>");
    })
}

function showChangeableData(into, data){
    $(into + " form").remove();
    $(into + " ul").remove();
    $(into + " p").remove();
    $(into).append("<form></form>");

    console.log(data);

    var div = $(into + " form");
    div.append("<label for = title>Title</label><br>");
    div.append("<input type = text name = title value=" + data.title + " id = title><br>");
    div.append("<label for = author>Author</label><br>");
    div.append("<input type = text name = author value=" + data.author + " id = author><br>");
    div.append("<label for = year>Year</label><br>");
    div.append("<input type = text name = year value=" + data.year + " id = year><br>");
    div.append("<label for = isbn>ISBN</label><br>");
    div.append("<input type = text name = isbn value=" + data.isbn + " id = isbn disabled><br>");
    $.each(data.genres, function(i, item){
        div.append("<input type = hidden name = 'genre" + (i + 1) + "' value=" + item.genre + ">");
    });
    div.append("<input type = button class = 'submitButton' onclick='updateBook()' value = 'update'>");
}

function deleteBook(){
    var data = $("#delete").serializeObject();
    var into = $("#deleteForm .viewBlock");
    $("button").prop('disabled', true);

    $.ajax({
        type: "POST",
        url: "delete",
        data: {'input data': JSON.stringify(data)},
        dataType: "json",
        success: function(){
            $(into + " ul").remove();
            $(into).append("<p>Book deleted!</p>")
        },
        error: function(){
            $(into + " ul").remove();
            $(into).append("<p>Error!</p>")
        }
    });

    $("button").prop('disabled', false);
}

function updateBook(){
    var data = $("#updateForm .viewBlock form").serializeObject();
    $("button").prop('disabled', true);

    $.ajax({
        type: "POST",
        url: "update",
        data: {'input data': JSON.stringify(data)},
        dataType: "json",
        success: function(data){
            showData("#updateForm .viewBlock", data);
        },
        error: function(xhr, status){
            alert(status);
        }
    });

    $("button").prop('disabled', false);
}


