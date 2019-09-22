/**
 * 
 */



$j10(document).ready(function() {
    
    $j10('.eval-js').on('click', function ( e ) {
        e.preventDefault();

        if ( !$j10(this).hasClass('generate-toast') ) {
            var code = $j10(this).siblings('pre').find('code').text();
            code.replace("<span class='hidden'>", '');
            code.replace("</span");

            eval( code );
        };
    });

    $j10('#icon-type').on('change', function () {
        if ( !$j10(this).val() ) {
            $j10('.custom-color-info').show();
            $j10('.toast-icon-line').hide();
            $j10('.toast-bgColor-line').show();
            $j10('.toast-textColor-line').show();
        } else {
            $j10('.toast-icon-line').show();
            $j10('.custom-color-info').hide();
            $j10('.toast-bgColor-line').hide();
            $j10('.toast-textColor-line').hide();
            $j10('.toast-icon-line span.toast-icon').text( $j10(this).val() );
        }
    });

    // You are about to see some extremely horrible code that can be MUCH MUCH improved,
    // I've knowlingly done it that way, please don't judge me based upon this ;)
    $j10(document).ready(function () {
        
        function generateCode () {
            var text = $j10('.plugin-options #toast-text').val(); 
            var heading = $j10('.plugin-options #toast-heading').val(); 
            var transition = $j10('.toast-transition').val(); 
            var allowToastClose = $j10('#allow-toast-close').val(); 
            var autoHide = $j10('#auto-hide-toast').val(); 
            var stackToasts = $j10('#stack-toasts').val(); 
            var toastPosition = $j10('#toast-position').val() 
            var toastBg = $j10('#toast-bg').val(); 
            var toastTextColor = $j10('#toast-text-color').val();
            var toastIcon = $j10('#icon-type').val();
            var textAlign = $j10('#text-align').val();
            var toastEvents = $j10('#add-toast-events').val();
            var loader = $j10('#show-loader').val();
            var loaderBg = $j10('#loader-bg').val();

            if ( text ) {
                $j10('.toast-text-line').show(); 
                $j10('.toast-text-line .toast-text').text( text ); 
            } else {
                $j10('.toast-text-line').hide() 
                $j10('.toast-text-line .toast-text').text(''); 
            };

            if ( heading ) {
                $j10('.toast-heading-line').show(); 
                $j10('.toast-heading-line .toast-heading').text( heading ); 
            } else {
                $j10('.toast-heading-line').hide() 
                $j10('.toast-heading-line .toast-heading').text(''); 
            }; 

            if ( transition ) {
                $j10('.toast-transition-line').show() 
                $j10('.toast-transition-line .toast-transition').text( transition ); 
            } else {
                $j10('.toast-transition-line').hide(); 
                $j10('.toast-transition-line .toast-transition').text('fade'); 
            } 

            if ( allowToastClose ) {
                $j10('.toast-allowToastClose-line').show(); 
                $j10('.toast-allowToastClose-line .toast-allowToastClose').text( allowToastClose ); 
            } else {
                $j10('.toast-allowToastClose-line').hide(); 
                $j10('.toast-allowToastClose-line .toast-allowToastClose').text( false ); 
            } 

            if ( autoHide && ( autoHide == 'false' ) ) {
                $j10('.toast-hideAfter-line').show(); 
                $j10('.toast-hideAfter-line .toast-hideAfter').text('false'); 
                $j10('.autohide-after').hide(); 
            } else {
                $j10('.toast-hideAfter-line').show(); 
                $j10('.toast-hideAfter-line .toast-hideAfter').text( $j10('#autohide-after').val() ? $j10('#autohide-after').val() : 3000 ); 
                $j10('.autohide-after').show(); 
            } 

            if ( stackToasts && stackToasts != 'true') {
                $j10('.toast-stackLength-line').show(); 
                $j10('.toast-stackLength-line .toast-stackLength').text( stackToasts ); 
                $j10('.stack-length').hide(); 
            } else {
                $j10('.stack-length').show(); 
                $j10('.toast-stackLength-line').show(); 
                $j10('.toast-stackLength-line .toast-stackLength').text( $j10('#stack-length').val() ? $j10('#stack-length').val() : 5 ); 
            } 

            if ( toastPosition && ( toastPosition !== 'custom-position' ) ) {
                $j10('.toast-position-string-line').show(); 
                $j10('.custom-toast-position').hide(); 
                $j10('.toast-position-string-line .toast-position').text( toastPosition ); 
            } else {
                $j10('.toast-position-string-line').hide(); 
                $j10('.toast-position-string-line .toast-position').text(''); 
            } 

            if ( toastPosition && ( toastPosition === 'custom-position' ) ) {
                $j10('.custom-toast-position').show(); 
                $j10('.toast-position-string-obj').show(); 
                var left = $j10('#left-position').val() ? $j10('#left-position').val() : 'auto'; 
                var right = $j10('#right-position').val() ? $j10('#right-position').val() : 'auto'; 
                var top = $j10('#top-position').val() ? $j10('#top-position').val() : 'auto'; 
                var bottom = $j10('#bottom-position').val() ? $j10('#bottom-position').val() : 'auto'; 
                $j10('.toast-position-string-obj .toast-position-left').text( ( left !== 'auto' ) ? left : "'" + left + "'" ); 
                $j10('.toast-position-string-obj .toast-position-right').text( ( right !== 'auto' ) ? right : "'" + right + "'" ); 
                $j10('.toast-position-string-obj .toast-position-top').text( ( top !== 'auto' ) ? top : "'" + top + "'" ); 
                $j10('.toast-position-string-obj .toast-position-bottom').text(  ( bottom !== 'auto' ) ? bottom : "'" + bottom + "'"  ); 
            } else {
                $j10('.toast-position-string-obj').hide(); 
                // $j10('.toast-position-string-obj toast-position').text('');
            } 

            if ( !toastIcon ) {
                if ( toastBg ) {
                    $j10('.toast-bgColor-line').show(); 
                    $j10('.toast-bgColor-line .toast-bgColor').text( toastBg ); 
                } else {
                    $j10('.toast-bgColor-line').hide(); 
                    $j10('.toast-bgColor-line .toast-bgColor').text(''); 
                } 

                if ( toastTextColor ) {
                    $j10('.toast-textColor-line').show(); 
                    $j10('.toast-textColor-line .toast-textColor').text( toastTextColor ); 
                } else {
                    $j10('.toast-textColor-line').hide(); 
                    $j10('.toast-textColor-line .toast-textColor').text(''); 
                } 
            }

            if ( textAlign ) {
                $j10('.toast-textAlign-line').show(); 
                $j10('.toast-textAlign-line .toast-textAlign').text( textAlign ); 
            } else {
                $j10('.toast-textAlign-line').hide(); 
                $j10('.toast-textAlign-line .toast-textAlign').text( ''); 
            } 

            if (loader == 'false') {
                $j10('.toast-textLoader').html('false');
            } else {
                $j10('.toast-textLoader').html('true');
            }
            
            if (loaderBg) {
                $j10('.toast-textLoaderBg').html(loaderBg);
            }

            if ( toastEvents == 'false' ) {
                $j10('.toast-beforeShow-line').hide(); 
                $j10('.toast-afterShown-line').hide(); 
                $j10('.toast-beforeHide-line').hide(); 
                $j10('.toast-afterHidden-line').hide(); 
            } else {
                $j10('.toast-beforeShow-line').show(); 
                $j10('.toast-afterShown-line').show(); 
                $j10('.toast-beforeHide-line').show(); 
                $j10('.toast-afterHidden-line').show(); 
            } 
        }

        $j10('#top-position').on('change', function () { $j10('#bottom-position').val('auto'); });
        $j10('#bottom-position').on('change', function () { $j10('#top-position').val('auto'); });
        $j10('#left-position').on('change', function () { $j10('#right-position').val('auto'); });
        $j10('#right-position').on('change', function () {$j10('#left-position').val('auto'); });
        $j10('.plugin-options :input').on('change', function () {
          $j10.toast().reset('all');
          generateCode();
        });

        $j10('.generate-toast').on('click', function( e ) {
          e.preventDefault();
          generateToast();
        });

        function generateToast () {
            var options = {};

            if ( $j10('.toast-text-line').is(':visible') ) {
                options.text = $j10('.toast-text-line .toast-text').text();
            } 

            if ( $j10('.toast-heading-line').is(':visible') ) {
                options.heading = $j10('.toast-heading').text(); 
            }; 

            if ( $j10('.toast-transition-line').is(':visible') ) {
                options.showHideTransition = $j10('.toast-transition-line .toast-transition').text(); 
            }; 

            if ( $j10('.toast-allowToastClose-line').is(':visible') ) {
                options.allowToastClose = ( $j10('.toast-allowToastClose-line .toast-allowToastClose').text() === 'true' ) ? true : false; 
            }; 

            if ( $j10('.toast-hideAfter-line').is(':visible') ) {
                options.hideAfter = parseInt($j10('.toast-hideAfter-line .toast-hideAfter').text(), 10) || false; 
            }; 

            if ( $j10('.toast-stackLength-line').is(':visible') ) {
                options.stack = parseInt($j10('.toast-stackLength-line .toast-stackLength').text(), 10) || false; 
            }; 

            if ( $j10('.toast-position-string-line').is(':visible') ) {
                options.position = $j10('.toast-position-string-line .toast-position').text(); 
            }; 

            if ( $j10('.toast-position-string-obj').is(':visible') ) {
                options.position = {}; 
                options.position.left =  parseFloat( $j10('.toast-position .toast-position-left').text() ) || 'auto'; 
                options.position.right =  parseFloat( $j10('.toast-position .toast-position-right').text() ) || 'auto'; 
                options.position.top =  parseFloat( $j10('.toast-position .toast-position-top').text() ) || 'auto'; 
                options.position.bottom =  parseFloat( $j10('.toast-position .toast-position-bottom').text() ) || 'auto'; 
            }; 

            if ( $j10('.toast-icon-line').is(':visible') ) {
                options.icon = $j10('.toast-icon-line .toast-icon').text();
            };

            if ( $j10('.toast-bgColor-line').is(':visible') ) {
                options.bgColor = $j10('#toast-bg').val(); 
            }; 

            if ( $j10('.toast-text-color').is(':visible') ) {
                options.textColor = $j10('#toast-text-color').val(); 
            }; 

            if ( $j10("#text-align").is(':visible') ) {
                options.textAlign = $j10('#text-align').val(); 
            };

            options.loader = $j10('.toast-textLoader').html() === 'false' ? false : true;
            options.loaderBg = $j10('.toast-textLoaderBg').html();

            $j10.toast( options ); 
        }

        generateCode(); 
    });
});