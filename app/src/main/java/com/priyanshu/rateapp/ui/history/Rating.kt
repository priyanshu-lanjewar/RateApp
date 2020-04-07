package com.priyanshu.rateapp.ui.history


class Rating {

    var date: String? = null
    var time: String? = null
    var range: String? = null
    var rate: String? = null

    constructor() {

    }

    constructor(date: String?, time: String?, range: String?, rate: String?) {
        this.date = date
        this.time = time
        this.range = range
        this.rate = rate
    }

    fun get_date() = date
    fun get_time() = time
    fun get_range() = range
    fun get_rate() = rate

}
