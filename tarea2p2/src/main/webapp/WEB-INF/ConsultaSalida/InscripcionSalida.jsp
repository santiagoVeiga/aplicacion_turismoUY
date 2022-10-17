<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="logica.DataUsuario,logica.DataTurista,logica.DataProveedor,logica.DataSalida,java.util.Set,logica.DataDepartamento" %>


<!DOCTYPE html>
<html lang="zxx">

<head>
    <meta charset="UTF-8">
    <meta name="description" content="turismo.uy">
    <meta name="keywords" content="turismo, uruguay">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Inscripcion a Salida</title>

    <!-- Google Font -->
    <link href="https://fonts.googleapis.com/css2?family=Cairo:wght@200;300;400;600;900&display=swap" rel="stylesheet">

	
    <!-- Css Styles -->
    <link rel="stylesheet" href="../css/bootstrap.min.css" type="text/css">
    <link rel="stylesheet" href="../css/font-awesome.min.css" type="text/css">
    <link rel="stylesheet" href="../css/elegant-icons.css" type="text/css">
    <link rel="stylesheet" href="../css/nice-select.css" type="text/css">
    <link rel="stylesheet" href="../css/jquery-ui.min.css" type="text/css">
    <link rel="stylesheet" href="../css/owl.carousel.min.css" type="text/css">
    <link rel="stylesheet" href="../css/slicknav.min.css" type="text/css">
    <link rel="stylesheet" href="../css/style.css" type="text/css">
    
</head>

<body>
    <!-- Page Preloder -->
    <div id="preloder">
        <div class="loader"></div>
    </div>

    <!-- Header Section Begin -->
    <header class="header">
        
        <div class="container">
            <div class="row">
                <div class="col-lg-3">
                    <div class="header__logo">
                        
                    </div>
                </div>
                <div class="col-lg-7">
                    <div class="hero__search">
                        
                    </div>
                </div>
                <div class="col-lg-2">
                    <div class="row float-right">
                        <div class="header__top__right__Usu" style="cursor: pointer;" onclick="window.location='./ConsultaUsuarioT.html';">
                            <span><a href="./ConsultaUsuarioT.html"><img src="https://pbs.twimg.com/media/EOHAP9zWoAsnkiM?format=jpg&name=small"> &nbsp; Rosa Maria Martinez</a></span>
                        </div>
                    </div>
                    <div class="row float-right">
                    	<div class="header__top__right__csesion ">
                           	<a href="./logout.html"> Cerrar Sesion</a>
                        </div>
                    </div>
                </div>
            </div>
            <div class="humberger__open">
                <i class="fa fa-bars"></i>
            </div>
        </div>
    </header>
    <!-- Header Section End -->

    

<section class="hero">
	<div class="row content-justify-center">
		<div class="col-lg-12" style:"border-radius: 25px; padding: 20px; padding-top:30px">
                    <div class="container">
                    	<div class="row justify-content-md-center">
                          <div class="col-lg-6">
               				<a href="./index_turista.html"><img alt="" src="../img/logo.png"></a>
                          </div>
                        </div>
                        <div class="text-center p-4">
                          <h2>Inscripción a Salida Turística</h2>
                          <p class="lead">Ingrese los datos de la Inscripción</p>
                        </div>
                        <div class="row justify-content-md-center">
                          <div class="col-md-8 order-md-1">
                            <form class="needs-validation" id="sell-info">

                                <!-- Nombre y Departamento -->                                
                                <div class="row">
                                    <!-- Cantidad de turistas -->
                                    <div class="col-md-6 mb-3">
                                        <label for="cantTur">Cantidad de Turistas</label>
                                        <input type="number" class="form-control" id="cantTur" required placeholder="Ingrese un Valor" min="1" >
                                    </div>
                                </div>
                              
                                <div class="row">
                                    <div class="col-md-12 mb-3">
                                        			<nav>
                            <div class="nav nav-tabs nav-fill" id="nav-tab" role="tablist">
                                <a class="nav-item nav-link active" id="nav-home-tab" data-toggle="tab" href="#nav-home" role="tab" aria-controls="nav-home" aria-selected="true">General</a>
                                <a class="nav-item nav-link" id="nav-profile-tab" data-toggle="tab" href="#nav-profile" role="tab" aria-controls="nav-profile" aria-selected="false">Por Paquete</a>
                            </div>
                        </nav>
                        <div class="tab-content" id="nav-tabContent">
                            <div class="tab-pane fade show active" id="nav-home" role="tabpanel" aria-labelledby="nav-home-tab">
                                <div class="form-outline mb-4">
					            </div>
					            <div class="pt-1 mb-4" >
					              <a href="./index_turista.html"> <button class="btn btn-info btn-lg btn-block" id="btn-tur-reg" style="background : #4bb1ff;" type="button">Inscribirse</button> </a>
					            </div>   
                            </div>
                            <div class="tab-pane fade" id="nav-profile" role="tabpanel" aria-labelledby="nav-profile-tab">
                                <div class="col-md-6 mb-4">
                                    <label for="paqDisp">Paquetes Disponibles</label>
                                    <select id="paqDisp" required>
                                        <option selected>Disfrutar Rocha</option>
                                        <option> Un día en Colonia</option>
                                    </select>
                                    <span></span><br />
                                </div>
					            <div class="pt-1 mb-4" >
					              <a href="./index_turista.html"> <button class="btn btn-info btn-lg btn-block" id="btn-prov-reg" style="background : #4bb1ff;" type="button">Inscribirse</button> </a>
					            </div>   
                            </div>
                            
                        </div>      
                                    </div>
                                </div>
                            </form>
                          </div>
                        </div>
                    </div>
                </div>
	</div>	
</section>

    <!-- Js Plugins -->
    <script src="../js/jquery-3.3.1.min.js"></script>
    <script src="../js/bootstrap.min.js"></script>
    <script src="../js/jquery.nice-select.min.js"></script>
    <script src="../js/jquery-ui.min.js"></script>
    <script src="../js/jquery.slicknav.js"></script>
    <script src="../js/mixitup.min.js"></script>
    <script src="../js/owl.carousel.min.js"></script>
    <script src="../js/main.js"></script>
    



</body>

</html>
