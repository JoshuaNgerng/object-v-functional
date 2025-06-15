/* ************************************************************************** */
/*                                                                            */
/*                                                        :::      ::::::::   */
/*   AircraftFactory.java                               :+:      :+:    :+:   */
/*                                                    +:+ +:+         +:+     */
/*   By: jngerng <jngerng@student.42kl.edu.my>      +#+  +:+       +#+        */
/*                                                +#+#+#+#+#+   +#+           */
/*   Created: 2025/04/28 17:01:38 by jngerng           #+#    #+#             */
/*   Updated: 2025/04/29 02:07:52 by jngerng          ###   ########.fr       */
/*                                                                            */
/* ************************************************************************** */

package main;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class AircraftFactory {
	private static interface CloneAircraft {
		Flyable clone(String name, Coordinates coordinates);    
	}
	private static final Map<String, CloneAircraft> mapping;

	static {
		HashMap<String, CloneAircraft> buffer = new HashMap<>();
		buffer.put("Baloon", AircraftFactory::cloneBaloon);
		buffer.put("JetPlane", AircraftFactory::cloneJetPlane);
		buffer.put("Helicopter", AircraftFactory::cloneHelicopter);
		mapping = Collections.unmodifiableMap(buffer);
	}

	public static Flyable newAirCraft(
		String type, String name, int longitude, int latitude, int height
	) {
		CloneAircraft clone = mapping.get(type);
		if (clone == null) {
			return null;
		}
		return clone.clone(name, new Coordinates(longitude, latitude, height));
	}
	private static Baloon cloneBaloon(String name, Coordinates coor) {
		return new Baloon(name, coor);
	}
	private static JetPlane cloneJetPlane(String name, Coordinates coor) {
		return new JetPlane(name, coor);
	}
	private static Helicopter cloneHelicopter(String name, Coordinates coor) {
		return new Helicopter(name, coor);
	}
}

//https://www.gregorygaines.com/blog/how-to-use-function-pointers-in-java/
