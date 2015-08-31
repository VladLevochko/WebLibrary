<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href = "../css/mainStyle.css" rel = stylesheet type="text/css">
    <link href = "../css/updateStyle.css" rel = stylesheet type="text/css">
    <title>Book for update</title>
</head>
<body>

  <div id = "header">
    Book for update
  </div>

  <div id = "formField">
    <div id = "updateForm" class = "form">
      <form method="post" action = "update">

        <div class = "up">
          <div class = inputBlock>
            <label for = "title">Title</label><br>
            <input type = text id = "title" name = "title" value = "${book.title}" class = textinput>
          </div>

          <div class = inputBlock>
            <label for = "author">Author</label><br>
            <input type = text id = "author" name = "author" value = "${book.author}" class = textinput>
          </div>
        </div>

        <div class = "down">
          <div class = inputBlock>
            <label for = "isbn">ISBN</label><br>
            <input type = text id = "isbn" value = "${book.isbn}" disabled>
            <input type = hidden name = "isbn" value = "${book.isbn}" class = textinput>
          </div>

          <div class = inputBlock>
            <label for = "year">Year</label><br>
            <input type = text id = "year" name = "year" value = "${book.year}" class = textinput>
          </div>
        </div>

        <div class = "down">
          <label>Genres</label><br>
          <div class = inputBlock>
            <select name = genre1>
              <option value=""></option>
              <option value = Horror>Horror</option>
              <option value = Fantasy>Fantasy</option>
              <option value = Travel>Travel</option>
              <option value = Autobiographies>Autobiographies</option>
            </select>
          </div>

          <div class = inputBlock>
            <select name = genre2>
              <option value=""></option>
              <option value = Horror>Horror</option>
              <option value = Fantasy>Fantasy</option>
              <option value = Travel>Travel</option>
              <option value = Autobiographies>Autobiographies</option>
            </select>
          </div>

          <div class = inputBlock>
            <select name = genre3>
              <option value=""></option>
              <option value = Horror>Horror</option>
              <option value = Fantasy>Fantasy</option>
              <option value = Travel>Travel</option>
              <option value = Autobiographies>Autobiographies</option>
            </select>
          </div>
        </div>

        <button type = submit class = "submitButton">update</button>
      </form>

      <form action =../admin.html>
        <button type = submit class = submitButton>return</button>
      </form>
    </div>
  </div>
</body>
</html>
