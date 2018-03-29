<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<div class="container-wrapper">
	<div class="container">
		<h1>Update Product</h1>
		<p class="lead">Fill the below information to add a product:</p>
		<sf:form action="${pageContext.request.contextPath}/admin/productInventory/updateProduct" method="post" modelAttribute="product">
		
		<sf:hidden path="id"/><!-- 이걸 넣어줘야 update 할때 id값을 넣어 보내줌, 안넣어줄 경우 update 시 id = 0 으로 됨 -->
		<div class="form-group">
			<label for="name">Name</label>
			<sf:input path="name" id="name" class="form-control"/>
		</div>
		<div class="form-group">
			<label for="category">Category: </label>
			<sf:radiobutton path="category" id="category" value="컴퓨터"/>컴퓨터
			<sf:radiobutton path="category" id="category" value="가전"/>가전
			<sf:radiobutton path="category" id="category" value="잡화"/>잡화
		</div>
		<div class="form-group">
			<label for="description">description</label>
			<sf:textarea path="description" id="description" class="form-control"/>
		</div>
		<div class="form-group">
			<label for="price">Name</label>
			<sf:input path="price" id="price" class="form-control"/>
		</div>
		<div class="form-group">
			<label for="unitInStock">unitInStock</label>
			<sf:input path="unitInStock" id="unitInStock" class="form-control"/>
		</div>
		<div class="form-group">
			<label for="manufacturer">manufacturer</label>
			<sf:input path="manufacturer" id="manufacturer" class="form-control"/>
		</div>
		<input type="submit" value = "submit" class="btn btn-default">
		<a href="<c:url value="/admin/productInventory" />" class="btn btn-default">Cancel</a>
		</sf:form>
		<br/>
	</div>
</div>
