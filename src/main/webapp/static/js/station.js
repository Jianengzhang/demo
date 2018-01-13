$(function () {
    $.ajax({
        type: "post",
        url: "/station/option",
        dataType: "json",
        success: function (data) {
            var seldeparture = data.data[0];
            $.each(data.data, function (i, item) {
                var $option = '<option  value=' + item + '>' + item + '</option>';
                $("#departure").append($option);
                $("#destination").append($option);
            });
        }
    })});

    $('#departure').change(function () {
        var _this = $(this);
        var departureCode = _this.find('option:selected').attr('code');

        setTimeout(function () {
            $('#departure').attr('departureId', departureCode);
        }, 100)
    });
    $('#destination').change(function () {
        var _this = $(this);
        var destinationCode = _this.find('option:selected').attr('code');

        setTimeout(function () {
            $('#destination').attr('destinationId', destinationCode);
        }, 100)
    });