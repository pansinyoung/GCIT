lmsApp.controller("listLoanController", function ($scope, $http, $window, loanService, bookService, branchService, borrowerService, Pagination, $filter, $modal, $log, $route) {
     $scope.loadData = function () {     
        loanService.readAllLoansService().then(function (data) {
            $scope.loans = data;
            $scope.pagination = Pagination.getNew(10);
            $scope.pagination.numPages = Math.ceil($scope.loans.length / $scope.pagination.perPage);
        })
     };
    
    $scope.loadData();
    
    $scope.searchLoan = function () {
        loanService.searchLoanService($scope.searchString).then(function (data) {
            $scope.loans = data;
            $scope.pagination = Pagination.getNew(10);
            $scope.pagination.numPages = Math.ceil($scope.loans.length / $scope.pagination.perPage);
        });
    };
    
    $scope.viewLoanClicked = function(){
        $scope.showUpdateButton = false;
    };
    
    $scope.updateLoanClicked = function(){
        $scope.showUpdateButton = true;
    };
    
    $scope.updateLoanButtonClicked = function(loan){
        $scope.loanToUpdate = loan;
        
        var anotherModalInstance = $modal.open({
          templateUrl: 'updateLoanModal.html',
          controller: 'updateLoanModalCtrl',
            size: 'lg',
          resolve: {
              loanToUpdate: function(){
                  return $scope.loanToUpdate;
              }
          }
        });

        anotherModalInstance.result.then(function (loanToUpdate) {
            $scope.loanToUpdate = loanToUpdate;
            loanService.updateLoanService(loanToUpdate).then(function(){
                $window.alert('update successfully!');
                $scope.loadData();
            });
        });
        
        
    };
    
    $scope.goToAuthor = function(){
        $window.location.href = "http://localhost:8080/lms/#/administrationAuthor";
    };
    
    $scope.goToPublisher = function(){
        $window.location.href = '#/administrationPublisher';
    };
    
    $scope.goToBranch = function(){
        $window.location.href = '#/administrationBranch';
    };
    
    $scope.goToBorrower = function(){
        $window.location.href = '#/administrationBorrower';
    };
    
    $scope.goToGenre = function(){
        $window.location.href = '#/administrationGenre';
    };
    
    $scope.goToBook = function(){
        $window.location.href = '#/administrationBook';
    };

});

lmsApp.controller('updateLoanModalCtrl', function ($log, $scope, $modalInstance, loanToUpdate) {

    $scope.loanToUpdate = loanToUpdate;

    $scope.ok = function () {
        $modalInstance.close($scope.loanToUpdate);
    };

    $scope.cancel = function () {
        $modalInstance.dismiss('cancel');
    };
});