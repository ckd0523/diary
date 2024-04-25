const Editor = toastui.Editor;
const editor = new Editor({
    el: document.querySelector('#editor'),
    height: '600px',
    initialEditType: 'wysiwyg', // 최초로 보여줄 에디터 타입 (markdown || wysiwyg)
    placeholder: '내용을 입력해주세요.',
    previewStyle: 'vertical', // 마크다운 프리뷰 스타일 (tab || vertical)
    /* start of hooks */
    hooks: {
        async addImageBlobHook(blob, callback) { // 이미지 업로드 로직 커스텀
            try {
                /*
                 * 1. 에디터에 업로드한 이미지를 FormData 객체에 저장
                 *    (이때, 컨트롤러 uploadEditorImage 메서드의 파라미터인 'image'와 formData에 append 하는 key('image')값은 동일해야 함)
                 */
                const formData = new FormData();
                formData.append('image', blob);

                // 2. FileApiController - uploadEditorImage 메서드 호출
                const response = await fetch('/tui-editor/image-upload', {
                    method: 'POST',
                    body: formData,
                });

                // 3. 컨트롤러에서 전달받은 디스크에 저장된 파일명
                const filename = await response.text();
                console.log('서버에 저장된 파일명 : ', filename);

                // 4. addImageBlobHook의 callback 함수를 통해, 디스크에 저장된 이미지를 에디터에 렌더링
                const imageUrl = `/tui-editor/image-print?filename=${filename}`;
                callback(imageUrl, 'image alt attribute');

            } catch (error) {
                console.error('업로드 실패 : ', error);
            }
        },

    }
});
editor.setHTML(document.getElementById('diary-content').value)


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

        fetch(`/api/diaries/${id}`, {
            method: 'PUT',
            headers : {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({
                title: document.getElementById("title").value,
                content: editor.getHTML(),
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
        fetch("/api/diaries", {
            method: 'POST',
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({
                title: document.getElementById("title").value,
                content: editor.getHTML(),
                date: document.getElementById("date").value,
            }),
        }).then(() => {
            alert( document.getElementById("date").value + "일도 수고했어요!!");
            location.replace("/calendar");
        });
    });
}
