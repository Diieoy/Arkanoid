function showPage(id) {
    var page = document.getElementById(''+id);

    if (page.style.visibility === 'hidden') {
        page.style.visibility = 'visible';
    } else {
        page.style.visibility = 'hidden';
    }
}
