function showImage(asd) {
    $("#img")[0].src = asd.href;
}
function deleteImage(id) {
    send([], [], 'DELETE', id, function(data_req) {
        console.log("Kuva poistettu.")
    })
}