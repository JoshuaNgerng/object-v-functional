/* ************************************************************************** */
/*                                                                            */
/*                                                        :::      ::::::::   */
/*   WeatherTower.java                                  :+:      :+:    :+:   */
/*                                                    +:+ +:+         +:+     */
/*   By: jngerng <jngerng@student.42kl.edu.my>      +#+  +:+       +#+        */
/*                                                +#+#+#+#+#+   +#+           */
/*   Created: 2025/04/24 02:43:33 by jngerng           #+#    #+#             */
/*   Updated: 2025/04/28 00:01:49 by jngerng          ###   ########.fr       */
/*                                                                            */
/* ************************************************************************** */

package main;

// import main.Tower;
// import main.Coordinates;

public class WeatherTower extends Tower {
	public WeatherTower() { super(); }
	public static String getWeather( Coordinates coordinates ) {
		return WeatherProvider.getCurrentWeather(coordinates);
	}
	void changeWeather() {
		this.conditionsChanged();
	}
}
