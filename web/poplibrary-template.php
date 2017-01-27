<?php

    /**
	 * Template Name: PopLibrary
	 *
     * The basic template file for poplibrary.
     */

     // THIS CODE DOESN'T APPEAR TO DO ANYTHING EVEN IN FUNCTIONS.PHP
     function load_css_js() {
         wp_enqueue_style( 'pl-style', get_template_directory_uri() . '/css/pl.css');

         wp_register_script( 'pl-script', get_template_directory_uri() . '/js/pl.js', array('jquery'), NULL, false);
         wp_localize_script('pl-script', 'myAjax', array(
                     'ajaxurl' => admin_url('admin-ajax.php')
                 ));

        wp_enqueue_script( 'pl-script' );
     }

     add_action( 'wp_enqueue_scripts', 'load_css_js');

     wp_head();


?>
<!--<script type="text/javascript" src="https://cybrotronics.com/wp-content/themes/colormag/js/pl.js"></script><div class="dBackgroundBox" id="bgModBox">
<p>
  <a id="bgModBox" onclick="ModifyBGColor('bgModBox','#FF0000');">Click here to modify the background color!</a>
</p>
-->

<div class="box">
  <div id="pl-library">This is the library panel</div>
  <div id="pl-search">This is the search panel</div>
</div>
