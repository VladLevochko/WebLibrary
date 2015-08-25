$(document).ready(function(){
    $(".showLeft").click(function(){
        $("#selectForm").toggle( "slide", { direction: "right"  }, 1000);
        $("#leftForm").toggle( "slide", { direction: "left"  }, 1000);

    });

    $(".showRight").click(function(){
        $("#selectForm").toggle( "slide", { direction: "left"  }, 1000);
        $("#rightForm").toggle( "slide", { direction: "right"  }, 1000);
    });

    $("#findAll").click(function(){

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

        var data = $("#allBookForm").serializeObject();

        $.ajax({
            type: "POST",
            url: "findAll",
            data: {bookData: JSON.stringify(data)},
            dataType: "json",
            success: function(data){
                var div = $("#leftForm").append("<div class = bookBlock><ul></ul></div>");
                $.each(data, function(i, item){
                    console.log(item);
                    $(".bookBlock:last-child ul").append("<li>"+ item +"</li>");
                })
            },
            error: function(xhr, status){
                alert(status);
            }
        });
    });

    var url = "genres.json";

    $.getJSON(url, function(genres){
        var $select = $("select");
        var g = genres.genre;
        $.each(g, function(i, item){
            $select.append("<option value='"+item+"'>" + item)
        })
    })
});