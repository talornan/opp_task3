# opp_task3

Name: tal ornan    i.d:209349356

### DirectedWeightedGraphAlgorithmslmpl:
Solution :
In order to solve this assignment I created the following object:
object|Explanation of the object
--- | --- |
NodeDataImpl|this object implements NodeData inteface.
EdgeDataImpl|this object implements EdgeData inteface.
GeoLocationImpl|this object implements GeoLocation inteface.
EdgeKey|this object presents key to the edge that consists of the edge's source and destination
Node| cosiste only the original field of NodeData to use it in save&load mathod.
Edge| cosiste only the original field of EdgeData to use it in save&load mathod.
Graph|cosiste only the original field of Graph to use it in save&load mathod.
Ex2|simple main that running the algo


#### In addition I implement DirectedWeightedGraph in the class DirectedWeightedGraphlmpl:
###### constructor 
field|Explanation of the field
--- | --- |
vertices|containing the number of vertices in the graph.
neighborsNode|containing the vertices that connected with edge. 
oppositeNeighborsNode|containing the vertices that connected with edge but now it's oppsite - the dest cell contian is source vertices .
edges|containing the edges of the graph, their key is their src and dist as I define in EdgeKey class.
numberOfEdge|counter of the edges in the graph.
modeCount| counter of the Modifications in the graph.
numberOfVertexes|counter of the Vertexes in the graph.

In addition created a deep copy constructor. 

method|Explanation of the method
--- | --- |
getNeighborsNode|return the NeighborsNode.
getOppositeNeighborsNode|return the OppositeNeighborsNode
getVertices|return the Vertices
getNode|return the Node by geting his key.
getEdge|return the Edge by geting his src and dest vertices.
addNode|add node to the graph.
connect| add edge between the src and dest we get.
removeNode| remove vertex from the graph.
nodeSize|return the hash map size of the vertices.
edgeSize|return the number of edges in the graph.
getMC|Returns the Mode Count - for testing changes in the graph.

### this object and class help to implemt DirectedWeightedGraphAlgorithms in DirectedWeightedGraphAlgorithmslmpl:
first I create 3 function that are not in the interface:

dijkstra algo - base on this https://en.wikipedia.org/wiki/Dijkstra%27s_algorithm.
To create this mathod I used in Priority Queue and add to the node filed called setVisit and getvisit to check if I was visit cur vertex and filed called setFrom and getFrom
to check from what vertex I came from in the last move.
and if the Priority Queue is not empty I calculate the go over cur NeighborsNode and check if neighbor Weight bigger than edgeWeight + cur Weight.


BFS -  base on this https://en.wikipedia.org/wiki/Breadth-first_search.
itertive method to implemnts BFS.
In the first node I change visit statue to True and Add this node to new QUEUE.
and than if the Queue is not empty I remove the the last elemnt and go over is NeighborsNode if I not visit some of their nighber I change is visit statur to true and add to QUEUE.

isStronglyConnected -Used the  bfs algo to validate  there is a  path to each node.

this function help me to implements the orignal method of DirectedWeightedGraphAlgorithms:

isConnected - I go over Vertices map and go over NeighborsNode map.
I define node as the value of nd and node as not visited check if the graph is connected by isStronglyConnected function.

shortestPathDist - first checked if src and dest is equals. if not checked if src and dest is equals 
if both not happend created new  node to contain the dest. than use dijkstra function to save the path from src and 
check if there is a path. if it is the function return the path if, not return -1.

shortestPath - first define a new list to contain the path.
I check if src and dest equals if yes, add src value to path list and return the list.
if not I check if src and dest have a value, if not return empty list.
Than I activate dijkstra function to get the shortest Path and add the path I get to path list in the end I add src to the path and return the path.

center - first checked if the graph is connected , if not return null. than I define a new map and go over Vertices value and add for every vertex is distance from all the other vertex's.
next step is put in distance(new array I define) the dis of every vertex to the other's.
And to end I check what node have the shortest path from the other by compare the value's is in the map

tsp - first   define a new list to contain the path I go over Vertices of the graph and update visit status to false.
if there is no value's in cities return empty list.
next I define a new node as the value of cities list in the first place and update prev visit status to true.
in addician I add to  tcp prev cities in the first place/
To the end I go over cities size and go in the shortest Path from prev to curr vertex, I update all the vertex I pass as visited and not go over them again.


### Test:
In order to test the solution,I created multipe unit test. each unit test,generate a graph, and test the relevant function.
I aslo used the given file - G1,G2,G3 to test my code, and load/lave methogs.

### Performance analysis on graph with 1000,10000,100000 vertex'es:
 graph|timeOfGetCenter
--- | --- |
1000|815988210 nanoseconds 
10000|960 153 243 170 nanoseconds
100000|timeOut 
1000000|timeOut

### the Jar :
 I chose to use in Jgraph library That I downloaded from here -- https://jgrapht.org/ .
 this library is used to drawing graph.
 to run the algo need to write in the terminle: java -jar Ex2.jar G1.json as requested.
 
 
 
 
 
 








