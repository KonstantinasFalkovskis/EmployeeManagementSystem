$(document).ready( function () {
    var table = $('#employeesTable').DataTable({
        "sAjaxSource": "/agentes",
        "sAjaxDataProp": "",
        "order": [[ 0, "asc" ]],
        "aoColumns": [
            { "mData": "id"},
            { "mData": "name" },
            { "mData": "lastName" },
            { "mData": "ci" },
            { "mData": "centroIdId" }
        ]
    });
});
