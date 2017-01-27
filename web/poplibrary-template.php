<?php

    /**
	 * Template Name: PopLibrary
	 *
     * The basic template file for poplibrary.
     */

    class PopLibrary {

        /**
         * Action hooks
         */
        public function run() {
            add_action( 'pop_library', array( $this, 'enqueue_pl_scripts' ));
            add_action( 'pop_library', array( $this, 'enqueue_pl_styles'));
            add_action( 'wp_ajax_load_pl', array( $this, 'load_pl'));   // Behavior for logged in users
            add_action( 'wp_ajax_nopriv_load_pl', array( $this, 'load_pl'));   // Behavior for logged out users
        }

        /**
         * Register template styles and scripts
         */
        public function register_pl_scripts() {
            wp_register_script( 'pl-script', get_template_directory_uri() . '/js/pl.js', array('jquery'), null, true);
            wp_register_style( 'pl-style', get_template_directory_uri() . '/css/pl.css');
        }

        /**
         * Enqueues template-specific scripts
         */
        public function enqueue_pl_scripts() {
            wp_enqueue_script( 'pl-script' );
        }

        /**
         * Enqueues template-specific styles
         */
        public function enqueue_pl_styles() {
            wp_enqueue_style( 'pl-style' );
        }

        /**
         * Loads the entire pop library
         */
        public function load_pl() {
            if ( is_user_logged_in ) {
                // Open library if user is logged in

                // Grab library information if it exists
                get_library(wp_get_current_user());

            } else {
                // Show login box otherwise
                // Potentially put wp_redirect here for prototype
                do_shortcode( '[theme-my-login]' );
            }
        }

        /**
         * Generates view based on supplied user
         */
        function get_library($pop_user) {
            echo $pop_user->user_login;
        }
    }

    $lib = new PopLibrary;
    $lib->run();

?>
