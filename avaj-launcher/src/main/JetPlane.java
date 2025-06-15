/* ************************************************************************** */
/*                                                                            */
/*                                                        :::      ::::::::   */
/*   JetPlane.java                                        :+:      :+:    :+:   */
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


public class JetPlane extends Aircraft implements Flyable {
	private static final Map<String, Info> movement;
	private WeatherTower weatherTower;
	static {
		HashMap<String, Info> buffer = new HashMap<>();
		buffer.put("SUN", new Info(
			"This is my element! Clear skies, full throttle, and nothing to slow me down. " + 
			"Let's soar above the clouds and feel the power of these engines.",
			new Coordinates(0, 10, 2))
		);
		buffer.put("RAIN", new Info(
			"Rain's hitting my wings, but no big deal—I’m built to handle it. " + 
			"I might lose a bit of speed, but I’ll power through. " + 
			"Nothing like cutting through the clouds in a storm!",
			new Coordinates(0, 5, 0))
		);
		buffer.put("FOG", new Info(
			"Hmm, fog's starting to mess with my speed. " + 
			"I can push through it, but visibility is a concern. " + 
			"Time to rely on my radar and instruments. Safety first",
			new Coordinates(0, 1, 0))
		);
		buffer.put("SNOW", new Info(
			"Snow, really? This is a bit tricky. " +
			"It’ll weigh down my wings and make takeoff a little more challenging. " +
			"But once I’m high enough, I can break through the snow clouds and keep flying smooth.",
			new Coordinates(0, 0, -7))
		);
		movement = Collections.unmodifiableMap(buffer);
	}
	public JetPlane(String name, Coordinates coordinates) {
		super(name, coordinates);
		this.weatherTower = null;
	}
	@Override
	public void registerTower(WeatherTower weatherTower) {
		this.weatherTower = weatherTower;
		this.weatherTower.register(this);
		System.out.printf("JetPlane#%s(%d) reporting for duty\n", name, id);
	}
	@Override
	public void updateConditions() {
		if (this.weatherTower == null) {
			System.out.printf("JetPlane#%s(%d) have not resigtered to a Weather Tower\n", name, id);
			return ;
		}
		String currentWeather = WeatherTower.getWeather(coordinates);
		Info info = movement.get(currentWeather);
		// System.out.printf("Before JetPlane#%s(%d) coor [%d, %d, %d]\n", name, id, coordinates.getLongitude(), coordinates.getLatitude(), coordinates.getHeight());
		coordinates.add(info.coordinates);
		System.out.printf("JetPlane#%s(%d): %s\n", name, id, info.message);
		if (coordinates.getHeight() == 0) {
			weatherTower.unregister(this);
			System.out.printf("JetPlane#%s(%d) successfully touch ground, signing off\n", name, id);
		}
		// System.out.printf("After JetPlane#%s(%d) coor [%d, %d, %d]\n", name, id, coordinates.getLongitude(), coordinates.getLatitude(), coordinates.getHeight());
	}
}
