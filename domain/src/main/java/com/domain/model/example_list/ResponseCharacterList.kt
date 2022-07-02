package com.domain.model.example_list

import androidx.annotation.Keep


class ResponseCharacterList : ArrayList<ResponseCharacterList.ExampleApiModelItem>() {
    @Keep
    data class ExampleApiModelItem(
        var image: String,
        var name: String,
    )
}