var api = "http://localhost:8080/api/airplanes";

function getAirplanes() {
    // Get the data from endpoint.
    $.ajax({
        url: api + "/retrieveAll",
        type:"GET",
        success: function(result) {
            // On successful GET, reload the datatable with new data.
            console.log("This is the data: " + result);
            $('#table').DataTable().clear();
            $('#table').DataTable().rows.add(result);
            $('#table').DataTable().columns.adjust().draw();
        }
    });
}

function createAirplane() {

    var modelName = $("#inputModel").val();

    var airplane = {
        model : modelName
    }

    var jsonAirplane = JSON.stringify(airplane);
    
    $.ajax({
        url: api + "/create",
        type:"POST",
        data: jsonAirplane,
        contentType: "application/json",
        success: function(result) {
            // On successful POST, reload the datatable with new data.
            getAirplanes();
        }
    });
}

$(document).ready(function () {
    // Modal submit.
    $("#newAirplaneForm").on('submit', function(e) {
        console.log("Submitted new book form");

        // Post the data from the modal.
        createAirplane();

        // Reset modal to hide and no values.
        $('#newAirplaneModal').modal('hide');
        $("#inputModel").val("");
    });

    // Load DataTable with data format.
    $('#table').DataTable({
        columns: [
            { "data": "id" },
            { "data": "model" },
            { "data": "location" },
            { "data": "fuel"}
        ]
    });

    // Load first data.
    getAirplanes();
});