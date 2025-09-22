const PI = 3.141592;
const COMPAY_NAME = 'PCWK Corporation';

/*
j05.js:5 Uncaught TypeError: Assignment to constant variable.
    at j05.js:5:4
*/
//PI = 9999;
console.log('PI=',PI);
console.log('COMPAY_NAME=',COMPAY_NAME);

//배열 상수 값 변경
const fruits = ['사과','바나나'];

console.log('배열:',fruits);

//요소변경
fruits[0] = '복숭아';


//요소추가
fruits.push('포도');
console.log('배열:',fruits);