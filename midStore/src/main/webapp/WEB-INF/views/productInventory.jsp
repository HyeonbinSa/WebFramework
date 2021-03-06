<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<div class="container-wrapper">
	<div class="container">
		<h2>Product Inventory</h2>
		<p>제품 관리 화면입니다.</p>
		<table class="table table-striped">
			<thead>
				<tr>
					<th>Name</th>
					<th>Category</th>
					<th>price</th>
					<th>Manufacturer</th>
					<th>unitInStock</th>
					<th>Description</th>
					<th></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="product" items="${products}">
					<tr>
						<td>${product.name}</td>
						<td>${product.category}</td>
						<td>${product.price}</td>
						<td>${product.manufacturer}</td>
						<td>${product.unitInStock}</td>
						<td>${product.description}</td>
						<td>
							<a href="<c:url value="/admin/productInventory/deleteProduct/${product.id}"/>"><i class="fa fa-times"></i></a>
							<a href="<c:url value="/admin/productInventory/updateProduct/${product.id}"/>"><i class="fa fa-edit"></i></a>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<a href="<c:url value="/admin/productInventory/addProduct"/>" class="btn btn-primary">Add Product</a>
	</div>
</div>