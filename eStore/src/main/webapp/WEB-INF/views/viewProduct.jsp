<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<div class="row">

	<div class="col-md-6">
		<h1>Product Detail Page</h1>
		<br>
		<p>Here id the detail information of the Product</p>
		<c:set var="imageFilename" value="/resources/images/${product.id}.jpg" />
		<img src="<c:url value="${imageFilename}" />" alt="image"
			style="width: 80%" />
	</div>
	<div class="col-md-6">
	<br/><br/><br/><br/><br/><br/><br/><br/>
		<h3>${product.name}</h3>
		<p>${product.description}</p>
		<p>
			<b>Manufacturer : </b>${product.manufacturer}</p>
		<p>
			<b>Category : </b>${product.category}</p>
		<p><strong>${product.price}</strong></p>
	</div>
</div>
