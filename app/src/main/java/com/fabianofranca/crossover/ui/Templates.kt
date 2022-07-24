package com.fabianofranca.crossover.ui

import com.fabianofranca.crossover.data.model.UserModel

object Templates {
    val users = listOf(
        UserModel(
            fullName = "John Wick",
            email = "john.wick@gmail.com",
            phone = "(31) 90000-0000",
            picture = ""
        ),
        UserModel(
            fullName = "Normano Nunes",
            email = "normano.nunes@example.com",
            phone = "(76) 2945-9421",
            picture = ""
        )
    )

    val user = users.first()

    const val lorem =
        "Lorem ipsum dolor sit amet, consectetur adipiscing elit. In tristique cursus " +
            "purus, eleifend fermentum sem pretium in. Pellentesque suscipit orci id risus luctus, " +
            "quis pulvinar purus suscipit. Ut viverra facilisis placerat. Donec porttitor dui in dolor " +
            "vestibulum, in porta tortor ultrices. In imperdiet metus augue, sed pharetra lacus " +
            "dapibus at. Pellentesque semper molestie molestie. Ut scelerisque tempor sem, et " +
            "sodales tortor aliquam eu. Suspendisse vestibulum suscipit laoreet. " +
            "Sed dapibus dolor non ornare consequat."
}
