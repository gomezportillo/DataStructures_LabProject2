ENERGY DRINKS PRODUCTION PLANT
=======                                                
Phase of organization of boxes for distribution

Problem description

Considering the plant of production of energy drinks of the previous project, now it will be necessary to
organize the boxes of bottles from the storage units, in which they were placed, into a warehouse, in a way
that eases its transportation to a series of destinations.  Therefore, the start point will be a specific set of
boxes already produced.
The information about these boxes and the destinations can be found in two text files:

a) Destinations file (Ciudades.txt), which contains a list of destinations (cities) with the distances
from the plant. The file format is:

                                                destination kilometers
b) Boxes file (Cajas.txt), representing the list of produced boxes. These boxes are stored in the
storage units in columns1. The file format is:

                                                 boxID storeUnitID destination 
The boxes file represents the sequence of boxes that a series of workers should organize over the surface
of a warehouse in a binary tree‐like accommodation. The procedure is as follows:

1. The first box is placed as the root of a binary tree.

2. The following boxes will be placed in a binary tree‐like structure (in its usual configuration). If the
distance to the destination of the next box in the file is smaller than that of the first box, it will be
placed in the left subtree, whereas if the distance to destination is greater than that of the first box,
the new box will be placed in the right subtree, and so on, using a binary search tree organization.

3. In this way, we will construct a tree as shown in the figure below. When several boxes have the
same distance to destination, they must be placed one on top of the other according to the order
determined by the storage unit ID (A, B, C…) –as noted below, this will be implemented as a list–.

4. This tree provides a physical organization that will allow the trucks loading the boxes to distribute
them easily according to the destinations and routes assigned.

                                                Goal and requirements

The goal of the project is to build a Java program that simulates the behavior of the previously described
operation and using a binary tree, represents the placement of the boxes in the warehouse. To do this, the
program must:

 Process the cities (destinations) file, using a list to store them and to search distances.

 Process the boxes file and represent the described arrangement through a binary tree.

 Each pile of boxes, stored in a child of the tree, can have several boxes, being represented by a list.

 Finally, the program should print:

i. The binary tree in text format.

ii. Using the binary tree structure and contents:

 The information of the box whose destination is the nearest city.

 The information of the box whose destination is the farthest city.

If there are several boxes that fulfill this condition, only the box with the smallest value of
the storage Unit ID should be printed (note in the figure above how the boxes are stored in
the tree nodes)

 The program must be as general as possible; it should process files with a different number of
elements. The files provided are an example to test the program.
