function deleteStation(obj) {
    var tr1 = obj.parentNode.parentNode;
    var num = tr1.rowIndex;
    console.log(num);
    var station = document.getElementById("mytable").rows[num].cells[0].innerHTML;
    console.log(station);
    $.ajax({
        type: "post",
        data: {
            'station': station
        },
        url: "/station/deleteStation",
        success: function () {
            console.log("11111111111111111111111111111111111111111");
            window.open("/station/showAll", "_self");
        }
    })
}

function addStation() {
    var name = $('#addStationName').val();
    console.log(name);
    $.ajax({
        type: "post",
        data: {
            "station": name
        },
        url: "/station/addStation",
        dataType: "json",
        success: function () {
            console.log(" add success");
            window.open("/station/showAll", "_self");
        }
    })
}

function addStationToTrain() {
    var train = $('#addTrain').val();
    var station = $('#addStation').val();
    var leave = $('#arrive').val();
    var arrive = $('#leave').val();
    var distance = $('#distance').val();
    $.ajax({
        type: "post",
        data: {
            "train": train,
            "station": station,
            "leave": leave,
            "arrive": arrive,
            "distance": distance
        },
        url: "/train/add",
        dataType: "json",
        success: function () {
            console.log(" add success");
            window.open("/station/showAll", "_self");
        }
    })

}