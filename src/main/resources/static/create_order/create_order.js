angular.module('app').controller('createOrderController', function ($scope, $http, $location) {
    const contextPath = 'http://localhost:8189/market';

    fillTable = function () {
        $http.get(contextPath + '/api/v1/cart')
            .then(function (response) {
                $scope.CartList = response.data;
            });
    };

    $scope.confirmOrder = function () {
        console.log($scope.userinfo.address);
        $http({
            url: contextPath + '/api/v1/orders/confirm',
            method: "POST",
            params: {address: $scope.userinfo.address}
        }).then(function (response) {
            $location.path('/create_order_result?id=' + response.data);
        });
    }

    fillTable();
});