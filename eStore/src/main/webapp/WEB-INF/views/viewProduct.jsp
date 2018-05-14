<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="container-wrapper">
	<div class="container">
		<h1>Product Detail Page</h1>
		<br>
		<p class="lead">Here id the detail information of the Product</p>
		<div class="row">
			<div class="col-md-6">
				<%-- <c:set var="imageFilename"
					value="/resources/images/${product.id}.jpg" /> --%>
				<img src="<c:url value="/resources/images/${product.imageFileName}" />" alt="image"
					style="width: 60%" />
			</div>

			<div class="col-md-6">
				<h3>${product.name}</h3>
				<p>
					<strong>${product.description}</strong>
				</p>
				<p>
					<b>Manufacturer : </b>${product.manufacturer}</p>
				<p>
					<b>Category : </b>${product.category}</p>
				<p>
					<strong>${product.price}원</strong>
				</p>
			</div>
		</div>
	</div>
</div>