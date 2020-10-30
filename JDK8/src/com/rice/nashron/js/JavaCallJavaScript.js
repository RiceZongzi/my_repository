// Jdk8的Nashorn是基于ES5.1的，不支持let和const
var welcome = function(name) {
    print('Hi there from Javascript, ' + name);
    return "greetings from javascript";
};

var jsClass = function (object) {
    print("JS Class Definition: " + Object.prototype.toString.call(object));
};
