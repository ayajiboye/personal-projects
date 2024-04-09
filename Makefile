#AyoOluwatomiwa Ajiboye, G01397258
#CS 262, Lab section 208
#Lab 7

CC = gcc
CFLAGS = -g -Wall -std=c99 -pedantic-errors
TARGET = lab7_aajiboye_208

all: $(TARGET)
$(TARGET): $(TARGET).c
	$(CC) $(TARGET).c -o $(TARGET) $(CFLAGS)
clean:
	rm $(TARGET)
