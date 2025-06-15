/* ************************************************************************** */
/*                                                                            */
/*                                                        :::      ::::::::   */
/*   Info.java                                          :+:      :+:    :+:   */
/*                                                    +:+ +:+         +:+     */
/*   By: jngerng <jngerng@student.42kl.edu.my>      +#+  +:+       +#+        */
/*                                                +#+#+#+#+#+   +#+           */
/*   Created: 2025/04/28 13:29:20 by jngerng           #+#    #+#             */
/*   Updated: 2025/04/28 14:08:28 by jngerng          ###   ########.fr       */
/*                                                                            */
/* ************************************************************************** */

package main;

public class Info {
    public String message;
    public Coordinates coordinates;
    public Info(String msg, Coordinates coor) {
        message = msg; coordinates = coor;
    }
}
