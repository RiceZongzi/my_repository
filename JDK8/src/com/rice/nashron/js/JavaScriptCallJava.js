var MyJavaClass = Java.type('com.rice.nashron.NashronDemo');

var result = MyJavaClass.welcome('Rice');
print(result);

MyJavaClass.javaClass(123);
// class java.lang.Integer

MyJavaClass.javaClass(49.99);
// class java.lang.Double

MyJavaClass.javaClass(true);
// class java.lang.Boolean

MyJavaClass.javaClass("hi there")
// class java.lang.String

MyJavaClass.javaClass(new Number(23));
// class jdk.nashorn.internal.objects.NativeNumber

MyJavaClass.javaClass(new Date());
// class jdk.nashorn.internal.objects.NativeDate

MyJavaClass.javaClass(new RegExp());
// class jdk.nashorn.internal.objects.NativeRegExp

MyJavaClass.javaClass({foo: 'bar'});
// class jdk.nashorn.internal.scripts.JO4

MyJavaClass.javaClass2({
    foo: 'bar',
    bar: 'foo'
});
