<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ADD NEW MOVIE</title>
          <link href="https://cdn.lineicons.com/4.0/lineicons.css" rel="stylesheet" />
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet"
    integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous"/>
   <link rel="stylesheet" href="leftbar.css">
    <link rel="stylesheet" href="movietable.css">
    </head>
    <body>
        <%
            String errorMessage = request.getAttribute("errorMessage") + "";
            errorMessage = (errorMessage.equals("null")) ? "" : errorMessage;

            String CoverImage = request.getAttribute("CoverImage") + "";
            CoverImage = (CoverImage.equals("null")) ? "" : CoverImage;

            String MovieName = request.getAttribute("MovieName") + "";
            MovieName = (MovieName.equals("null")) ? "" : MovieName;

            String Director = request.getAttribute("Director") + "";
            Director = (Director.equals("null")) ? "" : Director;

            String Actor = request.getAttribute("Actor") + "";
            Actor = (Actor.equals("null")) ? "" : Actor;

            String Summary = request.getAttribute("Summary") + "";
            Summary = (Summary.equals("null")) ? "" : Summary;

            String EffectiveDateFrom = request.getAttribute("EffectiveDateFrom") + "";
            EffectiveDateFrom = (EffectiveDateFrom.equals("null")) ? "" : EffectiveDateFrom;

            String EffectiveDateTo = request.getAttribute("EffectiveDateTo") + "";
            EffectiveDateTo = (EffectiveDateTo.equals("null")) ? "" : EffectiveDateTo;

            String duration = request.getAttribute("duration") + "";
            duration = (duration.equals("null")) ? "" : duration;

            String Trailer = request.getAttribute("Trailer") + "";
            Trailer = (Trailer.equals("null")) ? "" : Trailer;

            String AgeLimit = request.getAttribute("AgeLimit") + "";
            AgeLimit = (AgeLimit.equals("null")) ? "" : AgeLimit;

            String NationalOrigin = request.getAttribute("NationalOrigin") + "";
            NationalOrigin = (NationalOrigin.equals("null")) ? "" : NationalOrigin;

            String Status = request.getAttribute("Status") + "";
            Status = (Status.equals("null")) ? "" : Status;

            String Category = request.getAttribute("Category") + "";
            Category = (Category.equals("null")) ? "" : Category;

            String Language = request.getAttribute("Language") + "";
            Language = (Language.equals("null")) ? "" : Language;
        %>
        <div class="wrapper">     
            <jsp:include page="leftbar.jsp" />
            <div class="main p-3">
                <div class="container">
                    <h1>Thêm Mới Phim</h1>
                    <div class="red" id="errorMessage">
                        <%=errorMessage%>
                    </div>
                    <form action="CreateMovieSevelet" method="GET">
                        <div class="form-group-full">
                            <label for="image">Chọn File Image (*)</label>
                            <input type="file" id="image" name="CoverImage" required="required" value="<%=CoverImage%>">
                        </div>
                        <div class="form-row">
                            <div class="form-group">
                                <label for="movieName">Tên Phim (*)</label>
                                <input type="text" id="movieName" name="MovieName" required="required" value="<%=MovieName%>">
                            </div>
                            <div class="form-group">
                                <label for="director">Nhà Sản Xuất (*)</label>
                                <input type="text" id="director" name="Director" required="required" value="<%=Director%>">
                            </div>
                        </div>
                        <div class="form-row">
                            <div class="form-group">
                                <label for="actor">Diễn Viên (*)</label>
                                <input type="text" id="actor" name="Actor" required="required" value="<%=Actor%>">
                            </div>
                            <div class="form-group">
                                <label for="summary">Summary (*)</label>
                                <input type="text" id="summary" name="Summary" required="required" value="<%=Summary%>">
                            </div>
                        </div>
                        <div class="form-row">
                            <div class="form-group">
                                <label for="effectiveDateFrom">Ngày Khởi Chiếu (*)</label>
                                <input type="date" id="effectiveDateFrom" name="EffectiveDateFrom" required="required" value="<%=EffectiveDateFrom%>">
                            </div>
                            <div class="form-group">
                                <label for="effectiveDateTo">Ngày Kết Thúc (*)</label>
                                <input type="date" id="effectiveDateTo" name="EffectiveDateTo" required="required" value="<%=EffectiveDateTo%>">
                            </div>
                        </div>
                        <div class="form-row">
                            <div class="form-group">
                                <label for="duration">Thời Lượng Phim (*)</label>
                                <input type="number" id="duration" name="duration" required="required" value="<%=duration%>">
                            </div>
                            <div class="form-group">
                                <label for="language">Ngôn Ngữ</label>
                                <input type="text" id="language" name="Language" required="required" value="<%=Language%>">
                            </div>
                        </div>
                        <div class="form-row">
                            <div class="form-group">
                                <label for="trailer">Trailer</label>
                                <input type="url" id="trailer" name="Trailer" required="required" value="<%=Trailer%>">
                            </div>
                            <div class="form-group">
                                <label for="ageLimit">Giới Hạn Tuổi</label>
                                <input type="number" id="ageLimit" name="AgeLimit" required="required" value="<%=AgeLimit%>">
                            </div>
                        </div>
                        <div class="form-row">
                            <div class="form-group">
                                <label for="nationalOrigin">Quốc Gia</label>
                                <input type="text" id="nationalOrigin" name="NationalOrigin" required="required" value="<%=NationalOrigin%>">
                            </div>
                            <div class="form-group">
                                <label for="Status" class="form-label">Online Moives</label> <input type="checkbox" class="form-check-input"
                                                                                                    id="Status" name="Status">
                                </select>
                            </div>
                        </div>
                        <div class="checkbox-group">
                            <label><input type="checkbox" name="Category" value="Action"> Action</label>
                            <label><input type="checkbox" name="Category" value="Comedy"> Comedy</label>
                            <label><input type="checkbox" name="Category" value="Drama"> Drama</label>
                            <label><input type="checkbox" name="Category" value="Fantasy"> Fantasy</label>
                            <label><input type="checkbox" name="Category" value="Horror"> Horror</label>
                            <label><input type="checkbox" name="Category" value="Romance"> Romance</label>
                            <label><input type="checkbox" name="Category" value="Sci-Fi"> Sci-Fi</label>
                            <label><input type="checkbox" name="Category" value="Thriller"> Thriller</label>
                        </div>
                         <input class="btn btn-primary form-control" type="submit" 
                               value="Create" name="submit" id="submit" />
                    </form>
                </div>
            </div>
        </div>
                            <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.min.js"></script>
    <script src="admin.js"></script>
    </body>
</html>
