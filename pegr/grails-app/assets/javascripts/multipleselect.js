;(function (factory) {
	if (typeof define === 'function' && define.amd) {
		// AMD. Register as an anonymous module depending on jQuery.
		define(['jquery'], factory);
	} else {
		// No AMD. Register plugin with global jQuery object.
		factory(jQuery);
	}
}(function ($) {
	'use strict';

 	var Multipleselect = (function($) {
		/**	Multipleselect object constructor
		 *
		 *	@class Multipleselect
		 *	@constructor
		**/
		function Multipleselect( $select, settings ) {
			var id = $select.prop('id');
			this.left = $select;
			this.right = $( settings.right ).length ? $( settings.right ) : $('#' + id + '_to');
			this.actions = {
				leftSelected:	$( settings.leftSelected ).length ? $( settings.leftSelected ) : $('#' + id + '_leftSelected'),
				rightSelected:	$( settings.rightSelected ).length ? $( settings.rightSelected ) : $('#' + id + '_rightSelected'),
			};
			

			delete settings.leftSelected;
			delete settings.right;
			delete settings.rightSelected;

			this.options = {
				search: settings.search,
                unique: settings.unique,
			};

			delete settings.search;
            delete settings.unique;

			this.callbacks = settings;
			
			this.init();
            this.candidates = this.left.find('option').clone()
		}
		
		Multipleselect.prototype = {

			// Functions
			init: function() {
				var self = this;
                
				if ( typeof self.callbacks.startUp == 'function' ) {
					self.callbacks.startUp( self.left, self.right, self.options );
				}

                self.options.search.left = $(self.options.search.left);
				self.left.before(self.options.search.left);
				
				self.events( self.actions );
			},
			
			events: function( actions ) {
				var self = this;
				
				self.left.on('dblclick', 'option', function(e) {
					e.preventDefault();
					self.moveToRight(this, e);
				});
				
				self.right.on('dblclick', 'option', function(e) {
					e.preventDefault();
					self.moveToLeft(this, e);
				});

				// append left filter
                self.options.search.left.on('keyup', function(e) {
                    var regex = new RegExp(this.value,"ig");
                    self.left.empty();
                    self.candidates.each(function(i, option) {
                        if (option.text.search(regex) >= 0) {
                            self.left.append(option)
                        } 
                    });
                });

				// select all the options from right side
				// when submiting the parent form
				self.right.closest('form').on('submit', function(e) {
					self.right.children().prop('selected', true);
				});
				
				// dblclick support for IE
				if ( navigator.userAgent.match(/MSIE/i)  || navigator.userAgent.indexOf('Trident/') > 0 || navigator.userAgent.indexOf('Edge/') > 0) {
					self.left.dblclick(function(e) {
						actions.rightSelected.trigger('click');
					});
					
					self.right.dblclick(function(e) {
						actions.leftSelected.trigger('click');
					});
				}
				
				actions.rightSelected.on('click', function(e) {
					e.preventDefault();
					var options = self.left.find('option:selected');
					
					if ( options ) {
						self.moveToRight(options, e);
					}

					$(this).blur();
				});
				
				actions.leftSelected.on('click', function(e) {
					e.preventDefault();
					var options = self.right.find('option:selected');
					
					if ( options ) {
						self.moveToLeft(options, e);
					}

					$(this).blur();
				});
				

			},
			
			moveToRight: function( options, event, silent, skipStack ) {
				var self = this;

				if ( typeof self.callbacks.moveToRight == 'function' ) {
					return self.callbacks.moveToRight( self, options, event, silent, skipStack );
				} else {	
                    if (self.options.unique) {
                        self.right.append(options); 
                    }else{
					   self.right.append(options.clone());
                    }
					return self;
				}
			},
			
			moveToLeft: function( options, event, silent, skipStack ) {
				var self = this;
				
				if ( typeof self.callbacks.moveToLeft == 'function' ) {
					return self.callbacks.moveToLeft( self, options, event, silent, skipStack );
				} else {		
                    if (self.options.unique) {
                        self.left.append(options); 
                    }else{
				        options.remove();
                    }
					return self;
				}
			},
		}
		
		return Multipleselect;
	})($);
	
	$.multipleselect = {
		defaults: {
			/**	will be executed once - remove from $left all options that are already in $right
			 *
			 *	@method startUp
			**/
			startUp: function( $left, $right, $options ) {
                if ($options.unique) {
                    $right.find('option').each(function(index, option) {
                        $left.find('option[value="' + option.value + '"]').remove();});
                }
			},
		}
	};

	$.fn.multipleselect = function( options ) {
		return this.each(function() {
			var $this = $(this),
				data = $this.data();
			
			var settings = $.extend({}, $.multipleselect.defaults, data, options);
			
			return new Multipleselect($this, settings);
		});
	};
}));
