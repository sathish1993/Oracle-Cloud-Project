<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
       <!-- <link rel="stylesheet" href="../css/bootstrap.css"/>          -->
       <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
       <!-- <script src="../js/bootstrap.js"></script>    -->
    </head>
    <body>
        <div class="container">
            <form action="/users-app/user" method="post"  role="form" data-toggle="validator" >
                <c:if test ="${empty action}">                          
                    <c:set var="action" value="add"/>
                </c:if>
                <input type="hidden" id="action" name="action" value="${action}"/>
                <input type="hidden" id="idUser" name="idUser" value="${user.id}"/>
                <h2>User</h2>
                <div class="form-group col-xs-4">
                    <label for="name" class="control-label col-xs-4">Name:</label>
                    <input type="text" name="name" id="name" class="form-control" value="${user.name}" 
                        required="true"/>                                   

                    <label for="lastName" class="control-label col-xs-4">Last name:</label>                   
                    <input type="text" name="lastName" id="lastName" class="form-control" value="${user.lastName}" 
                        required="true"/> 

                    <label for="birthdate" class="control-label col-xs-4">Birth date</label>                 
                    <input type="text"  pattern="^\d{4}-\d{2}-\d{2}$" name="birthDate" id="birthdate" class="form-control" 
                        value="${user.birthDate}" maxlength="10" placeholder="yyyy-MM-dd" required="true"/>

                    <label for="role" class="control-label col-xs-4">Role:</label>                    
                    <input type="text" name="role" id="role" class="form-control" value="${user.role}" 
                        required="true"/> 

                    <label for="department" class="control-label col-xs-4">Department:</label>
                    <input type="text" name="department" id="department" class="form-control" 
                        value="${user.department}" required="true"/>

                    <label for="department" class="control-label col-xs-4">E-mail:</label>                   
                    <input type="text" name="email" id="email" class="form-control" value="${user.email}" 
                        placeholder="smith@xyz.com" required="true"/>

                    <label for="department" class="control-label col-xs-4">Photo:</label>                   
                    <input type="file" name="image" id="image" class="form-control" value="${user.image}" 
                        placeholder="Select your profile photo" required="true"/>

                    <br></br>
                    <button type="submit" class="btn btn-primary  btn-md">Accept</button> 
                </div>                                                      
            </form>
        </div>
    </body>
</html>