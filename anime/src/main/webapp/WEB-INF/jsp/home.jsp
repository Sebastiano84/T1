<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
  <head>
    <title>Bootstrap 101 Template</title>
    <sec:csrfMetaTags />
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- Bootstrap -->
     <link href="/webjars/bootstrap/3.3.7-1/css/bootstrap.min.css" rel="stylesheet">
  </head>
  <body>
    <div class="container-fluid">
		<sec:authorize access="hasRole('supervisor')">
		<div class="row">
		This content will only be visible to users who have
		the "supervisor" authority in their list of <tt>GrantedAuthority</tt>s.
		</div>
		</sec:authorize>
	</div>
    <div class="navbar navbar-inverse navbar-static-top">
      <div class="navbar-inner">
        <div class="container-fluid">
          <p class="navbar-text pull-left">
          Project name
          </p>
          <p class="navbar-text pull-right">
            Logged in as <a href="#" class="navbar-link"><sec:authentication property="principal.username"/></a>
          </p>
          <p class="navbar-text pull-right">
          	<a href="<c:url value="j_spring_security_logout" />  Logout</a>
          </p>
        </div>
      </div>
    </div>
    <div class="container-fluid">
    	<sec:authorize access="hasRole('supervisor')">
    	<div class="row">
    	This content will only be visible to users who have
    	the "supervisor" authority in their list of <tt>GrantedAuthority</tt>s.
    	</div>
    	</sec:authorize>
    	<div class="row">
    	    <div class="col-sm-2">
    	    </div>
    	    <div class="col-sm-10">
    	        <form:form action="submitCode" method="POST" modelAttribute="homeForm">
    	          <div class="form-group">
    	             <form:errors />
    	           </div>
                  <div class="form-group">
                    <label for="exampleInputEmail1">Email address</label>
                    <form:textarea path="animeCode" class="form-control" rows="3" cols="30" />
                  </div>
                  <button type="submit" class="btn btn-default">Submit</button>
                  <div class="form-group">
                    <div id="animeCodeValidation"></div>
                  </div>
                  <div class="form-group">
                    <label for="exampleInputPassword1">Password</label>
                    <input type="password" class="form-control" id="exampleInputPassword1" placeholder="Password">
                  </div>
                  <div class="form-group">
                    <label for="exampleInputFile">File input</label>
                    <input type="file" id="exampleInputFile">
                    <p class="help-block">Example block-level help text here.</p>
                  </div>
                  <div class="checkbox">
                    <label>
                      <input type="checkbox"> Check me out
                    </label>
                  </div>
		          <sec:csrfInput />
                </form:form>
            </div>
    	</div>
    </div>
    <!-- jQuery -->
    <script src="/webjars/jquery/2.1.1/jquery.min.js"></script>

    <!-- Json2html -->
    <script src="jquery/json2html.js"></script>
    <script src="jquery/jquery.json2html.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="/webjars/bootstrap/3.3.7-1/js/bootstrap.min.js"></script>
    <script src="custom/custom.js"></script>
  </body>
</html>
