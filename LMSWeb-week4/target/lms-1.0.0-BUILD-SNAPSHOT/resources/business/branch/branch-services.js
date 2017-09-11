lmsApp.factory("branchService", function ($http, $log) {
    return {
        
        initBranchService: function () {
            var newBranch = {};
            return $http({
                url: "http://localhost:8080/lms/initBranch",
                method: "POST"
            }).success(function (data) {
                newBranch = data;
            }).then(function () {
                return newBranch;
            });
        },
        addUpdateBranchService: function (branch) {
            $log.info(JSON.stringify(branch));
            branch = JSON.stringify(branch);
            return $http({
                url: "http://localhost:8080/lms/addUpdateBranch",
                method: "POST",
                data: branch
            });
        },
        deleteBranchService: function (branch) {
            return $http({
                url: "http://localhost:8080/lms/deleteBranch",
                method: "POST",
                data: branch
            });
        },
        searchBranchService: function (searchString) {
            var result = {};
            if(searchString){
                return $http({
                    url: "http://localhost:8080/lms/readAllBranch",
                    method: "POST",
                    data: searchString
                }).success(function (data) {
                    result = data;
                }).then(function () {
                    return result;
                });
            }
            return $http({
                url: "http://localhost:8080/lms/readAllBranches",
                method: "GET"
            }).success(function (data) {
                result = data;
            }).then(function () {
                return result;
            });
        },
        readAllBranchsService: function () {
            var result = {};
            return $http({
                url: "http://localhost:8080/lms/readAllBranches",
                method: "GET"
            }).success(function (data) {
                result = data;
            }).then(function () {
                return result;
            });
        },
        selectBranchByIdService: function (branchId) {
            var result = {};
            return $http({
                url: "http://localhost:8080/lms/selectBranchById",
                method: "POST",
                data: JSON.stringify(branchId)
            }).success(function (data) {
                result = data;
            }).then(function () {
                return result;
            });
        }
    };
});