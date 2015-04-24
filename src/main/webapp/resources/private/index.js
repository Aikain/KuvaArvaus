var imgarray;
var array = Array();

function preview(file) {
    $("#msg").text("Ladataan kuvaa ja piirretään arvoituskuvat..");
    var canvas = $("#preview")[0];
    canvas.getContext("2d").clearRect(0, 0, canvas.width, canvas.height);
    $("#halfImages").html("");
    imgarray = Array();
    var img = document.createElement("img");
    var reader = new FileReader();
    reader.onloadend = function (e) {
        img.src = e.target.result;
        var MAX_WIDTH = 512;
        var MAX_HEIGHT = 512;
        var width = img.width;
        var height = img.height;

        if (width > height) {
            if (width > MAX_WIDTH) {
                height *= MAX_WIDTH / width;
                width = MAX_WIDTH;
            }
        } else {
            if (height > MAX_HEIGHT) {
                width *= MAX_HEIGHT / height;
                height = MAX_HEIGHT;
            }
        }
        var ctx = canvas.getContext("2d");
        ctx.drawImage(img, 0, 0, width, height);

        doHalfImages(ctx, width, height);
    };
    reader.readAsDataURL(file.files[0]);
    $("#upload")[0].innerHTML = $("#upload")[0].innerHTML;

}
function doHalfImages(ctx, width, height) {
    array = [3, 5, 7, 9, 11];
    var isoin = Math.max.apply(null, array);
    var i = 0;
    var canvas2 = document.createElement("canvas");
    canvas2.width = width;
    canvas2.height = height;
    var ctx2 = canvas2.getContext("2d");
    for (var n = 0; n < isoin; n++) {
        var swidth = 50 + Math.random() * 50;
        var sheight = 50 + Math.random() * 50;
        var sx = Math.random() * (width - swidth);
        var sy = Math.random() * (height - sheight);
        ctx2.putImageData(ctx.getImageData(sx, sy, swidth, sheight), sx, sy);
        if (n + 1 == array[i]) {
            imgarray.push(cloneCanvas(canvas2));
            i++;
        }
    }
    drawHalfImages(imgarray);
}
function drawHalfImages(imgarray) {
    var MAX_WIDTH = 256;
    var MAX_HEIGHT = 256;
    var width = imgarray[0].width;
    var height = imgarray[0].height;
    if (width > height) {
        if (width > MAX_WIDTH) {
            height *= MAX_WIDTH / width;
            width = MAX_WIDTH;
        }
    } else {
        if (height > MAX_HEIGHT) {
            width *= MAX_HEIGHT / height;
            height = MAX_HEIGHT;
        }
    }
    for (var i = 0; i < imgarray.length; i++) {
        var myCanvas = imgarray[i];
        var targetCanvas = document.createElement("canvas");
        targetCanvas.id = "halfImage" + (i + 1);
        targetCanvas.className = "halfImage";
        targetCanvas.width = width;
        targetCanvas.height = height;
        targetCanvas.getContext('2d').drawImage(myCanvas, 0, 0, myCanvas.width, myCanvas.height, 0, 0, targetCanvas.width, targetCanvas.height);
        $("#halfImages").append(targetCanvas);
    }
    $("#msg").text("Lähetä kuvat 'lähetä' napista!");
}

var apu;
function sendImages() {
    $("#msg").text("Lähetetään pääkuvaa..");
    send(["file"], [$("#preview")[0].toDataURL("image/png").replace(/^data:image\/(png|jpg|jpeg);base64,/, "")], "POST", "images", function (data_req) {
        $("#msg").text("Pääkuva siirretty.");
        var obj = JSON.parse(data_req.response);
        apu = 0;
        for (var i = 0; i < imgarray.length; i++) {
            send(["file", "visibility"], [imgarray[i].toDataURL("image/png").replace(/^data:image\/(png|jpg|jpeg);base64,/, ""), calculate(imgarray[i])], "POST", "images/" + obj.id + "/halfImage", function () {
                $("#msg").text((apu + 1) + " kuvaa ladattu!");
                if (++apu == imgarray.length)
                    $("#msg").text("Kaikki kuvat ladanneet!");
            });
        }
    });
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

function cloneCanvas(oldCanvas) {
    var newCanvas = document.createElement('canvas');
    var context = newCanvas.getContext('2d');
    newCanvas.width = oldCanvas.width;
    newCanvas.height = oldCanvas.height;
    context.drawImage(oldCanvas, 0, 0, oldCanvas.width, oldCanvas.height);
    return newCanvas;
}

function calculate(canvas) {
    var data = canvas.getContext('2d').getImageData(0, 0, canvas.width, canvas.height).data;
    var n = 0;
    for (var y = 0; y < canvas.height; y++) {
        for (var x = 0; x < canvas.width; x++) {
            if (!data[4 * y * canvas.width + x + 3])
                n++;
        }
    }
    return Math.floor(n / (canvas.width * canvas.height) * 100);
}
