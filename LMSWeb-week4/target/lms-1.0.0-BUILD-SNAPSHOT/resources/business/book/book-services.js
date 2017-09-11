lmsApp.factory("bookService", function ($http, $log) {
    return {
        
        initBookService: function () {
            var newBook = {};
            return $http({
                url: "http://localhost:8080/lms/initBook",
                method: "POST"
            }).success(function (data) {
                newBook = data;
            }).then(function () {
                return newBook;
            });
        },
        addUpdateBookService: function (book) {
            $log.info(JSON.stringify(book));
            book = JSON.stringify(book);
            return $http({
                url: "http://localhost:8080/lms/addUpdateBook",
                method: "POST",
                data: book
            });
        },
        deleteBookService: function (book) {
            return $http({
                url: "http://localhost:8080/lms/deleteBook",
                method: "POST",
                data: book
            });
        },
        searchBookService: function (searchString) {
            var result = {};
            if(searchString){
                return $http({
                    url: "http://localhost:8080/lms/readAllBook",
                    method: "POST",
                    data: searchString
                }).success(function (data) {
                    result = data;
                }).then(function () {
                    return result;
                });
            }
            return $http({
                url: "http://localhost:8080/lms/readAllBooks",
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
                url: "http://localhost:8080/lms/readAllBooks",
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
                url: "http://localhost:8080/lms/viewAuthorBooks",
                method: "POST",
                data: JSON.stringify(authorId)
            }).success(function (data) {
                result = data;
            }).then(function () {
                return result;
            });
        },
        viewPublisherBooksService: function (publisherId) {
            var result = {};
            return $http({
                url: "http://localhost:8080/lms/viewPublisherBooks",
                method: "POST",
                data: JSON.stringify(publisherId)
            }).success(function (data) {
                result = data;
            }).then(function () {
                return result;
            });
        },
        viewGenreBooksService: function (genreId) {
            var result = {};
            return $http({
                url: "http://localhost:8080/lms/viewGenreBooks",
                method: "POST",
                data: JSON.stringify(genreId)
            }).success(function (data) {
                result = data;
            }).then(function () {
                return result;
            });
        },
        selectBookByIdService: function (bookId) {
            var result = {};
            return $http({
                url: "http://localhost:8080/lms/selectBookById",
                method: "POST",
                data: JSON.stringify(bookId)
            }).success(function (data) {
                result = data;
            }).then(function () {
                return result;
            });
        }
    };
});