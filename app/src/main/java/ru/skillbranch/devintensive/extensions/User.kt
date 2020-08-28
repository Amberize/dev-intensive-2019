package ru.skillbranch.devintensive.extensions

import ru.skillbranch.devintensive.models.User
import ru.skillbranch.devintensive.models.UserView
import ru.skillbranch.devintensive.utils.Utils

fun User.toUserView() : UserView {
    return UserView(
        id,
        fullName = "$firstName $lastName",
        nickName = Utils.transliteration("$firstName $lastName"),
        initials = Utils.toInitials(firstName, lastName),
        avatar = avatar,
        status = if (lastVisit == null) "N/A" else if (isOnline) "Online" else "Last visit on ${lastVisit?.humanizeDiff()}"
    )
}