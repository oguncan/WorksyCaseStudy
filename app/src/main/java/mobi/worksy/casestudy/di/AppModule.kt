package mobi.worksy.casestudy.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import mobi.worksy.casestudy.application.WorksyApplication
import mobi.worksy.casestudy.data.remote.BadgeApi
import mobi.worksy.casestudy.data.remote.PraiseApi
import mobi.worksy.casestudy.data.repository.BadgeRepository
import mobi.worksy.casestudy.data.repository.PraiseRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    fun provideApplicationContext(@ApplicationContext context: Context): WorksyApplication {
        return context as WorksyApplication
    }


}