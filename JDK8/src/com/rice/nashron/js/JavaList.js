var Java_ArrayList = Java.type('java.util.ArrayList');
var Java_HashMap = new java.util.HashMap();

var listJava = new Java_ArrayList();
listJava.add('a');
listJava.add('b');
listJava.add('c');

for each (var el in listJava) print(el);  // a, b, c

Java_HashMap.put('good', 'morning');
Java_HashMap.put('hello', 'world');

for each (var e in Java_HashMap.keySet()) print(e);  // good, hello
for each (var e in Java_HashMap.values()) print(e);  // morning, world
