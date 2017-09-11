lmsApp.factory("authorService", function ($http) {
    return {
        
        initAuthorService: function () {
            var newAuthor = {};
            return $http({
                url: "http://localhost:8080/lms/initAuthor",
                method: "POST"
            }).success(function (data) {
                newAuthor = data;
            }).then(function () {
                return newAuthor;
            });
        },
        addUpdateAuthorService: function (author) {
            return $http({
                url: "http://localhost:8080/lms/addUpdateAuthor",
                method: "POST",
                data: author
            });
        },
        deleteAuthorService: function (author) {
            return $http({
                url: "http://localhost:8080/lms/deleteAuthor",
                method: "POST",
                data: author
            });
        },
        searchAuthorService: function (searchString) {
            var result = {};
            if(searchString){
                return $http({
                    url: "http://localhost:8080/lms/readAllAuthor",
                    method: "POST",
                    data: searchString
                }).success(function (data) {
                    result = data;
                }).then(function () {
                    return result;
                });
            }
            return $http({
                url: "http://localhost:8080/lms/readAllAuthors",
                method: "GET"
            }).success(function (data) {
                result = data;
            }).then(function () {
                return result;
            });
        },
        readAllAuthorsService: function () {
            var result = {};
            return $http({
                url: "http://localhost:8080/lms/readAllAuthors",
                method: "GET"
            }).success(function (data) {
                result = data;
            }).then(function () {
                return result;
            });
        },
        viewBookAuthorsService: function (bookId) {
            var result = {};
            return $http({
                url: "http://localhost:8080/lms/viewBookAuthors",
                method: "POST",
                data: bookId
            }).success(function (data) {
                result = data;
            }).then(function () {
                return result;
            });
        },
        selectAuthorByIdService: function (authorId) {
            var result = {};
            return $http({
                url: "http://localhost:8080/lms/selectAuthorById",
                method: "POST",
                data: authorId
            }).success(function (data) {
                result = data;
            }).then(function () {
                return result;
            });
        }
    };
});