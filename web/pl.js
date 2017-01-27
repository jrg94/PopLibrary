jQuery(document).ready( function($) {

    $.ajax({
        // If we want this to be dynamic, we have to localize it through wordpress
        url: "http://cybrotronics.com",
        success: function( data ) {
            alert( 'Your home page has ' + $(data).find('div').length + ' div elements.');
        }
    })

})
