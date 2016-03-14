package com.cityescape.service.impl;

import com.cityescape.domain.TripTag;
import com.cityescape.exception.DuplicateDataException;
import com.cityescape.exception.EntityToSaveIsNullException;
import com.cityescape.repository.TripTagRepository;
import com.cityescape.service.TripTagService;
import com.cityescape.utils.TestDataHelper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.dao.DataRetrievalFailureException;

import java.util.List;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Mockito.*;

/**
 * Created by Slava on 28/02/2016.
 */
@RunWith(MockitoJUnitRunner.class)
public class TripTagServiceImplTest {

    @Mock
    private TripTagRepository tripTagRepositoryMock;

    @InjectMocks
    private TripTagService tripTagService = new TripTagServiceImpl();

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldFindTripTagByName() throws Exception {

        TripTag tripTag = TestDataHelper.getTripTag();
        when(tripTagRepositoryMock.findByTag("testTag")).thenReturn(tripTag);

        TripTag actual = tripTagService.findByTag("testTag");

        assertThat(actual).isNotNull();
        assertThat(actual.getTag()).isEqualTo("testTag");
        assertThat(actual.getDescription()).isEqualTo("Test Tag");

        verify(tripTagRepositoryMock, times(1)).findByTag("testTag");
        verifyNoMoreInteractions(tripTagRepositoryMock);
    }

    @Test(expected = DataRetrievalFailureException.class)
    public void shouldThrowDataRetrievalFailureExceptionIfTagNotFound() throws Exception {

        when(tripTagRepositoryMock.findByTag("testTag")).thenReturn(null);
        tripTagService.findByTag("testTag");
    }

    @Test
    public void shouldReturnCollectionOfTripTags() throws Exception {

        List<TripTag> tripTags = TestDataHelper.getTripTags();
        when(tripTagRepositoryMock.findAll()).thenReturn(tripTags);

        List<TripTag> actual = tripTagService.findAll();

        assertThat(actual).isNotNull();
        assertThat(actual.size()).isEqualTo(2);
        verify(tripTagRepositoryMock, times(1)).findAll();
        verifyNoMoreInteractions(tripTagRepositoryMock);
    }

    @Test
    public void shouldCreateNewTag() throws Exception {
        TripTag tripTag = TestDataHelper.getTripTag();
        when(tripTagRepositoryMock.findByTag("testTag")).thenReturn(null);
        when(tripTagRepositoryMock.save(any(TripTag.class))).thenReturn(tripTag);

        TripTag actual = tripTagService.create(tripTag);

        assertThat(actual).isNotNull();
        assertThat(actual.getTag()).isEqualTo("testTag");
        assertThat(actual.getDescription()).isEqualTo("Test Tag");

        verify(tripTagRepositoryMock, times(1)).findByTag("testTag");
        verify(tripTagRepositoryMock, times(1)).save(any(TripTag.class));
        verifyNoMoreInteractions(tripTagRepositoryMock);
    }

    @Test(expected = EntityToSaveIsNullException.class)
    public void shouldThrowEntityToSaveIsNullExceptionWhenTagToSaveIsNull() throws Exception {
        tripTagService.create(null);
    }

    @Test(expected = DuplicateDataException.class)
    public void shouldThrowDuplicateDataExceptionWhenTagToSaveAlreadyExist() throws Exception {
        TripTag tripTag = TestDataHelper.getTripTag();
        when(tripTagRepositoryMock.findByTag("testTag")).thenReturn(tripTag);
        tripTagService.create(tripTag);
    }

    @Test
    public void shouldDeleteTripTag() throws Exception {
        TripTag tripTag = TestDataHelper.getTripTag();
        tripTagService.delete(tripTag);

        verify(tripTagRepositoryMock, times(1)).delete(any(TripTag.class));
        verifyNoMoreInteractions(tripTagRepositoryMock);
    }
}