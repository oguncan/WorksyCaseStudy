package mobi.worksy.casestudy


import android.content.Context
import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import mobi.worksy.casestudy.application.WorksyApplication
import mobi.worksy.casestudy.data.domain.BadgeUseCase
import mobi.worksy.casestudy.data.domain.PraiseUseCase
import mobi.worksy.casestudy.data.domain.PraiseUseCaseAllResult
import mobi.worksy.casestudy.data.domain.PraiseUseCaseResult
import mobi.worksy.casestudy.data.model.Badge
import mobi.worksy.casestudy.data.model.BadgeGroupModel
import mobi.worksy.casestudy.data.model.PraiseItemModel
import mobi.worksy.casestudy.data.model.PraiseModel
import mobi.worksy.casestudy.data.repository.BadgeRepository
import mobi.worksy.casestudy.data.repository.PraiseRepository
import mobi.worksy.casestudy.ui.fragment.home.BadgeSliderUiState
import mobi.worksy.casestudy.ui.fragment.home.viewModel.HomeViewModel
import org.junit.jupiter.api.Assertions.*

import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.whenever


class HomeViewModelTest {

    private val testDispatchers = StandardTestDispatcher()

    @Mock
    lateinit var praiseUseCase : PraiseUseCase

    @Mock
    private lateinit var mockContext: Context

    @Mock
    lateinit var badgeUseCase : BadgeUseCase

    private val praiseRepository: PraiseRepository = mock(PraiseRepository::class.java)
    private val badgeRepository: BadgeRepository = mock(BadgeRepository::class.java)
    private val mockApplication: WorksyApplication = mock(WorksyApplication::class.java)


    @BeforeEach
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        Dispatchers.setMain(testDispatchers)
        praiseUseCase = PraiseUseCase(praiseRepository)
        badgeUseCase = BadgeUseCase(badgeRepository)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `test getAllPraiseData withEmptyListSuccess`() = runBlockingTest {

        val mockResponse = PraiseModel(emptyList())
        val expectedResult = PraiseUseCaseAllResult.Success(mockResponse.praiseItem)

        val mockPraiseUseCase: PraiseUseCase = mock(PraiseUseCase::class.java)
        `when`(mockPraiseUseCase.getAllPraiseData()).thenReturn(flowOf(expectedResult))

        val result = mockPraiseUseCase.getAllPraiseData().single()

        assertEquals(expectedResult, result)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `test getAllPraiseData withEmptyData`() = runBlockingTest {

        val mockResponse = PraiseModel(emptyList())
        val expectedResult = PraiseUseCaseAllResult.Success(mockResponse.praiseItem)

        val mockPraiseUseCase: PraiseUseCase = mock(PraiseUseCase::class.java)
        `when`(mockPraiseUseCase.getAllPraiseData()).thenReturn(flowOf(expectedResult))

        val result = mockPraiseUseCase.getAllPraiseData().single()

        assertEquals(expectedResult, result)
    }

    @Test
    fun `test calculateBadgeTotalAvg withNonEmptyList`() {
        val homeViewModel = HomeViewModel(badgeUseCase, praiseUseCase, mockApplication)

        val praiseItems = listOf(
            PraiseItemModel(
                author = emptyList(),
                authorTitle = "",
                badgeDetailInformation = emptyList(),
                badgeSingleString = "",
                createdDate = "",
                createdDateWithDot = "",
                id = "1",
                message = "",
                praiseRating = "5",
                relatedPerson = emptyList()
            ),
            PraiseItemModel(
                author = emptyList(),
                authorTitle = "",
                badgeDetailInformation = emptyList(),
                badgeSingleString = "",
                createdDate = "",
                createdDateWithDot = "",
                id = "2",
                message = "",
                praiseRating = "5",
                relatedPerson = emptyList()
            ),
        )

        val result = homeViewModel.calculateBadgeTotalAvg(praiseItems)

        assertEquals(2, result.first)
        assertEquals(5.0, result.second, 0.0)
    }

    @Test
    fun `test calculateBadgeGroups`() {
        val homeViewModel = HomeViewModel(badgeUseCase, praiseUseCase, mockApplication)
        val expectedGroups = listOf(
            BadgeGroupModel(3, "Değer Katan", 2, 5f),
            BadgeGroupModel(4, "Değişime Açık", 2, 5f),
        )

        val mockBadgeItems = listOf(
            PraiseItemModel(
                author = emptyList(),
                authorTitle = "",
                badgeDetailInformation = arrayListOf(
                    Badge(
                        isSecretFieldValue = false,
                        lookupId = 3,
                        lookupValue = "Değer Katan"
                    )
                ),
                badgeSingleString = "",
                createdDate = "",
                createdDateWithDot = "",
                id = "1",
                message = "",
                praiseRating = "5",
                relatedPerson = emptyList()
            ),
            PraiseItemModel(
                author = emptyList(),
                authorTitle = "",
                badgeDetailInformation = arrayListOf(
                    Badge(
                        isSecretFieldValue = false,
                        lookupId = 3,
                        lookupValue = "Değer Katan"
                    )
                ),
                badgeSingleString = "",
                createdDate = "",
                createdDateWithDot = "",
                id = "2",
                message = "",
                praiseRating = "5",
                relatedPerson = emptyList()
            ),
            PraiseItemModel(
                author = emptyList(),
                authorTitle = "",
                badgeDetailInformation = arrayListOf(
                    Badge(
                        isSecretFieldValue = false,
                        lookupId = 4,
                        lookupValue = "Değişime Açık"
                    )
                ),
                badgeSingleString = "",
                createdDate = "",
                createdDateWithDot = "",
                id = "2",
                message = "",
                praiseRating = "5",
                relatedPerson = emptyList()
            ),
            PraiseItemModel(
                author = emptyList(),
                authorTitle = "",
                badgeDetailInformation = arrayListOf(
                    Badge(
                        isSecretFieldValue = false,
                        lookupId = 4,
                        lookupValue = "Değişime Açık"
                    )
                ),
                badgeSingleString = "",
                createdDate = "",
                createdDateWithDot = "",
                id = "2",
                message = "",
                praiseRating = "5",
                relatedPerson = emptyList()
            ),
        )

        val result = homeViewModel.calculateBadgeGroups(mockBadgeItems)

        assertEquals(expectedGroups.size, result.size)

        result.forEachIndexed { index, badgeGroupModel ->
            assertEquals(expectedGroups[index].lookupId, badgeGroupModel.lookupId)
            assertEquals(expectedGroups[index].lookupValue, badgeGroupModel.lookupValue)
            assertEquals(expectedGroups[index].itemCount, badgeGroupModel.itemCount)
            assertEquals(expectedGroups[index].averagePraiseRating, badgeGroupModel.averagePraiseRating)
        }
    }

    @AfterEach
    fun tearDown() {
        Dispatchers.resetMain()
    }
}