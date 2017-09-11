lmsApp.factory("genreService", function ($http) {
    return {
        
        initGenreService: function () {
            var newGenre = {};
            return $http({
                url: "http://localhost:8080/lms/initGenre",
                method: "POST"
            }).success(function (data) {
                newGenre = data;
            }).then(function () {
                return newGenre;
            });
        },
        addUpdateGenreService: function (genre) {
            return $http({
                url: "http://localhost:8080/lms/addUpdateGenre",
                method: "POST",
                data: {
                    genre: genre
                }
            });
        },
        deleteGenreService: function (genre) {
            return $http({
                url: "http://localhost:8080/lms/deleteGenre",
                method: "POST",
                data: {
                    genre: genre
                }
            });
        },
        searchGenreService: function (searchString) {
            var result = {};
            if(searchString){
                return $http({
                    url: "http://localhost:8080/lms/readAllGenre",
                    method: "POST",
                    data: searchString
                }).success(function (data) {
                    result = data;
                }).then(function () {
                    return result;
                });
            }
            return $http({
                url: "http://localhost:8080/lms/readAllGenres",
                method: "GET"
            }).success(function (data) {
                result = data;
            }).then(function () {
                return result;
            });
        },
        readAllGenresService: function () {
            var result = {};
            return $http({
                url: "http://localhost:8080/lms/readAllGenres",
                method: "GET"
            }).success(function (data) {
                result = data;
            }).then(function () {
                return result;
            });
        },
        viewBookGenresService: function (bookId) {
            var result = {};
            return $http({
                url: "http://localhost:8080/lms/viewBookGenres",
                method: "POST",
                data: bookId
            }).success(function (data) {
                result = data;
            }).then(function () {
                return result;
            });
        },
        selectGenreByIdService: function (genreId) {
            var result = {};
            return $http({
                url: "http://localhost:8080/lms/selectGenreById",
                method: "POST",
                data: genreId
            }).success(function (data) {
                result = data;
            }).then(function () {
                return result;
            });
        }
    };
});