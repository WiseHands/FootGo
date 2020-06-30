document.addEventListener('DOMContentLoaded', function() {

    (function () {
        var lastcheck = null

        var checkboxes = document.querySelectorAll('label.request-block input[type=checkbox]')

        Array.prototype.forEach.call(checkboxes, function (cbx, idx) {
            cbx.addEventListener('click', function (evt) {

            if ( evt.shiftKey && null !== lastcheck && idx !== lastcheck ) {

                Array.prototype.slice.call(checkboxes, Math.min(lastcheck, idx), Math.max(lastcheck, idx))
                    .forEach(function (ccbx) {
                    ccbx.checked = true
                    })
            }
            lastcheck = idx
            })
        })
    }())

});