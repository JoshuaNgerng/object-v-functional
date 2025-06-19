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
import java.io.FileNotFoundException;
import java.util.Scanner;

class ParsingError extends Exception {
	public  ParsingError(String msg) {
		super(msg);
	}
	public ParsingError(String msg, Exception e) {
		super(String.format("%s: %s", msg, e.toString()));
	}
	public  ParsingError(String msg, int lineno) {
		super(String.format("%s, lineno:%d", msg, lineno));
	}
	public ParsingError(String msg, Exception e, int lineno) {
		super(String.format("%s: %s lineno: %d", msg, e.toString(), lineno));
	}
}

public class Main
{
	static private final WeatherTower weathertower;
	static private int cycle;

	static {
		weathertower = new WeatherTower();
		cycle = 0;
	}

	private static void simulationInit(String fname) throws ParsingError {
		int i = 0;
		try (Scanner reader = new Scanner(new File(fname))) {
			while (reader.hasNextLine()) {
				if (!reader.hasNextLine()) {
					throw new ParsingError("Empty Input File");
				}
				i ++;
				cycle = Integer.parseInt(reader.nextLine());
				while (reader.hasNextLine()) {
					i ++;
					String[] data = reader.nextLine().split(" ");
					if (data.length != 5) {
						throw new ParsingError("Aircraft needs 5 fields\nTYPE ID LONGTITUDE LATITUDE HEIGHT", i);
						
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
					throw new ParsingError("No Aircraft data in input file");
				}
			}
		} catch (FileNotFoundException e) {
			throw new ParsingError(String.join("File ", fname, " cannot be opened"), e);
		} catch (NumberFormatException e) {
			if (i == 1) {
				throw new ParsingError("First line must be number of cycle of the simulation", e, i); 
			}
			throw new ParsingError("Aircraft needs 5 fields\nTYPE ID LONGTITUDE LATITUDE HEIGHT", e, i); 
		}
	}

	public static void main(String[] args) {
		if (args.length != 1) {
			System.out.println("Pass in an input filename as parameter");
			System.exit(1);
		}
		try {
			simulationInit(args[0]);
		} catch (ParsingError e) {
			System.out.println(e.getMessage());
			System.exit(1);
		}
		for (int i = 0; i < cycle; i ++) {
			weathertower.changeWeather();
		}
	}
}
