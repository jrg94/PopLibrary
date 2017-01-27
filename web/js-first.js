jQuery(document).ready( function($) {

    $.ajax({
        url: "http://cybrotronics.com",
        success: function( data ) {
            alert( 'Your home page has ' + $(data).find('div').length + ' div elements.');
        }
    })

})
