/* ************************************************************************** */
/*                                                                            */
/*                                                        :::      ::::::::   */
/*   Baloon.java                                        :+:      :+:    :+:   */
/*                                                    +:+ +:+         +:+     */
/*   By: jngerng <jngerng@student.42kl.edu.my>      +#+  +:+       +#+        */
/*                                                +#+#+#+#+#+   +#+           */
/*   Created: 2025/04/28 01:24:16 by jngerng           #+#    #+#             */
/*   Updated: 2025/04/29 17:07:12 by jngerng          ###   ########.fr       */
/*                                                                            */
/* ************************************************************************** */

package main;

import java.util.Map;
import java.util.HashMap;
import java.util.Collections;


public class Baloon extends Aircraft implements Flyable {
	private static final Map<String, Info> movement;
	private WeatherTower weatherTower;
	static {
		HashMap<String, Info> buffer = new HashMap<>();
		buffer.put("SUN", new Info(
			"Ah, what a perfect day to float! The sun is warming my canvas," +
			" and the breeze is gentle. No rush—just me and the sky", new Coordinates(2, 0, 4))
		);
		buffer.put("RAIN", new Info(
			"Rain, rain, go away... I don’t mind a little drizzle, but too much rain can make it hard to stay afloat. " +
			"The weight of the water on my fabric is never a good sign", new Coordinates(0, 0, -5))
		);
		buffer.put("FOG", new Info(
			"Hmm, it's getting a little harder to see... " +
			"The fog is rolling in, and it's making my usually smooth glide a bit trickier. " +
			"Let's hope I don't bump into anything!", new Coordinates(0, 0, -3)))
		;
		buffer.put("SNOW", new Info(
			"Snowflakes gently falling from the sky... How beautiful! But too much snow can be a problem for me. " +
			"The cold will stiffen my balloon, and I may struggle to stay in the air.",
			new Coordinates(0, 0, -15))
		);
		movement = Collections.unmodifiableMap(buffer);
	}
	public Baloon(String name, Coordinates coordinates) {
		super(name, coordinates);
		this.weatherTower = null;
	}
	@Override
	public void registerTower(WeatherTower weatherTower) {
		this.weatherTower = weatherTower;
		this.weatherTower.register(this);
		System.out.printf("Baloon#%s(%d): resigtering to Weather Tower\n", name, id);
	}
	@Override
	public void updateConditions() {
		if (this.weatherTower == null) {
			System.out.printf("Baloon#%s(%d): have not resigter to Weather Tower\n", name, id);
			return ;
		}
		String currentWeather = WeatherTower.getWeather(coordinates);
		Info info = movement.get(currentWeather);
		// System.out.printf("Before Baloon#%s(%d) coor [%d, %d, %d]\n", name, id, coordinates.getLongitude(), coordinates.getLatitude(), coordinates.getHeight());
		coordinates.add(info.coordinates);
		System.out.printf("Baloon#%s(%d): %s\n", name, id, info.message);
		if (coordinates.getHeight() == 0) {
			weatherTower.unregister(this);
			System.out.printf("Baloon#%s(%d): have landed safetly\n", name, id);
		}
		// System.out.printf("After Baloon#%s(%d) coor [%d, %d, %d]\n", name, id, coordinates.getLongitude(), coordinates.getLatitude(), coordinates.getHeight());
	}
}
