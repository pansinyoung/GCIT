lmsApp.controller("listBranchController", function ($scope, $http, $window, branchService, Pagination, $filter, $modal, $log, $route) {
     $scope.loadData = function() {     
        branchService.readAllBranchsService().then(function (data) {
            $scope.branches = data;
            $scope.pagination = Pagination.getNew(10);
            $scope.pagination.numPages = Math.ceil($scope.branches.length / $scope.pagination.perPage);
        })
     };
    
    $scope.loadData();
    
    $scope.searchBranch = function() {
        branchService.searchBranchService($scope.searchString).then(function(data){
            $scope.branches = data;
            $scope.pagination = Pagination.getNew(10);
            $scope.pagination.numPages = Math.ceil($scope.branches.length / $scope.pagination.perPage);
        });
    };
    
    $scope.addBranchClicked = function(){
        var modalInstance = $modal.open({
          templateUrl: 'addBranchModal.html',
          controller: 'addBranchModalCtrl',
            size: 'lg',
        });

        modalInstance.result.then(function (newBranch) {
            $scope.newBranch = newBranch;
            branchService.addUpdateBranchService(newBranch).then(function(){
                $window.alert('add successfully!');
                $scope.loadData();
            });
        });
        
        
    };
    
    $scope.deleteBranchClicked = function(){
        $scope.showDeleteButton = true;
        $scope.showUpdateButton = false;
    };
    
    $scope.deleteBranchButtonClicked = function(branchId){
        branchService.deleteBranchService({'branchId': branchId}).then(function(){
            $window.alert('delete successfully!');
            $scope.loadData();
            $scope.showDeleteButton = true;
      });
    };
    
    $scope.viewBranchClicked = function(){
        $scope.showDeleteButton = false;
        $scope.showUpdateButton = false;
    };
    
    $scope.updateBranchClicked = function(){
        $scope.showDeleteButton = false;
        $scope.showUpdateButton = true;
    };
    
    $scope.updateBranchButtonClicked = function(branchId){
        $scope.branchToUpdate = branchService.selectBranchByIdService(branchId);
        
        var anotherModalInstance = $modal.open({
          templateUrl: 'updateBranchModal.html',
          controller: 'updateBranchModalCtrl',
            size: 'lg',
          resolve: {
              branchToUpdate: function(){
                  return $scope.branchToUpdate;
              }
          }
        });

        anotherModalInstance.result.then(function (branchToUpdate) {
            $scope.branchToUpdate = branchToUpdate;
            branchService.addUpdateBranchService(branchToUpdate).then(function(){
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
    
    $scope.goToBook = function(){
        $window.location.href = '#/administrationBook';
    };
    
    $scope.goToBorrower = function(){
        $window.location.href = '#/administrationBorrower';
    };
    
    $scope.goToGenre = function(){
        $window.location.href = '#/administrationGenre';
    };
    
    $scope.goToLoan = function(){
        $window.location.href = '#/administrationLoan';
    };

});

lmsApp.controller('addBranchModalCtrl', function ($log, $scope, $modalInstance) {

    $scope.ok = function () {
        $modalInstance.close($scope.newBranch);
    };

    $scope.cancel = function () {
        $modalInstance.dismiss('cancel');
    };
});

lmsApp.controller('updateBranchModalCtrl', function ($log, $scope, $modalInstance, branchToUpdate) {

    $scope.branchToUpdate = branchToUpdate;

    $scope.ok = function () {
        $modalInstance.close($scope.branchToUpdate);
    };

    $scope.cancel = function () {
        $modalInstance.dismiss('cancel');
    };
});