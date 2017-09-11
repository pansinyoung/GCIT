lmsApp.controller("listBorrowerController", function ($scope, $http, $window, borrowerService, authorService, genreService, borrowerService, Pagination, $filter, $modal, $log, $route) {
     $scope.loadData = function() {     
        borrowerService.readAllBorrowersService().then(function (data) {
            $scope.borrowers = data;
            $scope.pagination = Pagination.getNew(10);
            $scope.pagination.numPages = Math.ceil($scope.borrowers.length / $scope.pagination.perPage);
        })
     };
    
    $scope.loadData();
    
    $scope.searchBorrower = function() {
        borrowerService.searchBorrowerService($scope.searchString).then(function(data){
            $scope.borrowers = data;
            $scope.pagination = Pagination.getNew(10);
            $scope.pagination.numPages = Math.ceil($scope.borrowers.length / $scope.pagination.perPage);
        });
    };
    
    $scope.addBorrowerClicked = function(){
        
        var modalInstance = $modal.open({
          templateUrl: 'addBorrowerModal.html',
          controller: 'addBorrowerModalCtrl',
            size: 'lg'
          
        });

        modalInstance.result.then(function (newBorrower) {
            $scope.newBorrower = newBorrower;
            borrowerService.addUpdateBorrowerService(newBorrower).then(function(){
                $window.alert('add successfully!');
                $scope.loadData();
            });
        });
        
        
    };
    
    $scope.deleteBorrowerClicked = function(){
        $scope.showDeleteButton = true;
        $scope.showUpdateButton = false;
    };
    
    $scope.deleteBorrowerButtonClicked = function(cardNo){
        borrowerService.deleteBorrowerService({'cardNo': cardNo}).then(function(){
            $window.alert('delete successfully!');
            $scope.loadData();
            $scope.showDeleteButton = true;
      });
    };
    
    $scope.viewBorrowerClicked = function(){
        $scope.showDeleteButton = false;
        $scope.showUpdateButton = false;
    };
    
    $scope.updateBorrowerClicked = function(){
        $scope.showDeleteButton = false;
        $scope.showUpdateButton = true;
    };
    
    $scope.updateBorrowerButtonClicked = function(cardNo){
        $scope.borrowerToUpdate = borrowerService.selectBorrowerByIdService(cardNo);
        
        var anotherModalInstance = $modal.open({
          templateUrl: 'updateBorrowerModal.html',
          controller: 'updateBorrowerModalCtrl',
            size: 'lg',
          resolve: {
              borrowerToUpdate: function(){
                  return $scope.borrowerToUpdate;
              }
          }
        });

        anotherModalInstance.result.then(function (borrowerToUpdate) {
            $scope.borrowerToUpdate = borrowerToUpdate;
            borrowerService.addUpdateBorrowerService(borrowerToUpdate).then(function(){
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
    
    $scope.goToBook = function(){
        $window.location.href = '#/administrationBook';
    };
    
    $scope.goToGenre = function(){
        $window.location.href = '#/administrationGenre';
    };
    
    $scope.goToLoan = function(){
        $window.location.href = '#/administrationLoan';
    };

});

lmsApp.controller('addBorrowerModalCtrl', function ($log, $scope, $modalInstance) {

    $scope.ok = function () {
        $modalInstance.close($scope.newBorrower);
    };

    $scope.cancel = function () {
        $modalInstance.dismiss('cancel');
    };
});

lmsApp.controller('updateBorrowerModalCtrl', function ($log, $scope, $modalInstance, borrowerToUpdate) {

    $scope.borrowerToUpdate = borrowerToUpdate;

    $scope.ok = function () {
        $modalInstance.close($scope.borrowerToUpdate);
    };

    $scope.cancel = function () {
        $modalInstance.dismiss('cancel');
    };
});