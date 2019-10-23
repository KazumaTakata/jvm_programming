#! /bin/bash

oolong -g  main.j
oolong -g writeToFile.j
oolong -g vec3.j
oolong -g ray.j
oolong -g camera.j
java -cp ./class/  Main


