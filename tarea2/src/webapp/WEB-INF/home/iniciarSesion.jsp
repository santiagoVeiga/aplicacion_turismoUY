<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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

    <!-- Humberger Begin -->
    <div class="humberger__menu__overlay"></div>
    <div class="humberger__menu__wrapper">
        <div class="humberger__menu__logo">
            <a href="#"><img src="../img/logo.png" alt=""></a>
        </div>
        <div class="humberger__menu__cart">
            <ul>
                <li><a href="#"><i class="fa fa-heart"></i> <span>1</span></a></li>
                <li><a href="#"><i class="fa fa-shopping-bag"></i> <span>3</span></a></li>
            </ul>
            <div class="header__cart__price">item: <span>$150.00</span></div>
        </div>
        <div class="humberger__menu__widget">
            <div class="header__top__right__language">
                <img src="../img/language.png" alt="">
                <div>English</div>
                <span class="arrow_carrot-down"></span>
                <ul>
                    <li><a href="#">Spanish</a></li>
                    <li><a href="#">English</a></li>
                </ul>
            </div>
            <div class="header__top__right__auth">
                <a href="#"><i class="fa fa-user"></i> Login</a>
            </div>
        </div>
        <nav class="humberger__menu__nav mobile-menu">
            <ul>
                <li class="active"><a href="./index.html">Home</a></li>
                <li><a href="./shop-grid.html">Shop</a></li>
                <li><a href="#">Pages</a>
                    <ul class="header__menu__dropdown">
                        <li><a href="./shop-details.html">Shop Details</a></li>
                        <li><a href="./shoping-cart.html">Shoping Cart</a></li>
                        <li><a href="./checkout.html">Check Out</a></li>
                        <li><a href="./blog-details.html">Blog Details</a></li>
                    </ul>
                </li>
                <li><a href="./blog.html">Blog</a></li>
                <li><a href="./contact.html">Contact</a></li>
            </ul>
        </nav>
        <div id="mobile-menu-wrap"></div>
        <div class="header__top__right__social">
            <a href="#"><i class="fa fa-facebook"></i></a>
            <a href="#"><i class="fa fa-twitter"></i></a>
            <a href="#"><i class="fa fa-linkedin"></i></a>
            <a href="#"><i class="fa fa-pinterest-p"></i></a>
        </div>
        <div class="humberger__menu__contact">
            <ul>
                <li><i class="fa fa-envelope"></i> hello@colorlib.com</li>
                <li>Free Shipping for all Order of $99</li>
            </ul>
        </div>
    </div>
    <!-- Humberger End -->

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
			
          <form style="width: 23rem;">
			<a href="/turismo.uy/home"><img alt="" src="../img/logo.png"></a>
			<br>
            <h3 class="fw-normal mb-3 pb-3" style="letter-spacing: 1px;">Inicio de Sesion</h3>

            <div class="form-outline mb-4">
              <input type="email" id="emailnick_inicioSesion" class="form-control form-control-lg" />
              <label class="form-label" for="emailnick_inicioSesion">Email address or nickname</label>
            </div>

            <div class="form-outline mb-4">
              <input type="password" id="pass_iniSesion" class="form-control form-control-lg" />
              <label class="form-label" for="pass_iniSesion">Password</label>
            </div>
            
            <% session.setAttribute("emailnick_inicioSesion", request.getParameter("emailnick_inicioSesion")); 
           	   session.setAttribute("pass_iniSesion", request.getParameter("pass_iniSesion")); 
            %>
			
			<div class="pt-1 mb-4" >
              <a href="/turismo.uy/sesionIniciada"> <button class="btn btn-info btn-lg btn-block" style="background : #4bb1ff;" type="button">Iniciar Sesion</button> </a>
            </div>            

            <p>No estas registrado? <a href="/turismo.uy/altaUsuario" class="link-info" style = "color:#4bb1ff">Registrate aqui</a></p>

          </form>

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
    <%if("error"==request.getAttribute("error_contrasena")) {%>
    <script>window.onload=function() {
		alert('Contrasena equivocada');
	}	</script> 
	<%} %>
	<%if("error"==request.getAttribute("error_emailnick")) {%>
    <script>window.onload=function() {
		alert('Email/nick equivocado');
	}	</script> 
	<%} %>


</body>

