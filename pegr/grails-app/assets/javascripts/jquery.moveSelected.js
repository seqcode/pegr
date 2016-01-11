;
(function ($) {
	function moveSelected(select, up) {
		var $select = $(select);
		var $selected = $(":selected", $select);
		if (!up) {
			$selected = $($selected.get().reverse());
		}
		$selected.each(function () {
			var $this = $(this);
			if (up) {
				var $before = $this.prev();
				if ($before.length > 0 && !$before.is(":selected")) {
					$this.insertBefore($before);
				}
			} else {
				var $after = $this.next();
				if ($after.length > 0 && !$after.is(":selected")) {
					$this.insertAfter($after);
				}
			}
		});
	}
	$.fn.moveSelectedUp = function () {
		return this.each(function () {
			moveSelected(this, true);
		});
	};
	$.fn.moveSelectedDown = function () {
		return this.each(function () {
			moveSelected(this, false);
		});
	};
})(jQuery);
