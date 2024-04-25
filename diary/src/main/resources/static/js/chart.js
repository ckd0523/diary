$.ajax({
    url : "/api/diaries",
    method : "GET",
    dataType : "JSON",
    data : JSON.stringify({
        "resultArray" : JSON.stringify(resultArray),
        "dateArray" : JSON.stringify(dateArray)
    }),
    contentType : "application/json; charset=UTF-8",
    success : function(list) {
        chart.setData({
            categories: dateArray,
            series: list
        });

    }//chart suc

});



const chart = toastui.Chart;
const el = document.getElementById('chart');
const data = {
    categories: ['Jun', 'Jul', 'Aug', 'Sep', 'Oct'],
    series: [
        {
            name: 'Budget',
            data: [4000, 4000, 8000, 4000, 4000],
        },
        {
            name: 'Income',
            data: [4000, 4000, 8000, 4000, 4000],
        },
    ],
};
const options = {
    chart: { width: 700, height: 400 },
};

chart.barChart({ el, data, options });
