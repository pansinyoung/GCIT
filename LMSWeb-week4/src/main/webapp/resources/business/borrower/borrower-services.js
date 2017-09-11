lmsApp.factory("borrowerService", function ($http, $log) {
    return {
        
        initBorrowerService: function () {
            var newBorrower = {};
            return $http({
                url: "http://localhost:8080/lms/initBorrower",
                method: "POST"
            }).success(function (data) {
                newBorrower = data;
            }).then(function () {
                return newBorrower;
            });
        },
        addUpdateBorrowerService: function (borrower) {
            $log.info(JSON.stringify(borrower));
            borrower = JSON.stringify(borrower);
            return $http({
                url: "http://localhost:8080/lms/addUpdateBorrower",
                method: "POST",
                data: borrower
            });
        },
        deleteBorrowerService: function (borrower) {
            return $http({
                url: "http://localhost:8080/lms/deleteBorrower",
                method: "POST",
                data: borrower
            });
        },
        searchBorrowerService: function (searchString) {
            var result = {};
            if(searchString){
                return $http({
                    url: "http://localhost:8080/lms/readAllBorrower",
                    method: "POST",
                    data: searchString
                }).success(function (data) {
                    result = data;
                }).then(function () {
                    return result;
                });
            }
            return $http({
                url: "http://localhost:8080/lms/readAllBorrowers",
                method: "GET"
            }).success(function (data) {
                result = data;
            }).then(function () {
                return result;
            });
        },
        readAllBorrowersService: function () {
            var result = {};
            return $http({
                url: "http://localhost:8080/lms/readAllBorrowers",
                method: "GET"
            }).success(function (data) {
                result = data;
            }).then(function () {
                return result;
            });
        },
        selectBorrowerByIdService: function (cardNo) {
            var result = {};
            return $http({
                url: "http://localhost:8080/lms/selectBorrowerById",
                method: "POST",
                data: JSON.stringify(cardNo)
            }).success(function (data) {
                result = data;
            }).then(function () {
                return result;
            });
        }
    };
});