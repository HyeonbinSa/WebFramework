<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="container-wrapper">
	<div>
		<h2>Administrator Page</h2>
		<p class="lead">Product를 관리할 수 있는 페이지 입니다.</p>
	</div>
</div>
<div class="container-wrapper">
	<div>
		<h2><a href="<c:url value="/admin/productInventory"/>">Product Inventory</a></h2>
		<p class="lead">관리, 삭제, 추가 등 변경이 가능합니다.</p>
	</div>
</div>
  