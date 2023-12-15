package mobi.worksy.casestudy.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import mobi.worksy.casestudy.data.domain.BadgeUseCase
import mobi.worksy.casestudy.data.domain.PraiseUseCase
import mobi.worksy.casestudy.data.remote.BadgeApi
import mobi.worksy.casestudy.data.remote.PraiseApi
import mobi.worksy.casestudy.data.repository.BadgeRepository
import mobi.worksy.casestudy.data.repository.BadgeRepositoryImpl
import mobi.worksy.casestudy.data.repository.PraiseRepository
import mobi.worksy.casestudy.data.repository.PraiseRepositoryImpl

@Module
@InstallIn(ViewModelComponent::class)
class DataModule {

    @Provides
    fun provideBadgeRepository(badgeApi: BadgeApi): BadgeRepository {
        return BadgeRepositoryImpl(badgeApi)
    }

    @Provides
    fun providePraiseRepository(praiseApi: PraiseApi): PraiseRepository {
        return PraiseRepositoryImpl(praiseApi)
    }

    @Provides
    fun provideBadgeUseCase(badgeRepository: BadgeRepository): BadgeUseCase {
        return BadgeUseCase(badgeRepository)
    }

    @Provides
    fun providePraiseUseCase(praiseRepository: PraiseRepository): PraiseUseCase {
        return PraiseUseCase(praiseRepository)
    }
}