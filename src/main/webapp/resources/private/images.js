function showImage(asd) {
    $("#img")[0].src = asd.href;
}
function deleteImage(id) {
    send([], [], 'DELETE', id, function (data_req) {
        console.log("Kuva poistettu.")
    })
}
function send(names, values, type, destination, onreadystatechange) {
    var form = new FormData();
    if (names != null && values != null) {
        for (var i = 0; i < names.length; i++) {
            form.append(names[i], values[i]);
        }
    }
    var data_req = new XMLHttpRequest();
    data_req.open(type, destination, true);
    data_req.setRequestHeader("X-CSRF-TOKEN", $("input[name='_csrf']").val());
    data_req.send(form);
    onreadystatechange = onreadystatechange ? onreadystatechange : function () {
    };
    data_req.onreadystatechange = function () {
        if (data_req.readyState == 4) {
            if (data_req.status == 200 || data_req.status == 201) {
                onreadystatechange(data_req);
            } else {
                alert("Problem?");
            }
        }
    };
}