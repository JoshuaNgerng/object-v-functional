/* ************************************************************************** */
/*                                                                            */
/*                                                        :::      ::::::::   */
/*   WeatherProvider.java                               :+:      :+:    :+:   */
/*                                                    +:+ +:+         +:+     */
/*   By: jngerng <jngerng@student.42kl.edu.my>      +#+  +:+       +#+        */
/*                                                +#+#+#+#+#+   +#+           */
/*   Created: 2025/04/27 21:30:46 by jngerng           #+#    #+#             */
/*   Updated: 2025/04/27 22:15:19 by jngerng          ###   ########.fr       */
/*                                                                            */
/* ************************************************************************** */

package main;

// import main.Coordinates;

public class WeatherProvider {
    private static String[] weather = {"RAIN", "FOG", "SUN", "SNOW"};
    private static WeatherProvider weatherProvider = new WeatherProvider();

    private WeatherProvider() { }
    public static WeatherProvider getProvider() { return weatherProvider; }
    public static String getCurrentWeather( Coordinates coordinates ) {
        return weather[
            (
                coordinates.getLongitude() +
                coordinates.getLatitude() +
                coordinates.getHeight()
            ) % 4
        ];
    }
}

// singleton design