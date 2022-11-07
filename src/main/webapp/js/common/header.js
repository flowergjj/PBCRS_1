$(document).keydown(function (e) {
    var preventBackspace;
    var preventTab;

    /* 修改退格键行为 */
    if (e.keyCode == 8) {
        var d = e.srcElement || e.target;
        if (d.tagName.toUpperCase() == 'INPUT' || d.tagName.toUpperCase() == 'TEXTAREA') {
        	preventBackspace = d.readOnly || d.disabled;
        } else {
        	preventBackspace = true;
        }
    } else {
    	preventBackspace = false;
    }
    if (preventBackspace) 
    {
        e.preventDefault();
    }
});
