package mobi.worksy.casestudy.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import mobi.worksy.casestudy.data.remote.BadgeApi
import mobi.worksy.casestudy.data.remote.PraiseApi
import mobi.worksy.casestudy.data.repository.BadgeRepository
import mobi.worksy.casestudy.data.repository.PraiseRepository
import javax.inject.Singleton

@Module
@InstallIn(ViewModelComponent::class)
class ViewModelModule {

    @Provides
    fun provideBadgeRepository(badgeApi: BadgeApi): BadgeRepository {
        return BadgeRepository(badgeApi)
    }

    @Provides
    fun providePraiseRepository(praiseApi: PraiseApi): PraiseRepository {
        return PraiseRepository(praiseApi)
    }
}