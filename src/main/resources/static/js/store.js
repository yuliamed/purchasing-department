// import '../../styles/general.css';
// import '../../styles/mui.css';
// import './store.css';
// import {defineThePage} from '../../scripts/dom';

// defineThePage();

const fileUploader = document.getElementById('file');

fileUploader.addEventListener('change', (event) => {
    const files = event.target.files;
    console.log('files', files);

    const feedback = document.getElementById('label-for-file');
    const msg = `${files[0].name} загружен!`;
    feedback.innerHTML = msg;

    const feedback_btn = document.getElementById('btn-upload');
    feedback_btn.hidden=false;

});

const btnFile = document.getElementById('btn-upload');

btnFile.addEventListener('submit',()=>{
    const feedback = document.getElementById('label-for-file');
    const msg = ``;
    feedback.innerHTML = msg;
})