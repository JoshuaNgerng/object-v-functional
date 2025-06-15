/* ************************************************************************** */
/*                                                                            */
/*                                                        :::      ::::::::   */
/*   Coordinates.java                                   :+:      :+:    :+:   */
/*                                                    +:+ +:+         +:+     */
/*   By: jngerng <jngerng@student.42kl.edu.my>      +#+  +:+       +#+        */
/*                                                +#+#+#+#+#+   +#+           */
/*   Created: 2025/04/27 19:24:18 by jngerng           #+#    #+#             */
/*   Updated: 2025/04/28 02:26:22 by jngerng          ###   ########.fr       */
/*                                                                            */
/* ************************************************************************** */

package main;

public class Coordinates {
	private static final int maxLimit;
	private static final int minLimit;
	private int longitude;
	private int latitude;
	private int height;

	static {
		maxLimit = 100; minLimit = 0;
	}
	Coordinates(int longitude, int latitude, int height) {
		this.longitude = longitude; this.latitude = latitude; this.height = height;
	}
	public int  getLongitude() { return longitude; }
	public int  getLatitude() { return latitude; }
	public int  getHeight() { return height; }
	public void copy(Coordinates coordinates) {
		longitude = coordinates.longitude;
		latitude = coordinates.latitude;
		height = coordinates.height;
	}
	public void add(Coordinates coordinates) {
		longitude += coordinates.longitude;
		latitude += coordinates.latitude;
		height += coordinates.height;
		height = checkMinMax(height);
	}
	private int checkMinMax(int val) {
		if (val > Coordinates.maxLimit) {
			return Coordinates.maxLimit;
		}
		if (val < Coordinates.minLimit) {
			return Coordinates.minLimit;
		}
		return val;
	}
}
