angular.module('app').controller('createOrderResultController', function ($scope, $http, $location) {
    const contextPath = 'http://localhost:8189/market';

    fillTable = function () {
         // $http.get(contextPath + '/api/v1/orders/find/' + id)
       $http.get(contextPath + '/api/v1/orders/find?id=' + id)
            .then(function (response) {
                $scope.CartListResult = response.data;
            });
    };


    fillTable();
});