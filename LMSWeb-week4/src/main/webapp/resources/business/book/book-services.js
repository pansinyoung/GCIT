lmsApp.factory("bookService", function ($http, $log) {
    return {
        
        addBookService: function (book) {
            book = JSON.stringify(book);
            return $http({
                url: "http://localhost:8080/lms/book",
                method: "POST",
                data: book
            });
        },
        updateBookService: function (book) {
            book = JSON.stringify(book);
            return $http({
                url: "http://localhost:8080/lms/book",
                method: "PUT",
                data: book
            });
        },
        deleteBookService: function (book) {
            return $http({
                url: "http://localhost:8080/lms/book/" + book,
                method: "DELETE",
                data: book
            });
        },
        searchBookService: function (searchString) {
            var result = {};
            if(searchString){
                return $http({
                    url: "http://localhost:8080/lms/book?searchString=" + searchString,
                    method: "GET"
                }).success(function (data) {
                    result = data;
                }).then(function () {
                    return result;
                });
            }
            return $http({
                url: "http://localhost:8080/lms/book",
                method: "GET"
            }).success(function (data) {
                result = data;
            }).then(function () {
                return result;
            });
        },
        readAllBooksService: function () {
            var result = {};
            return $http({
                url: "http://localhost:8080/lms/book",
                method: "GET"
            }).success(function (data) {
                result = data;
            }).then(function () {
                return result;
            });
        },
        viewAuthorBooksService: function (authorId) {
            var result = {};
            return $http({
                url: "http://localhost:8080/lms/book?authorId="+authorId,
                method: "GET",
            }).success(function (data) {
                result = data;
            }).then(function () {
                return result;
            });
        },
        viewPublisherBooksService: function (publisherId) {
            var result = {};
            return $http({
                url: "http://localhost:8080/lms/book?publisherId="+publisherId,
                method: "GET",
            }).success(function (data) {
                result = data;
            }).then(function () {
                return result;
            });
        },
        viewGenreBooksService: function (genreId) {
            var result = {};
            return $http({
                url: "http://localhost:8080/lms/book?genreId=" + genreId,
                method: "GET",
            }).success(function (data) {
                result = data;
            }).then(function () {
                return result;
            });
        },
        selectBookByIdService: function (bookId) {
            var result = {};
            return $http({
                url: "http://localhost:8080/lms/book?bookId="+bookId,
                method: "GET",
            }).success(function (data) {
                result = data;
            }).then(function () {
                return result;
            });
        }
    };
});