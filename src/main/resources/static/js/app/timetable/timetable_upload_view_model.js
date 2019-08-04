(function (_$$) {


    _$$.util.registerComponents({
        timetableUploadViewModel : new Vue({

            el : "#main-invoices-upload-content",

            data : {
                file : null,
                fileUploading : false,
                invoices : [],
                uploadError : '',
                uploadSuccess : false
            },

            methods : {

                selectFile : function() {
                    this.file = this.$refs.file.files[0];
                },

                disabled : function() {
                    return this.file == null;
                },

                preUpload : function() {
                    this.uploadError = '';
                    this.uploadSuccess = false;
                    this.fileUploading = true;
                },

                successfulUpload : function(response) {
                    this.fileUploading = false;
                    this.uploadSuccess = true;
                    _$$.util.logInfo(JSON.stringify(response));

                    this.invoices = response.data.generatedInvoices;
                },

                failedUpload : function(error) {
                    this.fileUploading = false;
                    this.uploadError = _$$.util.extractError(error);
                    _$$.util.logError("Upload Error: " + this.uploadError);
                },

                invoiceListAvailable : function() {
                    _$$.util.logInfo("Invoices: " + (this.invoices && this.invoices.length));
                    return this.invoices && this.invoices.length;
                },

                clear : function() {
                    this.invoices = [];
                    this.uploadSuccess = false;
                    this.uploadError = '';
                },

                doUpload : function() {
                    var self = this;

                    var formData = new FormData();
                    formData.append("file", this.file);

                    self.preUpload();

                    _$$.services.timetableService.uploadTimetable(
                        formData,

                        function(response) {
                            self.successfulUpload(response);
                        },

                        function(error) {
                            self.failedUpload(error);
                        }
                    );
                }

            }

        })
    });


})(rootObject);