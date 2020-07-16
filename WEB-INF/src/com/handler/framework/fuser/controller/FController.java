package com.handler.framework.fuser.controller;

import java.util.*;
import java.text.*;
import java.lang.reflect.*;

import com.google.gson.*;
import com.google.gson.reflect.*;

import com.handler.framework.annotation.*;
import com.handler.framework.fuser.model.*;

@Path("/controller")
public class FController {

	@Path("/moviesForMaxProfit")
	public String moviesForMaxProfit(@RequestData("") Movies mvs) throws Exception {
		Gson gson = new Gson();		
		int i = 0;
		int count = 1;
		List<MovieWrapper> moviesWrapper = Arrays.asList(mvs.getMovieArr());
		List <MovieWrapper> moviesPicked = new ArrayList<>();
		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
		
		Collections.sort(moviesWrapper, new Comparator<MovieWrapper>() {
			public int compare(MovieWrapper m1, MovieWrapper m2) {
				Date d1 = null;
				Date d2 = null;
				try{
					d1 = simpleDateFormat.parse(m1.getEndDate());
					d2 = simpleDateFormat.parse(m2.getEndDate());
				} catch (Exception e) {
					e.printStackTrace();
				}
				return d1.compareTo(d2);
			}
		});
		
		moviesPicked.add(moviesWrapper.get(i));
		for (int j = 1; j < moviesWrapper.size(); j++) {
			if (simpleDateFormat.parse(moviesWrapper.get(j).getStartDate()).after(simpleDateFormat.parse(moviesWrapper.get(i).getEndDate()))) {
				count++;
				i = j;
				moviesPicked.add(moviesWrapper.get(i));
			}
		}
		
		return gson.toJson(new ResponseWrapper(moviesPicked,("Rs. "+ count +" cr.")));
	}

}