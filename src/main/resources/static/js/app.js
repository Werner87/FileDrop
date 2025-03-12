document.getElementById("file-upload-form").addEventListener("submit", function (e) {
    e.preventDefault();
    let formData = new FormData();
    formData.append("file", document.getElementById("file-input").files[0]);

    fetch("/upload", {
        method: "POST",
        body: formData,
    })
        .then(response => response.text())
        .then(data => alert(data))
        .catch(error => console.error("Error:", error));
});
