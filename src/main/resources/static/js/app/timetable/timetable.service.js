(function(_$$) {


    _$$.util.registerServices({
        timetableService : {

            uploadTimetable : function(formData, successHandler, errorHandler) {
                axios
                    .post(
                        _$$.env.url + "/api/v1/timetable/upload", formData,
                        {
                            headers: {
                                'Content-Type': 'multipart/form-data'
                            }
                        }
                    )
                    .then(successHandler)
                    .catch(errorHandler);
            }

        }
    });


})(rootObject);
