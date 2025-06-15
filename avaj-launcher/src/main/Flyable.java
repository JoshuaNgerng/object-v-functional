/* ************************************************************************** */
/*                                                                            */
/*                                                        :::      ::::::::   */
/*   Flyable.java                                       :+:      :+:    :+:   */
/*                                                    +:+ +:+         +:+     */
/*   By: jngerng <jngerng@student.42kl.edu.my>      +#+  +:+       +#+        */
/*                                                +#+#+#+#+#+   +#+           */
/*   Created: 2025/04/27 18:37:10 by jngerng           #+#    #+#             */
/*   Updated: 2025/04/27 18:54:26 by jngerng          ###   ########.fr       */
/*                                                                            */
/* ************************************************************************** */

package main;

public interface Flyable {
	public void updateConditions();
	public void registerTower(WeatherTower weathertower);
}