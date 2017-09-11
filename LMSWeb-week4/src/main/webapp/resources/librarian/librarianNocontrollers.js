lmsApp.controller("librarianController", function ($scope, $http, $window, librarianNoService, branchService, bookService, Pagination, $filter, $modal, $log, $route, $rootScope) {
    
    $scope.loadData = function () {
        branchService.readAllBranchsService().then(function (data) {
            $scope.branches = data;
        });
    };
    
    $scope.loadData();
    
    $scope.editClicked = function () {
        $scope.showAddCopiesButton = false;
        $scope.showEditButton = true;
    };
    
    $scope.addCopiesClicked = function () {
        $scope.showAddCopiesButton = true;
        $scope.showEditButton = false;
    };
    
    $scope.viewClicked = function () {
        $scope.showAddCopiesButton = false;
        $scope.showEditButton = false;
    };
    
    $scope.editButtonClicked = function (branch) {
        $scope.branchToUpdate = branch;
        
        var anotherModalInstance = $modal.open({
            templateUrl: 'editBranchModal.html',
            controller: 'editBranchModalCtrl',
            size: 'lg',
            resolve: {
                branchToUpdate: function () {
                    return $scope.branchToUpdate;
                }
            }
        });

        anotherModalInstance.result.then(function (branchToUpdate) {
            $scope.branchToUpdate = branchToUpdate;
            branchService.addUpdateBranchService(branchToUpdate).then(function () {
                $window.alert('update successfully!');
                $scope.loadData();
            });
        });
    };
    
    $scope.addCopiesButtonClicked = function (branch) {
        $scope.branchToUpdate = branch;
        $scope.books = bookService.readAllBooksService();
        
        var anotherModalInstance = $modal.open({
            templateUrl: 'addCopiesModal.html',
            controller: 'addCopiesModalCtrl',
            size: 'lg',
            resolve: {
                branchToUpdate: function () {
                    return $scope.branchToUpdate;
                },
                books: function () {
                    return $scope.books;
                }
            }
        });

        anotherModalInstance.result.then(function (bookCopy) {
            $log.info(bookCopy);
            branchService.addCopiesService(bookCopy).then(function () {
                $window.alert('add copy successfully!');
                $scope.loadData();
            });
        });
    };
});


lmsApp.controller('editBranchModalCtrl', function ($log, $scope, $modalInstance, branchToUpdate) {

    $scope.branchToUpdate = branchToUpdate;

    $scope.ok = function () {
        $modalInstance.close($scope.branchToUpdate);
    };

    $scope.cancel = function () {
        $modalInstance.dismiss('cancel');
    };
});

lmsApp.controller('addCopiesModalCtrl', function ($log, $scope, $modalInstance, branchToUpdate, books) {

    $scope.books = books;
    
    $scope.ok = function () {
        $modalInstance.close({
            book: $scope.book,
            branch: branchToUpdate,
            noOfCopies: $scope.noOfCopies
        });
    };

    $scope.cancel = function () {
        $modalInstance.dismiss('cancel');
    };
});