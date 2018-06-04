<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	 
<script src="<c:url value="/resources/js/controller.js" />"></script>
<div class="container-wrapper">
	<div class="container" ng-app="cartApp">
		<h1>Product Detail Page</h1>
		<br>
		<p class="lead">Here is the detail information of the Product</p>
		<div class="row" ng-controller="cartCtrl">
			<div class="col-md-6">
				<%-- <c:set var="imageFilename"	value="/resources/images/${product.id}.jpg" /> --%>
				<img src="<c:url value="/resources/images/${product.imageFileName}" />" alt="image"
					style="width: 60%" />
			</div>

			<div class="col-md-6">
				<h3>${product.name}</h3>
				<p><strong>${product.description}</strong></p>
				<p><b>Manufacturer : </b>${product.manufacturer}</p>
				<p><b>Category : </b>${product.category}</p>
				<p><strong>${product.price}원</strong></p>
				
				<br/>
				<c:if test="${pageContext.request.userPrincipal.name != null}">
					<p><a href="<c:url value="/products"/>" class="btn btn-danger">Back</a>
					<button class="btn btn-warning btn-large" ng-click="addToCart('${product.id}')">
						<i class="fa fa-shopping-cart"></i>Order Now
					</button>
					<a href="<c:url value="/cart"/>" class="btn btn-info">
						<i class="fa fa-eye"></i>View Cart
					</a>
				</c:if>
			</div>
		</div>
	</div>
</div>