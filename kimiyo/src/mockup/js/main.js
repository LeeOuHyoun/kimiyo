var Amber = {
    init : function() {
        this.mobileHeader();
        this.resizeMain();
        this.headerBackground();
        this.initSlider();
        this.initClientsSlider();
        this.initCommonCarousel();
        this.initCheckboxes();
        this.initLoginMenu();
        this.initMap();
        this.initEntirePageSlider();
        this.initPortfolioImages();
        this.initPortfolio();
        this.initElementTransitions();
        this.initContactForm();
        this.initDropdown();
        this.initSearch();
    },
    initSearch : function() {
        $('.search-group').find('.btn').click(this.toggleSearch);
    },
    initContactForm : function() {
        $('#contact-form').submit(this.submitContact);
    },
    newPos : function(x, windowHeight, pos, adjuster, inertia) {
        return x + "% " + (-((windowHeight + pos) - adjuster) * inertia)  + "px";
    },
    parallax : function() {
        var $window = $(window);
        var parallax = $(".parallax");

        var windowHeight = $window.height();

        var pos = $window.scrollTop();

        parallax.css({'backgroundPosition': Amber.newPos(100, windowHeight, pos, 3500, 0.2)});
    },
    toggleMobileDropdown : function(e) {
        var dropdown = $(this).parent().find('>ul');
        if(dropdown.is(':visible')) {
            $(this).html('<i class="entyp-level-down-1"></i>');
            dropdown.slideUp(300);
        } else {
            $(this).html('<i class="entyp-level-up-1"></i>');
            dropdown.slideDown(300);
        }
        return false;
    },
    mobileHeader : function() {
        var menu = $('.navbar-nav');
        menu.find('>li').each(function() {
            var item = $(this);
            if(item.find('ul').length) {
                item.addClass('has-dropdown');
                $('<button type="button" class="open-dropdown"><i class="entyp-level-down-1"></i></button>').insertAfter(item.find('>a'));
            }
        });
        $('.open-dropdown').click(this.toggleMobileDropdown);
    },
    toggleSearch : function(e) {
        e.preventDefault();
        var searchGroup = $('.search-group');
        if(searchGroup.hasClass('open')) {
            searchGroup.removeClass('open');
        } else {
            searchGroup.addClass('open');
            searchGroup.find('.form-control').focus();
        }
    },
    resizeMain : function() {
        var container = $('.fill-page'),
            headerHeight = $('.navbar').outerHeight(),
            footerHeight = $('.main-footer').outerHeight(),
            windowHeight = $(window).height(),
            properHeight = windowHeight - headerHeight - footerHeight;
        container.css({
            'min-height' : properHeight + 'px'
        })
    },
    initLoginMenu : function() {
        $('.open-login').click(function(event){
            $('.login-modal').addClass('login-show');
            $('main').addClass('blurry');
            $('.navbar').addClass('blurry');
            event.preventDefault();
        });
        $('.login-close').click(function(event){
            $('.login-modal').removeClass('login-show');
            $('main').removeClass('blurry');
            $('.navbar').removeClass('blurry');
            event.preventDefault();
        });
    },
    headerBackground : function() {
        if($(window).width() < 768) return false;
        var scrollTop = $(window).scrollTop(),
            navigation = $('.navbar-default'),
            navbarBackground = navigation.find('.background'),
            sliderH = 20;
            percentage = (100 * scrollTop) / sliderH / 100;
        var body = $('body');
        if(scrollTop <= 20 && body.hasClass('transparent-menu')) {
            navigation.addClass('transparent-background');
        } else {
            navigation.removeClass('transparent-background');
        }
        if(body.hasClass('transparent-menu')) {
            if(percentage <= 1) {
                navbarBackground.fadeTo(0, percentage);
            } else {
                navbarBackground.fadeTo(0, 1);
            }
        }
        return true;
    },
    initSlider : function() {
        var fxSlider = $('.top-slider'),
            sliderWrap = $('.slider-wrap');
        fxSlider.bxSlider({
            mode: "fade",
            speed : 1100,
            auto : true,
            selector : ">.item",
            controls : false,
            onSlideBefore : function($slideElement, oldIndex, newIndex) {
                setTimeout(function() {
                    fxSlider.find('.item').eq(newIndex).addClass('navInNext');
                    fxSlider.find('.item').eq(oldIndex).addClass('navOutNext');
                }, 50);
            },
            onSlideNext: function($slideElement, oldIndex, newIndex) {
                setTimeout(function() {
                    fxSlider.find('.item').eq(newIndex).addClass('navInNext');
                    fxSlider.find('.item').eq(oldIndex).addClass('navOutNext');
                }, 50);
            },
            onSlidePrev: function($slideElement, oldIndex, newIndex) {
                setTimeout(function() {
                    fxSlider.find('.item').eq(oldIndex).addClass('navOutPrev');
                    fxSlider.find('.item').eq(newIndex).addClass('navInPrev');
                }, 50);
            },
            onSlideAfter : function($slideElement, oldIndex, newIndex) {
                fxSlider.find('.item.current').removeClass('current');
                fxSlider.find('.item').eq(newIndex).addClass('current');
                fxSlider.find('.navOutNext').removeClass('navOutNext');
                fxSlider.find('.navOutPrev').removeClass('navOutPrev');
                fxSlider.find('.navInNext').removeClass('navInNext');
                fxSlider.find('.navInPrev').removeClass('navInPrev');
            }
        });
        sliderWrap.find('.carousel-control').click(function(e) {
            var button = $(this);
            if(button.data('slide') == 'next') {
                fxSlider.goToNextSlide();
            }
            if(button.data('slide') == 'prev') {
                fxSlider.goToPrevSlide();
            }
            e.preventDefault();
        });
        if($('body').hasClass('no-slider')) {
            $('.top-slider').find('.carousel-control').css('display','none');
            $('.bx-pager').css('display','none');
        }
    },
    initClientsSlider : function() {
        var slider = $('.clients-slider');
        slider.bxSlider({
                mode: "fade",
                speed : 1000,
                auto : true,
                selector : ">.item",
                controls : false
        });
    },
    initCommonCarousel : function() {
        var sliderWrap = $('.common-slider-wrap');
        var slider = sliderWrap.find('.common-slider');
        slider.bxSlider({
            mode: "horizontal",
            speed : 800,
            auto : true,
            selector : ">.item",
            controls : false
        });
        sliderWrap.find('.carousel-control').click(function(e) {
            var button = $(this);
            if(button.data('slide') == 'next') {
                slider.goToNextSlide();
            }
            if(button.data('slide') == 'prev') {
                slider.goToPrevSlide();
            }
            e.preventDefault();
        });
    },
    initPortfolioImages : function() {
        $('.portfolio-wrap').find('article').each(function() {
            var item = $(this);
            item.find('.image-background').css({
               'background-image' : "url('" + item.data('image') + "')"
            });
        });
    },
    initPortfolio : function() {
        var $container =  $('.portfolio-wrap'),
            windowW = $(window).width();
        if($container.hasClass('isotope')) {
            $container.isotope('destroy');
        }
        var defaultW = ($container.outerWidth() / 8);
        if(windowW < 992) {
            defaultW = ($container.outerWidth() / 6);
        }
        if(windowW < 768) {
            defaultW = ($container.outerWidth() / 2);
        }
        $container.find('article').outerWidth(defaultW * 2).outerHeight(defaultW * 2);
        $container.find('.width1').outerWidth(defaultW);
        $container.find('.width2').outerWidth(defaultW * 2);
        $container.find('.height1').outerHeight(defaultW);
        $container.find('.height2').outerHeight(defaultW * 2);
        $container.isotope({
            itemSelector : 'article',
            layoutMode : 'perfectMasonry',
            perfectMasonry: {
                columnWidth: defaultW,
                rowHeight: defaultW
            }
        });
        $('.portfolio-filters').find('button').click(function(e) {
            $('.portfolio-filters').find('ul').find('.active').removeClass('active');
            var $el = $(this);
            $el.parent().addClass('active');
            var selector = $el.attr('data-filter');
            $container.isotope({ filter: selector });
            e.preventDefault();
        });

        $container.find('article').find('.overlay').each( function() { $(this).hoverdir({
            hoverDelay : 75
        }); } );
    },
    initMap : function() {
        $('.map-container').bind('inview', function(isInView) {
            if (isInView) {
                if(!$(this).hasClass('map-init')) {
                    $('.map-container').addClass('map-init').gmap3({
                        map: {
                            address: "350 5th Ave, New York, NY 10118, United States",
                            options: {
                                zoom: 15,
                                mapTypeId: google.maps.MapTypeId.ROADMAP,
                                mapTypeControl: false,
                                mapTypeControlOptions: {
                                    style: google.maps.MapTypeControlStyle.DEFAULT
                                },
                                navigationControl: true,
                                scrollwheel: false,
                                streetViewControl: false,
                                styles: [{"featureType":"landscape","stylers":[{"saturation":-100},{"lightness":60}]},{"featureType":"road.local","stylers":[{"saturation":-100},{"lightness":40},{"visibility":"on"}]},{"featureType":"transit","stylers":[{"saturation":-100},{"visibility":"simplified"}]},{"featureType":"administrative.province","stylers":[{"visibility":"off"}]},{"featureType":"water","stylers":[{"visibility":"on"},{"lightness":30}]},{"featureType":"road.highway","elementType":"geometry.fill","stylers":[{"color":"#ef8c25"},{"lightness":40}]},{"featureType":"road.highway","elementType":"geometry.stroke","stylers":[{"visibility":"off"}]},{"featureType":"poi.park","elementType":"geometry.fill","stylers":[{"color":"#b6c54c"},{"lightness":40},{"saturation":-40}]},{}]
                            }
                        },
                        marker:{
                            address: "350 5th Ave, New York, NY 10118, United States"
                        }
                    });
                }
            }
        });
    },
    initDropdown : function() {
        $('select').selectric({
            maxHeight: 250
        });
    },
    initCheckboxes : function() {
        $('.amber-checkbox').find('a').on('click', function(event) {
            var item = $(this);
            console.log(item);
            if(item.hasClass('checked')) {
                item.removeClass('checked');
                item.prev('input').val("false");
            } else {
                item.addClass('checked');
                item.prev('input').val("true");
            }
            event.preventDefault();
        });
    },
    initElementTransitions : function() {
        if($(window).width() >= 786) {
            //header
            $('h1').css('opacity', 0).bind('inview', function(isInView) {
                if (isInView) {$(this).addClass('animated fadeInUp delayed-1');}
            });
            $('.title-icon').css('opacity', 0).bind('inview', function(isInView) {
                if (isInView) {$(this).addClass('animated fadeInUp delayed-1');}
            });

            //services
            $('.services-summary').css('opacity', 0).bind('inview', function(isInView) {
                if (isInView) {$(this).addClass('animated fadeInUp delayed-2');}
            });
            $('.service').each(function(index){
                var item = $(this);
                var fadeType = index%2 == 0 ? "fadeInUp" : "fadeInDown";
                item.css('opacity', 0).bind('inview', function(isInView) {
                    if (isInView) {
                        $(this).addClass('animated delayed-2').addClass(fadeType);
                    }
                });
            });

            //portfolio
            $('.portfolio-filters').css('opacity', 0).bind('inview', function(isInView) {
                if (isInView) {$(this).addClass('animated fadeInUp delayed-2');}
            });
            $('.box').css('opacity', 0).bind('inview', function(isInView) {
                if (isInView) {$(this).addClass('animated fadeInUp delayed-2');}
            });
            $('.common-slider-wrap').css('opacity', 0).bind('inview', function(isInView) {
                if (isInView) {$(this).addClass('animated fadeInUp delayed-2');}
            });
            $('.description').css('opacity', 0).bind('inview', function(isInView) {
                if (isInView) {$(this).addClass('animated fadeInUp delayed-3');}
            });
            $('.portfolio-wrap').find('.image-background').each(function(index){
                var item = $(this);
                item.css('opacity', 0).bind('inview', function(isInView) {
                    if (isInView) {
                        $(this).addClass('animated flipInY');
                        if(index%4 == 0) {
                            $(this).addClass("delayed-1");
                        } else if(index%4 == 1) {
                            $(this).addClass("delayed-2");
                        } else if(index%4 == 2) {
                            $(this).addClass("delayed-3");
                        } else if(index%4 == 3) {
                            $(this).addClass("delayed-4");
                        }
                    }
                });
            });

            //about
            $('.clients').css('opacity', 0).bind('inview', function(isInView) {
                if (isInView) {$(this).addClass('animated fadeInUp delayed-1');}
            });

            //shortcodes
            $('.shortcodes-set').css('opacity', 0).bind('inview', function(isInView) {
                if (isInView) {$(this).addClass('animated fadeInUp delayed-1');}
            });
            var pricingBox = $('.pricing-box');
            pricingBox.css('opacity', 0).bind('inview', function(isInView) {
                if (isInView) {$(this).addClass('animated fadeInUp delayed-1');}
            });
            pricingBox.find('.price').bind('inview', function(isInView) {
                if (isInView) {$(this).addClass('animated pulse delayed-2');}
            });
            var actionCall = $('.action-call');
            actionCall.find('.text').css('opacity', 0).bind('inview', function(isInView) {
                if (isInView) {$(this).addClass('animated fadeInUp delayed-2');}
            });
            actionCall.find('.btn').css('opacity', 0).bind('inview', function(isInView) {
                if (isInView) {$(this).addClass('animated fadeInUp delayed-3');}
            });

            //blog
            var blogPost = $('.blog-post');
            blogPost.css('opacity', 0).bind('inview', function(isInView) {
                if (isInView) {$(this).addClass('animated fadeInUp delayed-2');}
            });
            blogPost.find('.text-editor *').css('opacity', 0).bind('inview', function(isInView) {
                if (isInView) {$(this).addClass('animated fadeInUp delayed-2');}
            });
            $('.widget').css('opacity', 0).bind('inview', function(isInView) {
                if (isInView) {$(this).addClass('animated fadeInUp delayed-2');}
            });
            $('.comments').find('h3').css('opacity', 0).bind('inview', function(isInView) {
                if (isInView) {$(this).addClass('animated fadeInUp delayed-2');}
            });
            $('.comment').css('opacity', 0).bind('inview', function(isInView) {
                if (isInView) {$(this).addClass('animated fadeInUp delayed-2');}
            });
            $('.write-comment').css('opacity', 0).bind('inview', function(isInView) {
                if (isInView) {$(this).addClass('animated fadeInUp delayed-2');}
            });

            //contact
            $('.contact-form').css('opacity', 0).bind('inview', function(isInView) {
                if (isInView) {$(this).addClass('animated fadeInUp delayed-2');}
            });

            //about
            $('.about-box').css('opacity', 0).bind('inview', function(isInView) {
                if (isInView) {$(this).addClass('animated fadeInUp delayed-1');}
            });
            $('.team-member').each(function(index){
                var item = $(this);
                var fadeType = index%2 == 0 ? "fadeInUp" : "fadeInDown";
                item.css('opacity', 0).bind('inview', function(isInView) {
                    if (isInView) {
                        $(this).addClass('animated delayed-2').addClass(fadeType);
                    }
                });
            });

            //footer
            $('.footer-widget').css('opacity', 0).bind('inview', function(isInView) {
                if (isInView) {$(this).addClass('animated fadeInUp delayed-1');}
            });
        }

        //effects on mobile also
        $('.progress-bar').bind('inview', function(isInView) {
            if (isInView) {
                var item = $(this);
                var width = item.attr('aria-valuenow');
                item.css('width', width + "%");
            }
        });
    },
    initEntirePageSlider : function() {
        var windowHeight = $(window).height();
        var sliderWrap = $('.slider-wrap');
        sliderWrap.find('.top-slider').css('height', windowHeight + "px");
        sliderWrap.find('.image-background').css('height', windowHeight + "px");
        sliderWrap.find('.bx-viewport').css('height', windowHeight + "px");
    },
    submitContact : function() {
        $('#message-submit').text('Sending...');
        $.ajax({
            url: '/contact.php',
            type: "POST",
            data: {
                "message-select" : $('#message-select').val(),
                "message-name" : $('#message-name').val(),
                "message-email" : $('#message-email').val(),
                "message" : $('#message').val(),
                "message-submit" : true
            }
        }).done(function(rsp) {
            alert('message sent');
            $('#message-submit').text('Send Message').attr('disabled', 'disabled');
        });
        e.preventDefault();
    }
};
$(document).ready(function() {
    Amber.init();
});

$(window).bind('scroll', function(){
    if(jQuery(window).width() > 766) {
        Amber.parallax();
        Amber.headerBackground();
    }
});

$(window).resize(function() {
    Amber.initPortfolio();
    if($(window).width() < 768) {
        $('.navbar-default').removeClass('transparent-background');
    } else {
        Amber.headerBackground();
    }
    if(jQuery(window).width() > 766) {
        Amber.parallax();
    }
});
