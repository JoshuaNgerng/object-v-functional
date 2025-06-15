/* ************************************************************************** */
/*                                                                            */
/*                                                        :::      ::::::::   */
/*   Main.java                                          :+:      :+:    :+:   */
/*                                                    +:+ +:+         +:+     */
/*   By: jngerng <jngerng@student.42kl.edu.my>      +#+  +:+       +#+        */
/*                                                +#+#+#+#+#+   +#+           */
/*   Created: 2025/04/24 02:09:01 by jngerng           #+#    #+#             */
/*   Updated: 2025/04/29 14:02:29 by jngerng          ###   ########.fr       */
/*                                                                            */
/* ************************************************************************** */

package main;

import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Arrays;

// import main.WeatherTower;


public class Main
{
	static private WeatherTower weathertower;
	static private int cycle;

	static {
		weathertower = new WeatherTower();
		cycle = 0;
	}

	private static boolean simulationInit(String fname) {
		int i = 0;
		try (Scanner reader = new Scanner(new File(fname))) {
			while (reader.hasNextLine()) {
				if (!reader.hasNextLine()) {
					System.out.println("Empty Input File");
					return (false);
				}
				i ++;
				cycle = Integer.parseInt(reader.nextLine());
				while (reader.hasNextLine()) {
					i ++;
					String[] data = reader.nextLine().split(" ");
					// System.out.println(Arrays.deepToString(data));
					if (data.length != 5) {
						System.out.println(
							"Aircraft needs 5 fields\nTYPE ID LONGTITUDE LATITUDE HEIGHT"
						);
					}
					Flyable buffer = AircraftFactory.newAirCraft(
						data[0], data[1],
						Integer.parseInt(data[2]),
						Integer.parseInt(data[3]),
						Integer.parseInt(data[4])
					);
					buffer.registerTower(weathertower);
				}
				if (weathertower.numObservers() == 0) {
					System.out.println("No Aircraft data in input file");
					return false;
				}
			}
			return true;
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (NumberFormatException e) {
			if (i == 1) {
				System.out.println(
					"First line must be number of cycle of the simulation"
				);
			} else {
				System.out.println(
					"Aircraft needs 5 fields\nTYPE ID LONGTITUDE LATITUDE HEIGHT"
				);
			}
			System.out.printf("%d: %s", i, e.getMessage());	
		}
		return false;
	}

	public static void main(String[] args) {
		if (args.length != 1) {
			System.out.println("Pass in an input filename as parameter");
			System.exit(1);
		}
		if (!simulationInit(args[0])) {
			System.exit(1);
		}
		for (int i = 0; i < cycle; i ++) {
			weathertower.changeWeather();
		}
	}
}
