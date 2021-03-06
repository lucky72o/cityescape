package com.cityescape.utils;

import com.cityescape.domain.PoeTag;
import com.cityescape.domain.Trip;
import com.cityescape.domain.TripTag;
import com.cityescape.domain.TripTagWeight;
import com.cityescape.enums.TripStatus;
import com.cityescape.enums.TripTagStatus;

import java.math.BigDecimal;
import java.util.*;

/**
 * Created by Slava on 14/02/2016.
 */
public class TestDataHelper {

    public static TripTag getTripTag() {
        TripTag tripTag = new TripTag("testTag", "Test Tag");
        tripTag.setId(100L);
        tripTag.setTripTagStatus(TripTagStatus.ACTIVE);
        return tripTag;
    }

    public static TripTag getTripTagByName(String name) {
        TripTag tripTag = new TripTag(name, name + " Tag");
        tripTag.setTripTagStatus(TripTagStatus.ACTIVE);
        return tripTag;
    }

    public static List<TripTag> getTripTags() {
        List<TripTag> tripTags = new ArrayList<>();
        tripTags.add(getTripTagByName("Tag1"));
        tripTags.add(getTripTagByName("Tag2"));
        return tripTags;
    }

    public static PoeTag getPoeTag() {
        return new PoeTag("testTag");
    }

    public static Trip getTrip(String name) {
        Trip trip = new Trip();
        trip.setTripStatus(TripStatus.NEW);
        trip.setName(name);
        trip.setId(1L);
        trip.setDescription("description");
        trip.setTripTagWeights(getTripTagWeights());
        trip.setNumberOfVotesForTags(20L);

        return trip;
    }

    public static Trip getSimpleTrip(String name) {
        Trip trip = new Trip();
        trip.setTripStatus(TripStatus.NEW);
        trip.setName(name);
        trip.setDescription("description");
        trip.setNumberOfVotesForTags(20L);

        return trip;
    }

    private static Set<TripTagWeight> getTripTagWeights() {

        Set<TripTagWeight> tripTagWeights = new HashSet<>();
        TripTagWeight tripTagWeight1 = getTripTagWeight(11L);
        TripTagWeight tripTagWeight2 = getTripTagWeight(22L);
        tripTagWeights.add(tripTagWeight1);
        tripTagWeights.add(tripTagWeight2);

        return tripTagWeights;
    }

    private static TripTagWeight getTripTagWeight() {
        return getTripTagWeight(11L);
    }

    private static TripTagWeight getTripTagWeight(Long id) {
        TripTagWeight tripTagWeight = new TripTagWeight();
        tripTagWeight.setId(id);
        tripTagWeight.setTrip(new Trip());
        tripTagWeight.setTripTag(getTripTag());
        tripTagWeight.setWeight(BigDecimal.valueOf(0.5));
        tripTagWeight.setNumberOfVotes(10L);
        return tripTagWeight;
    }

    public static List<Trip> getTrips() {
        Trip trip1 = getTrip("Trip1");
        Trip trip2 = getTrip("Trip2");
        trip2.setId(2L);
        return Arrays.asList(trip1, trip2);
    }

    public static List<Trip> getActiveTrips() {
        Trip trip1 = getTrip("Trip1");
        trip1.setTripStatus(TripStatus.ACTIVE);
        Trip trip2 = getTrip("Trip2");
        trip2.setTripStatus(TripStatus.ACTIVE);
        trip2.setId(2L);
        return Arrays.asList(trip1, trip2);
    }


}
