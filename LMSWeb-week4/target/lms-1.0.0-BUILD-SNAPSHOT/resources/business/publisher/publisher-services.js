lmsApp.factory("publisherService", function ($http) {
    return {
        
        initPublisherService: function () {
            var newPublisher = {};
            return $http({
                url: "http://localhost:8080/lms/initPublisher",
                method: "POST"
            }).success(function (data) {
                newPublisher = data;
            }).then(function () {
                return newPublisher;
            });
        },
        addUpdatePublisherService: function (publisher) {
            return $http({
                url: "http://localhost:8080/lms/addUpdatePublisher",
                method: "POST",
                data: publisher
            });
        },
        deletePublisherService: function (publisher) {
            return $http({
                url: "http://localhost:8080/lms/deletePublisher",
                method: "POST",
                data: publisher
            });
        },
        searchPublisherService: function (searchString) {
            var result = {};
            if(searchString){
                return $http({
                    url: "http://localhost:8080/lms/readAllPublisher",
                    method: "POST",
                    data: searchString
                }).success(function (data) {
                    result = data;
                }).then(function () {
                    return result;
                });
            }
            return $http({
                url: "http://localhost:8080/lms/readAllPublishers",
                method: "GET"
            }).success(function (data) {
                result = data;
            }).then(function () {
                return result;
            });
        },
        readAllPublishersService: function () {
            var result = {};
            return $http({
                url: "http://localhost:8080/lms/readAllPublishers",
                method: "GET"
            }).success(function (data) {
                result = data;
            }).then(function () {
                return result;
            });
        },
        viewBookPublisherService: function (bookId) {
            var result = {};
            return $http({
                url: "http://localhost:8080/lms/viewBookPublisher",
                method: "POST",
                data: JSON.stringify(bookId)
            }).success(function (data) {
                result = data;
            }).then(function () {
                return result;
            });
        },
        selectPublisherByIdService: function (publisherId) {
            var result = {};
            return $http({
                url: "http://localhost:8080/lms/getPublisherById",
                method: "POST",
                data: JSON.stringify(publisherId)
            }).success(function (data) {
                result = data;
            }).then(function () {
                return result;
            });
        }
    };
});