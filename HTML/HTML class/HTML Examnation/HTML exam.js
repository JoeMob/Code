var xhr = new XMLHttpRequest();

function drawQ() {
    xhr.open("GET", "http://" + document.getElementById("serverIP").value + "/obtain/?studentId=" + document.getElementById("studentID").value);
    console.log(xhr);
    xhr.send();
    xhr.onreadystatechange = function() {
        if (xhr.readyState === 4 && xhr.status === 200) {
            document.getElementById("drawQOutput").innerHTML = xhr.responseText;
        }
    }
}

function getQ() {
    xhr.open("GET", "http://" + document.getElementById("serverIP").value + "/obtain/?studentId=" + document.getElementById("studentID").value + "&ordinal=" + document.getElementById("qNumber").value);
    console.log(xhr);
    xhr.send();
    xhr.onreadystatechange = function() {
        if (xhr.readyState === 4 && xhr.status === 200) {
            document.getElementById("getQOutput").innerHTML = xhr.responseText;
        }
    }
}

function answerQ() {
    var xhr = new XMLHttpRequest();
    xhr.open("GET", "http://" + document.getElementById("serverIP").value + "/submit/?studentId=" + document.getElementById("studentID").value + "&ordinal=" + document.getElementById("qNumber").value + "&answer=" + document.getElementById("answer").value);
    console.log(xhr);
    xhr.send();
    xhr.onreadystatechange = function() {
        if (xhr.readyState === 4 && xhr.status === 200) {
            document.getElementById("answerQOutput").innerHTML = xhr.responseText;
        }
    }
}