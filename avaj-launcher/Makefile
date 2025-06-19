# **************************************************************************** #
#                                                                              #
#                                                         :::      ::::::::    #
#    Makefile                                           :+:      :+:    :+:    #
#                                                     +:+ +:+         +:+      #
#    By: jngerng <jngerng@student.42kl.edu.my>      +#+  +:+       +#+         #
#                                                 +#+#+#+#+#+   +#+            #
#    Created: 2025/04/29 01:17:56 by jngerng           #+#    #+#              #
#    Updated: 2025/04/29 01:50:57 by jngerng          ###   ########.fr        #
#                                                                              #
# **************************************************************************** #

# Makefile for compiling and running Java programs with packages

# Compiler and Java interpreter
JAVAC = javac
JAVA = java

# Directories
SRC_DIR = src
BIN_DIR = bin

ENTRY = main.Main

# Find all .java files in src (including sub-directories)
SOURCES = $(shell find $(SRC_DIR) -name "*.java")

# Create corresponding .class files in the bin directory, preserving the package structure
CLASSES = $(SOURCES:$(SRC_DIR)/%.java=$(BIN_DIR)/%.class)

# Default target: compile and run
all: $(CLASSES)
	@echo $(JAVA) -cp $(BIN_DIR) $(ENTRY)

# Rule to compile .java files to .class files
$(BIN_DIR)/%.class: $(SRC_DIR)/%.java
	@mkdir -p $(dir $@)
	$(JAVAC) -sourcepath $(SRC_DIR) -d $(BIN_DIR) $<

# Clean up compiled .class files
clean:
	rm -rf $(BIN_DIR)

# A simple rule to compile but not run the program
compile:
	$(JAVAC) -d $(BIN_DIR) $(SOURCES)
