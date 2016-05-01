/* Final Project: A Text Adventure Game */
/* Date: 05-May-2016 */
/* Author: A01371166 Ivan David Diaz Sanchez */

/* File: public/js/animations.js */

// Animate text entrance upon loading the pages.
$(function() {
  $('.jumbotron').animate({top: '10%'}, 300, 'linear');
});

$(function() {
  $('.loginbg').animate({top: '70px'}, 300, 'linear');
});

$(function() {
  $('.gamebg').animate({top: '20px',
                        height: '85%'},
    1000, 'linear');
});
