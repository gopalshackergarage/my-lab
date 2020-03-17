package arrow.typeclasses.diframework

import arrow.typeclasses.User

fun UserDao.toUserFromDatabase(): User = realWorld {
    User(id)
}

fun UserDto.toUserFromNetwork(): User = realWorld {
    User(id)
}