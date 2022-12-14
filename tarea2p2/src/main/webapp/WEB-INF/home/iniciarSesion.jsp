<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page errorPage="/WEB-INF/errorPages/500.jsp" %>
<!DOCTYPE html>
<html lang="zxx">

<head>
    <meta charset="UTF-8">
    <meta name="description" content="turismo.uy">
    <meta name="keywords" content="turismo, uruguay">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>turismo.uy</title>

    <!-- Google Font -->
    <link href="https://fonts.googleapis.com/css2?family=Cairo:wght@200;300;400;600;900&display=swap" rel="stylesheet">

    <!-- Css Styles -->
    <link rel="stylesheet" href="css/bootstrap.min.css" type="text/css">
    <link rel="stylesheet" href="css/font-awesome.min.css" type="text/css">
    <link rel="stylesheet" href="css/elegant-icons.css" type="text/css">
    <link rel="stylesheet" href="css/nice-select.css" type="text/css">
    <link rel="stylesheet" href="css/jquery-ui.min.css" type="text/css">
    <link rel="stylesheet" href="css/owl.carousel.min.css" type="text/css">
    <link rel="stylesheet" href="css/slicknav.min.css" type="text/css">
    <link rel="stylesheet" href="css/style.css" type="text/css">
</head>

<body>
    <!-- Page Preloder -->
    <div id="preloder">
        <div class="loader"></div>
    </div>


    <!-- Header Section Begin -->
    <header class="header">
        <div class="header__top">
        </div>
        <div class="container">
            <div class="humberger__open">
                <i class="fa fa-bars"></i>
            </div>
        </div>
    </header>
    <!-- Header Section End -->

	<section class="vh-100">
  <div class="container-fluid">
    <div class="row" >
      <div class="col-sm-12 text-black">

        <div class="d-flex align-items-center">
			
          <form id="iniSes" style="width: 23rem;" action = "sesionIniciada" method="post">
			<a href="/tarea2p2/home"><img alt="" src="img/logo.png"></a>
			<br>
            <h3 class="fw-normal mb-3 pb-3" style="letter-spacing: 1px;">Inicio de Sesion</h3>
            
            <% String ec = (String) request.getAttribute("error_contrasena"); 
               String een = (String) request.getAttribute("error_emailnick");
               if(een != null && een.equals("error")){%>
               <div class="alert alert-danger" role="alert" style="display: flex;justify-content: space-between;">
				  Email o nickname incorrecto
				  <button id="closei" onclick="this.parentNode.remove(); return false;" >x</button>
				</div>
			<%} else if (ec != null && ec.equals("error")){ %>
				<div class="alert alert-danger" role="alert" style="display: flex;justify-content: space-between;">
				  Contrase??a incorrecta
				  <button id="closei" onclick="this.parentNode.remove(); return false;" >x</button>
				</div>
			<%} %>
		<fieldset>
            <div class="form-outline mb-4">
            	<label class="form-label" for="emailnick_inicioSesion">Email address or nickname</label>
              <input type="text" id="emailnick_inicioSesion" name="emailnick_inicioSesion" class="form-control form-control-lg" required/>
            </div>

            <div class="form-outline mb-4">
              <label class="form-label" for="pass_iniSesion">Password</label>
              <input type="password" id="pass_iniSesion" name="pass_iniSesion" class="form-control form-control-lg" required/>
            </div>
         </fieldset>   
			
			<div class="pt-1 mb-4" >
              <button class="btn btn-info btn-lg btn-block" id="btn-iniS" onclick="inicioSesion();" style="background : #4bb1ff;" type="button">Iniciar Sesion</button>
            </div>            

            <p>No estas registrado? <a href="/tarea2p2/AltaUsuario" class="link-info" style = "color:#4bb1ff">Registrate aqui</a></p>

          </form>

        </div>

      </div>
    </div>
  </div>
</section>

    <!-- Js Plugins -->
    <script src="js/jquery-3.3.1.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/jquery.nice-select.min.js"></script>
    <script src="js/jquery-ui.min.js"></script>
    <script src="js/jquery.slicknav.js"></script>
    <script src="js/mixitup.min.js"></script>
    <script src="js/owl.carousel.min.js"></script>
    <script src="js/main.js"></script>
    <script src="js/jquery.validate.js"></script>
    
	<script> function inicioSesion(){
		$("#iniSes").validate({
			rules:{
				emailnick_inicioSesion: "required",
				pass_iniSesion: "required"
			},
			messages:{
				emailnick_inicioSesion: "Por favor ingrese email o nick",
				pass_iniSesion: "Por favor ingrese contrase??a"
			},
			errorElement: 'span'
		});
		if($("#iniSes").valid()){
			document.getElementById("iniSes").submit();
		}
	}
	</script>

</body>

