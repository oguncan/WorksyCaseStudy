package mobi.worksy.casestudy.data.repository

import mobi.worksy.casestudy.data.remote.PraiseApi
import javax.inject.Inject

class PraiseRepository @Inject constructor(
    val praiseApi: PraiseApi
) {
}