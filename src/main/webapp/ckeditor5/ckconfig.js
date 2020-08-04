ClassicEditor
    .create(document.getElementById('editor'), {
        toolbar: {
            items: [
                'heading',
                '|',
                'bold',
                'italic',
                'alignment',
                'link',
                'bulletedList',
                'numberedList',
                '|',
                'indent',
                'outdent',
                '|',
                'imageUpload',
                'blockQuote',
                'insertTable',
                'mediaEmbed',
                'fontColor',
                'fontSize',
                'undo',
                'redo'
            ]
        },
        language: 'uk',
        image: {
            toolbar: [
                'imageTextAlternative',
                'imageStyle:full',
                'imageStyle:side'
            ]
        },
        table: {
            contentToolbar: [
                'tableColumn',
                'tableRow',
                'mergeTableCells'
            ]
        },
        licenseKey: '',
    })
    .then(editor => {
        window.editor = editor;
    })
    .catch(error => {
        console.error('Oops, something gone wrong!');
        console.error('Please, report the following error in the https://github.com/ckeditor/ckeditor5 with the build id and the error stack trace:');
        console.warn('Build id: 4rlyryfrmzpb-kanr9nwzb6ts');
        console.error(error);
    });