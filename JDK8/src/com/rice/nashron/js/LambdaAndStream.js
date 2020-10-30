var Java_ArrayList = Java.type('java.util.ArrayList');

var listJava = new Java_ArrayList();
listJava.add("ddd2");
listJava.add("aaa2");
listJava.add("bbb1");
listJava.add("aaa1");
listJava.add("bbb3");
listJava.add("ccc");
listJava.add("bbb2");
listJava.add("ddd1");

listJava
    .stream()
    .filter(function(el) {
        return el.startsWith("aaa");
    })
    .sorted()
    .forEach(function(el) {
        print(el);
    });
// aaa1, aaa2


