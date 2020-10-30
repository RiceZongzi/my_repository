var Java_Int_Array = Java.type("int[]");

var arrJava = new Java_Int_Array(5);
var arrJS = new Array(5);
arrJava[0] = 5;
arrJS[0] = 5;
arrJava[1] = 4;
arrJS[1] = 4;
arrJava[2] = 3;
arrJS[2] = 3;
arrJava[3] = 2;
arrJS[3] = 2;
arrJava[4] = 1;
arrJS[4] = 1;

try {
    arrJava[5] = 23;
    print('Java，arrJava[5]：' + arrJava[5]);
} catch (e) {
    print(e.message);  // Array index out of range: 5
}

arrJava[0] = "17";
arrJS[0] = "17";
print('Java，arrJava[0]：' + arrJava[0]);  // 17
print('JS，arrJS[0]：' + arrJS[0]);  // 17

arrJava[0] = "wrong type";
arrJS[0] = "wrong type";
print('Java，arrJava[0]：' + arrJava[0]);  // 0
print('JS，arrJS[0]：' + arrJS[0]);  // wrong type

arrJava[0] = "17.3";
arrJS[0] = "17.3";
print('Java，arrJava[0]：' + arrJava[0]);  // 17
print('JS，arrJS[0]：' + arrJS[0]);  // 17.3
