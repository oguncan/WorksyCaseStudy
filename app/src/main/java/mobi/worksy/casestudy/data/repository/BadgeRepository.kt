package mobi.worksy.casestudy.data.repository

import mobi.worksy.casestudy.data.remote.BadgeApi
import javax.inject.Inject

class BadgeRepository @Inject constructor(
    val badgeApi: BadgeApi
) {
}