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
    var data = $(form).serializeObject();

    $.ajax({
        type: "POST",
        url: action,
        data: {data: JSON.stringify(data)},
        dataType: "json",
        success: function(data){
            var into = form + "Form .viewBlock";
            $(into + "<ul></ul>").remove();

            switch (form){
                case "#add":
                    showData(into, data);
                    break;
                case "#find":
                    showData(into, data);
                    break;
                case "#update":
                    showChangeableData(into, data);
                    $(into).append("<button type = button class = submitButton onclick = updateBook()>delete</button>");
                    break;
                case "#delete":
                    showData(into, data);
                    $(into).append("<button type = button class = submitButton onclick = deleteBook()>delete</button>");
                    break;
            }
        },
        error: function(xhr, status){
            alert(status);
        }
    })
}

function showData(into, data){
    var div = $(into).append('<ul></ul>');
    div.append("<li>"+ data.title +"</li>");
    div.append("<li>"+ data.author +"</li>");
    div.append("<li>"+ data.year +"</li>");
    $.each(data.genres, function(i, item){
        console.log(item);
        div.append("<li>" + item.genre + "</li>");
    })
}

function showChangeableData(into, data){
    var div = $(into).append(<form></form>);
    div.append("<label for = title>Title</label>");
    div.append("<input type = text name = title value=" + data.title + " id = title>");
    div.append("<label for = author>Author</label>");
    div.append("<input type = text name = author value=" + data.author + " id = author>");
    div.append("<label for = year>Year</label>");
    div.append("<input type = text name = year value=" + data.year + " id = year>");
    div.append("<label for = isbn>ISBN</label>");
    div.append("<input type = text name = isbn value=" + data.isbn + " id = isbn disabled>");

    div.append("<button class = submitButton>update</button>");
}

