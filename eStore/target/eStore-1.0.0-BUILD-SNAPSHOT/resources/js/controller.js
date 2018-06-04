var cartApp = angular.module('cartApp',[]);//Angular 모듈 정의

cartApp.controller('cartCtrl',function($scope, $http){//컨트롤러 정의(생성자임) $scope는 모델 $http는 서비스 의존성 주입해줌.
	
	$scope.initCartId = function(cartId){
		$scope.cartId =cartId;
		$scope.refreshCart();
	};
	
	$scope.refreshCart = function(){
		$http.get('/eStore/api/cart/' + $scope.cartId).then(
				function successCallback(response){
					$scope.cart = response.data;
				});		
	};
	$scope.clearCart = function(){
		$scope.setCsrfToken();
		
		$http({
			method : 'DELETE',
			url : '/eStore/api/cart/'+ $scope.cartId
		}).then(function successCallback(){
			$scope.refreshCart();
		},function errorCallBack(response){
			console.log(response.data);
		});
	};
	$scope.addToCart = function(productId){
		$scope.setCsrfToken();
		
		$http.put('/eStore/api/cart/add/'+productId).then(
				function successCallback(){
					alert("제품이 성공적으로 카트에 담겼습니다.");
				},function errorCallback(response){
					alert("장바구니 담기에 실패하였습니다.");
				});
	};
	$scope.removeFromCart = function(productId){
		$scope.setCsrfToken();
		
		$http({
			method : 'DELETE',
			url : '/eStore/api/cart/cartitem/'+productId
		}).then(function successCallback(){
			$scope.refreshCart();
		}, function errorCallback(response){
			console.log(response.data);
		});
	};
	
	$scope.plusFromCart = function(productId){
		$scope.setCsrfToken();
		
		$http.put('/eStore/api/cart/plus/'+productId).then(
				function successCallback(){
					//alert("제품이 성공적으로 카트에 담겼습니다.");
					$scope.refreshCart();
				},function errorCallback(response){
					alert("재고가 부족합니다.");
					$scope.refreshCart();
		});
	};
	$scope.minusFromCart = function(productId){
		$scope.setCsrfToken();
		
		$http.put('/eStore/api/cart/minus/'+productId).then(
				function successCallback(){
					//alert("제품이 성공적으로 카트에 담겼습니다.");
					$scope.refreshCart();
				},function errorCallback(response){
					alert("0 밑으로 안됩니다.");
					$scope.refreshCart();
		});
	};
	$scope.calGrandTotal = function(){
		var grandTotal = 0;
		
		for(var i=0; i<$scope.cart.cartItems.length; i++){
			grandTotal += $scope.cart.cartItems[i].totalPrice;
		}
		return grandTotal;
	};
	$scope.setCsrfToken = function(){
		var csrfToken = $("meta[name='_csrf']").attr("content");
		var csrfHeader = $("meta[name='_csrf_header']").attr("content");
		
		$http.defaults.headers.common[csrfHeader] = csrfToken;
	}
});