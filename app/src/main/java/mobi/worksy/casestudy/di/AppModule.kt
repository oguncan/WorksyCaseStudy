package mobi.worksy.casestudy.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import mobi.worksy.casestudy.application.WorksyApplication

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    fun provideApplicationContext(@ApplicationContext context: Context): WorksyApplication {
        return context as WorksyApplication
    }
}