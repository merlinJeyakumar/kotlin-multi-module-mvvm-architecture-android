package com.nativedevps.domain.model.example_list


import androidx.annotation.Keep

/* -- JeyK --
 * http://hp-api.herokuapp.com/api/characters
 */
@Keep
data class ExampleApiModelItem(
    var image: String,
    var name: String,
)