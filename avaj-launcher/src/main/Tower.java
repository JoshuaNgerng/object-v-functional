/* ************************************************************************** */
/*                                                                            */
/*                                                        :::      ::::::::   */
/*   Tower.java                                         :+:      :+:    :+:   */
/*                                                    +:+ +:+         +:+     */
/*   By: jngerng <jngerng@student.42kl.edu.my>      +#+  +:+       +#+        */
/*                                                +#+#+#+#+#+   +#+           */
/*   Created: 2025/04/27 17:59:44 by jngerng           #+#    #+#             */
/*   Updated: 2025/04/29 15:46:59 by jngerng          ###   ########.fr       */
/*                                                                            */
/* ************************************************************************** */

package main;

import java.util.ArrayList;
import java.util.List;

// import main.Flyable;

public class Tower {
	private List<Flyable> observers; 
	public Tower() { observers = new ArrayList<Flyable>(); }

	public void register(Flyable flyable) {
		observers.add(flyable);
	}

	public void unregister(Flyable flyable) {
		observers.remove(flyable);
	}

	// Notify all registered Flyable objects of a condition change
	protected void conditionsChanged() {
		for (int i = 0; i < observers.size(); i++) {
			observers.get(i).updateConditions();
		}
	}

	public int numObservers() {
		return observers.size();
	}
}
