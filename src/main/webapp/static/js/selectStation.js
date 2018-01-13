$(document).ready(function () {

    $('.footable').footable();
    $('.footable2').footable();

});

function submitGet() {
//获取选择的出发站
    var departureTemp = $('#departure').val();
//获取选择的目的站
    var destinationTemp = $('#destination').val();

    if(destinationTemp == departureTemp){

        location.reload(true);
        $("#SelectError").html("请选择不同的站点");
        return false;
    }
    window.open("/search/direct?departure="+departureTemp+"&&destination="+destinationTemp, "_self");
    // $.ajax({
    //     url: "/station/select",
    //     data: {
    //         'departure': departureTemp,
    //         'destination': destinationTemp
    //     },
    //     type: "post",
    //     traditional: true,
    //     success: function (data) {
    //         for (i = 0; i < data.length; i++) {
    //             console.log("ok:" + data.departure);
    //
    //         }
    //     }
    // );
}