/* ************************************************************************** */
/*                                                                            */
/*                                                        :::      ::::::::   */
/*   Helicopter.java                                        :+:      :+:    :+:   */
/*                                                    +:+ +:+         +:+     */
/*   By: jngerng <jngerng@student.42kl.edu.my>      +#+  +:+       +#+        */
/*                                                +#+#+#+#+#+   +#+           */
/*   Created: 2025/04/28 01:24:16 by jngerng           #+#    #+#             */
/*   Updated: 2025/04/28 15:55:24 by jngerng          ###   ########.fr       */
/*                                                                            */
/* ************************************************************************** */

package main;

import java.util.Map;
import java.util.HashMap;
import java.util.Collections;


public class Helicopter extends Aircraft implements Flyable {
	private static final Map<String, Info> movement;
	private WeatherTower weatherTower;
	static {
		HashMap<String, Info> buffer = new HashMap<>();
		buffer.put("SUN", new Info(
			"What a smooth ride! The sun is out, the sky is clear, " + 
			"and my rotors are slicing through the air with ease. " + 
			"Perfect conditions for a smooth hover",
			new Coordinates(10, 0, 2))
		);
		buffer.put("RAIN", new Info(
			"A little rain never hurt anyone, right? I can handle this. " + 
			"But if it gets any heavier, my rotors will start losing efficiency. " + 
			"I’ll keep my eye on the sky for any sudden changes.",
			new Coordinates(5, 0, 0))
		);
		buffer.put("FOG", new Info(
			"Foggy, huh? Visibility’s down, but I’m used to this. " +
			"As long as I can keep a close eye on my instruments, I can navigate through. " + 
			"I’ll just keep things slow and steady.",
			new Coordinates(1, 0, 0))
		);
		buffer.put("SNOW", new Info(
			"Snowflakes aren’t my favorite. The cold can mess with my engine performance, " + 
			"and if it piles up too much, my rotors could get iced over. " + 
			"Gotta keep a close check on everything.",
			new Coordinates(0, 0, -12))
		);
		movement = Collections.unmodifiableMap(buffer);
	}
	public Helicopter(String name, Coordinates coordinates) {
		super(name, coordinates);
		this.weatherTower = null;
	}
	@Override
	public void registerTower(WeatherTower weatherTower) {
		this.weatherTower = weatherTower;
		this.weatherTower.register(this);
		System.out.printf("Helicopter#%s(%d) established connection to WeatherTower\n", name, id);
	}
	@Override
	public void updateConditions() {
		if (this.weatherTower == null) {
			System.out.printf("Helicopter#%s(%d) have not resigter to a WeatherTower\n", name, id);
			return ;
		}
		// System.out.printf("Before Helicopter#%s(%d) coor [%d, %d, %d]\n", name, id, coordinates.getLongitude(), coordinates.getLatitude(), coordinates.getHeight());
		String currentWeather = WeatherTower.getWeather(coordinates);
		Info info = movement.get(currentWeather);
		coordinates.add(info.coordinates);
		System.out.printf("Helicopter#%s(%d): %s\n", name, id, info.message);
		if (coordinates.getHeight() == 0) {
			weatherTower.unregister(this);
			System.out.printf("Helicopter#%s(%d) have sucessfully landed going offline\n", name, id);
		}
		// System.out.printf("After Helicopter#%s(%d) coor [%d, %d, %d]\n", name, id, coordinates.getLongitude(), coordinates.getLatitude(), coordinates.getHeight());
	}
}
