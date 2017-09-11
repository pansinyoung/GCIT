lmsApp.controller("borrowerController", function ($scope, $http, $window, borrowerNoService, Pagination, $filter, $modal, $log, $route, $rootScope, bookService, branchService, borrowerService) {
    
    $scope.borrower = $rootScope.borrower;
    
    $scope.borrowerLogin = function () {
        if ($scope.cardNo) {
            borrowerNoService.selectBorrowerByIdService($scope.cardNo).then(function (data) {
                if (data.cardNo) {
                    
                    $rootScope.borrower = data;
                    $window.location.href = "http://localhost:8080/lms/#/borrower";
                } else {
                    $log.info(data);
                    $window.alert('Invalid Card Number. Please try again.');
                    $window.location.reload();
                }
            });
        } else {
            $window.alert('Enter your cardNo!');
        }
    };
    
    $scope.loadData = function () {
        borrowerNoService.selectBorrowerByIdService($scope.borrower.cardNo).then(function (data) {
            $scope.borrower = data;
        });
    };
    
    $scope.returnClicked = function () {
        $scope.showReturnButton = true;
    };
    
    $scope.viewClicked = function () {
        $scope.showReturnButton = false;
    };
    
    $scope.checkOutClicked = function () {
        
        $scope.bookCopies = borrowerNoService.getBookCopiesService();
        $scope.newLoan = $scope.borrower.loans[0];
        
        var modalInstance = $modal.open({
            templateUrl: 'checkOutBookModal.html',
            controller: 'checkOutBookCtrl',
            size: 'lg',
            resolve: {
                bookCopies: function () {
                    return $scope.bookCopies;
                },
                newLoan: function () {
                    return $scope.newLoan;
                }
            }
        });

        modalInstance.result.then(function (newLoan) {
            $scope.newLoan = newLoan;
            borrowerNoService.checkOutBookService(newLoan).then(function () {
                $window.alert('check out successfully!');
                $scope.loadData();
            });
        });
    };
    
    $scope.returnBook = function (loan) {
        borrowerNoService.returnBookService(loan).then(function () {
            $window.alert('Return successfully!');
            $scope.loadData();
        });
    };
});


lmsApp.controller('checkOutBookCtrl', function ($log, $scope, $modalInstance, bookCopies, newLoan, borrowerNoService) {

    $scope.bookCopies = bookCopies;
    $scope.newLoan = newLoan;
    $scope.ok = function () {
        $modalInstance.close($scope.newLoan);
    };

    $scope.cancel = function () {
        $modalInstance.dismiss('cancel');
    };
    
    $scope.branchSelected = function () {
        borrowerNoService.getBookCopiesByBranchIdService($scope.selectedBookCopyBranch.branch.branchId).then(function (data) {
            $scope.bookCoiesByBranch = data;
        });
        $scope.newLoan.branch = $scope.selectedBookCopyBranch.branch;
    };
    
    $scope.bookSelected = function () {
        if ($scope.selectedBookCopyBook.book) {
            $scope.newLoan.book = $scope.selectedBookCopyBook.book;
        }
        $log.info(newLoan);
    };
});
