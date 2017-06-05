function a() {
    var formData = new FormData();
    formData.append('file', $('#file')[0].files[0]);
    $.ajax({
       // url: 'https://test.myfcarewards.com/myfcarewards/services/files/imageUpload',
        url: './services/files/imageUpload',
        type: 'POST',
        data: formData,
        processData: false,  // tell jQuery not to process the data
        contentType: false,  // tell jQuery not to set contentType
        success: function (data) {
            //console.log(data);
            //alert(data);
            alert("Successfully uploaded image")
        },
        error:
        function (error) {
            if ($('#file')[0].files[0] == null) {
                alert("Please choose an image")
            } else {
                alert("Error in uploading image")
            }

        }

    });
}