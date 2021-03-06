<%@ page import="com.weblibrary.entity.Genre" %>
<%@ page import="com.weblibrary.entity.Book" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="../css/mainStyle.css" rel="stylesheet" type="text/css">
    <link href="../css/viewStyle.css" rel = stylesheet type = "text/css">
    <title><%=request.getAttribute("msg")%></title>
</head>
<body>
    <div id = "header">
        <%=request.getAttribute("msg")%>
    </div>

    <div id = view>
        <div id = bookCard>
            <div class = block>
                <div class = ttl>title</div>
                <div class = data>
                    <div class = dataField>
                        ${book.title}
                    </div>
                </div>
            </div>
            <div class = block>
                <div class = ttl>author</div>
                <div class = data>
                    <div class = dataField>
                        ${book.author}
                    </div>
                </div>
            </div>
            <div class = block>
                <div class = ttl>ISBN</div>
                <div class = data>
                    <div class = dataField>
                        ${book.isbn}
                    </div>
                </div>
            </div>
            <div class = block>
                <div class = ttl>year</div>
                <div class = data>
                    <div class = dataField>
                        ${book.year}
                    </div>
                </div>
            </div>
            <%
                Book book = (Book) request.getAttribute("book");
                System.out.println(book.toString());
                List<Genre> genres = book.getGenres();
                for(Genre genre:genres) {
            %>
            <div class = block>
                <div class = ttl>genre</div>
                <div class = data>
                    <div class = dataField>
                        <%=genre.getGenre()%>
                    </div>
                </div>
            </div>

            <%}%>
        </div>

    </div>

</body>
</html>
