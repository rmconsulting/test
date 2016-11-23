package com.tbs.service.client.services;

import java.util.Calendar;
import java.util.TimeZone;

import org.junit.Test;

import com.tbs.service.calendar.AstronomicalCalendar;
import com.tbs.service.calendar.GeoLocation;

public class RelojTestCase {
	@Test
	public void BuenosAires() {
	        GeoLocation geoLocation = new GeoLocation("Arg", -34.62868797377059, -58.36212158203125, TimeZone.getTimeZone("America/Argentina/Buenos_Aires"));
	        Calendar calendar = Calendar.getInstance();
	        calendar.set(2016, 6, 11);
	        
	        AstronomicalCalendar calculator2 = new AstronomicalCalendar(geoLocation);
	        calculator2.setCalendar(calendar);
	        
	        System.out.println("date " + calculator2.getCalendar());
	        System.out.println("sunrise " + calculator2.getSunrise());
	        System.out.println("sunset " + calculator2.getSunset());
	        
	        System.out.println("Astronomical Twilight start " + calculator2.getBeginAstronomicalTwilight());
	        System.out.println("Astronomical Twilight end " + calculator2.getEndAstronomicalTwilight());
	        
	        System.out.println("Nautical Twilight start " + calculator2.getBeginNauticalTwilight());
	        System.out.println("Nautical Twilight end " + calculator2.getEndNauticalTwilight());
	        
	        System.out.println("Civil Twilight start " + calculator2.getBeginCivilTwilight());
	        System.out.println("Civil Twilight end " + calculator2.getEndCivilTwilight());
	        
	        System.out.println("Sun Transit " + calculator2.getSunTransit());
	        
	       
	}

}
