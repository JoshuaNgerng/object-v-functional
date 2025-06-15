/* ************************************************************************** */
/*                                                                            */
/*                                                        :::      ::::::::   */
/*   Aircraft.java                                      :+:      :+:    :+:   */
/*                                                    +:+ +:+         +:+     */
/*   By: jngerng <jngerng@student.42kl.edu.my>      +#+  +:+       +#+        */
/*                                                +#+#+#+#+#+   +#+           */
/*   Created: 2025/04/27 23:21:34 by jngerng           #+#    #+#             */
/*   Updated: 2025/04/29 02:06:01 by jngerng          ###   ########.fr       */
/*                                                                            */
/* ************************************************************************** */

package main;

public class Aircraft {
	protected long			id;
	protected String		name;
	protected Coordinates	coordinates;
	static private long		idCounter = 0;

	protected Aircraft(String name, Coordinates coordinates) {
		this.name = name; this.coordinates = coordinates;
		this.id = this.nextId();
	}
	long	nextId() { return ++ idCounter; }
}
