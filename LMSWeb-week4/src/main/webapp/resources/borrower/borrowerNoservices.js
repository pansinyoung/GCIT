lmsApp.factory("borrowerNoService", function ($http, $log) {
    return {
        
        selectBorrowerByIdService: function (cardNo) {
            var result = {};
            return $http({
                url: "http://localhost:8080/lms/selectBorrowerById",
                method: "POST",
                data: cardNo
            }).success(function (data) {
                result = data;
            }).then(function () {
                return result;
            });
        },
        
        getBookCopiesService: function () {
            var result = {};
            return $http({
                url: "http://localhost:8080/lms/selectAllCopies",
                method: "GET"
            }).success(function (data) {
                result = data;
            }).then(function () {
                return result;
            });
        },
        
        getBookCopiesByBranchIdService: function (branchId) {
            var result = {};
            return $http({
                url: "http://localhost:8080/lms/selectCopiesByBranchId",
                method: "POST",
                data: branchId
            }).success(function (data) {
                result = data;
            }).then(function () {
                return result;
            });
        },
        
        checkOutBookService: function (loan) {
            return $http({
                url: "http://localhost:8080/lms/bookCheck",
                method: "POST",
                data: loan
            });
        },
        
        returnBookService: function (loan) {
            return $http({
                url: "http://localhost:8080/lms/bookReturn",
                method: "POST",
                data: loan
            });
        }
        
    };
});