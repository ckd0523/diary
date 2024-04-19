
let editor;

ClassicEditor
    .create( document.querySelector( '#editor' ) )
    .then( newEditor => {
        editor = newEditor;
    } )
    .catch( error => {
        console.error( error );
    } );

// 삭제 기능
const deleteButton = document.getElementById("delete-btn");

if(deleteButton){
    deleteButton.addEventListener("click", event => {
        let id = document.getElementById('diaryId').value;
        fetch(`/api/diaries/${id}`, {
            method: 'DELETE'
        })
            .then(()=>{
                alert("아쉬워요 ㅠㅠ");
                location.replace('/calendar');
            });
    });
}

// 수정 기능
// id가 modify-btn인 엘리먼트 조회
const modifyButton = document.getElementById("modify-btn");

if(modifyButton){
    modifyButton.addEventListener("click", event => {
        let params = new URLSearchParams(location.search);
        let id = params.get("id");
        const editorData = editor.getData();
        fetch(`/api/diaries/${id}`, {
            method: 'PUT',
            headers : {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({
                title: document.getElementById("title").value,
                content: editorData,
            })
        })
            .then(() => {
                alert("아차차,,,");
                location.replace(`/diaries/${id}`);
            });
    });
}

// 등록 기능
// id가 create-btn 인 엘리먼트
const createButton = document.getElementById("create-btn");
if(createButton){
    createButton.addEventListener("click", event => {
        const editorData = editor.getData();
        fetch("/api/diaries", {
            method: 'POST',
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({
                title: document.getElementById("title").value,
                content: editorData,
                date: document.getElementById("date").value,
            }),
        }).then(() => {
            alert( document.getElementById("date").value + "일도 수고했어요!!");
            location.replace("/calendar");
        });
    });
}