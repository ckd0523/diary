
window.onload = function() {
    $.ajax({
        url: "/api/diaries",
        method: "GET",
        dataType: "JSON",
        success: function (result) {
            grid.resetData(result);
        }
    });
}

const Grid = tui.Grid;
    const grid = new Grid({
        el: document.getElementById('grid'),
        scrollX: false,
        scrollY: false,
        columns: [
            {
                header: 'title',
                name: 'title'
            },
            {
                header: 'content',
                name: 'content'
            },
            {
                header: 'author',
                name: 'author'
            },
            {
                header: 'date',
                name: 'date'
            }
        ]
    });


