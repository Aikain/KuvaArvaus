function showImage(asd) {
    $("#img")[0].src = asd.href;
}
function deleteImage(delbtn, id) {
    send([], [], 'DELETE', "images/" + id, function (data_req) {
        var a = data_req.responseURL.split("/");
        $("ul:contains(" + a[a.length-1] + ")").remove();
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
    data_req.setRequestHeader("X-CSRF-TOKEN", $("meta[name='_csrf']").attr("th:content"));
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
