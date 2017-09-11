lmsApp.factory("loanService", function ($http, $log) {
    return {
        
        initLoanService: function () {
            var newLoan = {};
            return $http({
                url: "http://localhost:8080/lms/initLoan",
                method: "POST"
            }).success(function (data) {
                newLoan = data;
            }).then(function () {
                return newLoan;
            });
        },
        addLoanService: function (loan) {
            return $http({
                url: "http://localhost:8080/lms/addLoan",
                method: "POST",
                data: loan
            });
        },
        updateLoanService: function (loan) {
            return $http({
                url: "http://localhost:8080/lms/updateLoan",
                method: "POST",
                data: loan
            });
        },
        searchLoanService: function (searchString) {
            var result = {};
            if (searchString) {
                return $http({
                    url: "http://localhost:8080/lms/readAllLoan",
                    method: "POST",
                    data: searchString
                }).success(function (data) {
                    result = data;
                }).then(function () {
                    return result;
                });
            }
            return $http({
                url: "http://localhost:8080/lms/readAllLoans",
                method: "GET"
            }).success(function (data) {
                result = data;
            }).then(function () {
                return result;
            });
        },
        readAllLoansService: function () {
            var result = {};
            return $http({
                url: "http://localhost:8080/lms/readAllLoans",
                method: "GET"
            }).success(function (data) {
                result = data;
            }).then(function () {
                return result;
            });
        },
        bookCheckService: function (loan) {
            return $http({
                url: "http://localhost:8080/lms/bookCheck",
                method: "POST",
                data: loan
            });
        },
        bookReturnService: function (loan) {
            return $http({
                url: "http://localhost:8080/lms/bookReturn",
                method: "POST",
                data: loan
            });
        }
    };
});