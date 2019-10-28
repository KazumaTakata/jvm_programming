#! /bin/bash

oolong -g  main.j
oolong -g renderer.j
oolong -g vec3.j
oolong -g ray.j
oolong -g camera.j
oolong -g  hitable.j
oolong -g  hit_record.j
oolong -g hitable_list.j
oolong -g Sphere.j
oolong -g lambertian.j
oolong -g material.j

java -cp ./class/  Main


