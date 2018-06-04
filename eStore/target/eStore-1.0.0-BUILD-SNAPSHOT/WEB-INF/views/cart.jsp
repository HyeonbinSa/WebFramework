<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<script src="<c:url value="/resources/js/controller.js" />"></script>

<div class="container-wrapper">
	<div class="container">

		<div class="jumbotron">
			<div class="container">
				<h2>Cart</h2>
				<p>All the selected products in your shopping cart</p>
			</div>

		</div>
		
		<section class="container" ng-app="cartApp">
			<div ng-controller="cartCtrl" ng-init="initCartId('${cartId}')">
				<a class="btn btn-warning pull-left" ng-click="clearCart()">
					<i class="fa fa-trash"></i>Clear Cart
				</a>
				<br/>
				<table class="table table-hover" style="width:100%">
					<tr>
						<th width="17.5%">Product</th>
						<th width="17.5%">Unit Price</th>
						<th width="17.5%">Quantity</th>
						<th width="17.5%">Total price</th>
						<th width="30%">Action</th>
					</tr>
					
					<tr ng-repeat="item in cart.cartItems">
						<td>{{item.product.name}}</td>
						<td>{{item.product.price}}</td>
						<td>{{item.quantity}}</td>
						<td>{{item.totalPrice}}</td>
						<td><a class="btn btn-danger" ng-click="removeFromCart(item.product.id)" style="color:white">
							<i class="fa fa-times"></i>Remove</a>
							<a class="btn btn-danger" ng-click="plusFromCart(item.product.id)" style="color:white">
							<i class="fa fa-plus"></i>plus</a>
							<a class="btn btn-danger" ng-click="minusFromCart(item.product.id)" style="color:white">
							<i class="fa fa-minus"></i>minus</a>
						</td>
					</tr>
					<tr>
						<td></td>
						<td></td>
						<td>GrandTotal</td>
						<td>{{calGrandTotal()}}</td>
						<td></td>
					</tr>
				</table>
				<a class="btn btn-info" href="<c:url value="/products" />" class="btn btn-default">Continue Shopping</a>
			</div>
		</section>
	</div>
</div>