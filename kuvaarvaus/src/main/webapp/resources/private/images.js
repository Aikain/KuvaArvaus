var selectedImage;

$(document).ready(function() {
    $("table")
    .tablesorter({widthFixed: true, widgets: ['zebra']})
    .tablesorterPager({container: $("#pager")});

    var dialog = $("#dialog").dialog({
        autoOpen: false,
        height: 350,
        width: 350,
        modal: true,
        buttons: {
            "Määritä": function () {
                updateSingleLink();
                dialog.dialog("close");
            },
            "Peruuta": function () {
                dialog.dialog("close");
            }
        },
        open: function() {
            $(".datetimepicker").datetimepicker({
                format: "Y-m-d H:i"
            });
        }
    });
});

function showImage(asd) {
    $("#img")[0].src = asd.href;
}
function deleteImage(delbtn, id) {
    send([], [], 'DELETE', "images/" + id, function (data_req) {
        var a = data_req.responseURL.split("/");
        $("a[href*='" + a[a.length-1] + "']").parent().remove();
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
function addSingleLink(btn, id, singleLink) {
    $("#singleLink").attr("href", "images/singleLink/" + singleLink + ".png");
    $("#singleLink").text(singleLink);
    $(btn).parent().parent().children(".halfImage").each(function(i, obj) {
        $("#halfImage-" + (i+1)).attr("name", $(obj).children("a").attr("href").replace("images/halfImage/", "").replace(".png", ""));
        $("#halfImage-" + (i+1)).attr("value", $(obj).children("span").text().replace("(", "").replace(")", "").trim());
    });
    selectedImage = id;
    $("#dialog").dialog("open");
}
function updateSingleLink() {
    send(
        $("#dialog form input").map(function(i, obj) { return obj.name; }),
        $("#dialog form input").map(function(i, obj) { return obj.value; }),
        'POST',
        'images/' + selectedImage + '/singleLink'
    );
    $("#dialog form input").each(function(i, obj) {
        $("a[href*='" + obj.name + "']").parent().children("span").text(" (" + obj.value + ")");
    });
}